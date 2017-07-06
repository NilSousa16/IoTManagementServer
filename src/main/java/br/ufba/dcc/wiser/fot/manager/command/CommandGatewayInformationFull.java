package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

@Command(scope = "wiser", name = "gateway-information-full")
public class CommandGatewayInformationFull {

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	public Object execute(CommandSession session) throws Exception {
		List<Gateway> gateways = gatewayDBService.getListGateway();

		System.out.println("\n---------------------------------------------------");
		System.out.println("REPORT REGISTERED GATEWAYS FULL");
		System.out.println("---------------------------------------------------");
		for (Gateway gateway : gateways) {
			System.out.println(">>>>> Mac: " + gateway.getMac());
			System.out.println("	Description: " + gateway.getDescription());
			System.out.println("	Model: " + gateway.getModel());
			System.out.println("	Manufacturer: " + gateway.getManufacturer());
			System.out.println("	Firmware: " + gateway.getFirmware());
			System.out.println("	Status: " + gateway.isStatus());
			System.out.println("	LastUpdate: " + gateway.getLastUpdate());
			System.out.println("	Ip: " + gateway.getIp());
			System.out.println("	HostName: " + gateway.getHostName());
			System.out.println("	Location: " + gateway.getLocation());
		}
		System.out.println("---------------------------------------------------");
		return null;
	}
	
}
