package com.cbf4life.Mediator;

/**
 * @author wujc
 * @ClassName AbstractColleague
 * @Description: TODO
 * @create 2018-11-10 19:55
 */
public class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
