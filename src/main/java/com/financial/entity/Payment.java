package com.financial.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financial.entity.enums.StagePayment;

@Entity
public abstract class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private Integer stage;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	@JoinColumn(name = "request_id")
	private Request request;

	public Payment() {
	}

	public Payment(Long id, StagePayment stage, Request request) {
		this.id = id;
		this.stage = stage.getCod();
		this.request = request;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StagePayment getStage() {
		return StagePayment.toEnum(stage);
	}

	public void setStage(StagePayment stage) {
		this.stage = stage.getCod();
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
