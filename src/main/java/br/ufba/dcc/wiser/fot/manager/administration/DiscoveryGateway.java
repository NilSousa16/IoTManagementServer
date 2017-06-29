package br.ufba.dcc.wiser.fot.manager.administration;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.util.ConverterStringIp;
import br.ufba.dcc.wiser.fot.manager.util.InfraUtil;
import br.ufba.dcc.wiser.fot.manager.util.JsonUtil;

/**
 * Class responsible detect gateway and update database with your ip, mac and
 * status
 *
 * @author Nilson Rodrigues Sousa
 */
public class DiscoveryGateway {

	private HazelcastInstance instance = null;

	private GatewayDBService gatewayDBService = null;

	private static Set<Member> listGateway = new HashSet<Member>();

	Set<Member> listNewGateway = new HashSet<Member>();
	Set<Member> listDisconnectedGateway = new HashSet<Member>();

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

	/**
	 * Method responsible for discovering the existing gateways in the system
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void discovery() {
		System.out.println("Entry method discovery");
		try {

			Cluster clusterInst = instance.getCluster();

			Set<Member> members = clusterInst.getMembers();

			json = new JsonUtil();

			Set<Member> listGatewayAux = new HashSet<Member>();

			// Executed only when the list is empty or in the first iteration
			if (listGateway.isEmpty()) {
				for (Member m : members) {
					if (InfraUtil.getIpMachine()
							.equals(ConverterStringIp.converterStringIp(m.getAddress().toString()))) {
						continue;
					}
					listGatewayAux.add(m);
				}

				this.addGateway(listGatewayAux);

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

				if (!listNewGateway.isEmpty()) {
					this.addGateway(listNewGateway);
				} else {
					System.out.println(">>>>>>>>>>>>>List new gateway null.");
				}

				if (!listDisconnectedGateway.isEmpty()) {
					this.desactiveGateway(listDisconnectedGateway);
				} else {
					System.out.println(">>>>>>>>>>>>>List disconnected gateway null.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in discovery method: " + e.toString());
		}

	}

	/**
	 * Helper method that adds a detected gateway to the database or changes its
	 * status to true if it is disabled
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param list
	 */
	private void addGateway(Set<Member> list) {
		for (Member m : list) {
			String ip = ConverterStringIp.converterStringIp(m.getAddress().toString());

			try {
				if (ip.equals(InfraUtil.getIpMachine())) {
					continue;
				}

				JSONObject jsonObject = json.getInformation(ip, "cxf/gtw/gatewayservice", "gateway/mac");

				String mac = jsonObject.getJSONObject("Gateway").getString("mac");

				Gateway gateway = gatewayDBService.find(mac);

				if (gateway != null) {
					gateway.setIp(ip);
					gateway.setStatus(true);
					// replacing the activate method
					gatewayDBService.update(gateway);

				} else {
					gateway = new Gateway();
					gateway.setIp(ip);
					gateway.setMac(mac);
					gateway.setStatus(true);
					gatewayDBService.add(gateway);
				}

				listGateway.add(m);

			} catch (Exception e) {
				// To modify database routine for log storage
				log += "Ip: " + ip + " " + InfraUtil.getDateHour() + " " + e + "\n";
				System.out.println("\n" + log + "\n");
			}
		}
	}

	/**
	 * Helper method that changes the status of a gateway to false in the database
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param list
	 */
	private void desactiveGateway(Set<Member> list) {
		for (Member m : list) {
			String ip = ConverterStringIp.converterStringIp(m.getAddress().toString());

			try {
				if (ip.equals(InfraUtil.getIpMachine())) {
					continue;
				}

				gatewayDBService.desactive(ip);

			} catch (Exception e) {
				// To modify database routine for log storage
				log += "Ip: " + ip + " " + InfraUtil.getDateHour() + " " + e + "\n";
				System.out.println("\n" + log + "\n");
			}

			listGateway.remove(m);
		}
	}

}
