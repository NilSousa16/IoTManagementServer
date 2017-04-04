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

	BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

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

	// Responsible for updating gateway information, adding new gateway, etc
	public void monitorGateway() {

		try {

			Cluster clusterInst = instance.getCluster();

			Set<Member> members = clusterInst.getMembers();

			json = new JsonUtil();

			Set<Member> listGatewayAux = new HashSet<Member>();

			if (listGateway.isEmpty()) {
				System.out.println(">>>>>>>>>>>>>>>Entrou quando a lista é vazia");

				for (Member m : members) {
					if (InfraUtil.getIpMachine()
							.equals(ConverterStringIp.converterStringIp(m.getAddress().toString()))) {
						continue;
					}
					listGatewayAux.add(m);
				}

				// >>>>>>>>>>>melhorar lógica de implementação desse código
				for (Member m : listGatewayAux) {
					String ip = ConverterStringIp.converterStringIp(m.getAddress().toString());

					try {
						if (ip.equals(InfraUtil.getIpMachine())) {
							continue;
						}
						System.out.println(
								">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para conectar quando a lista é vazia");
						JSONObject jsonObject = json.getInformation(ip, "cxf/gtw/gatewayservice", "gateway/gt");

						ConverterInfoJsonClass<Gateway> converter = new ConverterInfoJsonClass<Gateway>();
						Gateway gateway = converter.getInfo(jsonObject, Gateway.class);

						System.out.println(">>>" + gateway.getMac());
						System.out.println(">>>" + jsonObject.toString());

						// gatewayDBService.add(gateway);

						listGateway.add(m);

					} catch (Exception e) {
						// To modify database routine for log storage
						log += "Ip: " + ip + " " + InfraUtil.getDateHour() + " " + e + "\n";
						System.out.println("\n" + log + "\n");
					}
				}

			} else {
				listNewGateway = new HashSet<Member>();
				listDisconnectedGateway = new HashSet<Member>();

				for (Member m : members) {
					if (InfraUtil.getIpMachine()
							.equals(ConverterStringIp.converterStringIp(m.getAddress().toString()))) {
						continue;
					}
					listNewGateway.add(m);

					listGatewayAux.add(m);
				}

				listDisconnectedGateway.addAll(listGateway);
				// return new gateways
				listNewGateway.removeAll(listGateway);
				// return gateways disconnected
				listDisconnectedGateway.removeAll(listGatewayAux);

				// evaluate connected
				if (!listNewGateway.isEmpty()) {
					// clear list main gateway for update
					// if there are changes the list will be rebooted
					
					for (Member m : listNewGateway) {
						String ip = ConverterStringIp.converterStringIp(m.getAddress().toString());
								
								//ConverterStringIp.converterStringIp(m.toString());
						try {
							if (ip.equals(InfraUtil.getIpMachine())) {
								continue;
							}
							System.out.println(">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para conectar " + ip);
							JSONObject jsonObject = json.getInformation(ip, "cxf/gtw/gatewayservice", "gateway/gt");

							ConverterInfoJsonClass<Gateway> converter = new ConverterInfoJsonClass<Gateway>();
							Gateway gateway = converter.getInfo(jsonObject, Gateway.class);

							// >>>>implementar avaliação caso seja um gateway se
							// reconectando

							// gatewayDBService.add(gateway);

							// update list main gateway
							listGateway.add(m);

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
						String ip = ConverterStringIp.converterStringIp(m.toString());

						try {
							if (ip.equals(InfraUtil.getIpMachine())) {
								continue;
							}
							System.out.println(">>>>>>>>>>>>>>>GatewayMonitor.monitorGateway: ENTROU para desconectar");
							// gatewayDBService.desactive(ip);

						} catch (Exception e) {
							// To modify database routine for log storage
							log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
							System.out.println("\n" + log + "\n");
						}
						
						listGateway.remove(m);
					}

				} else {
					System.out.println("GatewayMonitor.MonitorGateway: Lista de gateways desconectados vazia.");
				}
			}
		} catch (Exception e) {
			System.out.println("Erro no método MonitorGateway.monitorGateway(): " + e.toString());
		}

		System.out.println("\n##########ROUND REPORT##########");
		try {
			List<Gateway> gatewayList = gatewayDBService.getListGateway();

			if (!gatewayList.isEmpty()) {
				for (Gateway list : gatewayList) {
					System.out.println("Ip [" + list.getIp() + "] Mac [" + list.getMac() + "]");
				}
			} else {
				System.out.println("Não há gateways na lista.");
			}

		} catch (Exception e) {
			System.out.println("Falha na impressão dos gateways.");
		}

		System.out.println("################################\n");

		System.out.println("\n##########GATEWAY REPORT##########");
		
		for (Member lt : listGateway) {
			System.out.println(">>>>>>>>>>>>>>> " + lt.toString());
		}
		
		System.out.println("################################\n");

	}

	// Updates information for all gateways - Refresh every 2 minutes
	// Configure in blueprint
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

					System.out.println(">>>" + gateway.getMac());
					// gatewayDBService.update(gateway);
				}
			}
		} catch (Exception e) {
			// To modify database routine for log storage
			log += "Ip: " + InfraUtil.getIpMachine() + " " + InfraUtil.getDateHour() + " " + e + "\n";
			System.out.println("\n" + log + "\n");
		}

	}
}
