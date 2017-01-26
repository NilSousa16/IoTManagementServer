package br.ufba.dcc.wiser.fot.manager;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

public class MonitorAddress {

	private HazelcastInstance instance = null;
	private static Set<Member> listGateway = new HashSet<Member>();

	public void setInstance(HazelcastInstance instance) {
		this.instance = instance;
	}
	
	public static Set<Member> getListGateway(){
		return listGateway;
	}

	public void monitorAddress() {
		Cluster clusterInst = instance.getCluster();

		try {			
			// lista capturada do cellar
			Set<Member> members = clusterInst.getMembers();
			// lista de gateways que caíram
			Set<Member> membersTemp = new HashSet<Member>();
			// gateways que surgiram
			Set<Member> listGatewayTemp = new HashSet<Member>();

			membersTemp.addAll(members);

			if (listGateway == null || listGateway.isEmpty()) {
				// Preencheu a listGateway pela primeira vez
				listGateway.addAll(members);				
			} else if (!listGateway.equals(members)) {				
				listGatewayTemp = new HashSet<Member>();

				listGatewayTemp.addAll(listGateway);
				// retorna os gateways que cairam
				listGatewayTemp.removeAll(members);
				// retorna os gateways novos que surgiram
				membersTemp.removeAll(listGateway);
				// atualização da lista principal
				// adiciona novos gateways
				listGateway.addAll(membersTemp);
				// retirando gateways que sairam do sistema
				for(Member newElement : listGatewayTemp) {
					listGateway.remove(newElement);
				}
				
				// após execução haverá
				// listGateway -- atualizada
				// listGatewayTemp -- com os gateways que caíram
				// membersTemp -- com os gateways que surgiram

			}
			//for(Member m : listGateway){
			//	System.out.println(">>>>>>Dispositivo: " + m.toString().substring(8, 21));
			//}
		} catch (NullPointerException ex) {
			Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedOperationException e) {
			Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
