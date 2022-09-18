package com.example.birdwikibot.category;

public enum Categories {
    ASIA("Птицы_Азии"),
    CENTRAL_AMERICA("Птицы_Центральной_Америки"),
    EUROPE("Птицы_Европы"),
    ANTARCTICA("Птицы_Антарктики"),
    EURASIA("Птицы_Евразии"),
    SOUTH_AMERICA("Птицы_Южной_Америки"),
    NORTH_AMERICA("Птицы_Северной_Америки"),
    AFRICA("Птицы_Африки"),
    OCEANIA("Птицы_Океании"),
    DEAD("Вымершие_птицы");

    private final String name;

    Categories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
