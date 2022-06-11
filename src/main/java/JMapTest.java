import cn.hutool.core.convert.Convert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: jmap工具的使用
 * @Author: binga
 * @Date: 2020/9/14 16:37
 * @Blog: https://blog.csdn.net/pang5356
 */
public class JMapTest {

    public static void main(String[] args) throws InterruptedException {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new User());
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    static class User {
    }
}
