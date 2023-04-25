package com.shop.bike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//A java.util.function.Predicate
	final static Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage("com.shop.bike.admin.rest")
			.or(RequestHandlerSelectors.basePackage("com.shop.bike.consumer.rest"));
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(predicate)
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder().title("Dev bike shop").description("Dev bike shop api").build());
	}
}
