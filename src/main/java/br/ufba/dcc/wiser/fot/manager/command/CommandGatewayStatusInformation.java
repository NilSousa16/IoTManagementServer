package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;
import br.ufba.dcc.wiser.fot.manager.service.GatewayStatusDBService;

@Command(scope = "wiser", name = "gateway-status-information",  description = "Return historic gateways status")
public class CommandGatewayStatusInformation implements Action{

	private GatewayStatusDBService gatewayStatusDBService = null;

	public void setGatewayStatusDBService(GatewayStatusDBService gatewayStatusDBService) {
		this.gatewayStatusDBService = gatewayStatusDBService;
	}

	public Object execute(CommandSession session) throws Exception {
		try {
			List<GatewayStatus> listGatewayStatus = gatewayStatusDBService.getListGateway();

			System.out.println("\n---------------------------------------------------");
			System.out.println("REPORT REGISTERED GATEWAYS STATUS INFORMATION");
			System.out.println("---------------------------------------------------");
			if (listGatewayStatus != null && !listGatewayStatus.isEmpty()) {
				for (GatewayStatus gatewayStatus : listGatewayStatus) {
					System.out.println(">>> Mac: " + gatewayStatus.getGateway().getMac());
					System.out.println("	BaterryLevel: " + gatewayStatus.getBaterryLevel());
					System.out.println("	FreeMemory:" + gatewayStatus.getFreeMemory());
					System.out.println("	FreeProcessor:" + gatewayStatus.getFreeProcessor());
					System.out.println("	TotalMemory:" + gatewayStatus.getTotalMemory());
					System.out.println("	UsedMemory:" + gatewayStatus.getUsedMemory());
					System.out.println("	UsedProcessor:" + gatewayStatus.getUsedProcessor());
				}

			} else {
				System.out.println("\nNo items stored.\n");
			}
			System.out.println("---------------------------------------------------");

			System.out.println("Operation successfully completed.");

		} catch (Exception e) {
			System.out.println("CommandGatewayStatusInformation operation failed");
			e.printStackTrace();
		}

		return null;
	}

}
