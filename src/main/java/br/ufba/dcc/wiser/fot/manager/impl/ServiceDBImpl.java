package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvidedId;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceUsed;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

public class ServiceDBImpl implements ServiceDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Service service) {
		try {
			entityManager.persist(service);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Service service) {
		entityManager.merge(service);
	}

	public Service find(String nameService) {
		Service service = entityManager.find(Service.class, nameService);
		if (service != null) {
			return service;
		}
		return null;
	}

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

	public List<Service> getListService() {
		return entityManager.createQuery("select p from Service p", Service.class).getResultList();
	}

	public void addServiceProvided(BundlerInstalled bundlerInstalled, Service service) {
		ServiceProvided serviceProvided = new ServiceProvided();

		serviceProvided.setId(new ServiceProvidedId(bundlerInstalled, service));
		entityManager.persist(serviceProvided);
	}

	public void removeServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind) {
		ServiceProvided serviceProvided = new ServiceProvided();

		serviceProvided.setId(new ServiceProvidedId(bundlerInstalled, serviceFind));
		entityManager.remove(serviceProvided);
	}

	public ServiceProvided findServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind) {
		ServiceProvided serviceProvidedTemp = new ServiceProvided();
		
		serviceProvidedTemp.setId(new ServiceProvidedId(bundlerInstalled, serviceFind));
		
		ServiceProvided serviceProvided = entityManager.find(ServiceProvided.class, serviceProvidedTemp);
		
		return serviceProvided;
	}
	
	public void addServiceUsed(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided) {
		ServiceUsed serviceUsed = new ServiceUsed();
		
		serviceUsed.setBundleInstalled(bundlerInstalledUse);
		serviceUsed.setServiceProvided(serviceProvided);
		
		entityManager.persist(serviceUsed);
	}
	
	public void removeServiceUsed(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided) {
		ServiceUsed serviceUsed = new ServiceUsed();
		
		serviceUsed.setBundleInstalled(bundlerInstalledUse);
		serviceUsed.setServiceProvided(serviceProvided);
		
		entityManager.remove(serviceUsed);
	}

}
