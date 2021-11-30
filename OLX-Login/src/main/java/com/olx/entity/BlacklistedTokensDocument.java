package com.olx.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="blacklistedtokens")
public class BlacklistedTokensDocument {

	@Id
	private BigInteger id;	
	private String token;

	public BlacklistedTokensDocument() {}

	public BlacklistedTokensDocument(BigInteger id, String token) {
		this.id = id;
		this.token = token;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "BlacklistedTokensDocument [id=" + id + ", token=" + token + "]";
	}

	
	
	
}
