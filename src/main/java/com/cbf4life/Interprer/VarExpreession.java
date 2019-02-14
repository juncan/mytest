package com.cbf4life.Interprer;

import java.util.HashMap;

/**
 * @author wujc
 * @ClassName VarExpreession
 * @Description: TODO
 * @create 2018-11-11 9:35
 */
public class VarExpreession extends Expression {
    private String key;

    public VarExpreession(String _key) {
        this.key = _key;
    }
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
