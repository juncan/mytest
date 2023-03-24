package com.jvm;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @author xingkong
 * @date 2022/10/3 11:43
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("the bootstrapLoader:" + bootstrapLoader);
        System.out.println("the extClassLoader:" + extClassLoader);
        System.out.println("the appClassLoader:" + appClassLoader);

        System.out.println();

        System.out.println("bootstrapLoader加载以下文件：");

        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}
