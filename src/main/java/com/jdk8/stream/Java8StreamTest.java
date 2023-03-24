package com.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/3/7 15:46
 */
public class Java8StreamTest {

    public static class Book{

        private String id;

        private String name;

        public Book(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void testUnique(){
        List<Book> books = Lists.newArrayList(new Book("1","1"),new Book("2","2"),new Book("3","3"),new Book("2","6"));

        //使用TreeSet去重
        List<Book> unique1 = books.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))),
                        ArrayList::new));

        System.out.println(unique1);

        //使用map去重
        List<Book> unique2 = books.stream()
                .filter(distinctByKey(o -> o.getId()))
                .collect(Collectors.toList());
        System.out.println(unique2);

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        System.out.println("这个函数将应用到每一个item");
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        testUnique();
    }

}
