package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.ufba.dcc.wiser.fot.manager.model.Service;

@Embeddable
public class ServiceProvidedId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private BundlerInstalled bundleInstalled;

	@ManyToOne
	private Service service;

	public ServiceProvidedId() {

	}

	public ServiceProvidedId(BundlerInstalled bundlerInstalled, Service service) {
		this.bundleInstalled = bundlerInstalled;
		this.service = service;
	}

	public BundlerInstalled getBundleInstalled() {
		return bundleInstalled;
	}

	public Service getService() {
		return service;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundleInstalled == null) ? 0 : bundleInstalled.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceProvidedId other = (ServiceProvidedId) obj;
		if (bundleInstalled == null) {
			if (other.bundleInstalled != null)
				return false;
		} else if (!bundleInstalled.equals(other.bundleInstalled))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}

}
