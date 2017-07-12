package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;

public interface GatewayStatusDBService {

	void add(GatewayStatus gatewayStatus);
	List<GatewayStatus> findGatewayInformation(Gateway gateway);
	List<GatewayStatus> getListGateway();

}
