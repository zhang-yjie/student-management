package com.example.importassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan("com.example.importassistant.dao")
@SpringBootApplication
@EnableWebSecurity
public class ImportAssistantApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImportAssistantApplication.class, args);
  }

}
