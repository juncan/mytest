package com.jdk8.stream;

/**
 * @author wujc
 * @ClassName Person
 * @Description: TODO
 * @create 2019-06-07
 */
public class Person {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
