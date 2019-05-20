package at.kaismi.java8features;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    public static void main(String args[]) {
        System.out.println("Stream 1:");
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

        println("Stream 2:");
        Stream.of("a1", "a2", "a3").filter(s -> s.startsWith("a1")).findFirst().ifPresent(System.out::println);  // a1

        println("Stream 3:");
        IntStream.range(1, 4).forEach(System.out::println);

        println("Stream 4:");
        Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1)      // 3, 5, 7
                .average().ifPresent(System.out::println);

        println("Stream 5:");
        Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
                .ifPresent(System.out::println);

        println("Stream 6:");
        Stream.of("a1", "a2", "a3").map(s -> {
            String result = null;
            if (s.equals("a1")) {
                result = "1a";
            }

            if (s.equals("a2")) {
                result = "2a";
            }

            return result;
        }).findAny().ifPresent(System.out::println);

        println("Stream 7:");
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        }).anyMatch(s -> {
            System.out.println("anyMatch: " + s);
            return s.startsWith("A");
        });

        println("Stream 8:");
        Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1, s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        }).filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("a");
        }).map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach: " + s));

        println("Stream 8.1:");
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("a");
        }).sorted((s1, s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        }).map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach: " + s));

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        println("Stream 9:");
        streamSupplier.get().anyMatch(s -> s.equals("a2"));   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok

        println("Stream 10:");
        Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
            System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
            return true;
        }).map(s -> {
            System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
            return s.toUpperCase();
        }).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));

        println("Stream 11:");
        IntStream.range(1, 10000).parallel().filter(n -> {
            // System.out.println("Processing " + n + " " + Thread.currentThread().getName());
            return (n % 2) == 0;
        }).average().ifPresent(System.out::println);

        println("Stream 12:");
        List<Animal> animals = Arrays.asList(new Animal("Cat", 18), new Animal("Dog", 23), new Animal("Duck", 24),
                new Animal("Elephant", 12));

        List<Animal> filtered = animals.stream().filter(a -> a.name.startsWith("D")).collect(Collectors.toList());
        System.out.println(filtered);

        println("Stream 13:");
        Map<Integer, List<Animal>> animalsByAge = animals.stream().collect(Collectors.groupingBy(p -> p.age));

        animalsByAge.forEach((age, a) -> System.out.format("age %s: %s\n", age, a));

        println("Stream 14:");
        Map<Integer, String> map = animals.stream()
                .collect(Collectors.toMap(a -> a.age, a -> a.name, (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);

        println("Stream 15:");
        Collector<Animal, StringJoiner, String> animalNameCollector = Collector
                .of(() -> new StringJoiner(" | "),          // supplier
                        (j, a) -> j.add(a.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = animals.stream().collect(animalNameCollector);
        System.out.println(names);

        Supplier<StringJoiner> joinerSupplier = () -> new StringJoiner(" | ");

        println("Stream 16:");
        animals.stream().reduce((a1, a2) -> a1.age > a2.age ? a1 : a2).ifPresent(System.out::println);

        println("Stream 17:");
        animals.parallelStream().reduce(0, (sum, a) -> {
            System.out.format("accumulator: sum=%s; animal=%s [%s]\n", sum, a, Thread.currentThread().getName());
            return sum += a.age;
        }, (sum1, sum2) -> {
            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
            return sum1 + sum2;
        });

        println("Stream 18:");
        Animal result = animals.stream().reduce(new Animal("", 0), (a1, a2) -> {
            a1.age += a2.age;
            a1.name += a2.name;
            return a1;
        });
        System.out.println(result);

        println("Stream 19:");
        Integer ageSum = animals.stream().reduce(0, (sum, a) -> sum += a.age, (sum1, sum2) -> sum1 + sum2);
        System.out.println(ageSum);

        println("Stream 20:");
        Integer ageSum2 = animals.parallelStream().reduce(0, (sum, a) -> {
            System.out.format("accumulator: sum=%s; animal=%s\n", sum, a);
            return sum += a.age;
        }, (sum1, sum2) -> {
            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
            return sum1 + sum2;
        });
        System.out.println(ageSum2);

    }

    private static void println(String msg) {
        System.out.println("\n" + msg);
    }

    private static class Animal {

        String name;
        int age;

        Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override public String toString() {
            return name;
        }

        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Animal animal = (Animal)o;

            if (age != animal.age)
                return false;
            return name != null ? name.equals(animal.name) : animal.name == null;
        }

        @Override public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    }

}
