package com.example.birdwikibot.telegram.command;

import lombok.Getter;

@Getter
public enum CommandNames {

    START_COMMAND("/start", "Начать"),
    RANDOM_COMMAND("/random", "Случайная птица"),
    SUBSCRIPTION_MENU("/subscription", "Ежедневная птица"),
    SUBSCRIBE_COMMAND("/subscribe", "Подписаться"),
    CHANGE_TIME_COMMAND("/changetime", "Изменить время"),
    BACK_COMMAND("/back", "Назад"),
    UNSUBSCRIBE_COMMAND("/unsubscribe", "Отписаться");

    private final String id;
    private final String key;

    CommandNames(String id, String key) {
        this.id = id;
        this.key = key;
    }
}