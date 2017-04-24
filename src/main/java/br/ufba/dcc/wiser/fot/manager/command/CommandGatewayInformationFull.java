package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

//criar comando para listar status dos gateways existentes
//@Command(scope = "fm", name = "informationFull")
public class CommandGatewayInformationFull {

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	public Object execute(CommandSession session) throws Exception {
		List<Gateway> gateways = gatewayDBService.getListGateway();

		for (Gateway gateway : gateways) {
			System.out.println("\n################################\n");
			System.out.println(">>>>> Description: " + gateway.getDescription()); //OK
			System.out.println(">>>>> Model: " + gateway.getModel()); //NULL
			System.out.println(">>>>> Manufacturer: " + gateway.getManufacturer()); //NULL
			System.out.println(">>>>> Firmware: " + gateway.getFirmware()); //NULL
			System.out.println(">>>>> Status: " + gateway.isStatus()); //ALTERADO NO BANCO
			System.out.println(">>>>> LastUpdate: " + gateway.getLastUpdate()); //OK
			System.out.println(">>>>> BaterryLevel: " + gateway.getBaterryLevel()); //OK
			System.out.println(">>>>> TotalMemory: " + gateway.getTotalMemory()); //OK
			System.out.println(">>>>> UsedMemory: " + gateway.getUsedMemory()); //OK
			System.out.println(">>>>> FreeMemory: " + gateway.getFreeMemory()); //OK
			System.out.println(">>>>> UsedProcessor" + gateway.getUsedProcessor() + "%"); //OK
			System.out.println(">>>>> FreeProcessor: " + gateway.getFreeProcessor() + "%"); //OK
			System.out.println(">>>>> Mac: " + gateway.getMac()); //OK
			System.out.println(">>>>> Ip: " + gateway.getIp()); //OK
			System.out.println(">>>>> HostName: " + gateway.getHostName()); //OK
			System.out.println(">>>>> Location: " + gateway.getLocation()); //NULL
			System.out.println("\n################################\n");
		}
		return null;
	}
	
}
