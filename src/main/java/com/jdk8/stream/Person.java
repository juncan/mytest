package com.jdk8.stream;

/**
 * @author wujc
 * @ClassName Person
 * @Description: TODO
 * @create 2019-06-07
 */
public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
