package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class ShoppingApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingApiApplication.class);

    @Bean
    public SystemStartShutdownHandler startShutdownHandler() {
        return new SystemStartShutdownHandler();
    }

    public static void main(String[] args) {
        logger.info("Starting network node");
        SpringApplication.run(ShoppingApiApplication.class, args);
        logger.info("Started");
    }

    private static class SystemStartShutdownHandler {
        @PostConstruct
        public void startup() {
            logger.info("node server start up");
        }

        @PreDestroy
        public void shutdown() {
            logger.info("node server shut down");
        }
    }

}