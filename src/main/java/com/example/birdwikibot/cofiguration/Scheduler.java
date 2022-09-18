package com.example.birdwikibot.cofiguration;

import com.example.birdwikibot.telegram.BirdWikiBot;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {

    private final BirdWikiBot bot;

    public Scheduler(BirdWikiBot bot) {
        this.bot = bot;
    }

    @Scheduled(cron = "${time.schedule}", zone = "Europe/Moscow")
    public void doIt() {
        bot.sendScheduledMessage();
    }
}
