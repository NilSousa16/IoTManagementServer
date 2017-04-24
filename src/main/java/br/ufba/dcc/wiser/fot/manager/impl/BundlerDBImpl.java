package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

public class BundlerDBImpl implements BundlerDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Bundler bundler) throws Exception {
		entityManager.persist(bundler);
		entityManager.flush();
	}

	public void update(Bundler bundler) {
		entityManager.merge(bundler);
	}

	public Bundler find(String name) {
		Bundler bundler = entityManager.find(Bundler.class, name);
		if (bundler != null) {
			return bundler;
		}
		return null;
	}

	public void desactive(String name) throws Exception {
		// TODO Auto-generated method stub
	}

	public void remove(String name) throws Exception {

		Bundler bundler = entityManager.find(Bundler.class, name);

		if (bundler != null) {
			entityManager.remove(bundler);
		}

	}

	public void active(Bundler bundler) {
		// TODO Auto-generated method stub

	}

	public List<Bundler> getListBundler() {
		if (entityManager.createQuery("select p from Bundler p", Bundler.class).getResultList() == null) {
			System.out.println(">>>>>>>>>>>Lista vazia em getListGateway.");
		}

		return entityManager.createQuery("select p from Bundler p", Bundler.class).getResultList();
	}

}
