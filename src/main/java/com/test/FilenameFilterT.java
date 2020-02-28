package com.test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author wujc
 * @ClassName FilenameFilterT
 * @Description: TODO
 * @create 2019-06-06
 */
public class FilenameFilterT {
    public static void main(String[] args) {
        String dir = "src" + File.separator +
                "main"+File.separator+
                "java"+File.separator+
                "com" + File.separator +
                "test" + File.separator +
                "redis";
        File file = new File(dir);
        //创建过滤器文件
        MyFilter filter = new MyFilter("n.java");
        String[] files = file.list(filter);
        for (String s : files) {
            System.out.println(s);
        }
    }

    private static class MyFilter implements FilenameFilter {

        private String type;

        public MyFilter(String type) {
            this.type = type;
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(type);
        }
    }
}
