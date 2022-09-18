package com.example.birdwikibot.telegram.command;

import lombok.Getter;

@Getter
public enum CommandNames {
    RANDOM_COMMAND("/random", "Случайная птица"),
    SUBSCRIPTION_MENU("/subscription", "Ежедневная птица"),
    SUBSCRIBE_COMMAND("/subscribe", "Подписаться"),
    UNSUBSCRIBE_COMMAND("/unsubscribe", "Отписаться");

    private final String id;
    private final String key;

    CommandNames(String id, String key) {
        this.id = id;
        this.key = key;
    }
}