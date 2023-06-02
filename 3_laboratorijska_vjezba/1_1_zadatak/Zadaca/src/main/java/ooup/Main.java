package ooup;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Animal[] animals = new Animal[10];

        for (int i = 0; i < animals.length; i++) {
            String type = getType();
            animals[i] = AnimalFactory.newInstance(type, "animal" + i);
            animals[i].animalPrintGreeting();;
            animals[i].animalPrintMenu();
        }



    }

    private static String getType() {
        Integer broj = new Random().nextInt();
        if (broj < 0) return "Tiger";
        return "Parrot";
    }
}
