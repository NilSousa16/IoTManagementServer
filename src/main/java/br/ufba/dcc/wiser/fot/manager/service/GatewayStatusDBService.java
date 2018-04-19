package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;

public interface GatewayStatusDBService {

	public void add(GatewayStatus gatewayStatus);
	public List<GatewayStatus> findGatewayInformation(Gateway gateway);
	public List<GatewayStatus> getListGateway();

}
