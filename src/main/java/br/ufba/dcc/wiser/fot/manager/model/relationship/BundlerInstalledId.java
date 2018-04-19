package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;

/**
 * Class for representation of relationships in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Embeddable
public class BundlerInstalledId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Gateway gateway;

	@ManyToOne
	private Bundler bundler;

	public BundlerInstalledId() {

	}

	public BundlerInstalledId(Gateway gateway, Bundler bundler) {
		this.gateway = gateway;
		this.bundler = bundler;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Bundler getBundler() {
		return bundler;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundler == null) ? 0 : bundler.hashCode());
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
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
		BundlerInstalledId other = (BundlerInstalledId) obj;
		if (bundler == null) {
			if (other.bundler != null)
				return false;
		} else if (!bundler.equals(other.bundler))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		return true;
	}

}
