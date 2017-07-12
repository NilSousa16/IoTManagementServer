package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;
import br.ufba.dcc.wiser.fot.manager.service.GatewayStatusDBService;

public class GatewayStatusDBImpl implements GatewayStatusDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(GatewayStatus gatewayStatus) {
		try {
			entityManager.persist(gatewayStatus);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<GatewayStatus> findGatewayInformation(Gateway gateway) {
		return null;
	}

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
