package com;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.test.PayNotifyOrderReqVO;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.hutool.crypto.Mode.ECB;
import static cn.hutool.crypto.Padding.NoPadding;

/**
 * @author wujc
 * @ClassName Client
 * @create 2018-10-10 15:53
 */
@Slf4j
public class Client {

    static{
        Security.addProvider(new BouncyCastleProvider());
    }
    private final static String SPLIT_STR = "-";
    public static void main(String[] args) throws FileNotFoundException {
        //JsonConvert2Map();
        //Sm4();

        //System.out.println(new BigDecimal("000000009900").longValue());
        //PayNotifyOrderReqVO data = new PayNotifyOrderReqVO();
        //
        //if (1 == 1 || "111".equals("212")) {
        //    System.out.println("2121");
        //}
        //
        //String str = "{\"biz_content\":\"TKk5cqdJaNOrlZWt8qe/jE5Au/yKnf7cqVJTmwdvbQKy5a2xmWBofs2bAysSr8dbrddrTGV8mG6EOO+GVIaXX4sPBK6znWxp" +
        //        "/pRPhnUlY2K7O3RiI+q1QV03616h+JHNAw3iS8XAht6sv8ehWTwJvz9ZQeJ3E3TXM+gOIKee+HbFHZwfGg4BglZf4pEXXiSM/IRfzBnZnNGJGrvv" +
        //        "+hHGrsgqik2jj8WqHRm25kLpqP1lDfXqyNepttHVWMELFXn5lzqBjztQ8KCK3AApoT9AotxFFxirH91jO1z3vEMdiALxjmv1a6fWHDKknMBvW7i0nVN6Usn98kcIZRwrTvMVe6Sk9w0IaiIfgBcb5I9q4bJ9DcnYfQ9BsPFCrojPFa0Pg3o10k486IqJM2jrzZ95K02pjrhdmrnja+inwFBjHm1JUfpCWh2Fk3vWWNJKgfpBJmFlnQlrnnsm618rZ8vKZpPLB7uuQ73H6HyX5oXz878UmyDW1Oub6npYO6PnLkYf3+geVeAChXITTQxoRJTWMnvxii942FJ6Dr7vbx6E8SAEu6Y1vcdWGq91n1YcE38/s2XK6iMSWbsw19B4c6iabg==\",\"msg_id\":\"a7a16117ad1b4d7994e153dc681449ef\",\"timestamp\":\"2023-05-31 17:08:24\",\"encrypt_key\":\"IWBWSQI1n4wtMAnghdmeFTy2YxExahJBqtYrlEcI/6XD9CBvfL1Tbacq0ZveSByEaC6P34+PJgHYqr40ArmAUw8Kg3gLY5YeGbpL8c8YHZYnVzrCuLqCy9tiVIoimZoQtZtgXXwL+iVqwhEzTdYGIhO0F8pBbFE4pVlYpv9FWBDzA6BkHKQx8qr61fESnLry+PraoxQNMNjfViHmjmSpWiy5+FFP9xZnXCSP3RPuOgKtWn1f5M7UT7g9a+PkjzHI1hnO/aUB8rnO9EQWxrV7BNc3CsTyGHcJz4G1GEPyFozFNryH04ISGVxRFTaKvFjimqddLd/Qbe0n4DS8wnHriw==\",\"sign\":\"KEfJLAyFXSLBpU66LM5u9gCVU9YQGnhczdltysTc8NM1wlRmVK0hdNDEgk/wg3XVNw1zXMRoOQShb/6X+pmx6P/+Iem+VNluBI8mqgGyClnsRf3qYctb1ctOjf2EgkU8Pr/PkpSMJXau+PTuoGNA6TM/Z4+1eJjQIcXNQ/eGlFPcnt5NFgm4VhX7rB8bcbDf5AtGtqc+Lc84Qyq9N8nwyMWEuibSHOcxnZKIAkGj0UGi+h7ujA3pts/USK3/gzR+DQs8lYO/PQ0Kf4miqSHO6sRDD+0fte7Ljx1JSbaHt8hRVU+pQWMoo6r6NmNjWOA4K3D9oG6AChtYC2S2ZE1hOQ==\"}\n";
        //
        //System.out.println(JSONUtil.toBean(str, LinkedHashMap.class));

        //calUrl();

        System.out.println(Convert.toStr(Convert.toBool(1)));

        dealIdentity("斤斤计较bbnnnbx,450481197804234431,你们慢慢慢慢慢慢呢,450481197804234431,慢慢慢性病不错不错不好,450481197804234431");

    }

    private static void dealIdentity(String  data) {
        //身份信息
        StringBuilder idCardBd = new StringBuilder();
        if (StrUtil.isNotBlank(data)) {
            List<String> idCardList = Arrays.asList(data.split(","));
            for (int i = 0; i < idCardList.size(); i++) {
                String s = idCardList.get(i);
                if ((i+1) % 2 == 0) {
                    idCardBd.append(s).append((char) 10);
                } else {
                    idCardBd.append(s).append(":");
                }
            }
            System.out.println(idCardBd.toString());
        }
    }

