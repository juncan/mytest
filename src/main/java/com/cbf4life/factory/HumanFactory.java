package com.cbf4life.factory;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

/**
 * @author wujc
 * @ClassName HumanFactory
 * @Description: 今天讲女娲造人的故事，这个故事梗概是这样的
 * 很久很久以前，盘古开辟了天地，用身躯造出日月星辰，山川草木，天地一片繁华
 * one day，女娲下界走了一遭，哎！太寂寞，太孤独了，没个会笑的，会哭的，会说话的东西
 * 那怎么办呢？不用愁，女娲，神仙呀，造出来呀，然后捏泥巴，放八卦炉（后来这个成了太白金星的宝贝）
 * 中烤，于是就有了人
 * 我们把这个生产人的过程用java程序表现出来
 * @create 2018-07-15 16:00
 */
public class HumanFactory {

    //定义一个烤箱，泥巴塞进去，人就出来了，这个太先进了
    public static Human createHuman(Class c) {
        Human human = null; //定义一个类型的人类

        try {
            human = (Human) Class.forName(c.getName()).newInstance(); //产生一个人类
        } catch (InstantiationException e) { //你要是不说个人类颜色的话，没法烤，要白的要黑的，你说话了才好烤
            System.out.println("必须指定人类的颜色");
        } catch (IllegalAccessException e) { //定义的人类有问题，那就烤不出来，这是 。。。。
            System.out.println("人类定义错误！");
        }catch(ClassNotFoundException e){ //你随便说个人类，我到哪里给你制造区？
            System.out.println("混蛋，你指定的人类找不到!");
        }
        return human;
    }

    //女娲生气了，把一团泥巴塞到八卦炉，爱产生咋人类就咋人类
    public static Human createHuman() throws URISyntaxException {
        Human human = null; //定义一个类型的人类

        //首先是获得有多少个实现类，多少个人类
        List<Class> concreateHumanList = ClassUtils.getAllClassByInterface(Human.class);//定义了多少人类

        //八卦炉自己开始想烧什么人就什么人
        Random random = new Random();
        int rand = random.nextInt(concreateHumanList.size());

        human = createHuman(concreateHumanList.get(rand));
        return human;
    }
}
