package com.jdk8.lambda;

/**
 * @author wujc
 * @ClassName PersonFactory
 * @Description: TODO
 * @create 2019-06-07
 */
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
