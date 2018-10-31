package com.cbf4life.advance;

import com.cbf4life.factory.Human;

import java.util.HashMap;

/**
 * @author wujc
 * @ClassName HumanFactory
 * @Description: TODO
 * @create 2018-07-15 18:30
 */
@SuppressWarnings("all")
public class HumanFactory {
    //定义一个Map，初始化过的human对象都放在这里
    private static HashMap<String, Human> humans = new HashMap<String, Human>();

    //定义一个烤箱，泥巴塞进去，人就出来了，这个太先进了
    public static Human createHuman(Class c) {
        Human human = null; //定义一个类型的人类
        try {
            //如果MAP中有，则直接取出，不用初始化
            if (humans.containsKey(c.getSimpleName())) {
                human = humans.get(c.getSimpleName());
            } else {
                human = (Human) Class.forName(c.getName()).newInstance();
                //放到MAP里面
                humans.put(c.getSimpleName(), human);
            }
        } catch (InstantiationException e) { //你要是不说个人类颜色的话，没法烤，要白的黑的，你说了才好烤
            System.out.println("必须指定人类颜色");
        } catch (IllegalAccessException e) { //定义的人类有问题，那就烤不出来了
            System.out.println("人类定义错误！");
        } catch (ClassNotFoundException e) {
            System.out.println("混蛋，你指定的人类找不到");
        }
        return human;
    }

}
