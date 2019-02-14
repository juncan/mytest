package com.test.excel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujc
 * @ClassName Record
 * @Description: TODO
 * @create 2018-11-09 16:33
 */
public class Record implements Serializable {
    private Map<String, Object> columns;

    public Record() {

    }

    public Record set(String column, Object value) {
        this.getColumns().put(column, value);
        return this;
    }

    public Map<String, Object> getColumns() {
        if (this.columns == null) {
            this.columns = new HashMap<String, Object>();
        }
        return this.columns;
    }

}
