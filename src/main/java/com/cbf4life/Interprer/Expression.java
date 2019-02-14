package com.cbf4life.Interprer;

import java.util.HashMap;

/**
 * @author wujc
 * @ClassName Expression
 * @Description: TODO
 * @create 2018-11-11 9:32
 */
public abstract class Expression {
    //解析公式和数值，其中var中的key值是公式中的参数，value值是具体的数字
    public abstract int interpreter(HashMap<String, Integer> var);
}
