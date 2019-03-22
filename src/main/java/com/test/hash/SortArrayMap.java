package com.test.hash;

import com.sun.glass.ui.Size;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wujc
 * @ClassName SortArrayMap
 * @Description: TODO
 * @create 2019-03-21
 */
public class SortArrayMap {
    //核心数组
    private Node[] buckets;
    private static final int DEFAULT_SIZE = 10;
    //数组大小
    private int size = 0;

    public SortArrayMap() {
        buckets = new Node[DEFAULT_SIZE];
    }

    /**
     * @MethodsName: add
     * @Description: 写入数据
     * @date: 2019-3-21 10:14
     * @param key
     * @param value
     * @return:
     */
    public void add(Long key, String value) {
        checkSize(size + 1);
        Node node = new Node(key, value);
        buckets[size++] = node;
    }

    /**
     * @MethodsName: checkSize
     * @Description: 检验是否需要扩容
     * @date: 2019-3-21 10:16
     * @param size
     * @return:
     */
    private void checkSize(int size) {
        if (size >= buckets.length) {
            //扩容自身的3/2
            int oldLen = buckets.length;
            int newLen = oldLen + (oldLen >> 1);
            buckets = Arrays.copyOf(buckets, newLen);
        }
    }

    public void sort() {
        Arrays.sort(buckets, 0, size, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.key > o2.key) {
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }

    /**
     * @MethodsName: firstNodeValue
     * @Description: 顺时针取出数据
     * @date: 2019-3-21 10:20
     * @param key
     * @return:
     */
    public String firstNodeValue(long key) {
        if (size == 0) {
            return null;
        }
        for (Node bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            if (bucket.key >= key) {
                return bucket.value;
            }
        }
        return buckets[0].value;
    }

}
