package com.chaos.fission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.chaos.fission.config.UploadPropertis;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({UploadPropertis.class})
public class Fission4BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fission4BaseApplication.class, args);
	}
}
