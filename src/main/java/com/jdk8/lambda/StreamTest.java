package com.jdk8.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xingkong
 * @date 2022/5/28 15:00
 */
public class StreamTest {
    public static void main(String[] args) {
        //stream的静态方法：of(),iterate(),generate()
        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.forEach(System.out::print);

        System.out.println();
        //iterate
        Stream<Integer> stream1 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream1.forEach(System.out::print);

        System.out.println();
        //generate
        Stream<Double> stream2 = Stream.generate(Math::random).limit(3);
        stream2.forEach(System.out::println);

        List<Dog> list = new ArrayList<>();
        list.add(new Dog("zhangsan", 2, "male"));
        list.add(new Dog("lisi", 1, "male"));
        list.add(new Dog("zhanglong", 3, "female"));
        list.add(new Dog("zhaowu", 6, "female"));
        list.add(new Dog("wangda", 5, "male"));
        list.add(new Dog("alisa", 8, "female"));

        list.stream().forEach(System.out::println);
        //匹配第一个
        Optional<Dog> findList = list.stream().findFirst();

        //匹配任意
        Optional<Dog> findAny = list.stream().findAny();

        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(e -> e.getAge() == 2);
        System.out.println("匹配第一只狗：" + findList.get());
        System.out.println("匹配任意一个狗" + findAny.get());
        System.out.println("是否存在年龄等于2的狗：" + anyMatch);

        list.stream().filter(e -> e.getAge() > 7).forEach(System.out::println);

        list.stream().map(Dog::getName).forEach(System.out::println);
        list.stream().map(e -> e.getAge() + 2).forEach(System.out::println);

        List<String> list1 = Arrays.asList("a-b-c-d", "1-2-3-4");
        List<String> newList = list1.stream().flatMap(e->{
            //将原集合的单个元素拆分成一个数组
            String[] split = e.split("-");
            //将数组中的元素转换成一个Stream
            Stream<String> e2 = Arrays.stream(split);
            return e2;
        }).collect(Collectors.toList());

        System.out.println("处理之前的集合：" + list1);
        System.out.println("处理之后的集合：" + newList);

        List<Integer> list2 = Arrays.asList(32, 33, 55, 44, 24, 64, 44);
        Optional<Integer> min = list2.stream().min(Comparator.comparing(Integer::intValue));
        System.out.println(min.get());

        boolean match = StreamUtils.anyMatch(list, e -> e.getAge() > 1);

        Map<String, String> toMap = StreamUtils.toMap(list, e -> e.getName(), e -> e.getName());

        toMap.forEach((k,v)->{
            System.out.println(k + "----->" + v);
        });

        List<Dog> dogs = StreamUtils.filters(list, e -> e.getName().equals("11"), e -> e.getAge() > 2);

        list.stream().forEach(dog->{
            System.out.println(dog.getSex());
        });

    }
}
