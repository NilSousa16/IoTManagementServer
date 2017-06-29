package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;

public interface ServiceDBService {
	
	public void add(Service service);
	public void update(Service service);
	public Service find (String name);
	public void remove(String name);
	public List<Service> getListService();
	
	public void addServiceProvided(BundlerInstalled bundlerInstalled, Service service);
	public void removeServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind);
	public ServiceProvided findServiceProvided(BundlerInstalled bundlerInstalled, Service serviceFind);
	
	public void addServiceUsed(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided);
	public void removeServiceUsed(BundlerInstalled bundlerInstalledUse, ServiceProvided serviceProvided);
	
}
