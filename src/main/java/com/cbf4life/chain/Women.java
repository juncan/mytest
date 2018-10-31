package com.cbf4life.chain;

/**
 * @author wujc
 * @ClassName Women
 * @Description: TODO
 * @create 2018-08-26 21:10
 */
public class Women implements IWomen {
    /**
     * 通过一个int类型的参数来描述妇女的个人状况
     * 1---未出嫁
     * 2---出嫁
     * 3---夫死
     *
     * @return
     */
    private int type = 0;

    //妇女的请示
    private String request = "";

    public Women(int _type, String _request) {
        this.type = _type;
        this.request = _request;
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
