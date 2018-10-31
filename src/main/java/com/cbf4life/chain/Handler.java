package com.cbf4life.chain;

/**
 * @author wujc
 * @ClassName Handler
 * @Description: 父系社会，那就是男性有至高权利，handler控制权
 * @create 2018-08-26 21:49
 */
public abstract class Handler {
    //能处理的级别
    private int level = 0;

    //责任传递，下一个人责任人是谁
    private Handler nextHander;

    //每个类都要说明一下自己能处理哪些请求
    public Handler(int _level) {
        this.level = _level;
    }

    //一个女性（女儿、妻子或者母亲）要求逛街，你要 处理这个请求
    public final void HandleMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHander != null) { //有后续环节，才把请求往后递送
                this.nextHander.HandleMessage(women);
            } else { //已经没有后续处理人了，不用处理
                System.out.println("--------没地方请示了，无需处理-------");
            }
        }
    }

    /**
     * 如果你属于你处理的返回，你应该让她找下一个环节，比如
     * 女儿出嫁了，还向父亲请示是否可以逛街，那父亲就告诉女儿，应该找丈夫请示
     */
    public void setNext(Handler _handler) {
        this.nextHander = _handler;
    }

    //有请示那当然要回应
    public abstract void response(IWomen women);
}
