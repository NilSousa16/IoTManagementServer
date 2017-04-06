package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

public interface GatewayDBService {
	
	void add(Gateway gateway) throws Exception;
	void update(Gateway gateway);
	Gateway find(String mac);
	void desactive(String ip) throws Exception;
	void active(Gateway gateway);
	List<Gateway> getListGateway();

}
