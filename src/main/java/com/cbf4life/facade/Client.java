package com.cbf4life.facade;

/**
 * @author wujc
 * @ClassName Client
 * @Description: 我开始给朋友写信了
 * @create 2018-07-15 22:07
 */
public class Client {
    public static void main(String[] args) {
        /*//创建一个处理信件的过程
        LetterProcess letterProcess = new LetterProcessImpl();
        //开始写信
        letterProcess.writeContext("hello,It's me,do you know who i am?I'm your lover. i'd like to ...");

        //开始写信封
        letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");

        //把信放到信封里，并封装好
        letterProcess.letterIntoEnvelope();

        //跑到邮局把信塞到邮箱，投递
        letterProcess.sendLetter();*/

        //现代化的邮局，有这项服务，邮局名称叫Hell Road
        ModenPostOffice helloRoadPostOffice = new ModenPostOffice();

        //你只要把信的内容和收信人地址给他，他会帮你完成一系列的工作
        String address = "Happy Road No. 666,God province,Heaven"; //定义一个地址
        String context = "hello，It's me,Do you know who i am?I'm your lover,i'd like to ....";
        helloRoadPostOffice.sendLetter(address,context);

    }
}
