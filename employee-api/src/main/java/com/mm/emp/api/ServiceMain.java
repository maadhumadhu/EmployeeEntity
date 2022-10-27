package com.mm.emp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.mm.emp.api","com.mm.emp","com.mm.emp.dal","com.mm.emp.dal.repository","com.mm.emp.model"})
@EntityScan(basePackages = {"com.mm.emp.model","com.mm.emp.dal.dao","com.mm.emp.dal.repository"})
public class ServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMain.class,args);
		System.out.println("APP STARTED");
    }
}
