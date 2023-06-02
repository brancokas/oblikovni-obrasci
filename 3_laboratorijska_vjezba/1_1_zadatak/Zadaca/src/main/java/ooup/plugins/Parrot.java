package ooup.plugins;

import ooup.Animal;

public class Parrot extends Animal {
    private String name;

    public Parrot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String greet() {
        return "dagadadagada";
    }

    @Override
    public String menu() {
        return "meat";
    }
}
