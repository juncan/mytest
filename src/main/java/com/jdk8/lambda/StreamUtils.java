package com.jdk8.lambda;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xingkong
 * @date 2022/5/28 15:56
 */
public class StreamUtils {
    /**
     * 集合为空，创建空的Stream流；否则创建集合的Stream流
     * 避免出现空指针
     *
     * @param collection 集合
     * @param <T>   集合元素的泛型
     * @return Stream对象
     */
    private static <T> Stream<T> streamOf(Collection<T> collection) {
        return CollectionUtils.isEmpty(collection) ? Stream.empty() : collection.stream();
    }

    /**
     * 按照映射规则映射成一个新的集合流
     *
     * @param list  集合
     * @param mapper   集合属性元素
     * @param <T>   函数输入类型的泛型
     * @param <R>   函数结果类型的泛型
     * @return  新的集合
     */
    public static <T, R> List<R> mapList(List<T> list, Function<? super T, ? extends R> mapper) {
        return streamOf(list).map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据给定的条件进行筛选，将符合条件的元素提取成新的流
     *
     * @param list          集合
     * @param predicate     筛选规则
     * @param <T>           流元素的类型
     * @return  符合条件的流集合
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        return streamOf(list).filter(predicate).collect(Collectors.toList());
    }

    /**
     * 根据给定的条件进行筛选，将符合条件的元素提取成新的流
     *
     * @param list          集合
     * @param predicates    多个筛选条件
     * @param <T>           流元素的类型
     * @return 符合条件的流集合
     */
    @SafeVarargs
    public static <T> List<T> filters(List<T> list, Predicate<? super T> ... predicates) {
        Stream<T> stream = streamOf(list);
        for (Predicate<? super T> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * 根据指定元素对集合进行升序排序
     *
     * @param list  集合
     * @param keyExtractor  用来排序的元素
     * @param <T>   函数输入类型的泛型
     * @param <U>   函数结果类型的泛型
     * @return  排序后的集合
     */
    public static <T, U extends Comparable<? super U>> List<T> sorted(
            List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return streamOf(list).sorted(Comparator.comparing(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * 根据指定元素对集合进行升序排序
     *
     * @param list  集合
     * @param keyExtractor  用来排序的元素
     * @param limit     排序后集合中保留的数量
     * @param <T>   函数输入类型的泛型
     * @param <U>   函数结果类型的泛型
     * @return  排序后的集合
     */
    public static <T, U extends Comparable<? super U>> List<T> sorted(
            List<T> list, Function<? super T, ? extends U> keyExtractor, Integer limit) {
        return streamOf(list).sorted(Comparator.comparing(keyExtractor)).limit(limit).collect(Collectors.toList());
    }

    /**
     * 根据指定元素对集合进行降序排序
     *
     * @param list  集合
     * @param keyExtractor  用来排序的元素
     * @param <T>   函数输入类型的泛型
     * @param <U>   函数结果类型的泛型
     * @return  排序后的集合
     */
    public static <T, U extends Comparable<? super U>> List<T> sortedDesc(
            List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return streamOf(list).sorted(Comparator.comparing(keyExtractor).reversed()).collect(Collectors.toList());
    }

    /**
     *根据规则判断元素是否匹配
     *
     * @param list  集合
     * @param predicate 匹配规则
     * @param <T>   元素类型
     * @return  匹配结果
     */
    public static <T> boolean anyMatch(List<T> list, Predicate<? super T> predicate) {
        return streamOf(list).anyMatch(predicate);
    }

    /**
     * 将List集合转换成Map集合,同一个key时对value进行去重，保留第一个出现的value值
     *
     * @param list  集合
     * @param keyMapper     新的Map中的key
     * @param <T>   参数的类型
     * @return  转换后的Map集合   <key, T>
     */
    public static <T, K> Map< K, T> toMapDistinctFirst(List<T> list, Function<? super T, ? extends K> keyMapper) {
        return streamOf(list).collect(Collectors.toMap(keyMapper, Function.identity(), (key1, key2) -> key1));
    }

    /**
     * 将List集合转换成Map集合,同一个key时对value进行去重，保留最后出现的value值
     *
     * @param list  集合
     * @param keyMapper     新的Map中的key
     * @param <T>   参数的类型
     * @return  转换后的Map集合   <key, T>
     */
    public static <T, K> Map<K, T> toMapDistinctLast(List<T> list, Function<? super T, ? extends K> keyMapper) {
        return streamOf(list).collect(Collectors.toMap(keyMapper, Function.identity(), (key1, key2) -> key2));
    }

    /**
     * 将List转换为指定key->value键值对元素的Map集合
     * @param list  集合
     * @param keyMapper     Map的key元素
     * @param valueMapper   Map的value元素
     * @param <T>   函数输入的类型
     * @param <K>   函数输出的类型
     * @param <U>   函数输出的类型
     * @return  转换后的Map集合   <key, value>
     */
    public static <T, K, U> Map<K, U> toMap(List<T> list, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper) {
        return streamOf(list).collect(Collectors.toMap(keyMapper, valueMapper));
    }
}

