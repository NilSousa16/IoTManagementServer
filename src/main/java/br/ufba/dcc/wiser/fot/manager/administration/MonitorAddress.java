package br.ufba.dcc.wiser.fot.manager.administration;

import java.util.HashSet;
import java.util.Set;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

public class MonitorAddress {

	private HazelcastInstance instance = null;
	// lista de gateways atual
	private static Set<Member> listGateway = new HashSet<Member>();
	// lista de gateways que caíram
	private static Set<Member> listNewGateway = new HashSet<Member>();
	// gateways que surgiram
	private static Set<Member> listDisconnectedGateway = new HashSet<Member>();

	public void setInstance(HazelcastInstance instance) {
		this.instance = instance;
	}

	public static Set<Member> getListNewGateway() {
		return listNewGateway;
	}

	public static Set<Member> getListDisconnectedGateway() {
		return listDisconnectedGateway;
	}

	public void monitorAddress() {
		Cluster clusterInst = instance.getCluster();
		try {
			// lista capturada do cellar
			Set<Member> members = clusterInst.getMembers();

			if (listGateway == null || listGateway.isEmpty()) { //teste null é irrelevante
				// Preencheu a listGateway pela primeira vez
				listGateway.addAll(members);
				listNewGateway.addAll(listGateway);
				listDisconnectedGateway.clear();

			} else if (!listGateway.equals(members)) {

				Set<Member> listAuxTemp = new HashSet<Member>();

				// verificar novos gateways
				listAuxTemp.addAll(members);
				listAuxTemp.removeAll(listGateway);
				if (!listAuxTemp.isEmpty()) {
					listNewGateway.clear();
					listNewGateway.addAll(listAuxTemp);										
				} 

				// verifica gateways desconectados

				listAuxTemp = new HashSet<Member>();

				listAuxTemp.addAll(listGateway);

				listAuxTemp.removeAll(members);
				if (!listAuxTemp.isEmpty()) {
					listDisconnectedGateway.clear();
					listDisconnectedGateway.addAll(listAuxTemp);					
				} 

				// atualizar listGateway
				listGateway.clear();
				listGateway.addAll(members);
			}

		} catch (NullPointerException ex) {
			System.out.println("Tratar NullPointerException em MonitorAddress.");
		} catch (UnsupportedOperationException e) {
			System.out.println("Tratar UnsupportedOperationException em MonitorAddress.");
		}

	}

}
