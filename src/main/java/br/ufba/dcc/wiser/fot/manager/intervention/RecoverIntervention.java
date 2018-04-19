package br.ufba.dcc.wiser.fot.manager.intervention;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.BundlerCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;
import br.ufba.dcc.wiser.fot.manager.service.RecoverInterventionService;

public class RecoverIntervention implements RecoverInterventionService {

	private EntityManager entityManager = null;

	private BundlerDBService bundlerDBService = null;

	static int cont = 0;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	public void sendRestoreConfigurationExperiment() {
		// "08-00-27-CC-68-B6";
		// String mac;

		List<String> listMAC = new ArrayList<String>();
		// String[] listMAC = new String[12];

		listMAC.add("08-00-27-BD-1F-DF");
//		listMAC.add("08-00-27-96-C5-D9");
//		listMAC.add("08-00-27-9F-99-27");
//		listMAC.add("08-00-27-00-11-6B");
//		listMAC.add("08-00-27-62-BA-0E");
//		listMAC.add("08-00-27-42-3E-72");
//		listMAC.add("08-00-27-B2-7E-A6");
//		listMAC.add("08-00-27-C6-94-DB");
//		listMAC.add("08-00-27-5F-99-FA");
//		listMAC.add("08-00-27-73-BA-11");
//		listMAC.add("08-00-27-75-A9-CA");
//		listMAC.add("08-00-27-6C-ED-06");

		if (cont >= 2) {

			for (String mac : listMAC) { // 12

				String ip;
				System.out.println("Iniciando restauração de configuração");
				/* start counting */
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS");
				Date hora = Calendar.getInstance().getTime(); // Ou
																// qualquer
																// outra
																// forma
																// que
																// tem
				String dataFormatada = sdf.format(hora);

				System.out.println(">>>>Data: " + dataFormatada);

				try {
					Gateway gateway;

					System.out.println(">>>>>>>>>>>>>>>POINT 01");
					System.out.println(">>>>>>>>>>>>>>>Value MAC: " + mac);
					gateway = bundlerDBService.listBundlerInstalled(mac);
					System.out.println(">>>>>>>>>>>>>>>POINT 02");
					ip = gateway.getIp();

					if (gateway != null) {
						/* preparing information to send */
						System.out.println(">>>>>>>>>>>>>>>POINT 03");
						GatewayCommunication gatewayCommunicationSend = new GatewayCommunication();
						System.out.println(">>>>>>>>>>>>>>>POINT 04");
						for (BundlerInstalled bundler : gateway.getListBundlerInstalled()) {
							/*
							 * Simulation of sending bundles that are not basic
							 */
							System.out.println("Location find: " + bundler.getId().getBundler().getLocation());

							if (bundler.getId().getBundler().getLocation().equals(
									"mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.jersey-core/1.11_1")
									|| bundler.getId().getBundler().getLocation()
											.equals("mvn:org.apache.felix/org.apache.felix.coordinator/1.0.0")
									|| bundler.getId().getBundler().getLocation()
											.equals("mvn:org.apache.karaf.jaas/org.apache.karaf.jaas.modules/4.1.4")) {

								System.out.println(">>>>>>>>>>Entrou na montagem da mensagem de envio...");
								System.out.println(
										">>>>>>>>>>Location: " + bundler.getId().getBundler().getLocation() + "...");

								/* Assembly of shipping information */
								BundlerCommunication bundlerCommunicationSend = new BundlerCommunication();

								bundlerCommunicationSend.setLocation(bundler.getId().getBundler().getLocation());
								bundlerCommunicationSend.setStatus(bundler.getStatus());

								//gatewayCommunicationSend.getListBundler().add(bundlerCommunicationSend);
							}
						}

						/*
						 * Assembly of shipping information - simulation
						 */

						List<String> listBundles = new ArrayList<String>();
						listBundles.add(
								"mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.jersey-core/1.11_1");
						listBundles.add("file:/home/gateway/jars/org.apache.felix.coordinator-1.0.0.jar");
						listBundles.add("file:/home/gateway/jars/org.apache.karaf.jaas.modules-4.1.4.jar");
						listBundles.add("file:/home/gateway/jars/jclouds-compute-2.0.3.jar");
						listBundles.add("file:/home/gateway/jars/commons-cli-1.4.jar");
						listBundles.add("file:/home/gateway/jars/metrics-core-4.0.2.jar");
						listBundles.add("file:/home/gateway/jars/jsr305-3.0.2.jar");
						listBundles.add("file:/home/gateway/jars/akka-actor_2.12-2.5.9.jar");
						listBundles.add("file:/home/gateway/jars/scala-actors-2.11.12.jar");
						listBundles.add("file:/home/gateway/jars/jcommander-1.72.jar");
						listBundles.add("file:/home/gateway/jars/ph-datetime-9.0.0.jar");
						listBundles.add("file:/home/gateway/jars/joda-time-2.9.9.jar");
						listBundles.add("file:/home/gateway/jars/time4j-core-4.34.jar");
						listBundles.add("file:/home/gateway/jars/jsoup-1.11.2.jar");
						listBundles.add("file:/home/gateway/jars/incremental-compiler-0.13.15.jar");
						listBundles.add("file:/home/gateway/jars/mockito-core-2.13.0.jar");
						listBundles.add("file:/home/gateway/jars/easymock-3.5.1.jar");
						listBundles.add("file:/home/gateway/jars/powermock-module-junit4-2.0.0-beta.5.jar");
						// 18 bundles
						listBundles.add("file:/home/gateway/jars/android-4.1.1.4.jar");
						listBundles.add("file:/home/gateway/jars/blended.testsupport-2.4.0-RC2.jar");
						listBundles.add("file:/home/gateway/jars/byteman-4.0.0.jar");
						listBundles.add("file:/home/gateway/jars/google-api-services-pubsub-v1-rev374-1.23.0.jar");
						listBundles.add("file:/home/gateway/jars/guava-23.6-jre.jar");
						listBundles.add("file:/home/gateway/jars/infinispan-embedded-9.1.5.Final.jar");
						listBundles.add("file:/home/gateway/jars/jjwk-0.10.0.jar");
						listBundles.add("file:/home/gateway/jars/log4j-core-2.10.0.jar");
						listBundles.add("file:/home/gateway/jars/logback-core-1.3.0-alpha2.jar");
						listBundles.add("file:/home/gateway/jars/mangooio-core-4.10.0.jar");
						listBundles.add("file:/home/gateway/jars/mapdb-3.0.5.jar");
						listBundles.add("file:/home/gateway/jars/pdfbox-2.0.8.jar");
						listBundles.add("file:/home/gateway/jars/play-ws_2.12-2.6.11.jar");
						listBundles.add("file:/home/gateway/jars/mckoisqldb-1.0.5.jar");
						// 32 bundles
						listBundles.add("file:/home/gateway/jars/abcl-1.0.1.jar");
						listBundles.add("file:/home/gateway/jars/bsf-2.4.0.jar");
						listBundles.add("file:/home/gateway/jars/bsf-api-3.1.jar");
						listBundles.add("file:/home/gateway/jars/bsh-2.0b5.jar");
						listBundles.add("file:/home/gateway/jars/ceylon.language-1.3.3.jar");
						listBundles.add("file:/home/gateway/jars/clojure-1.10.0-alpha2.jar");
						listBundles.add("file:/home/gateway/jars/composite-logging-jcl-3.3.jar");
						listBundles.add("file:/home/gateway/jars/fscript-2.1.3.jar");
						listBundles.add("file:/home/gateway/jars/golo-2.1.0.jar");
						listBundles.add("file:/home/gateway/jars/gosu-core-api-1.14.7.jar");
						listBundles.add("file:/home/gateway/jars/hamcrest-core-1.3.jar");
						listBundles.add("file:/home/gateway/jars/janino-3.0.8.jar");
						listBundles.add("file:/home/gateway/jars/jruby-complete-9.1.15.0.jar");
						listBundles.add("file:/home/gateway/jars/kotlin-stdlib-1.2.21.jar");
						listBundles.add("file:/home/gateway/jars/latte-compiler-0.0.9.1-ALPHA.jar");
						listBundles.add("file:/home/gateway/jars/leola-0.10.2.jar");
						listBundles.add("file:/home/gateway/jars/lua4j-core-0.0.1.jar");
						listBundles.add("file:/home/gateway/jars/lua4j-interpreter-0.0.1.jar");
						listBundles.add("file:/home/gateway/jars/pnuts-1.2.jar");
						listBundles.add("file:/home/gateway/jars/rhino-1.7.8.jar");
						listBundles.add("file:/home/gateway/jars/scala-library-2.13.0-M3.jar");
						listBundles.add("file:/home/gateway/jars/spring-2.5.6.SEC03.jar");
						listBundles.add("file:/home/gateway/jars/spring-aop-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-beans-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-context-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-core-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-orm-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-test-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-web-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/spring-webmvc-5.0.3.RELEASE.jar");
						listBundles.add("file:/home/gateway/jars/sterling-1.0-alpha-4.jar");
						listBundles.add("file:/home/gateway/jars/stupid-0.1.0.jar");
						// 64 bundles
						
						for (String bundler : listBundles) {
							BundlerCommunication bundlerCommunicationSend = new BundlerCommunication();

							bundlerCommunicationSend.setLocation(bundler);
							bundlerCommunicationSend.setStatus("Stop");

							gatewayCommunicationSend.getListBundler().add(bundlerCommunicationSend);
						}

						System.out.println(
								">>>>>>>>>>URL para envio: http://" + ip + ":8181/cxf/recover/restoreconfiguration");

						/* add time */
						gatewayCommunicationSend.setDescription(dataFormatada);

						this.sendConfiguration("http://" + ip + ":8181/cxf/recover/restoreconfiguration",
								gatewayCommunicationSend);

						System.out.println(">>>>>>>>>>Restauração enviada com sucesso...");
					}
				} catch (Exception e) {
					System.out.println("Failed to send configuration...\n");
					e.printStackTrace();
				}

			}
		} else {
			cont++;
			System.out.println(">>>>>>Incrementation CONT.");
		}

	}

