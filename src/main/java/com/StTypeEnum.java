package com;

/**
 * 商家类型  商家 - 门店
 *
 * @author xingkong
 * @date 2022/3/25 15:27
 */
public enum StTypeEnum {
    /**
     * 商家
     */
    MERCHANT("商家"),

    /**
     * 门店
     */
    STORE("门店"),

    /**
     * 站点
     */
    SITE("站点"),
    ;

    private String desc;

    StTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
