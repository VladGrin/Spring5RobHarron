package org.example.init.method.annot;

import org.example.init.method.Singer;

public class SingerAnn {

    private static final String DEFAULT_NАМЕ = "Eric Clapton";
    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name ");
            name = DEFAULT_NАМЕ;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type " + Singer.class);
        }
    }

    @Override
    public String toString() {
        return "SingerAnnotation{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
