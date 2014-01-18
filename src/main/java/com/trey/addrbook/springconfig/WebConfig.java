package com.trey.addrbook.springconfig;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.trey.addrbook")
public class WebConfig extends WebMvcConfigurerAdapter {

	// TODO refactor to remove web.xml

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource bean = new DriverManagerDataSource();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUsername("root");
		bean.setPassword("");
		bean.setUrl("jdbc:mysql://localhost:3306/addressbook");
		return bean;
	}

}
