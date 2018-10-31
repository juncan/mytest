package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName Client
 * @Description: 我要关心我自己的公司了
 * @create 2018-08-05 21:48
 */
public class Client {
    public static void main(String[] args) {
        /*System.out.println("========房地产公司是这样子运行的。。。。");
        //先找到我的公司
        HouseCrop houseCrop = new HouseCrop();
        //看我怎么赚钱
        houseCrop.makeMoney();
        System.out.println("\n");

        System.out.println("=======服装公司是这样运行的========");
        ClothesCrop clothesCrop = new ClothesCrop();
        clothesCrop.makeMoney();
        System.out.println("\n");

        System.out.println("==========山赛公司是这样运行的===========");
        IPodCorp iPodCorp = new IPodCorp();
        iPodCorp.makeMoney();*/

        House house = new House();
        System.out.println("--------房地产公司是这样子运行的--------");
        //先找到我的公司
        UpHouseCrop upHouseCrop = new UpHouseCrop(house);
        upHouseCrop.makeMoney();
        System.out.println("\n");

        //山赛公司的产品很多，不过我只要指定产品就成了
        System.out.println("------山赛公司是这样子运行的---------");
        //ShanZhaiCrop shanZhaiCrop = new ShanZhaiCrop(new Clothes());
        ShanZhaiCrop shanZhaiCrop = new ShanZhaiCrop(new IPod());
        shanZhaiCrop.makeMoney();

    }
}
