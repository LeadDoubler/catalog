package com.blob.pas.agent;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.asap.catalog.dao.Component;

@Entity
@Table(name="agent_status")
public class AgentStatus extends Component implements java.io.Serializable  {

	/**
	 * No action with this job 
	 */
	public static final int STATUS_NOACTION = 0;
	
	/**
	 * Job is currently being executed
	 */
	public static final int STATUS_RUNNING = 1;
	
	private String clazz;
	
	private int status = STATUS_NOACTION;
	
	public AgentStatus() { }
	
	public AgentStatus(String clazz, int status) {
		super();
		this.clazz = clazz;
		this.status = status;
	}

	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	private static final long serialVersionUID = -3251678693678034656L;
	
	
}
