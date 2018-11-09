package com.test.weixin;

import com.test.weixin.util.WX_TemplateMsgUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-08 15:30
 */
public class Client {
    public static void main(String[] args) {
        List<String> openIds = new ArrayList<String>();
        openIds.add("oEouy0nAkmGGZtOM_3wBDWlbOt7k");
        Iterator<String> iter = openIds.iterator();
        for (String openId : openIds) {
            WX_TemplateMsgUtil.senMsg(openId);
        }
    }
}
