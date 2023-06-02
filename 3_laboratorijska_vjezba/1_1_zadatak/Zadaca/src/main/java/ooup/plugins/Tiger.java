package ooup.plugins;

import ooup.Animal;

public class Tiger extends Animal {
    private String name;

    public Tiger(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String greet() {
        return "grauu";
    }

    @Override
    public String menu() {
        return "meat";
    }
}
