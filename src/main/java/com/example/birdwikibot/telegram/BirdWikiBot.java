package com.example.birdwikibot.telegram;

import com.example.birdwikibot.service.BotService;
import com.example.birdwikibot.telegram.command.*;
import com.example.birdwikibot.telegram.handler.CallbackHandler;
import com.example.birdwikibot.telegram.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@Slf4j
public class BirdWikiBot extends TelegramLongPollingBot {

    @Value("${birdwikibot.username}")
    private String BOT_USERNAME;

    @Value("${birdwikibot.token}")
    private String BOT_TOKEN;

    private final RandomCommand randomCommand;
    private final CallbackHandler callbackHandler;
    private final MessageHandler messageHandler;
    private final BotService botService;

    public BirdWikiBot(RandomCommand randomCommand, CallbackHandler callbackHandler, MessageHandler messageHandler, BotService botService) {
        super();
        this.randomCommand = randomCommand;
        this.callbackHandler = callbackHandler;
        this.messageHandler = messageHandler;
        this.botService = botService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message message = update.getMessage();
                executeAsync(messageHandler.router(message.getText(), message.getChatId().toString()));

            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();

                executeAsync(messageHandler.getSimpleMessage(callbackQuery.getMessage().getChatId().toString(), "Что-нибудь ещё?"));
                executeAsync(callbackHandler.handleSubscription(callbackQuery));
            }
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }

    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void sendScheduledMessage() {
        int hour = LocalDateTime.now().getHour();
        Set<String> chatIds = botService.getUsersByTime(String.valueOf(hour));
        if (chatIds.isEmpty()) return;

        chatIds.forEach(id -> {
            try {
                executeAsync(randomCommand.handle(id));
            } catch (TelegramApiException e) {
                log.error(String.valueOf(e));
            }
        });
    }
}
