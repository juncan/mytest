package com;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 统一退款请求信息
 *
 * @author xingkong
 */
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayRefundUnifiedReqDTO {

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds 中的 transaction_id
     * https://opendocs.alipay.com/apis alipay.trade.refund 中的 trade_no
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds 中的 out_trade_no
     * https://opendocs.alipay.com/apis alipay.trade.refund 中的 out_trade_no
     * 支付交易号 {PayOrderExtensionDO no字段} 和 渠道订单号 不能同时为空
     */
    private String payTradeNo;

    /**
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds 中的 out_refund_no
     * https://opendocs.alipay.com/apis alipay.trade.refund 中的 out_trade_no
     * 退款请求单号  同一退款请求单号多次请求只退一笔。
     * 使用 商户的退款单号。{PayRefundDO 字段 merchantRefundNo}
     */
    private String merchantRefundId;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款金额，单位：分
     */
    private Long amount;

    /**
     * 退款结果 notify 回调地址， 支付宝退款不需要回调地址， 微信需要
     */
    private String notifyUrl;
    // ========== 拓展参数 ==========
    /**
     * 支付渠道的额外参数
     *
     * 例如说，微信公众号需要传递 openid 参数
     */
    private Map<String, String> channelExtras;

}
