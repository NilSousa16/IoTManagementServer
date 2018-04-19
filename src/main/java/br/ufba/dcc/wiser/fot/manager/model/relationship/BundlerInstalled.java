package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class for representation of relationships in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "bundler_installed")
public class BundlerInstalled implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BundlerInstalledId id;

	private String status;

	@OneToMany(mappedBy = "id.bundleInstalled", fetch = FetchType.LAZY)
	private List<ServiceProvided> serviceProvided;
	
	@ManyToMany(mappedBy = "bundlerUsed")
	private List<ServiceProvided> serviceUsed;

	public BundlerInstalledId getId() {
		return id;
	}

	public void setId(BundlerInstalledId id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ServiceProvided> getServiceProvided() {
		return serviceProvided;
	}

	public void setServiceProvided(List<ServiceProvided> serviceProvided) {
		this.serviceProvided = serviceProvided;
	}
	
	public List<ServiceProvided> getServiceUsed() {
		return serviceUsed;
	}

	public void setServiceUsed(List<ServiceProvided> serviceUsed) {
		this.serviceUsed = serviceUsed;
	}

}
