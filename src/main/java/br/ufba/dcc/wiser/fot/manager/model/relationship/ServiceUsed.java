package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "service_used")
public class ServiceUsed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private BundlerInstalled bundleInstalled;

	@Id
	@ManyToOne
	private ServiceProvided serviceProvided;

	public BundlerInstalled getBundleInstalled() {
		return bundleInstalled;
	}

	public void setBundleInstalled(BundlerInstalled bundleInstalled) {
		this.bundleInstalled = bundleInstalled;
	}

	public ServiceProvided getServiceProvided() {
		return serviceProvided;
	}

	public void setServiceProvided(ServiceProvided serviceProvided) {
		this.serviceProvided = serviceProvided;
	}

}
