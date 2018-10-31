package com.cbf4life.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujc
 * @ClassName OuterUser
 * @Description: 外系统的用户信息的实现类
 * @create 2018-07-15 23:08
 */
@SuppressWarnings("all")
public class OuterUser implements IOuterUser {
    @Override
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王。。。");
        baseInfoMap.put("mobileNumber", "这个员工电话是。。。");
        return baseInfoMap;
    }

    @Override
    public Map getUserOfficeInfo() {
        HashMap officeInfo = new HashMap();

        officeInfo.put("jobPosition", "这个人的职位是Boss...");
        officeInfo.put("officeTelNumber", "员工的办公电话是。。。");

        return officeInfo;
    }

    @Override
    public Map getUserHomeInfo() {
        HashMap homeInfo = new HashMap();

        homeInfo.put("homeTelNumber", "员工的家庭电话是。。。");
        homeInfo.put("homeAddress", "员工的家庭住址是。。。");
        return homeInfo;
    }
}
