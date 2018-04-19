package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;
import br.ufba.dcc.wiser.fot.manager.service.GatewayStatusDBService;

/**
 * Class responsible for performing access to the database referring to gateway
 * status information.
 *
 * @author Nilson Rodrigues Sousa
 */
public class GatewayStatusDBImpl implements GatewayStatusDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Method responsible for adding status information.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewayStatus
	 *            GatwayStatus
	 */
	public void add(GatewayStatus gatewayStatus) {
		try {
			entityManager.persist(gatewayStatus);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method responsible for fetching status information from all gateways.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gateway
	 *            Gateway
	 * @return List<GatewayStatus> - List of information found
	 */
	public List<GatewayStatus> findGatewayInformation(Gateway gateway) {
		return null;
	}

	/**
	 * Method responsible for fetching status information from a specific
	 * gateway.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Bundler> - List of information found
	 */
	public List<GatewayStatus> getListGateway() {
		List<GatewayStatus> listGatewayStatus = entityManager
				.createQuery("select p from GatewayStatus p", GatewayStatus.class).getResultList();

		if (!listGatewayStatus.isEmpty() && listGatewayStatus != null) {
			return listGatewayStatus;
		} else {
			return null;
		}
	}

}
