package com.cbf4life.facade;

/**
 * @author wujc
 * @ClassName LetterProcess
 * @Description: 定义一个写信的过程
 * @create 2018-07-15 22:02
 */
public interface LetterProcess {
    //首先要写信的内容
    public void writeContext(String context);

    //其次写信封
    public void fillEnvelope(String address);

    //把信放到信封里
    public void letterIntoEnvelope();

    //然后邮递
    public void sendLetter();
}
