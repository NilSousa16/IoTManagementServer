package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

public interface GatewayDBService {
	
	public void add(Gateway gateway);
	public void update(Gateway gateway);
	public Gateway find(String mac);
	public void desactive(String ip) throws Exception;
	public void active(Gateway gateway);
	public List<Gateway> getListGateway();
	public Gateway getListGateway(String mac);

}
