package com.Interview;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author xingkong
 * @date 2022/5/28 19:11
 */

class A{
    int add(int a, int b) {
        return a + b;
    }
}

class B{
    double add(int a, int b) {
        return a + b;
    }
}

public class Client {
    public static void main(String[] args) {
        System.out.println(new B().add(1,2));

        Client client = new Client();
        System.out.println(client.isEqual("test").test("test"));
    }

    public Predicate<String> isEqual(Object object) {
        return Predicate.isEqual(object);
    }
}
