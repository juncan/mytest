package com.jdk8.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wujc
 * @ClassName Foo
 * @Description: TODO
 * @create 2019-06-07
 */
public class Foo {
    String name;
    List<Bar> bars = new ArrayList();

    Foo(String name) {
        this.name = name;
    }

}
