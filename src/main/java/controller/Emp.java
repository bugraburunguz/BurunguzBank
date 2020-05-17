package controller;

import java.io.Serializable;

public class Emp implements Serializable {
    private static final long serialversionUID =
            129348938L;
    static int a;
    static int b;
    static String name;
    int age;

    // Default constructor
    public Emp(String name, int age, int a, int b) {
        Emp.name = name;
        this.age = age;
        Emp.a = a;
        Emp.b = b;
    }

}
