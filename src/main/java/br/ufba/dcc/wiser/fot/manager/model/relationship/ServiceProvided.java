package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Class for representation of relationships in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity(name = "service_provided")
public class ServiceProvided implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceProvidedId id;

	@ManyToMany
	@JoinTable(name = "bundler_used")
	private List<BundlerInstalled> bundlerUsed = new ArrayList<BundlerInstalled>();

	public ServiceProvidedId getId() {
		return id;
	}

	public void setId(ServiceProvidedId id) {
		this.id = id;
	}

	public List<BundlerInstalled> getBundlerUsed() {
		return bundlerUsed;
	}

	public void setBundlerUsed(List<BundlerInstalled> bundlerUsed) {
		this.bundlerUsed = bundlerUsed;
	}

}
