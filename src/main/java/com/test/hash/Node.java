package com.test.hash;

/**
 * @author wujc
 * @ClassName Node
 * @Description: TODO
 * @create 2019-03-21
 */
public class Node {
    public Long key;
    public String value;

    public Node(Long key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
