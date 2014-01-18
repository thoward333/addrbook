package com.trey.addrbook.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Bootstrap for service layer.
 * 
 * @author Trey
 */
@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = { "com.trey.addrbook.service", "com.trey.addrbook.dao", "com.trey.addrbook.util" })
public class RootConfig {

}
