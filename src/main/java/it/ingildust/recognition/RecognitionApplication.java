package it.ingildust.recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("it.ingildust")
@EnableSwagger2
public class RecognitionApplication {
	
	
	// main springboot class

	public static void main(String[] args) {
		SpringApplication.run(RecognitionApplication.class, args);
	}
	
	 @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.useDefaultResponseMessages(false)
	                .groupName("pattern recognition")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(Predicates.not(PathSelectors.regex("/error.*")))
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("pattern recognition")
	                .description("pattern recognition")
//	                .termsOfServiceUrl("http://github")
//	                .license("")
//	                .licenseUrl("https://github.com/")
	                .version("1.0-SNAPSHOT")
	                .build();
	    }
}
