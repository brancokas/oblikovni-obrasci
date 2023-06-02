package ooup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {
    public static Animal newInstance(String animalKind, String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<Animal> clazz = null;
        clazz = (Class<Animal>) Class.forName("ooup.plugins." + animalKind);

        Constructor<?> constructor = clazz.getConstructor(String.class);
        Animal animal = (Animal) constructor.newInstance(name);

        return animal;
    }
}
