package com.cbf4life.Interprer;

/**
 * @author wujc
 * @ClassName SymbolExpression
 * @Description: TODO
 * @create 2018-11-11 9:37
 */
public abstract class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;

    //所有的解析公式都应只关心自己左右两个表达式的结果
    public SymbolExpression(Expression _left, Expression _right) {
        this.left = _left;
        this.right = _right;
    }
}