    private static void buildTree() {

    }

    private static void JsonConvert2Map() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "212");
        jsonObject.put("sn", "212");
        Map<String, String> toMap = Convert.toMap(String.class, String.class, jsonObject);
        System.out.println(toMap);
    }

    public static void calUrl() {
        Map<String, String> extra = new HashMap<>();
        extra.put("platformType", "ONLINE");
        extra.put("billFundsDesc", "121311");
        Object request = PayNotifyOrderReqVO.builder().merchantOrderId("21212322")
                .payOrderId(111L).channelCode("UNION_PAY_WX_MINI")
                .extra(extra).build();

        String response = HttpUtil.post("http://192.168.1.60:40100/pay/notify/public", JSONUtil.toJsonStr(request),
                (int) 120 * 1000);

        System.out.println(response);


    }

    private static void Sm4() {
        String secretKey = "E5E7518D64184683ADF53009192B2CFC";
        String content = "{\"mobile\":\"13111444587\"}";

        SymmetricCrypto sm4 = new SM4(ECB, NoPadding, secretKey.getBytes());
        byte[] data = padding(content);

        System.out.println(sm4.encryptHex(data));
    }

    // 在NoPadding模式下需要手动对齐16字节的倍数
    public static byte[] padding(String arg_text) {
        byte[] encrypt = arg_text.getBytes();

        if (encrypt.length % 16 != 0) { // not a multiple of 8
            // create a new array with a size which is a multiple of 8
            byte[] padded = new byte[encrypt.length + 16 - (encrypt.length % 16)];

            // copy the old array into it
            System.arraycopy(encrypt, 0, padded, 0, encrypt.length);
            encrypt = padded;
        }
        return encrypt;
    }


    private String getConcatInfo(SettleOrdBill bill) {
        StTypeEnum typeEnum = StTypeEnum.valueOf(bill.getStType());
        String groupBy = "";
        switch (typeEnum) {
            case MERCHANT:
                groupBy =  bill.getStType() + SPLIT_STR + bill.getMerchantId() + SPLIT_STR + bill.getSettleStatus();
                break;
            case STORE:
                groupBy =  bill.getStType() + SPLIT_STR + bill.getStoreId() + SPLIT_STR + bill.getSettleStatus();
                break;
            case SITE:
                groupBy =  bill.getStType() + SPLIT_STR + bill.getSiteId() + SPLIT_STR + bill.getSettleStatus();
                break;
            default:
        }

        return groupBy;
    }

    private static void PSBCPOST() {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("transName","WPER");
        String plain;
        plain ="TranAbbr=WPER"//Y
                +"|" + "MercDtTm="+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"))//Y
                +"|" + "TermSsn=213424235423434343" //Y
                +"|" + "OSttDate="//N
                +"|" + "OAcqSsn="//N
                +"|" + "MercCode=1100122410001100110"//Y
                +"|" + "TermCode="//N
                +"|" + "TranAmt=0.01"
                +"|" + "MercUrl=http://35877g80n8.oicp.vip/pay/notify/psbc/callbak/public"
                +"|" + "Remark1="//N
                +"|" + "Remark2="//N
                +"|" + "LimitTime="//N
                +"|" + "GoodsName="+ Base64.getEncoder().encodeToString("圈享生活".getBytes(StandardCharsets.UTF_8))//Y
                +"|" + "MercUrl2="//+ URLEncoder.encode(payCommonProperties.getPSBCH5PayConfig().getReturnUrl())//Y
                +"|" + "SupportPayType="//+ "A"//Y
                +"|" + "CustPhNo="//+ cache.getPhone()//Y
        ;
        System.out.println(plain);
        paramMap.put("Plain",plain);
        paramMap.put("Signature","dsadsdsadsa");
        //链式构建请求
        String result2 = HttpRequest.post("http://220.248.253.172:8443/psbcpay/main")
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result2);

    }

    private static Date addDate(int days) {
        Date date = null;
        try {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
            d = ca.getTime();
            String enddate = format.format(d);
            date = format.parse(enddate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
//
        return date;
    }

    public static String dateToStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        String dateString = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateString = sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateString;
    }


    public static int reverse(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '-') {
                res.append(new char[]{s.charAt(i)}, 0, n);
            }
            res.append(s.charAt(i));
        }
        return Integer.valueOf(res.toString());
    }

    public static Date strToDate(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int c = 0, a = 1, b = 1;
            for (int i = 3; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }
    }

    private static void exchange(String a) {
        a = "dsjfkj";
        System.out.println("exchange a:" + a);
    }

    public static String getIP(String name) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("获取失败");
        }
        return address.getHostAddress().toString();
    }

    public static void operateStream() {
        List<String> dlist = new ArrayList<>();
        dlist.add("java");
        dlist.add("jsp");
        dlist.add("Ios");
        System.out.println(dlist.stream().distinct().filter((x) -> x.equals("jsp")).count());
    }

    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

    public static void updateFirst() {
        char[] c = new char[]{
                '赵', '钱', '孙', '李', '周', '吴', '郑', '王', '冯', '陈', '楮', '卫', '蒋', '沈', '韩', '杨',
                '朱', '秦', '尤', '许', '何', '吕', '施', '张', '孔', '曹', '严', '华', '金', '魏', '陶', '姜',
                '戚', '谢', '邹', '喻', '柏', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范', '彭', '郎',
                '鲁', '韦', '昌', '马', '苗', '凤', '花', '方', '俞', '任', '袁', '柳', '酆', '鲍', '史', '唐',
                '费', '廉', '岑', '薛', '雷', '贺', '倪', '汤', '滕', '殷', '罗', '毕', '郝', '邬', '安', '常',
                '乐', '于', '时', '傅', '皮', '卞', '齐', '康', '伍', '余', '元', '卜', '顾', '孟', '平', '黄',
                '和', '穆', '萧', '尹', '姚', '邵', '湛', '汪', '祁', '毛', '禹', '狄', '米', '贝', '明', '臧',
                '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈', '项', '祝', '董', '梁',
                '杜', '阮', '蓝', '闽', '席', '季', '麻', '强', '贾', '路', '娄', '危', '江', '童', '颜', '郭',
                '梅', '盛', '林', '刁', '锺', '徐', '丘', '骆', '高', '夏', '蔡', '田', '樊', '胡', '凌', '霍',
                '虞', '万', '支', '柯', '昝', '管', '卢', '莫', '经', '房', '裘', '缪', '干', '解', '应', '宗',
                '丁', '宣', '贲', '邓', '郁', '单', '杭', '洪', '包', '诸', '左', '石', '崔', '吉', '钮', '龚',
                '程', '嵇', '邢', '滑', '裴', '陆', '荣', '翁', '荀', '羊', '於', '惠', '甄', '麹', '家', '封',
                '芮', '羿', '储', '靳', '汲', '邴', '糜', '松', '井', '段', '富', '巫', '乌', '焦', '巴', '弓',
                '牧', '隗', '山', '谷', '车', '侯', '宓', '蓬', '全', '郗', '班', '仰', '秋', '仲', '伊', '宫',
                '宁', '仇', '栾', '暴', '甘', '斜', '厉', '戎', '祖', '武', '符', '刘', '景', '詹', '束', '龙',
                '叶', '幸', '司', '韶', '郜', '黎', '蓟', '薄', '印', '宿', '白', '怀', '蒲', '邰', '从', '鄂',
                '索', '咸', '籍', '赖', '卓', '蔺', '屠', '蒙', '池', '乔', '阴', '郁', '胥', '能', '苍', '双',
                '闻', '莘', '党', '翟', '谭', '贡', '劳', '逄', '姬', '申', '扶', '堵', '冉', '宰', '郦', '雍',
                '郤', '璩', '桑', '桂', '濮', '牛', '寿', '通', '边', '扈', '燕', '冀', '郏', '浦', '尚', '农',
                '温', '别', '庄', '晏', '柴', '瞿', '阎', '充', '慕', '连', '茹', '习', '宦', '艾', '鱼', '容',
                '向', '古', '易', '慎', '戈', '廖', '庾', '终', '暨', '居', '衡', '步', '都', '耿', '满', '弘',
                '匡', '国', '文', '寇', '广', '禄', '阙', '东', '欧', '殳', '沃', '利', '蔚', '越', '夔', '隆',
                '师', '巩', '厍', '聂', '晁', '勾', '敖', '融', '冷', '訾', '辛', '阚', '那', '简', '饶', '空',
                '曾', '毋', '沙', '乜', '养', '鞠', '须', '丰', '巢', '关', '蒯', '相', '查', '后', '荆', '红',
                '游', '竺', '权', '逑', '盖', '益', '桓', '公', '仉', '督', '晋', '楚', '阎', '法', '汝', '鄢',
                '涂', '钦', '岳', '帅', '缑', '亢', '况', '后', '有', '琴', '归', '海', '墨', '哈', '谯', '笪',
                '年', '爱', '阳', '佟', '商', '牟', '佘', '佴', '伯', '赏'
        };
        for (int i = 0; i < c.length; i++) {
            if ((i + 1) != c.length) {
                System.out.println("update user set user_name=concat('" + c[i] + "',right(user_name,CHAR_LENGTH" +
                        "(user_name)-1)) where user_name like '" + c[i + 1] + "%' and site_id = " +
                        "'94a87ae8f3b911e8b31300505681279f';");
            } else {
                System.out.println("update user set user_name=concat('" + c[i] + "',right(user_name,CHAR_LENGTH" +
                        "(user_name)-1)) where user_name like '" + c[0] + "%' and site_id = " +
                        "'94a87ae8f3b911e8b31300505681279f';");
            }

        }
    }
}
