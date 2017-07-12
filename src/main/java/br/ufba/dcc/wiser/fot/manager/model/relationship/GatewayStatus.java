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

}
