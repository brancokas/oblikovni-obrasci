package ooup;

public abstract class Animal {
    public abstract String name();

    public abstract String greet();

    public abstract String menu();

    public void animalPrintGreeting() {
		System.out.println(name() + " says " + greet());
    }

    public void animalPrintMenu() {
		System.out.println(name() + " eats " + menu());
    }

}
