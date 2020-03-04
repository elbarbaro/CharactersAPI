package com.barbaro.subirarchivo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.barbaro.subirarchivo.dao")
public class AppConfig {

}
