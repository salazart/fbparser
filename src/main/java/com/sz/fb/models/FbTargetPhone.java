package com.sz.fb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "FB_TARGET_PHONE" )
public class FbTargetPhone implements IEntity{
	@Id
	@Column(name = "phone")
	private String id;
	
	@Column(name = "isUsed")
	private boolean  isUsed;
	
	public FbTargetPhone() {
	}
	
	public FbTargetPhone(String phone, boolean isUsed) {
		super();
		this.id = phone;
		this.isUsed = isUsed;
	}
	
	public String toString(){
		return getId() + " " + isUsed();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}
