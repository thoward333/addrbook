package com.trey.addrbook.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = { "com.trey.addrbook.service", "com.trey.addrbook.dao" })
public class RootConfig {

}
