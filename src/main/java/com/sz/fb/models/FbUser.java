package com.sz.fb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FbUser {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	public FbUser() {
	}

	@JsonIgnore
	public FbUser(String id) {
		super();
		this.setId(id);
	}

	@JsonIgnore
	public FbUser(String id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
