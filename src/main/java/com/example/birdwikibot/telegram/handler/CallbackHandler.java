package com.example.birdwikibot.telegram.handler;

import com.example.birdwikibot.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@AllArgsConstructor
public class CallbackHandler {

    private final BotService botService;
    private final MessageHandler messageHandler;

    public AnswerCallbackQuery handleSubscription(CallbackQuery callbackQuery) {
        String text;
        String chatId = callbackQuery.getMessage().getChatId().toString();
        String time = callbackQuery.getData();

        if (botService.isUserSubscriber(chatId)) {
            text = "Вы уже подписаны";
        } else {
            botService.subscribeUser(time, chatId);
            text = "Подписка оформлена!";
        }

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setText(text);

        return answerCallbackQuery;
    }
}
