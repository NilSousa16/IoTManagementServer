package br.ufba.dcc.wiser.fot.manager;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.karaf.cellar.core.ClusterManager;
import org.apache.karaf.cellar.core.GroupManager;
import org.apache.karaf.cellar.core.Node;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

public class DeviceMonitor {

	private HazelcastInstance instance = null;
	private ClusterManager cluster = null;
	private GroupManager group = null;
	Set<Member> listGateway = MonitorAddress.getListGateway();

	public void setInstance(HazelcastInstance instance) {
		this.instance = instance;
	}

	public void setCluster(ClusterManager cluster) {
		this.cluster = cluster;
	}

	public void setGroup(GroupManager group) {
		this.group = group;
	}

	private static String getStringFromInputStream(InputStream in) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int c = 0;
		while ((c = in.read()) != -1) {
			bos.write(c);
		}
		in.close();
		bos.close();
		return bos.toString();
	}

	private static String inputStreamToString(InputStream isr) {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(isr));
			StringBuilder sb = new StringBuilder();
			String s = null;

			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}

			return sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void deviceMonitor() throws Exception {
		try {
			//System.out.println(">>>>>>>>>>>>>>>>>Device monitor operacional.");
			//System.out.println("Sent HTTP GET request to query customer info");
			if (!(listGateway.isEmpty())) {				
				for (Member m : listGateway) {
					String ip = m.toString().substring(8, 21);
					if(ip.equals("192.168.0.112")){
						continue;
					}
					URL url = new URL("http://"+ip+":8181/cxf/gtw/gatewayservice/gateway/gt");
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou URL");
					InputStream in = url.openStream();
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou IN");

					JSONObject json = new JSONObject(getStringFromInputStream(in));
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Passou JSON");
					System.out.println(">>>>>>>>>>>>InfoJSON: " + json.toString());
					System.out.println(">>>>>>>>>>>>UsedMemory: " + json.getJSONObject("Gateway").getInt("usedMemory"));
					System.out.println(">>>>>>>>>>>>FreeMemory: " + json.getJSONObject("Gateway").getInt("freeMemory"));
					//System.out.println(">>>>>>>>>>>>Funcionando.");

				}
			}

		} catch (IOException e) {
			System.out.println("Erro na captura de informações: " + e);
		}
	}
}
