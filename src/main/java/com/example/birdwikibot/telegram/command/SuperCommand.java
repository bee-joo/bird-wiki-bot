package com.example.birdwikibot.telegram.command;

import com.example.birdwikibot.service.BotService;
import com.example.birdwikibot.telegram.keyboard.ReplyKeyboard;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public abstract class SuperCommand {
    protected final BotService botService;
    protected final ReplyKeyboard replyKeyboard;

    public abstract SendMessage handle(String chatId);
}
