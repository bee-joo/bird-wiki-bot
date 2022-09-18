package com.example.birdwikibot.telegram.command;

import com.example.birdwikibot.service.BotService;
import com.example.birdwikibot.telegram.keyboard.ReplyKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UnsubscribeCommand extends SuperCommand {

    public UnsubscribeCommand(BotService botService, ReplyKeyboard replyKeyboard) {
        super(botService, replyKeyboard);
    }

    @Override
    public SendMessage handle(String chatId) {
        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);

        String text;
        if (!botService.isUserSubscriber(chatId)) {
            text = "Вы ещё подписаны";
        } else {
            text = "Вы успешно отписались";
            botService.unsubscribeUser(chatId);
        }

        answer.setReplyMarkup(replyKeyboard.getMainReplyKeyboard());
        answer.setText(text);
        return answer;
    }
}
