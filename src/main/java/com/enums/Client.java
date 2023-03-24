package com.enums;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/2/24 15:37
 */
public class Client {
    public static void main(String[] args) {
        NoticeTagsEnum tagsEnum = NoticeTagsEnum.getIfPresent("TEMPLATEMSG");
        if (tagsEnum != null) {
            System.out.println("dssf");
        }

        String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?><packet><ErrorCode>00000458</ErrorCode><ErrorMsg>未清算</ErrorMsg></packet>";
        String[] split = str.split("\r\n");
        List<PsbcSettleDetail> psbcSettleDetails = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            String[] column = split[i].split("\\|");
            PsbcSettleDetail psbcSettleDetail = new PsbcSettleDetail();
            psbcSettleDetail.setPayType(column[0]);
            psbcSettleDetail.setPayDate(column[1]);
            psbcSettleDetail.setTimeStamp(column[2]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            psbcSettleDetail.setPayTime(LocalDateTime.parse(column[2], formatter));
            psbcSettleDetail.setPayOrderId(column[3]);
            psbcSettleDetail.setPsbcNo(column[1]+column[4]);
            if ("DCPR".equals(column[0])){//支付
                psbcSettleDetail.setPayAmount(new BigDecimal(column[7]));
            }else{//退款转化为负数
                psbcSettleDetail.setPayAmount(new BigDecimal(column[7]).negate());
            }
            psbcSettleDetail.setOtherAmount(new BigDecimal(column[8]));

            psbcSettleDetails.add(psbcSettleDetail);
        }
    }
}
