package com.maersk.logexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class LogExporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogExporterApplication.class, args);
    }

}
