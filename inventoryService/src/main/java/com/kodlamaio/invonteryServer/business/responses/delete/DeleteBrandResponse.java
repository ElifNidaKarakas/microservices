package com.kodlamaio.invonteryServer.business.responses.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandResponse {
	
	private String id;
	private  String name;
}
