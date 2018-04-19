package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

/**
 * Class responsible for performing access to the database for gateway
 * information.
 *
 * @author Nilson Rodrigues Sousa
 */
public class GatewayDBImpl implements GatewayDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Method that adds a gateway.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gateway
	 *            Gateway
	 */
	public void add(Gateway gateway) {
		try {
			entityManager.persist(gateway);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that updates all referenced gateway information.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gateway
	 *            Gateway
	 */
	public void update(Gateway gateway) {
		entityManager.merge(gateway);
	}

	/**
	 * Method responsible for fetching information from a gateway through its
	 * MAC address.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param mac
	 *            String
	 * @return Gateway - Localized gateway
	 */
	public Gateway find(String mac) {
		Gateway gateway = new Gateway();
		System.out.println(">>>>>>>>>>>>>>>Estou no GatewayDBImplemen.find");
		gateway = entityManager.find(Gateway.class, mac);
		if (gateway != null) {
			return gateway;
		}
		return null;
	}

	/**
	 * Method that directs the gateway to off within the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param ip
	 *            String
	 */
	public void desactive(String ip) throws Exception {
		List<Gateway> listGateway = this.getListGateway();
		Gateway findGateway = null;
		for (Gateway gateway : listGateway) {
			if (gateway.getIp().equals(ip)) {
				findGateway = gateway;
				break;
			}
		}

		if (findGateway != null) {
			findGateway.setStatus(false);
			this.update(findGateway);
		}

	}

	/**
	 * Method that directs the gateway as enabled within the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gateway
	 *            Gateway
	 */
	// Avaliar real utilidade deste método que está sendo implementado em update
	public void active(Gateway gateway) {
		gateway.setStatus(true);
		this.update(gateway);
	}

	/**
	 * Method responsible for returning all information from a gateway.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Gateway> - List of existing gateways
	 */
	public List<Gateway> getListGateway() {

		List<Gateway> listGatewayReturn = new ArrayList<Gateway>();

		String jpqlGateway = "select g from Gateway g";
		TypedQuery<Gateway> queryCreate = entityManager.createQuery(jpqlGateway, Gateway.class);

		for (Gateway gtw : queryCreate.getResultList()) {
			if (gtw.isStatus()) {
				Gateway gateway = new Gateway();
				
				String jpqlBI = "select bi from BundlerInstalled bi where bi.id.gateway = :gtw";
				TypedQuery<BundlerInstalled> queryCreateBI = entityManager.createQuery(jpqlBI, BundlerInstalled.class);
				queryCreateBI.setParameter("gtw", gtw);
				
				gtw.getListBundlerInstalled().addAll(queryCreateBI.getResultList());
				gateway = gtw;
				
				//por algum motivo ele só carrega quando uso algum comando
				gateway.getListBundlerInstalled().isEmpty();
				
				listGatewayReturn.add(gateway);
			}
		}

		return listGatewayReturn;
	}

	/**
	 * Method that returns the gateway information specified by the mac.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param mac
	 *            String
	 * @return Gateway - Search for gateway information
	 */
	public Gateway getListGateway(String mac) {
		String jpql = "select p from Gateway p inner join fetch p.bundlerInstalled";
		TypedQuery<Gateway> query = entityManager.createQuery(jpql, Gateway.class);
		query.setParameter("mac", mac);
		Gateway gtw = query.getSingleResult();
		try {
			return gtw;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
