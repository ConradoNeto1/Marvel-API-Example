package br.eti.wagnermessias.marvelexample.entities;

import com.google.gson.annotations.SerializedName;

public class OriginalIssue{

	@SerializedName("name")
	private String name;

	@SerializedName("resourceURI")
	private String resourceURI;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}


}