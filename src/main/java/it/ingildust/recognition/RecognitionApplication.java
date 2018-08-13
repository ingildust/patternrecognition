package it.ingildust.recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.ingildust")
public class RecognitionApplication {
	
	
	// main springboot class

	public static void main(String[] args) {
		SpringApplication.run(RecognitionApplication.class, args);
	}
}
