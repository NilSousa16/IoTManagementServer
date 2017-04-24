package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Service;

public interface ServiceDBService {
	
	void add(Service bundler) throws Exception;
	void update(Service bundler);
	Service find (String name);
	void desactive (String name) throws Exception;
	public void remove(String name) throws Exception;
	void active(Service service);
	List<Service> getListService();

}
