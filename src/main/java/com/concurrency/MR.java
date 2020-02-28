package com.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @author wujc
 * @ClassName MR
 * @Description: TODO
 * @create 2019-06-13
 */
public class MR extends RecursiveTask<Map<String, Long>> {

    private String[] fc;
    private int start, end;

    //构造函数
    MR(String[] fc, int fr, int to) {
        this.fc = fc;
        this.start = fr;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        }else{
            int mid = (start + end) / 2;
            MR mr1 = new MR(fc, start, mid);
            mr1.fork();
            MR mr2 = new MR(fc, mid, end);
            //计算子任务，并返回合并的结果
            return merge(mr2.compute(), mr1.join());
        }
    }

    //合并结果
    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>();
        result.putAll(r1);
        //合并结果
        r2.forEach((k,v)->{
            Long c = result.get(k);
            if (c != null) {
                result.put(k, c + v);
            }else{
                result.put(k, v);
            }
        });
        return result;
    }

    //统计单词数量
    private Map<String, Long> calc(String line) {
        Map<String, Long> result = new HashMap<>();
        //分割单词
        String[] words = line.split("\\s+");
        //统计单词数量
        for (String word : words) {
            Long v = result.get(word);
            if (v != null) {
                result.put(word, v + 1);
            }else{
                result.put(word, 1L);
            }
        }
        return result;
    }

}
