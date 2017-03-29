package br.ufba.dcc.wiser.fot.manager.administration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.util.ConverterInfoJsonClass;
import br.ufba.dcc.wiser.fot.manager.util.ConverterStringIp;
import br.ufba.dcc.wiser.fot.manager.util.InfraUtil;
import br.ufba.dcc.wiser.fot.manager.util.JsonUtil;

public class GatewayMonitor {

	private HazelcastInstance instance = null;
	
	BundleContext bundleContext =
            FrameworkUtil.
            getBundle(this.getClass()).
            getBundleContext();	
	
	private static Set<Member> listGateway = new HashSet<Member>();

	Set<Member> listNewGateway = new HashSet<Member>();
	Set<Member> listDisconnectedGateway = new HashSet<Member>();	

	private GatewayDBService gatewayDBService = null;

	private JsonUtil json = new JsonUtil();

	// Simulates a log
	public static String log = "";

	// Used by blueprint
	public void setInstance(HazelcastInstance instance) {
		this.instance = instance;
	}

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}
	
	// >>>>>>>>MÉTODO RESPONSAVEL POR SOLICITAR INFORMAÇÕES<<<<<<<<

	// Responsible for updating gateway information, adding new gateway, etc
	public void monitorGateway() {

		try {

			Cluster clusterInst = instance.getCluster();

			Set<Member> members = clusterInst.getMembers();

			json = new JsonUtil();
			System.out.println("Teste BundleContext: " + bundleContext.toString());
			if (listGateway.isEmpty()) {
				System.out.println(">>>>>>>>>>>>>>>Entrou quando a lista é vazia");
				listGateway.addAll(members);
				// >>>>>>>>>>>melhorar lógica de implementação desse código
				for (Member m : listGateway) {
					String ip = ConverterStringIp.converterStringIp(m.getAddress().toString());

					System.out.println("IP local x IP Cellar: " + ip + " - " + InfraUtil.getIpMachine() + " - "
							+ ip.equals(InfraUtil.getIpMachine()));
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>Ent: " + m.getAddress().toString());
					try {
						if (ip.equals(InfraUtil.getIpMachine())) {
							continue;
						}
						System.out.println(
								">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para conectar quando a lista é vazia");
						JSONObject jsonObject = json.getInformation(ip, "cxf/gtw/gatewayservice", "gateway/gt");

						ConverterInfoJsonClass<Gateway> converter = new ConverterInfoJsonClass<Gateway>();
						Gateway gateway = converter.getInfo(jsonObject, Gateway.class);					
						
						System.out.println(">>>" + jsonObject.toString());
						
						gatewayDBService.add(gateway);

					} catch (Exception e) {
						// To modify database routine for log storage
						log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
						System.out.println("\n" + log + "\n");
					}
				}

			} else {
				listNewGateway = new HashSet<Member>();
				listDisconnectedGateway = new HashSet<Member>();

				listNewGateway.addAll(members);
				listDisconnectedGateway.addAll(listGateway);

				// retorna os novos gateways
				listNewGateway.removeAll(listGateway);
				// retorna os gateways desconectados
				listDisconnectedGateway.removeAll(members);

				// avalia os conectados
				if (!listNewGateway.isEmpty()) {
					for (Member m : listNewGateway) {
						// linha com potencial erro
						String ip = ConverterStringIp.converterStringIp(m.toString());
						try {

							if (ip.equals(InfraUtil.getIpMachine())) {
								continue;
							}
							System.out.println(">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para conectar");
							JSONObject jsonObject = json.getInformation(ip, "cxf/gtw/gatewayservice", "gateway/gt");

							ConverterInfoJsonClass<Gateway> converter = new ConverterInfoJsonClass<Gateway>();
							Gateway gateway = converter.getInfo(jsonObject, Gateway.class);

							// >>>>implementar avaliação caso seja um gateway se
							// reconectando

							//gatewayDBService.add(gateway);
							
							System.out.println(">>>Gateway: " + gateway.getMac());

						} catch (Exception e) {
							// To modify database routine for log storage
							log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
							System.out.println("\n" + log + "\n");
						}
					}

				} else {
					System.out.println("GatewayMonitor.MonitorGateway: Lista de novos gateways vazia.");
				}

				if (!listDisconnectedGateway.isEmpty()) {
					for (Member m : listDisconnectedGateway) {
						String ip = m.toString().substring(8, 21);

						try {
							if (ip.equals(InfraUtil.getIpMachine())) {
								continue;
							}
							System.out.println(">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para desconectar");
							gatewayDBService.desactive(ip);

						} catch (Exception e) {
							// To modify database routine for log storage
							log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
							System.out.println("\n" + log + "\n");
						}
					}

				} else {
					System.out.println("GatewayMonitor.MonitorGateway: Lista de gateways desconectados vazia.");
				}
				listGateway.clear();
				listGateway.addAll(members);
			}

			// LISTA MANUAL
			/*
			 * if (!gatewayDBService.getListGateway().isEmpty()) {
			 * 
			 * List<Gateway> gateways = gatewayDBService.getListGateway();
			 * 
			 * for (Gateway gateway : gateways) {
			 * System.out.println("\n################################\n");
			 * System.out.println(">>>>> Description: " +
			 * gateway.getDescription()); System.out.println(">>>>> Model: " +
			 * gateway.getModel()); System.out.println(">>>>> Manufacturer: " +
			 * gateway.getManufacturer()); System.out.println(">>>>> Firmware: "
			 * + gateway.getFirmware()); System.out.println(">>>>> Status: " +
			 * gateway.isStatus()); System.out.println(">>>>> LastUpdate: " +
			 * gateway.getLastUpdate());
			 * System.out.println(">>>>> BaterryLevel: " +
			 * gateway.getBaterryLevel());
			 * System.out.println(">>>>> TotalMemory: " +
			 * gateway.getTotalMemory());
			 * System.out.println(">>>>> UsedMemory: " +
			 * gateway.getUsedMemory()); System.out.println(">>>>> FreeMemory: "
			 * + gateway.getFreeMemory());
			 * System.out.println(">>>>> UsedProcessor" +
			 * gateway.getUsedProcessor() + "%");
			 * System.out.println(">>>>> FreeProcessor: " +
			 * gateway.getFreeProcessor() + "%");
			 * System.out.println(">>>>> Mac: " + gateway.getMac());
			 * System.out.println(">>>>> Ip: " + gateway.getIp());
			 * System.out.println(">>>>> HostName: " + gateway.getHostName());
			 * System.out.println(">>>>> Location: " + gateway.getLocation());
			 * System.out.println("\n################################\n"); } }
			 * else { System.out.println("Não há nada a ser mostrado."); }
			 */

		} catch (Exception e) {
			System.out.println("Erro no método MonitorGateway.monitorGateway(): " + e.toString());
		}

	}

	// Updates information for all gateways - Refresh every 2 minutes
	// configurar dentro do blueprint
	public void updateDataGateway() {
		try {
			json = new JsonUtil();

			List<Gateway> gt = gatewayDBService.getListGateway();

			if (gt != null) {
				for (Gateway g : gt) {
					JSONObject jsonObject = json.getInformation(g.getIp(), "cxf/gtw/gatewayservice", "gateway/gt");

					ConverterInfoJsonClass<Gateway> converter = new ConverterInfoJsonClass<Gateway>();

					// >>> implementar possibilidade de erro ao não encontrar
					// gateway atualizar como desativado
					Gateway gateway = converter.getInfo(jsonObject, Gateway.class);

					gatewayDBService.update(gateway);
				}
			}
		} catch (Exception e) {
			// To modify database routine for log storage
			log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
			System.out.println("\n" + log + "\n");
		}

	}
}
