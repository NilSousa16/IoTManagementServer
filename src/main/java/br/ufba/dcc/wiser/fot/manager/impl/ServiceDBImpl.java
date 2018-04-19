package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvidedId;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

/**
 * Class responsible for making access to the database referring to the
 * information of the services.
 *
 * @author Nilson Rodrigues Sousa
 */
public class ServiceDBImpl implements ServiceDBService{

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Method that adds a service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param service
	 *            Service
	 */
	public void add(Service service) {
		try {
			entityManager.persist(service);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that updates a service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param service
	 *            Service
	 */
	public void update(Service service) {
		try {
			entityManager.merge(service);
		} catch (Exception e) {
			System.out.println("Failure in update service.");
			e.printStackTrace();
		}
	}

	/**
	 * A method that looks for a service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param nameService
	 *            String
	 * @return Service - Service returned
	 */
	public Service find(String nameService) {
		System.out.println("%%%%%%%%%%Entrou em find service");
		String jpqlService = "select s from Service s where s.nameService = :nameService";
		TypedQuery<Service> queryService = entityManager.createQuery(jpqlService, Service.class);
		queryService.setParameter("nameService", nameService);

		Service service = new Service();

		try {
			service = queryService.getSingleResult();
			return service;
		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Method that removes a service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param name
	 *            String
	 */
	public void remove(String name) {
		try {
			Service service = entityManager.find(Service.class, name);
			if (service != null) {
				entityManager.remove(service);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that returns the list of all services registered in the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Service - List of returned services
	 */
	public List<Service> getListService() {
		List<Service> listService = new ArrayList<Service>();

		listService = entityManager.createQuery("select p from Service p", Service.class).getResultList();

		if (listService != null) {
			return listService;
		}

		return null;
	}

	/**
	 * Method that adds a service provided.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalled
	 *            BundlerInstalled, service Service List<Bundler>, l2
	 *            List<Bundler>
	 */
	public void addServiceProvided(BundlerInstalled bundlerInstalled, Service service) {
		ServiceProvided serviceProvided = new ServiceProvided();

		try {
			serviceProvided.setId(new ServiceProvidedId(bundlerInstalled, service));
			entityManager.persist(serviceProvided);
		} catch (Exception e) {
			System.out.println("Failure in addServiceProvided.");
			e.printStackTrace();
		}
	}

	/**
	 * Method that removes a service provided.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalled,
	 *            serviceFind Service
	 */
	public void removeServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind) {
		ServiceProvided serviceProvided = new ServiceProvided();
		try {
			serviceProvided.setId(new ServiceProvidedId(bundlerInstalled, serviceFind));
			entityManager.remove(serviceProvided);
		} catch (Exception e) {
			System.out.println("Failure in removeServiceProvided.");
			e.printStackTrace();
		}
	}

	/**
	 * Method that seeks a service provided.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalled
	 *            BundlerInstalled, serviceFind Service
	 * @return ServiceProvided - Returns the provided service
	 */
	public ServiceProvided findServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind) {
		try {
			ServiceProvided serviceProvided = entityManager.find(ServiceProvided.class,
					new ServiceProvidedId(bundlerInstalled, serviceFind));
			return serviceProvided;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Method that adds a used service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalledUse
	 *            BundlerInstalled, serviceProvided ServiceProvided
	 */
	public void addBundlerUser(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided) {
		ServiceProvided serviceProvidedFind = new ServiceProvided();

		try {
			serviceProvidedFind = entityManager.find(ServiceProvided.class, new ServiceProvidedId(
					serviceProvided.getId().getBundleInstalled(), serviceProvided.getId().getService()));

			serviceProvidedFind.getBundlerUsed().add(bundlerInstalledUse);

			entityManager.merge(serviceProvidedFind);
		} catch (Exception e) {
			System.out.println("Failed in addBundlerUser.");
			e.printStackTrace();
		}

	}

	/**
	 * Method that removes a used service.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalledUse
	 *            BundlerInstalledUse, serviceProvided ServiceProvided
	 */
	public void removeBundlerUser(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided) {
		ServiceProvided serviceProvidedFind = new ServiceProvided();

		try {
			serviceProvidedFind = entityManager.find(ServiceProvided.class, new ServiceProvidedId(
					serviceProvided.getId().getBundleInstalled(), serviceProvided.getId().getService()));

			serviceProvidedFind.getBundlerUsed().remove(bundlerInstalledUse);

			entityManager.merge(serviceProvidedFind);
		} catch (Exception e) {
			System.out.println("Failed in removeBundlerUser.");
			e.printStackTrace();
		}
		
	}

	/**
	 * Method that returns the services and bundles that use it.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundlerInstalledUse
	 *            BundlerInstalledUse, serviceProvided ServiceProvided
	 */
	public List<ServiceProvided> findBundlerUser(String mac) {
		
		List<ServiceProvided> listServiceProvidedReturn = new ArrayList<ServiceProvided>();
		List<BundlerInstalled> bdl = new ArrayList<BundlerInstalled>();
		
		Gateway gtw = new Gateway();
		gtw = entityManager.find(Gateway.class, mac);
		
		String jpqlService = "select s from service_provided s where s.id.bundleInstalled.id.gateway = :gtw";
		Query queryService = entityManager.createQuery(jpqlService);
		
		queryService.setParameter("gtw", gtw);
		
		listServiceProvidedReturn.addAll(queryService.getResultList());

		for (ServiceProvided sp : listServiceProvidedReturn) {
			String jpqlBU = "select s.bundlerUsed from service_provided s where s = :s ";
			Query queryBU = entityManager.createQuery(jpqlBU);
			
			queryBU.setParameter("s", sp);
			
			bdl = new ArrayList<BundlerInstalled>();
			
			sp.getBundlerUsed().addAll(queryBU.getResultList());
			
		}
		
		return listServiceProvidedReturn;
		
	}

}
