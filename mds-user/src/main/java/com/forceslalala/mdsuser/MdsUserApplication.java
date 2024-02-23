package com.forceslalala.mdsuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MdsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdsUserApplication.class, args);
    }

}
