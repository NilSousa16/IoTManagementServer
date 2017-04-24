package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class GatewayDBImpl implements GatewayDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Gateway gateway) throws Exception {		
		entityManager.persist(gateway);
		entityManager.flush();
	}

	// Updates all gateway information
	public void update(Gateway gateway) {
		entityManager.merge(gateway);
	}

	public Gateway find(String mac) {
		Gateway gateway = entityManager.find(Gateway.class, mac);
		if (gateway != null) {
			return gateway;
		}
		return null;
	}

	// Implies only the arrow in the gateway as inactive
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
			//implementar desativação dos serviços na tabela m:m com campo de situação do gateway = desativado/ativado
			this.update(findGateway);
		}

	}

	// Implies only the arrow in the gateway as active
	public void active(Gateway gateway) {
		gateway.setStatus(true);
		this.update(gateway);
	}

	public List<Gateway> getListGateway() {
		if (entityManager.createQuery("select p from Gateway p", Gateway.class).getResultList() == null) {
			System.out.println(">>>>>>>>>>>Lista vazia em getListGateway.");
		}

		return entityManager.createQuery("select p from Gateway p", Gateway.class).getResultList();
	}

}
