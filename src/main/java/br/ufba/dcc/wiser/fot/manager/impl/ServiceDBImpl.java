package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

public class ServiceDBImpl implements ServiceDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Service bundler) throws Exception {
		// TODO Auto-generated method stub

	}

	public void update(Service bundler) {
		// TODO Auto-generated method stub

	}

	public Service find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void desactive(String name) throws Exception {
		// TODO Auto-generated method stub

	}

	public void remove(String name) throws Exception {
		// TODO Auto-generated method stub

	}

	public void active(Service service) {
		// TODO Auto-generated method stub

	}

	public List<Service> getListService() {
		// TODO Auto-generated method stub
		return null;
	}

}
