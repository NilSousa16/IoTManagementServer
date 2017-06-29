package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bundler_installed")
public class BundlerInstalled implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BundlerInstalledId id;

	private boolean status;

	@OneToMany(mappedBy = "id.bundleInstalled", fetch = FetchType.LAZY)
	private List<ServiceProvided> serviceProvided;

	@OneToMany(mappedBy = "bundleInstalled", fetch = FetchType.LAZY)
	private List<ServiceUsed> ServiceUsed;

	public BundlerInstalledId getId() {
		return id;
	}

	public void setId(BundlerInstalledId id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<ServiceProvided> getServiceProvided() {
		return serviceProvided;
	}

	public void setServiceProvided(List<ServiceProvided> serviceProvided) {
		this.serviceProvided = serviceProvided;
	}

	public List<ServiceUsed> getServiceUsed() {
		return ServiceUsed;
	}

	public void setServiceUsed(List<ServiceUsed> serviceUsed) {
		ServiceUsed = serviceUsed;
	}

}
