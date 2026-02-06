package com.vj.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication
public class AddressserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressserviceApplication.class, args);
	}

}
