package br.ufba.dcc.wiser.fot.manager.administration;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Device;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.DeviceCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.service.DeviceDBService;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationDevice {
	
	/* Instance of GatewayDBService to retrieve and store information */
	private GatewayDBService gatewayDBService = null;
	
	/* Instance of DeviceDBService to retrieve and store information */
	private DeviceDBService deviceDBService = null;
	
	/* Method used by blueprint to create gatewayDBService instance */
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/* Method used by blueprint to create deviceDBService instance */
	public void setDeviceDBService(DeviceDBService deviceDBService) {
		this.deviceDBService = deviceDBService;
	}
	
	@POST
	@Path("/connecteddevice")
	@Produces("application/json")
	public void connectedDevice(String value) {
		
		System.out.println("Information received in connectedDevice");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = new GatewayCommunication();
		gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = new Gateway();
		gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());
		
		if (gatewayFind != null) {
			for (DeviceCommunication dc : gatewayCommunication.getListDevice()) {

				Device newDevice = new Device();
				Device deviceDB = deviceDBService.findDevice(dc.getName());
				
				if(deviceDB == null) {
					newDevice.setName(dc.getName());
					newDevice.setManufacturer(dc.getManufacturer());
					newDevice.setVersion(dc.getVersion());
					newDevice.setDescriptionSemantic(dc.getDescriptionSemantic());

					deviceDBService.addDevice(newDevice);
					deviceDB = deviceDBService.findDevice(dc.getName());
				}

				//deviceDBService.addDeviceUsed(deviceDB, gatewayFind, true);

			}
			System.out.println("DeviceUsed stored successfully.");
		}
	}
	
	@POST
	@Path("/disconnecteddevice")
	@Produces("application/json")
	public void disconnectedDevice(String value) {
		
		

	}
	
	@POST
	@Path("/alterdeviceinformation/connected")
	@Produces("application/json")
	public void alterDeviceConnected(String value) {
		
		

	}
	
	@POST
	@Path("/alterdeviceinformation/disconnected")
	@Produces("application/json")
	public void alterDeviceDisconnected(String value) {
		
		

	}	

}
