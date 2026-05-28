package com.factory.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableFeignClients
public class ApiGatewayApplication {

    public static void main(String[] args) {
        loadDotenv();
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    private static void loadDotenv() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            if (System.getProperty(key) == null && System.getenv(key) == null) {
                System.setProperty(key, entry.getValue());
            }
        });
    }
}