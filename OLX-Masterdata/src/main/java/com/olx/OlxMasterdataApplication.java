package com.olx;

import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class OlxMasterdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMasterdataApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelPpaer() {
		return new ModelMapper();
	}
	
	
	@Bean
	public Docket getCustomizedDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
				.paths(PathSelectors.any())
				.build();
		
		
		
	}
	
	public ApiInfo getApiInfo() {
		
		return new ApiInfo("Olx Masterdata REST API Documentation",
			       "Olx Masterdata REST API documentation released by Zensar Ltd.",
			       "2.2",
			       "http://zensar.com",
			       new Contact("Ranganath", "http://ranganath.com", "ranganath@test.com"),
			       "GPL",
			       "http://gpl.com", new ArrayList<>());
	}

}
