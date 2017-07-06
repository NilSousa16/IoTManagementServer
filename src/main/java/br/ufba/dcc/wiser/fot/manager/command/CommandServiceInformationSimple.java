package br.ufba.dcc.wiser.fot.manager.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

@Command(scope = "ws", name = "serviceInformationSimple")
public class CommandServiceInformationSimple implements Action{

	BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public Object execute(CommandSession session) throws Exception {
		this.listNames();
		return null;
	}
	
	public void listNames() {
		System.out.println(">>>>>>>BundleContext: " + bundleContext != null);
		Map<String, Integer> serviceNames = getServiceNamesMap(bundleContext);
		ArrayList<String> serviceNamesList = new ArrayList<String>(serviceNames.keySet());
		Collections.sort(serviceNamesList);
		System.out.println("####################");
		for (String name : serviceNamesList) {
			System.out.println(name + " (" + serviceNames.get(name) + ")");
		}
		System.out.println("####################");
	}

	public static Map<String, Integer> getServiceNamesMap(BundleContext bundleContext) {
		Map<String, Integer> serviceNames = new HashMap<String, Integer>();
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			ServiceReference<?>[] services = bundle.getRegisteredServices();
			if (services != null) {
				for (ServiceReference<?> serviceReference : services) {
					String[] names = (String[]) serviceReference.getProperty(Constants.OBJECTCLASS);
					if (names != null) {
						for (String name : names) {
							int curCount = (serviceNames.containsKey(name)) ? serviceNames.get(name) : 0;
							serviceNames.put(name, curCount + 1);
						}
					}
				}
			}
		}
		return serviceNames;
	}

}
