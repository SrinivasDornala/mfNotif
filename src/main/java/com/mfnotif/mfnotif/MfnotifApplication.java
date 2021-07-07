package com.mfnotif.mfnotif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfnotif.cache.LoadCache;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mfnotif.*"})
@EntityScan("com.mfnotif.*")
@EnableJpaRepositories(basePackages = "com.mfnotif.*")
public class MfnotifApplication {

	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.driver-class-name}")
	String driver;
	@Value("${spring.datasource.password}")
	String password;

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(username);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setDriverClassName(driver);
		return driverManagerDataSource;
	}

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(MfnotifApplication.class, args);
		LoadCache.loadNotifications();
	}

}
