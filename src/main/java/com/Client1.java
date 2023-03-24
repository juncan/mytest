package com;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
//        Week dayOfWeek = LocalDateTimeUtil.dayOfWeek(LocalDate.of(2022, 9, 25));
//
//        testGPS();
        List<GoodsEntry> entryList = new ArrayList<>();
        GoodsEntry goodsEntry = new GoodsEntry();
        goodsEntry.setEntryTime(LocalDateTime.now());
        goodsEntry.setEntryPrice(BigDecimal.valueOf(30000L));
        entryList.add(goodsEntry);

        GoodsEntry goodsEntry1 = new GoodsEntry();
        goodsEntry1.setEntryTime(LocalDateTime.now());
        goodsEntry1.setEntryPrice(BigDecimal.valueOf(300L));
        entryList.add(goodsEntry1);

        List<List<GoodsEntry>> result = splitGoodsEntry(entryList);
        System.out.println(result);

        LocalDate today = LocalDate.now();
        String monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toString();
        String sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toString();

        String replace = StrUtil.replace("QX_XM_XCX  \t \r\n", "\\s*|\t|\r|\n",parameter -> parameter.replaceAll(""));
        String start = LocalDateTimeUtil.format(LocalDateTimeUtil.parse("2022-10-18 00:00:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateTimeFormatter.ofPattern("yyyyMMdd"));

        String replace1 = "2022-10-18 00:00:21".substring(0, 10).replace("-", "");

        System.out.println(monday);

        System.out.println(BigDecimal.ZERO.setScale(2));

        System.out.println(Convert.toLong("undefined", -1L) == -1L);

        System.out.println(BigDecimal.valueOf(3.5));

        List<DateTime> result11 = DateUtil.rangeToList(DateUtil.parse("2022-10-01", "yyyy-MM-dd"),
                DateUtil.parse("2022-12-01", "yyyy-MM-dd"), DateField.DAY_OF_YEAR);
        System.out.println(result11);


        Long supplySiteId = 0L;
        if(supplySiteId == null || supplySiteId == 0L){
            System.out.println("111");
        }

        String itemSn = "XDQXWXMFJFZS221018190956YCTIBDT5";
        

        System.out.println(itemSn.split(",")[0]);

        LocalDateTime mdd = Convert.toLocalDateTime("");
        System.out.println(mdd);

        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.randomInt(100000000,1000000000));
        }

        System.out.println(LocalDateTimeUtil.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        int i = 1;
        System.out.println(i++);
        i = 1;
        System.out.println(++i);
    }

    private static List<List<GoodsEntry>> splitGoodsEntry(List<GoodsEntry> entryList) {
        List<List<GoodsEntry>> allList = new ArrayList<>();
        //提现记录按最高提现金额分批生成结算单
        BigDecimal addPrice = BigDecimal.ZERO;
        List<GoodsEntry> partList = new ArrayList<>();
        for (GoodsEntry goodsEntry : entryList) {
            if (goodsEntry.getEntryPrice().compareTo(BigDecimal.valueOf(30000L)) > 0) {
                throw new RuntimeException("单笔金额超出提现最高额度");
            }
            addPrice = addPrice.add(goodsEntry.getEntryPrice());
            if (addPrice.compareTo(BigDecimal.valueOf(30000L)) > 0) {
                addPrice = goodsEntry.getEntryPrice();
                allList.add(partList);
                partList = new ArrayList<>();
            }
            partList.add(goodsEntry);
        }
        allList.add(partList);
        return allList;
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
