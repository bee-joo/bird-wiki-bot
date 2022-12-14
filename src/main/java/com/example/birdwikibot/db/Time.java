package com.example.birdwikibot.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@RedisHash("Time")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Time {

    @Id
    private String time;
    private Set<String> chatIds;
}
