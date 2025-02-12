package com.dinoelnirgihc.emeraldbot.configuration;

import com.dinoelnirgihc.emeraldbot.EmeraldBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class EmeraldBotInitializer
{
    private EmeraldBot emeraldBot;

    @Autowired
    public EmeraldBotInitializer(EmeraldBot service)
    {
        this.emeraldBot = service;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException
    {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try
        {
            telegramBotsApi.registerBot(emeraldBot);
        }
        catch(TelegramApiException e)
        {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
