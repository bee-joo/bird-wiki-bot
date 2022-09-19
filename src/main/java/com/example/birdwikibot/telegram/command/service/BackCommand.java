package com.example.birdwikibot.telegram.command.service;

import com.example.birdwikibot.service.BotService;
import com.example.birdwikibot.telegram.command.SuperCommand;
import com.example.birdwikibot.telegram.keyboard.ReplyKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class BackCommand extends SuperCommand {

    public BackCommand(BotService botService, ReplyKeyboard replyKeyboard) {
        super(botService, replyKeyboard);
    }

    @Override
    public SendMessage handle(String chatId) {
        String text = "Привет ещё раз!";
        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(replyKeyboard.getMainReplyKeyboard());

        return message;
    }
}
