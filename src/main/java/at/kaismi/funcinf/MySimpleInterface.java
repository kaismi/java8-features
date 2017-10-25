package at.kaismi.funcinf;

@FunctionalInterface public interface MySimpleInterface<T> {

    void doIt(T t);

    default void doIt2(T t) {
        System.out.println(t);
    }

    // void doIt2(T t);
}
