package com.cbf4life.Interprer;

import java.util.HashMap;

/**
 * @author wujc
 * @ClassName SubExpression
 * @Description: TODO
 * @create 2018-11-11 11:22
 */
public class SubExpression extends SymbolExpression {
    public SubExpression(Expression _left, Expression _right) {
        super(_left, _right);
    }
    //左右两个表达式相减
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }

}
