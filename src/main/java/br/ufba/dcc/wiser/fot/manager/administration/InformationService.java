package br.ufba.dcc.wiser.fot.manager.administration;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.model.communication.BundlerCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.ServiceCommunication;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

public class InformationService {

	// assing in the blueprint
	private ServiceDBService serviceDBService = null;
	private BundlerDBService bundlerDBService = null;
	private GatewayDBService gatewayDBService = null;

	// Used by blueprint
	public void setServiceDBService(ServiceDBService serviceDBService) {
		this.serviceDBService = serviceDBService;
	}

	// Used by blueprint
	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	@POST
	@Path("/connectedservice")
	@Produces("application/json")
	public void addService(String value) {
		
		System.out.println("Information received in addService");
		System.out.println(value);

//		Gson gson = new Gson();
//
//		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);
//
//		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());
//
//		List<ServiceCommunication> listServiceCommunication = gatewayCommunicationFind.getListService();
//
//		for (ServiceCommunication sc : listServiceCommunication) {
//
//			Service serviceFind = serviceDBService.find(sc.getNameService());
//
//			if (serviceFind == null) {
//				Service service = new Service();
//
//				service.setNameService(sc.getNameService());
//
//				serviceDBService.add(service);
//
//				serviceFind = serviceDBService.find(sc.getNameService());
//			}
//
//			// trabalhar os bundles para saber se já existem ou não
//			// selecionar os bundles que não existem informações solicitar ao
//			// gateway e armazenar
//			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
//					sc.getBundlerProvide().getVersion());
//
//			// considerado que o bundlerinstalled sempre existe<<<<<<<<<<<<<<
//			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);
//
//			serviceDBService.addServiceProvided(bundlerInstalled, serviceFind);
//
//			// considerado que o bundlerinstalled sempre existe<<<<<<<<<<<<<<<<
//			// armazenamento dos service_used
//
//			ServiceProvided serviceProvided = serviceDBService.findServiceProvided(bundlerInstalled, serviceFind);
//			
//			for (BundlerCommunication bc : sc.getListUsesBundles()) {
//				
//				Bundler bundlerFindUsed = bundlerDBService.find(bc.getName(), bc.getVersion());
//				
//				BundlerInstalled bundlerInstalledUse = bundlerDBService.findBundlerInstalled(bundlerFindUsed, gatewayFind);
//				
//				serviceDBService.addServiceUsed(bundlerInstalledUse, serviceProvided);
//
//			}
//
//		}

	}

	@POST
	@Path("/disconnectedservice")
	@Produces("application/json")
	public void disconnectedService(String value) {
		
		System.out.println("Information received in disconnectedService");
		System.out.println(value);

//		Gson gson = new Gson();
//
//		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);
//
//		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());
//
//		List<ServiceCommunication> listServiceCommunication = gatewayCommunicationFind.getListService();
//
//		for (ServiceCommunication sc : listServiceCommunication) {
//
//			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
//					sc.getBundlerProvide().getVersion());
//
//			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);
//
//			Service serviceFind = serviceDBService.find(sc.getNameService());
//
//			serviceDBService.removeServiceProvided(bundlerInstalled, serviceFind);
//			
//			//service_used é excluido através de cascate
//
//		}
	}

	@POST
	@Path("/alteredserviceconnectedbundleruse")
	@Produces("application/json")
	public Response alteredServiceConnectedBundlerUse(String value) {

		// implementar futuramente
		
		System.out.println("Information received in alteredServiceConnectedBundlerUse");
		System.out.println(value);

		return null;
	}
	
	@POST
	@Path("/alteredservicedisconnectedbundleruse")
	@Produces("application/json")
	public Response alteredServiceDisconnectedBundlerUse(String value) {

		// implementar futuramente
		
		System.out.println("Information received in alteredServiceDisconnectedBundlerUse");
		System.out.println(value);
		return null;
	}

}
