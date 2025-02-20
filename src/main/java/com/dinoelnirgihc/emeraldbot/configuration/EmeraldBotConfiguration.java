package com.dinoelnirgihc.emeraldbot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class EmeraldBotConfiguration
{
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.key}")
    private String botToken;
}
