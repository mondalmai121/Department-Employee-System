package org.jsp.DE;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.jsp.DE"})
public class DeEmConfig {

	@Bean
	public EntityManager getManager() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
