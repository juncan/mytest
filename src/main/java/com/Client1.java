package com;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.Week;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.time.LocalDate;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2022/9/18 19:44
 */
public class Client1 {
    public static void main(String[] args) throws InterruptedException {
//        while (true) {
//            Thread.sleep(1000);
//            System.out.println(123);
//        }
        Week dayOfWeek = LocalDateTimeUtil.dayOfWeek(LocalDate.of(2022, 9, 25));

        testGPS();
    }

    public static void testGPS() {
        //给定两个坐标系,计算两点相差距离
        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);
        //Sphere坐标的计算结果
        double meter1 =getDistanceMeter(source,target, Ellipsoid.Sphere);
        //WGS84坐标系计算结果
        double meter2 = getDistanceMeter(source,target,Ellipsoid.WGS84);


        //计算结果Sphere 坐标系的计算结果与 WGS84坐标系的计算结果存在几十米的误差，不同的坐标系精度不同,
        System.out.println("Sphere 坐标系的计算结果是" + meter1 + "M");
        System.out.println("wGS84  坐标系的计算结果是" + meter2 + "M");
    }

    public static  double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){
        //创建GeodeticCalculator,调用计算方法,传入坐标系,经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }
}
