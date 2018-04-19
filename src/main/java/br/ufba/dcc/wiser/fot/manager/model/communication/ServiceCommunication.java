package br.ufba.dcc.wiser.fot.manager.model.communication;

import java.util.ArrayList;
import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class ServiceCommunication {

	private String nameService;
	private Bundler bundlerProvide;
	private List<BundlerCommunication> listUsesBundles = new ArrayList<BundlerCommunication>();

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public Bundler getBundlerProvide() {
		return bundlerProvide;
	}

	public void setBundlerProvide(Bundler bundlerProvide) {
		this.bundlerProvide = bundlerProvide;
	}

	public List<BundlerCommunication> getListUsesBundles() {
		return listUsesBundles;
	}

	public void setListUsesBundles(List<BundlerCommunication> listUsesBundles) {
		this.listUsesBundles = listUsesBundles;
	}

}
