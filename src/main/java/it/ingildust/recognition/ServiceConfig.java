package it.ingildust.recognition;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import it.ingildust.recognition.service.RecognitionService;
import it.ingildust.recognition.service.impl.RecognitionServiceImpl;


@Configuration
public class ServiceConfig {
 
	// service configuration class
	
	
    @Bean
    @Primary
    public RecognitionService customerService() {
        return new RecognitionServiceImpl();
    }
    
    
}