package com.enums;

/**
 * 支付tags
 *
 * @author xingkong
 * @Title: PayTagsEnum
 * @date 2022/1/20 11:21
 */
public enum PayTagsEnum {
    /**
     * 支付成功
     */
    PAY_SUCCESS("支付成功"),
    /**
     * 支付失败
     */
    PAY_FAIL("支付失败"),

    /**
     * 退款
     */
    REFUND_SUCCESS("退款成功"),


    /**
     * 退款失败
     */
    REFUND_FAIL("退款失败"),

    /**
     * 退款申请
     */
    REFUND_APPLY("退款申请"),
    ;

    private final String desc;

    PayTagsEnum(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
