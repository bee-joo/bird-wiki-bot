package com.example.birdwikibot.telegram.command.service;

import com.example.birdwikibot.service.BotService;
import com.example.birdwikibot.telegram.command.SuperCommand;
import com.example.birdwikibot.telegram.keyboard.ReplyKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class RandomCommand extends SuperCommand {

    public RandomCommand(BotService botService, ReplyKeyboard replyKeyboard) {
        super(botService, replyKeyboard);
    }

    @Override
    public SendMessage handle(String chatId) {
        String text = botService.randomLinkText();

        SendMessage answer = new SendMessage(chatId, text);
        answer.enableMarkdown(true);
        answer.setReplyMarkup(replyKeyboard.getMainReplyKeyboard());

        return answer;
    }
}
