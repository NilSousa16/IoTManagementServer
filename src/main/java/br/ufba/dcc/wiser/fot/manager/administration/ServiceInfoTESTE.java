package br.ufba.dcc.wiser.fot.manager.administration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ServiceInfoTESTE {

	BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public void listNames() {
		System.out.println(">>>>>>>BundleContext: " + bundleContext != null);
		Map<String, Integer> serviceNames = getServiceNamesMap(bundleContext);
		ArrayList<String> serviceNamesList = new ArrayList<String>(serviceNames.keySet());
		Collections.sort(serviceNamesList);
		System.out.println("####################3");
		for (String name : serviceNamesList) {
			System.out.println(name + " (" + serviceNames.get(name) + ")");
		}
		System.out.println("####################3");
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
