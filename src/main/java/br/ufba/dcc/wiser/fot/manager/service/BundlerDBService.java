package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;

public interface BundlerDBService {
	
	public void add(Bundler bundler);
	public void update(Bundler bundler);
	public Bundler find (String name);
	public Bundler find (String name, String version);
	public void remove(String name);
	public List<Bundler> getListBundler();
	
	public void addBundlerInstalled(Bundler bundler, Gateway gateway, String status);
	public BundlerInstalled findBundlerInstalled (Bundler bundler, Gateway gateway);
	public void removeBundlerInstalled (BundlerInstalled bundlerInstalled);
	public void updateBundlerInstalled(BundlerInstalled bundlerInstalled);
	public List<Gateway> getListBundlersGateway();
	public Gateway listBundlerInstalled (String mac);

	
}
