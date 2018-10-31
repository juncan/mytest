package com.cbf4life.adapter;

import java.util.Map;

/**
 * @author wujc
 * @ClassName IOuterUser
 * @Description: 外系统的人员信息
 * @create 2018-07-15 23:04
 */
@SuppressWarnings("all")
public interface IOuterUser {
    //基本信息，比如名称，性别，手机号码了等
    public Map getUserBaseInfo();

    //工作区域信息
    public Map getUserOfficeInfo();

    //用户的家庭信息
    public Map getUserHomeInfo();


}
