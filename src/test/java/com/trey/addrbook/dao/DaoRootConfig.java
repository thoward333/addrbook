package com.trey.addrbook.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.trey.addrbook.bootstrap.DatabaseConfig;

/**
 * Bootstrap for service layer.
 * 
 * @author Trey
 */
@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = { "com.trey.addrbook.dao" })
public class DaoRootConfig {

}
