package com.push.xiaomi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wujc
 * @ClassName DemoPush
 * @Description: 发送Demo
 * @create 2019-05-22
 */
public class DemoPush {
    public static void main(String[] args) {
        //绑定

        List<String> listAndroid =  new ArrayList<String>();
        listAndroid.add("iwJj9RfdKFul+LZIgMzDPvImaXElSln+1tnqWKtMMlSPPVxy7R5lA5XwGMF1R+n1");
        List<String> listIos = new ArrayList<String>();
        listIos.add("");
        try {
            XmPush.sendAndroidMessage(3, "通知", "内容", "http://pj.test.yoya.com/szxx/pages/kp/h5/score-task.html", listAndroid);
        } catch (Exception e) {
        }
    }
}
