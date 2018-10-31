package com.cbf4life.facade;

/**
 * @author wujc
 * @ClassName ModenPostOffice
 * @Description: TODO
 * @create 2018-07-15 22:14
 */
public class ModenPostOffice {
    private LetterProcess letterProcess = new LetterProcessImpl();
    //private Police letterPolice = new Police();

    //写信，封装，投递，一体化
    public void sendLetter(String context, String address) {
        //帮你写信
        letterProcess.writeContext(context);

        //写好信封
        letterProcess.fillEnvelope(address);

        //警察要检查信封了
        //letterPolice.checkLetter(letterProcess);

        //把信放到信封中
        letterProcess.letterIntoEnvelope();

        //邮递信件
        letterProcess.sendLetter();
    }
}
