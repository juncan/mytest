package com.cbf4life.chain.change;

import com.cbf4life.chain.IWomen;

/**
 * @author wujc
 * @ClassName Woman
 * @Description: TODO
 * @create 2018-08-26 22:19
 */
public class Woman implements IWomen {
    /**
     * 通过一个int类型的参数来描述妇女个人状况
     * 1----未出嫁
     * 2-----出嫁
     * 3-----夫死
     *
     * @return
     */
    private int type = 0;

    //妇女的请示
    private String request = "";

    //构造函数传递过来请求
    public Woman(int _type, String _request) {
        this.type = _type;
        //为了显示好看点，我在这里做点处理
        switch (this.type) {
            case 1:
                this.request = "女儿的请求是：" + _request;
                break;
            case 2:
                this.request = "妻子的请求是：" + _request;
                break;
            case 3:
                this.request = "母亲的请求是：" + _request;
                break;
        }
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
