package com.example.birdwikibot.category;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomCategory {
    private static final List<Categories> values = Arrays.asList(Categories.values());
    private static final int size = values.size();
    private static final Random random = new Random();

    public static String getRandomCategory() {
        return values.get(random.nextInt(size)).getName();
    }

}
