package com.example.birdwikibot.telegram.handler;

import com.example.birdwikibot.telegram.command.*;
import com.example.birdwikibot.telegram.keyboard.ReplyKeyboard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class MessageHandler {

    private final ReplyKeyboard replyKeyboard;
    private final RandomCommand randomCommand;
    private final SubscriptionMenu subscriptionMenu;
    private final SubscribeCommand subscribeCommand;
    private final UnsubscribeCommand unsubscribeCommand;

    public SendMessage router(String route, String chatId) {
        String text;

        if (route.equals("/start")) {
            text = "Привет! Я просто присылаю ссылку на Wikipedia со случайной птицей";
            return getSimpleMessage(chatId, text);

        } else if (route.equals(CommandNames.RANDOM_COMMAND.getId()) || route.equals(CommandNames.RANDOM_COMMAND.getKey())) {
            return randomCommand.handle(chatId);

        } else if (route.equals(CommandNames.SUBSCRIPTION_MENU.getId()) || route.equals(CommandNames.SUBSCRIPTION_MENU.getKey())) {
            return subscriptionMenu.handle(chatId);

        } else if (route.equals(CommandNames.SUBSCRIBE_COMMAND.getId()) || route.equals(CommandNames.SUBSCRIBE_COMMAND.getKey())) {
            return subscribeCommand.handle(chatId);

        } else if (route.equals(CommandNames.UNSUBSCRIBE_COMMAND.getId()) || route.equals(CommandNames.UNSUBSCRIBE_COMMAND.getKey())) {
            return unsubscribeCommand.handle(chatId);

        } else if (route.equals("Назад")) {
            text = "Привет ещё раз!";
            return getSimpleMessage(chatId, text);

        } else {
            text = "Я не понял ваше желание";
            return getSimpleMessage(chatId, text);
        }
    }

    public SendMessage getSimpleMessage(String chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(replyKeyboard.getMainReplyKeyboard());

        return message;
    }
}
