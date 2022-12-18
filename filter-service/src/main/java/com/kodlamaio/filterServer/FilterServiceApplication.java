package com.kodlamaio.filterServer;

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
=======
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import com.kodlamaio.common.utilities.mapping.ModelMapperManager;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;

@SpringBootApplication
@EnableDiscoveryClient
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4
public class FilterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterServiceApplication.class, args);
	}
<<<<<<< HEAD
=======
	
	 @Bean
	    public ModelMapper getModelMapper() {
	        return new ModelMapper();
	    }

	    @Bean
	    public ModelMapperService getModelMapperService(ModelMapper mapper) {
	        return new ModelMapperManager(mapper);
	    }
	    
//	    @Bean
//	    public StringJsonMessageConverter jsonConverter() {
//	        return new StringJsonMessageConverter();
//	    }
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4

}
