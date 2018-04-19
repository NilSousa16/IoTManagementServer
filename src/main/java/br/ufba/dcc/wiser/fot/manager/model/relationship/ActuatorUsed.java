package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Class for representation of relationships in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "actuator_used")
public class ActuatorUsed implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActuatorUsedId id;
	
	@Type(type = "true_false")
	private boolean status;

	public ActuatorUsedId getId() {
		return id;
	}

	public void setId(ActuatorUsedId id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
