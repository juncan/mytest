package com;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.constant.GuardTypeEnum;
import com.core.utils.CommonUtils;
import com.core.utils.CronUtils;
import com.core.utils.CurrencyUtil;
import com.core.utils.ShareCodeUtil;
import com.enums.PayTagsEnum;
import com.google.common.base.Joiner;
import com.psbc.Signature;
import com.test.constants.FindMoreTypeEnum;
import net.rubyeye.xmemcached.KeyProvider;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wujc
 * @ClassName Client
 * @create 2018-10-10 15:53
 */
public class Client {
    public static void main(String[] args) throws FileNotFoundException {

        String powerName = "admin,xiaowu";
        if ("admin".indexOf(powerName) > -1) {
            System.out.println("true");
        }

        Double dl = 34.9;
        Double d2 = 34.9;
        if (d2.equals(dl)) {
            System.out.println("ok");
        }

        System.out.println(System.currentTimeMillis() / 1000);

        Date end_time = DateUtil.parse("2020-05-31", "yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(end_time);
        calendar.add(Calendar.DATE, 1);
        end_time = calendar.getTime();
        System.out.println(end_time);


      /*  Object obj1 = new FileReader(new File("1.dat"));
        Object obj3 = new BufferedReader(new FileReader("1.dat"));
        Object obj4 = new FileInputStream(new File("1.dat"));*/

        int[] a1 = {3, 4, 5};

        int[][] a2 = new int[3][3];
        String a3[] = {"string1", "string2", "string3"};
        String a4[] = new String[]{"string4", "string5", "string6"};

        BigDecimal joinRate =
                new BigDecimal(3).divide(new BigDecimal(26), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        System.out.println(joinRate);
        System.out.println("md5:" + SecureUtil.md5().toString());

        String str = HttpUtil.get("http://weixin.img.upload.meisuitv.com/ba9afa43fa4b47e08f1924136da15291" +
                ".zip?qhash/md5");
        System.out.println(str);

        /*String a = "sfhsfj";
        exchange(a);
        System.out.println("mian a:" + a);*/

        /*BigDecimal ss = new BigDecimal(0.10);

        if (!(ss.compareTo(BigDecimal.ZERO) == 0)) {
            System.out.println("true");
        }

        if (ss.equals(0)) {
            System.out.println("true");
        }

        Date old = new Timestamp(System.currentTimeMillis()-1);
        if (old.before(new Date())) {
            System.out.println("true");
        }

        if ((23 == 22) && (100 / 0 == 0))
            System.out.println("运算没有问题。");
        else
            System.out.println("没有报错");
*/
        /*String title = "哈哈回复后（但是）+（南大厦）";
        title = title.replace("（", "、");
        System.out.println(title);

        switch (0) {
            case 1:
                break;
            case 2:
                break;
            default:

        }*/
        /*String idCard = "56032119930512811X";
        //System.out.println(IdCardCheckUtil.IDCardValidate(idCard));
        *//*System.out.println(Integer.parseInt("0"));*//*
        System.out.println(getIP("nss-public.yoya.com"));
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.println(i);
                if (j == 12) {
                    return;
                }
            }
        }
        operateStream();*/
        //updateFirst();

        /*List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add(0, "f");
        System.out.println(list);*/
        /*Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8); //1

        ByteBuf sliced = buf.slice(0, 14);          //2
        System.out.println(sliced.toString(utf8));  //3

        buf.setByte(0, (byte) 'J');                 //4
        assert buf.getByte(0) == sliced.getByte(0);*/
        BigDecimal rate =
                new BigDecimal(3).divide(new BigDecimal(7), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        System.out.println(rate.toString());
        if ((23 == 23) || (100 / 0 == 0))
            System.out.println("运算没有问题。");
        else
            System.out.println("没有报错");

        System.out.println(fibonacci(10));


        Date date = strToDate("2019-10-14 00:00:00");
        System.out.println(date.toString());

        if ("14:51".compareTo("14:50") > 0) {
            System.out.println("能比较");
        }

        String dateStr = dateToStr(new Date(), "MM月-dd日");
        System.out.println(dateStr);

        String userAgent = ("User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 " +
                "(KHTML, like Gecko) Version/8.0 Mobile/12A366 Safari/600.1.4").toLowerCase();
        String regEx = "micromessenger|ipad|iphone|ipod|android|yoya-ios|yoya-android";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(userAgent);
        if (matcher.find()) {
            System.out.println("或时间福建省");
        }

        char[] c = {'h', 'e', 'l', 'l', 'o'};
        reverseString(c);


        Date futureDate = addDate(10);
        long ts = (futureDate.getTime() - System.currentTimeMillis()) / 1000;
        System.out.println(ts);

        System.out.println(CronUtils.getCron(new Date()));

        String is_anchor = "0";
        if (StringUtils.isBlank(is_anchor) || !is_anchor.equals("1")) {
            System.out.println("fsfkjs");
        }

        int num = 0;
        try {
            for (int i = 0; i < 200; i++) {
                if (i == 1) {
                    int ss = i / 0;
                }
                num++;
            }
        } catch (Exception e) {

        }
        System.out.println(num);

        if (true && !false) {
            System.out.println("test");
        }

        System.out.println(CommonUtils.mobileEncrypt("18350211269"));

        System.out.println("20.20".compareTo("20.1"));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fsaf:2121,sdasdd:2121,");
        String value = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println(value);
        System.out.println("邀请码：" + ShareCodeUtil.idToCode("1"));

        System.out.println("2123".indexOf("1"));

        if (true && false) {
            System.out.println("true");
        }

        System.out.println(GuardTypeEnum.valueOf("MONTHLY").getCode());

        BigDecimal a = new BigDecimal(0.1);

        System.out.println(Integer.MAX_VALUE);

        //System.out.println(LocalDateTime.parse(DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN)+"
        // 00:00:00"));

        System.out.println(DateUtil.beginOfWeek(new Date()));

        System.out.println(DateUtil.endOfWeek(new Date()));

        System.out.println(DateUtil.betweenDay(new Date(), DateUtil.offsetDay(new Date(), 1), true));
        //System.out.println(FindMoreTypeEnum.valueOf("超值好货").getCode());

        Map<String, Object> data = new HashMap<>();
        data.put("startTime", new Date());
        Date startTime = MapUtil.getDate(data, "startTime");
        System.out.println(startTime);
        try {
            for (int i = 0; i < 100; i++) {
                if (i == 50) {
                    i = i / 0;
                }
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        String str11 = "{\n" +
                "  \"CallbackCommand\": \"C2C.CallbackBeforeSendMsg\", // 回调命令\n" +
                "  \"From_Account\": \"jared\", // 发送者\n" +
                "  \"To_Account\": \"Jonh\", // 接收者\n" +
                "  \"MsgSeq\": 48374, // 消息序列号\n" +
                "  \"MsgRandom\": 2837546, // 消息随机数\n" +
                "  \"MsgTime\": 1557481126, // 消息的发送时间戳，单位为秒 \n" +
                "  \"MsgKey\": \"48374_2837546_1557481126\", //消息的唯一标识，可用于 REST API 撤回单聊消息\n" +
                "  \"MsgBody\": [ // 消息体，参见 TIMMessage 消息对象\n" +
                "      {\n" +
                "          \"MsgType\": \"TIMTextElem\", // 文本\n" +
                "          \"MsgContent\": {\n" +
                "              \"Text\": \"red packet\"\n" +
                "          }\n" +
                "      }\n" +
                "  ]\n" +
                "}";
       /* JSONObject body = JSON.parseObject(str11);
        System.out.println(body.getJSONArray("MsgContent").toJSONString());*/


        Duration duration = LocalDateTimeUtil.between(LocalDateTime.now(), LocalDateTime.now().plusHours(-1));
        System.out.printf(duration.toMillis() + "");


        String ftUrl = Joiner.on("").join("FT_CORPID=", 11, "&FT_TILEID=",
                11,
                "&FT_SCENARIO=", 22, "&FT_ORDERNO=", 222,
                "&CUSTOMERID=", 423, "&USERID=", 555, "&PASSWORD=",
                88,
                "&TXCODE=", "5WX004", "&LANGUAGE=CN&CCB_IBSVersion=V6&PT_STYLE=F&resType=jsp",
                "&MONEY=", 434, "&Enqr_StDt=", 997, "&Enqr_CODt=",
                3434, "&ORDER=", 8878);

        StringBuilder sb = new StringBuilder(ftUrl);
        if (StrUtil.isNotBlank("wq23q")) {
            sb.append("&MsgRp_Jrnl_No=").append("323");
        }

        BigDecimal result = BigDecimal.valueOf(12L);

        System.out.println(result.add(result.negate()));

        PayTagsEnum payTagsEnum = EnumUtil.fromString(PayTagsEnum.class, "ewqwe", null);


        LocalTime localTime = LocalTime.parse("20:13");

        System.out.println(LocalTime.now());

        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

        //构建
        AES aes = SecureUtil.aes(key);

        //加密
        byte[] encrypt = aes.encrypt(content);
         //解密
        byte[] decrypt = aes.decrypt(encrypt);

        long between = DateUtil.between(DateUtil.date(), DateUtil.endOfDay(new Date())
                , DateUnit.SECOND);
        System.out.println(between);

        System.out.println(IdUtil.simpleUUID());

        System.out.println(DateUtil.format(DateUtil.yesterday(),DatePattern.PURE_DATE_PATTERN));

        LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).plusDays(1);

        System.out.println(LocalDateTime.now().getDayOfMonth() );


        System.out.println(CurrencyUtil.mul(new BigDecimal("0.01"), new BigDecimal("0.09")));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("CCB_IBSVersion", "V6");
        paramMap.put("MERCHANTID", 1);
        paramMap.put("POSID", 1);
        paramMap.put("BRANCHID", 1);
        paramMap.put("ORDERID", 11);
        paramMap.put("PAYMENT",1);
        paramMap.put("CURCODE", "01");
        paramMap.put("TXCODE", "HT0000");
        String mac = SecureUtil.hmacMd5(
                "30819d300d06092a864886f70d01010150003818b00308187028181009c355967993e99186e6df9b80d75d9b397f8b428e7af0e0eddc13a35 12bbfb3f743dfb62c52f8f391bb760ce2a3f8d6c39bc56c30bd0781bb4a7aa9d95440a3a3786a65a53ec604f859b75153f73471d58a15cc391049cb406928fc9f698e986735d7580d550ab3648f767c5be813aaa0ab01b8cf020111\n").digestHex(HttpUtil.toParams(paramMap));
        System.out.println("http://128.196.119.53:8101/CCBIS/ccbMain_XM"+HttpUtil.toParams(paramMap));
        //HttpResponse response = HttpUtil.createPost("http://128.196.119.53:8101/CCBIS/ccbMain_XM"+HttpUtil.toParams(paramMap)).execute();

        //System.out.println(response.toString());

        PSBCPOST();
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
