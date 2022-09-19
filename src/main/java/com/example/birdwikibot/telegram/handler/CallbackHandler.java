package com.example.birdwikibot.telegram.handler;

import com.example.birdwikibot.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@AllArgsConstructor
public class CallbackHandler {

    private final BotService botService;

    public AnswerCallbackQuery handleSubscription(CallbackQuery callbackQuery) {
        String text;
        String chatId = callbackQuery.getMessage().getChatId().toString();
        String time = callbackQuery.getData();

        if (botService.isUserSubscriber(chatId)) {
            botService.unsubscribeUser(chatId);
            text = "Время изменено!";
        } else {
            text = "Подписка оформлена!";
        }

        botService.subscribeUser(time, chatId);

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setText(text);

        return answerCallbackQuery;
    }

    public EditMessageText editCallback(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        Integer messageId = callbackQuery.getMessage().getMessageId();

        EditMessageText editMessageText = new EditMessageText("Отлично!");
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);

        return editMessageText;
    }
}
