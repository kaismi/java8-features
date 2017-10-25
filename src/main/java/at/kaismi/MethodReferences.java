package at.kaismi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MethodReferences {

    public static void main(String args[]) {
        System.out.println("Method refs 1:");
        List<String> names = new ArrayList<>();

        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");

        names.forEach(System.out::println);

        Consumer<String> myConsumer1 = (n) -> {
            String result = n + "!";
            System.out.println(result);
        };

        Consumer<String> myConsumer2 = (n) -> {
            String result = n + "!!!";
            System.out.println(result);
        };

        println("Method refs 2:");
        names.forEach(myConsumer1.andThen(myConsumer2).andThen((n) -> System.out.println("finished")));

        println("Method refs 3:");
        names.forEach((n) -> {
            String result = n + "!";
            System.out.println(result);
        });
    }

    private static void println(String msg) {
        System.out.println("\n" + msg);
    }
}
