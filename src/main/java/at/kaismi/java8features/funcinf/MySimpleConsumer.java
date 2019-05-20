package at.kaismi.java8features.funcinf;

public interface MySimpleConsumer<T> {

    void accept(T t, boolean doItReally);

    // void accept2(T t, boolean doItReally);
}
