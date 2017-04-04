package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

public interface GatewayDBService {
	
	void add(Gateway gateway);
	void update(Gateway gateway);
	Gateway find(String mac);
	void desactive(String ip);
	void active(Gateway gateway);
	List<Gateway> getListGateway();

}