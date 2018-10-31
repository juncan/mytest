package com.cbf4life.visitor;

/**
 * @author wujc
 * @ClassName Manager
 * @Description: TODO
 * @create 2018-10-20 16:13
 */
public class Manager extends Employee {
    //这类人物的职责非常明确：业绩
    private String performance;

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    protected void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
