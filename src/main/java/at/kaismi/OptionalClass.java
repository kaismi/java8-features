package at.kaismi;

import java.util.Objects;
import java.util.Optional;

public class OptionalClass {

    public static void main(String args[]) {
        System.out.println("Optional 1:");
        Optional<Integer> value1 = Optional.empty();
        Optional<Integer> value2 = Optional.of(10);

        int sum = sum(value1, value2);
        System.out.println(sum);

        println("Optional 2:");
        int sum2 = sum2(null, 15);
        System.out.println(sum2);

        println("Optional 3:");
        int sum3 = sum3(null, null);
        System.out.println(sum2);
    }

    private static int sum(Optional<Integer> a, Optional<Integer> b) {
        return a.orElse(0) + b.orElse(0);
    }

    private static int sum2(Integer a, Integer b) {
        Objects.requireNonNull(b);
        Optional<Integer> aOptional = Optional.ofNullable(a);
        Optional<Integer> bOptional = Optional.ofNullable(b);

        return aOptional.orElse(0) + bOptional.orElse(0);
    }

    private static int sum3(Integer a, Integer b) {
        Optional<Integer> aOptional = Optional.ofNullable(a);
        Optional<Integer> bOptional = Optional.ofNullable(b);

        return aOptional.orElse(0) + bOptional.orElseThrow(() -> new UnsupportedOperationException());
    }

    private static void println(String msg) {
        System.out.println("\n" + msg);
    }

}