	public void sendRestoreConfiguration(String mac) {

	}

	// public void sendRestoreConfiguration(String mac) {
	// // "08-00-27-CC-68-B6";
	// String ip;
	// System.out.println("Iniciando restauração de configuração");
	// try {
	// Gateway gateway;
	//
	// System.out.println(">>>>>>>>>>>>>>>POINT 01");
	// System.out.println(">>>>>>>>>>>>>>>Value MAC: " + mac);
	// gateway = bundlerDBService.listBundlerInstalled(mac);
	// System.out.println(">>>>>>>>>>>>>>>POINT 02");
	// ip = gateway.getIp();
	//
	// if (gateway != null) {
	// /* preparing information to send */
	// System.out.println(">>>>>>>>>>>>>>>POINT 03");
	// GatewayCommunication gatewayCommunicationSend = new
	// GatewayCommunication();
	// System.out.println(">>>>>>>>>>>>>>>POINT 04");
	// for (BundlerInstalled bundler : gateway.getListBundlerInstalled()) {
	// /* Simulation of sending bundles that are not basic */
	// System.out.println("Location find: " +
	// bundler.getId().getBundler().getLocation());
	//
	// if (bundler.getId().getBundler().getLocation().equals(
	// "mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.jersey-core/1.11_1")
	// || bundler.getId().getBundler().getLocation()
	// .equals("mvn:org.apache.felix/org.apache.felix.coordinator/1.0.0")
	// || bundler.getId().getBundler().getLocation()
	// .equals("mvn:org.apache.karaf.jaas/org.apache.karaf.jaas.modules/4.1.4"))
	// {
	//
	// System.out.println(">>>>>>>>>>Entrou na montagem da mensagem de
	// envio...");
	// System.out.println(">>>>>>>>>>Location: " +
	// bundler.getId().getBundler().getLocation() + "...");
	//
	// /* Assembly of shipping information */
	// BundlerCommunication bundlerCommunicationSend = new
	// BundlerCommunication();
	//
	// bundlerCommunicationSend.setLocation(bundler.getId().getBundler().getLocation());
	// bundlerCommunicationSend.setStatus(bundler.getStatus());
	//
	// gatewayCommunicationSend.getListBundler().add(bundlerCommunicationSend);
	// }
	// }
	// COLOCAR CONDICIONAL CASO NÃO SEJA ENCONTRADO NENHUM BUNDLE PARA
	// RESTAURAÇÃO
	// System.out.println(">>>>>>>>>>URL para envio: http://" + ip +
	// ":8181/cxf/recover/restoreconfiguration");
	// this.sendConfiguration("http://" + ip +
	// ":8181/cxf/recover/restoreconfiguration",
	// gatewayCommunicationSend);
	//
	// System.out.println(">>>>>>>>>>Restauração enviada com sucesso...");
	// }
	// } catch (Exception e) {
	// System.out.println("Failed to send configuration...\n");
	// e.printStackTrace();
	// }
	// }

	private boolean sendConfiguration(String url, GatewayCommunication gatewayCommunication) throws Exception {

		String output = null;

		try {
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);

			Gson gson = new Gson();

			String jsonInString = gson.toJson(gatewayCommunication);
			
			System.out.println("\n"+jsonInString+"\n");

			mPost.setRequestEntity(new StringRequestEntity(jsonInString, null, null));

			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			// output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			// if (output.equals("true")) {
			// return true;
			// }
		} catch (Exception e) {
			throw new Exception("Exception in adding bucket : " + e);
		}

		return false;

	}

}
