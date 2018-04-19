package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalledId;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

/**
 * Class responsible for making access to the database referring to bundle
 * information.
 *
 * @author Nilson Rodrigues Sousa
 */
public class BundlerDBImpl implements BundlerDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Method to add a new bundle.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundler
	 *            Bundler
	 * 
	 */
	public void add(Bundler bundler) {
		try {
			entityManager.persist(bundler);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to update a bundle.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundler
	 *            Bundler
	 */
	public void update(Bundler bundler) {
		entityManager.merge(bundler);
	}

	/**
	 * Method to fetch a bundle by its name.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param name
	 *            String
	 * @return Bundler - Bundler located
	 */
	public Bundler find(String name) {
		Bundler bundler = entityManager.find(Bundler.class, name);
		if (bundler != null) {
			return bundler;
		}
		return null;
	}

	/**
	 * Method to fetch a bundle by its name and version.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param name
	 *            String, version String
	 * @return Bundler - Bundler located
	 */
	public Bundler find(String name, String version) {
		String jpql = "select p from Bundler p where p.name = :name and p.version = :version";
		TypedQuery<Bundler> query = entityManager.createQuery(jpql, Bundler.class);
		query.setParameter("name", name);
		query.setParameter("version", version);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to remove a bundler.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param name
	 *            String
	 */
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

	/**
	 * Method returns the list of bundles already registered in the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Bundler> - List of bundles retrieved from the system
	 */
	public List<Bundler> getListBundler() {
		return entityManager.createQuery("select p from Bundler p", Bundler.class).getResultList();
	}

	/**
	 * Method responsible for persisting a BundlerInstalled.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundler
	 *            Bundler, gateway Gateway, status String
	 */
	public void addBundlerInstalled(Bundler bundler, Gateway gateway, String status) {
		BundlerInstalled bundlerInstalled = new BundlerInstalled();

		bundlerInstalled.setId(new BundlerInstalledId(gateway, bundler));
		bundlerInstalled.setStatus(status);
		entityManager.persist(bundlerInstalled);
	}

	/**
	 * Method responsible for fetching a BundlerInstalled.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundler
	 *            Bundler, gateway Gateway
	 * @return BundlerInstalled - BundlerInstalled returned
	 */
	public BundlerInstalled findBundlerInstalled(Bundler bundler, Gateway gateway) {
		BundlerInstalled bundlerInstalled = entityManager.find(BundlerInstalled.class,
				new BundlerInstalledId(gateway, bundler));

		return bundlerInstalled;
	}

	/**
	 * Method to remove a BundlerInstalled.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalled
	 *            BundlerInstalled
	 */
	public void removeBundlerInstalled(BundlerInstalled bundlerInstalled) {
		entityManager.remove(entityManager.find(BundlerInstalled.class, bundlerInstalled.getId()));
	}

	/**
	 * Method to update a BundlerInstalled.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalled
	 *            BundlerInstalled
	 */
	public void updateBundlerInstalled(BundlerInstalled bundlerInstalled) {
		entityManager.merge(bundlerInstalled);
	}

	/**
	 * A method that returns the bundle information that is installed on the
	 * gateways.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Gateway> - List of bundles installed in the gateways
	 */
	public List<Gateway> getListBundlersGateway() {

		List<Gateway> listGatewayReturn = new ArrayList<Gateway>();

		String jpqlGateway = "select g from Gateway g";
		TypedQuery<Gateway> queryCreate = entityManager.createQuery(jpqlGateway, Gateway.class);

		for (Gateway gtw : queryCreate.getResultList()) {
			Gateway gateway = new Gateway();

			String jpqlBI = "select bi from BundlerInstalled bi where bi.id.gateway = :gtw";
			TypedQuery<BundlerInstalled> queryCreateBI = entityManager.createQuery(jpqlBI, BundlerInstalled.class);
			queryCreateBI.setParameter("gtw", gtw);

			gtw.getListBundlerInstalled().addAll(queryCreateBI.getResultList());
			gateway = gtw;

			// por algum motivo ele só carrega quando uso algum comando
			gateway.getListBundlerInstalled().isEmpty();

			listGatewayReturn.add(gateway);
		}

		return listGatewayReturn;
	}

	/**
	 * Method that returns the bundles installed on a given gateway
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param mac
	 *            String
	 * @return Gateway - Returns the list of installeds bundlers from a gateway
	 */
	public Gateway listBundlerInstalled(String mac) {

		try {
			String jpqlGateway = "select g from Gateway g where g.mac = :mac";
			TypedQuery<Gateway> queryGateway = entityManager.createQuery(jpqlGateway, Gateway.class);
			queryGateway.setParameter("mac", mac);

			Gateway gtw = new Gateway();

			gtw = queryGateway.getSingleResult();

			String jpqlBI = "select bi from BundlerInstalled bi where bi.id.gateway = :gtw";
			TypedQuery<BundlerInstalled> queryCreateBI = entityManager.createQuery(jpqlBI, BundlerInstalled.class);
			queryCreateBI.setParameter("gtw", gtw);

			gtw.getListBundlerInstalled().addAll(queryCreateBI.getResultList());
			// gateway = gtw;

			// por algum motivo ele só carrega quando uso algum comando
			if (!gtw.getListBundlerInstalled().isEmpty()) {
				return gtw;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Failure in listBundlerInstalled: ");
			e.printStackTrace();
			return null;
		}
	}

}
