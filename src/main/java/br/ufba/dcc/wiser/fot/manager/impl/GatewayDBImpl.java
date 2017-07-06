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

	public void add(Gateway gateway) {	
		try {
			entityManager.persist(gateway);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// updates all gateway information
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

	// implies only the arrow in the gateway as inactive
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
			//talvez não seja necessário pois a situação dos serviços era ativo antes da interrupção do gateway
			this.update(findGateway);
		}

	}

	// implies only the arrow in the gateway as active
	//no momento a função desse método está sendo implementada pelo update. Talvez ele possa ser exluido.
	public void active(Gateway gateway) {
		gateway.setStatus(true);
		this.update(gateway);
	}

	public List<Gateway> getListGateway() {
		if (entityManager.createQuery("select p from Gateway p", Gateway.class).getResultList().isEmpty()) {
			System.out.println(">>>>>>>>>>>Lista vazia em getListGateway.");
		}

		return entityManager.createQuery("select p from Gateway p", Gateway.class).getResultList();
	}

	@Override
	public String toString() {
		return "GatewayDBImpl in full operation";
	}

}
