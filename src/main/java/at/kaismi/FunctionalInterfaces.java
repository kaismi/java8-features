package at.kaismi;

import at.kaismi.funcinf.MySimpleConsumer;
import at.kaismi.funcinf.MySimpleInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class FunctionalInterfaces {

    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("Print even numbers:");
        eval(list, n -> true);

        println("Print even numbers:");
        eval(list, n -> n % 2 == 0);

        println("Print numbers greater than 2:");
        eval(list, n -> n > 2);

        println("String predicate:");
        List<String> listString = Arrays.asList("Karl", "Peter");
        evalString(listString, n -> n.equals("Karl"));

        println("Simple supplier:");
        supply(() -> "Seppl");

        println("Simple boolean supplier:");
        supplyBoolean(() -> list.contains(1));

        println("Runnable:");
        Thread thread = new Thread(() -> System.out.println("Runnable"));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("Runnable2:");
        Thread thread2 = new Thread(() -> {
            System.out.println("Runnable");
            System.out.println("Runnable2");
        });
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("BooleanSupplier:");
        BooleanSupplier booleanSupplier = () -> {
            String test = "test";
            return test.equals("test");
        };
        System.out.println(booleanSupplier.getAsBoolean());

        println("ToInt:");
        toInt(Integer::valueOf, "1");

        println("Consume:");
        consume(System.out::println, "Test consume");

        println("Consume complex:");
        consumeComplex(n -> n.doInit(), new MyComplexType());

        println("Runnable3:");
        Runnable r = () -> {
            System.out.println("test1");
            System.out.println("test2");
        };

        Thread thread1 = new Thread(r);
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("MySimpleInterface:");
        MySimpleInterface<String> mySimpleOperation = n -> {
            System.out.println("test3 " + n);
        };

        mySimpleOperation.doIt("gogogog");
        mySimpleOperation.doIt2("hhhh");

        println("String consumer:");
        Consumer<String> stringConsumer = n -> {
            System.out.println(n);
            System.out.println(n + "2");
        };

        stringConsumer.accept("kkkkk");

        println("MySimpleConsumer:");
        MySimpleConsumer<String> mySimpleConsumer = (n, b) -> {
            if (b) {
                System.out.println(n);
            }
        };

        mySimpleConsumer.accept("myConsumer", true);
        mySimpleConsumer.accept("myConsumer1", false);

        println("Comparator:");
        Comparator<String> c = (a, b) -> Integer.compare(a.length(), b.length());
        Comparator<String> c2 = (a, b) -> -1;

        int testResult = c.compare("test11", "test1");
        System.out.println(testResult);
    }

    private static void println(String msg) {
        System.out.println("\n" + msg);
    }

    public static class MyBaseType {

        public void doInit() {
            System.out.println("Init");
        }
    }

    public static class MyComplexType extends MyBaseType {

        public void doSomething() {
            System.out.println("Go");
        }

        @Override public void doInit() {
            System.out.println("Init Override");
        }
    }

    private static void consumeComplex(Consumer<? super MyBaseType> consumer, MyComplexType myComplexType) {
        consumer.accept(myComplexType);
    }

    private static void consume(Consumer<String> consumer, String acceptString) {
        consumer.accept(acceptString);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {

            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    private static void evalString(List<String> list, Predicate<String> predicate) {
        for (String n : list) {

            if (predicate.negate().or(a -> true).negate().test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    private static void supply(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    private static void supplyBoolean(BooleanSupplier supplier) {
        System.out.println(supplier.getAsBoolean());
    }

    private static void toInt(ToIntFunction<String> toIntFunction, String value) {
        System.out.println(toIntFunction.applyAsInt(value));
    }
}
