package com.kodlamaio.common;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kodlamaio.common.utilities.mapping.ModelMapperManager;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;

@SpringBootApplication
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}
	@Bean  
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
