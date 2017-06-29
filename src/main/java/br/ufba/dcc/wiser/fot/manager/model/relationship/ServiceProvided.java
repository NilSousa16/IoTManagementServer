package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "service_provided")
public class ServiceProvided implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceProvidedId id;

	@OneToMany(mappedBy = "serviceProvided", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ServiceUsed> ServiceUsed;

	public ServiceProvidedId getId() {
		return id;
	}

	public void setId(ServiceProvidedId id) {
		this.id = id;
	}

	public List<ServiceUsed> getServiceUsed() {
		return ServiceUsed;
	}

	public void setServiceUsed(List<ServiceUsed> serviceUsed) {
		ServiceUsed = serviceUsed;
	}

}
