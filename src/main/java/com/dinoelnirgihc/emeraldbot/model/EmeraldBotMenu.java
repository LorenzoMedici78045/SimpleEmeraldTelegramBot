package com.dinoelnirgihc.emeraldbot.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmeraldBotMenu
{
    private static List<BotCommand> listOfCommands;

    public static List<BotCommand> createMenu()
    {
        listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Хочешь познакомится с миром изумрудов?"));
        listOfCommands.add(new BotCommand("/origin", "История камня"));
        listOfCommands.add(new BotCommand("/fields", "Месторождения камней"));
        listOfCommands.add(new BotCommand("/cutting", "Огранка"));
        listOfCommands.add(new BotCommand("/synthetic", "Искусственные камни"));
        return listOfCommands;
    }
}
