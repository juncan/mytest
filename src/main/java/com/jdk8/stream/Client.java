package com.jdk8.stream;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-06-07
 */
public class Client {
    public static void main(String[] args) {
        /*List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter",23),
                new Person("Pamela",23),
                new Person("David",12)
        );
        List<Person> filtered = persons.stream().filter(obj -> obj.name.startsWith("P")).collect(Collectors.toList());
        System.out.println(filtered);

        Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);

        IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);

        String phrase = persons.stream().filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        System.out.println(phrase);

        Collector<Person, StringJoiner, String> personNameCollector = Collector.of(
                () -> new StringJoiner(" | "),
                (j,p)->j.add(p.name.toUpperCase()),
                (j1,j2)->j1.merge(j2),
                StringJoiner::toString
        );

        String names = persons.stream().collect(personNameCollector);
        System.out.println(names);*/


        List<Foo> foos = new ArrayList<>();

        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

        foos.forEach(f -> IntStream.range(1, 4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));

        foos.stream().flatMap(f->f.bars.stream())
                .forEach(b-> System.out.println(b.name));

        IntStream.range(1,4)
                .mapToObj(i->new Foo("Foo"+i))
                .peek(f->IntStream.range(1,4)
                .mapToObj(i->new Bar("Bar"+i+"<-" + f.name))
                .forEach(f.bars::add))
                .flatMap(f->f.bars.stream())
                .forEach(b-> System.out.println(b.name));

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
        
    }
}
