package com.dinoelnirgihc.emeraldbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EmeraldBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmeraldBotApplication.class, args);
    }

}
