package br.com.api.imobiliaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class Swagger {
	 @Bean
	    public Docket freteApi() {
	        return new Docket( DocumentationType.SWAGGER_2)
	        		 .select()
	                 .apis(RequestHandlerSelectors.basePackage("br.com.api.imobiliaria") )
	                 .paths(PathSelectors.ant("/**"))
	                 .build();

	    }
}
