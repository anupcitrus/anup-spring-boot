package com.anup.shopapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShopApiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(ShopApiApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Shop API Application...");
        
        try {
            ConfigurableApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
            logger.info("Shop API Application started successfully!");
            logger.info("Application is running on port: {}", 
                context.getEnvironment().getProperty("server.port", "8080"));
            logger.info("API Base URL: http://localhost:{}/api/shops", 
                context.getEnvironment().getProperty("server.port", "8080"));
                
        } catch (Exception e) {
            logger.error("Failed to start Shop API Application: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
}
