package com.test.rpc.serialize.support;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author wujc
 * @ClassName RpcSerializeProtocol
 * @Description: RpcSerializeProtocol功能模块
 */
public enum RpcSerializeProtocol {
    JDKSERIALIZE("jdknative"), KRYOSERIALIZE("kryo"), HESSIANSERIALIZE("hession"), PROTOSTUFFSERIALIZE("protostuff"),;

    private String serializeProtocol;

    private RpcSerializeProtocol(String serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }

    public String getSerializeProtocol() {
        return serializeProtocol;
    }
}
