package com.scms.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
	
	private String jwtToken;

	public AuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
	

}
