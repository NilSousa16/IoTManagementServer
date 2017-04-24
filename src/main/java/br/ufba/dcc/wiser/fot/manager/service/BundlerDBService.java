package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;

public interface BundlerDBService {
	
	void add(Bundler bundler) throws Exception;
	void update(Bundler bundler);
	Bundler find (String name);
	void desactive (String name) throws Exception;
	public void remove(String name) throws Exception;
	void active(Bundler bundler);
	List<Bundler> getListBundler();

}
