package com;

import com.core.utils.IdCardCheckUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-10-10 15:53
 */
public class Client {
    public static void main(String[] args) {
        String idCard = "56032119930512811X";
        //System.out.println(IdCardCheckUtil.IDCardValidate(idCard));
        /*System.out.println(Integer.parseInt("0"));
        System.out.println(getIP("pj.gray.yoya.com"));*/
        operateStream();

    }

    public static String getIP(String name){
        InetAddress address = null;
        try {
            address = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("获取失败");
        }
        return address.getHostAddress().toString();
    }

    public static void operateStream() {
        List<String> dlist = new ArrayList<>();
        dlist.add("java");
        dlist.add("jsp");
        dlist.add("Ios");
        System.out.println(dlist.stream().distinct().filter((x)->x.equals("jsp")).count());
    }
}
