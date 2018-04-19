package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

/**
 * Class for representation of relationships in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "gateway_status")
public class GatewayStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private double baterryLevel;
	private long totalMemory;
	private long usedMemory;
	private long freeMemory;
	private double usedProcessor;
	private double freeProcessor;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@Id
	@ManyToOne
	private Gateway gateway;

	public double getBaterryLevel() {
		return baterryLevel;
	}

	public void setBaterryLevel(double baterryLevel) {
		this.baterryLevel = baterryLevel;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getUsedProcessor() {
		return usedProcessor;
	}

	public void setUsedProcessor(double usedProcessor) {
		this.usedProcessor = usedProcessor;
	}

	public double getFreeProcessor() {
		return freeProcessor;
	}

	public void setFreeProcessor(double freeProcessor) {
		this.freeProcessor = freeProcessor;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baterryLevel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (freeMemory ^ (freeMemory >>> 32));
		temp = Double.doubleToLongBits(freeProcessor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + (int) (totalMemory ^ (totalMemory >>> 32));
		result = prime * result + (int) (usedMemory ^ (usedMemory >>> 32));
		temp = Double.doubleToLongBits(usedProcessor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GatewayStatus other = (GatewayStatus) obj;
		if (Double.doubleToLongBits(baterryLevel) != Double.doubleToLongBits(other.baterryLevel))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (freeMemory != other.freeMemory)
			return false;
		if (Double.doubleToLongBits(freeProcessor) != Double.doubleToLongBits(other.freeProcessor))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (totalMemory != other.totalMemory)
			return false;
		if (usedMemory != other.usedMemory)
			return false;
		if (Double.doubleToLongBits(usedProcessor) != Double.doubleToLongBits(other.usedProcessor))
			return false;
		return true;
	}

}
