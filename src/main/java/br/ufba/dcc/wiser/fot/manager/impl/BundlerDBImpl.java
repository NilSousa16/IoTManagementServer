package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalledId;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

public class BundlerDBImpl implements BundlerDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Bundler bundler) {
		try {
			entityManager.persist(bundler);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Bundler find(String name, String version) {
		String jpql = "select p from Bundler p where p.name = :name and p.version = :version";
		TypedQuery<Bundler> query = entityManager.createQuery(jpql, Bundler.class);
		query.setParameter("name", name);
		query.setParameter("version", version);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			//e.printStackTrace();
			return null;
		}
	}

	public void remove(String name) {
		try {
			Bundler bundler = entityManager.find(Bundler.class, name);
			if (bundler != null) {
				entityManager.remove(bundler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Bundler> getListBundler() {
		return entityManager.createQuery("select p from Bundler p", Bundler.class).getResultList();
	}

	public void addBundlerInstalled(Bundler bundler, Gateway gateway) {
		BundlerInstalled bundlerInstalled = new BundlerInstalled();

		bundlerInstalled.setId(new BundlerInstalledId(gateway, bundler));
		bundlerInstalled.setStatus(true);
		entityManager.persist(bundlerInstalled);
	}

	public BundlerInstalled findBundlerInstalled(Bundler bundler, Gateway gateway) {
		BundlerInstalled bundlerInstalled = entityManager.find(BundlerInstalled.class,
				new BundlerInstalledId(gateway, bundler));

		return bundlerInstalled;
	}

	public void removeBundlerInstalled(BundlerInstalled bundlerInstalled) {
		entityManager.remove(bundlerInstalled);
	}

	public void updateBundlerInstalled(BundlerInstalled bundlerInstalled) {
		entityManager.merge(bundlerInstalled);
	}

	@Override
	public String toString() {
		return "BundlerDBImpl in full operation";
	}

}
