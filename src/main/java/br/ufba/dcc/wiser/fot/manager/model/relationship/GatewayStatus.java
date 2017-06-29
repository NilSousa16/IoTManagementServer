package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

@Entity
@Table(name = "gateway_status")
public class GatewayStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private int baterryLevel;
	private long totalMemory;
	private long usedMemory;
	private long freeMemory;
	private double usedProcessor;
	private double freeProcessor;

	@Id
	private Date date;

	@Id
	@ManyToOne
	private Gateway gateway;

	public int getBaterryLevel() {
		return baterryLevel;
	}

	public void setBaterryLevel(int baterryLevel) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

}
