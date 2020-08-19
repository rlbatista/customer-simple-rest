package br.com.brasilprev.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import({BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)	.select()
														.apis(RequestHandlerSelectors.basePackage("br.com.brasilprev.controllers"))
														.paths(PathSelectors.any())
														.build()
														.apiInfo(this.apiInfo())
														.useDefaultResponseMessages(false);
	}

	// O bean abaixo visa resolver o seguinte problema aque acontece na inicialização do springboot:
	// Parameter 0 of method linkDiscoverers in org.springframework.hateoas.config.HateoasConfiguration required a single bean, but 17 were found
	@Bean
	public LinkDiscoverers discovers() {
		return new LinkDiscoverers(SimplePluginRegistry.create(Arrays.asList(new CollectionJsonLinkDiscoverer())));
	}
	
	@Bean
	public UiConfiguration uiConfig() {
	    return
	    	UiConfigurationBuilder
	    		.builder()
	            .operationsSorter(OperationsSorter.METHOD)
	            .build();
	}
	
	private ApiInfo apiInfo() {
		return
			new ApiInfoBuilder()
				.title("Backend REST Java")
				.description("Test for Hexadata/BrasilPrev")
				.contact(new Contact("Renato Lopes Batista", null, "rlbatistasp@gmail.com"))
			.build();
	}
}
