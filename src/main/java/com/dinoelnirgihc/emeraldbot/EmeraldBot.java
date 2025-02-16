package com.dinoelnirgihc.emeraldbot;

import com.dinoelnirgihc.emeraldbot.configuration.EmeraldBotConfiguration;
import com.dinoelnirgihc.emeraldbot.model.EmeraldBotMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.dinoelnirgihc.emeraldbot.model.Text.*;
import static com.dinoelnirgihc.emeraldbot.model.Text.SYNTHETIC;
import static com.dinoelnirgihc.emeraldbot.model.UrlPhoto.*;


@Slf4j
@Component
public class EmeraldBot extends TelegramLongPollingBot
{
    final EmeraldBotConfiguration config;

    @Autowired
    public EmeraldBot(EmeraldBotConfiguration config, MessageSourceAutoConfiguration messageSourceAutoConfiguration)
    {
        super(config.getBotToken());
        this.config = config;
        try
        {
            this.execute(new SetMyCommands(EmeraldBotMenu.createMenu(), new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e)
        {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername()
    {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch(message)
            {
                    case "/start": startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                    case "/origin": originCommandReceived(chatId);
                    break;
                    case "/fields": fieldsCommandReceiver(chatId);
                    break;
                    case "/cutting": cuttingCommandReceived(chatId);
                    break;
                    case "/synthetic": syntheticCommandReceived(chatId);
                    break;
                default:sendMessage(chatId, "Back");
            }
        }
    }

    private void fieldsCommandReceiver(long chatId)
    {
        sendPhoto(chatId, FIELDSPH, FIELDS);
    }

   private void startCommandReceived(long chatId, String firstName)
    {
        String callback = "Привет, "  + firstName + "! Сегодня я расскажу все о изумрудах";
        log.info("Replied to user " + firstName);
        sendMessage(chatId, callback);
    }

    private void originCommandReceived(long chatId){

        sendPhoto(chatId, ORIGINPH, ORIGIN);
    }

    private void cuttingCommandReceived(long chatId){

        sendPhoto(chatId, CUTTINGPH, CUTTING);
    }

    private void syntheticCommandReceived(long chatId){

        sendPhoto(chatId, SYNTHETICPH, SYNTHETIC);
    }

    public void sendPhoto(long chatId, String photoAddress, String text)
    {
        SendPhoto sendPhoto = SendPhoto
                .builder()
                .chatId(chatId)
                .photo(new InputFile(photoAddress))
                .caption(text)
                .build();
        try
        {
            this.execute(sendPhoto);
        }
        catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }

    }



    public void sendMessage(long chatId, String textToSend) {

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(textToSend)
                .build();
        try {

            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());


        }
    }
}
