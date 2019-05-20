package at.kaismi.java8features;

public class DefaultMethods {

    public static void main(String args[]) {
        System.out.println("Default methods 1:");
        Vehicle vehicle = new Car();
        vehicle.print();
        vehicle.go();
        vehicle.go2();
    }

    public interface Vehicle {

        default void print() {
            System.out.println("I am a vehicle!");
        }

        static void blowHorn() {
            System.out.println("Blowing horn!!!");
        }

        default void go() {
            System.out.println("I am a four wheeler!");
        }

        default void go2() {
            System.out.println("I am a four wheeler!");
        }
    }

    public interface FourWheeler {

        default void print() {
            System.out.println("I am a four wheeler!");
        }

        default void go() {
            System.out.println("I am a four wheeler!");
        }
    }

    public static class Car implements Vehicle, FourWheeler {

        @Override public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("I am a car!");
        }

        @Override public void go() {
            System.out.println("go");
        }
    }
}
