package com.test.rpc.model;

import java.util.Map;

/**
 * @author wujc
 * @ClassName MessageKeyVal
 */
public class MessageKeyVal {
    private Map<String, Object> messageKeyVal;

    public void setMessageKeyVal(Map<String, Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }
}
