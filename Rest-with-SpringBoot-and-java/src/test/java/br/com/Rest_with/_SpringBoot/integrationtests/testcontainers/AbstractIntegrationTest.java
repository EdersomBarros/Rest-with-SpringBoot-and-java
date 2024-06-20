package br.com.Rest_with._SpringBoot.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers =  AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
	
	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			
		}
		
	}
	
	

}
