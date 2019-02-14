package com.test.weixin.pay;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author wujc
 * @ClassName WxUtils
 * @Description: 微信支付工具类
 * @create 2018-11-12 9:46
 */
public class WxUtils {
    /**
     * 执行Post 方法的Http请求
     * @param url
     * @param parameters
     * @return
     * @throws IOException
     */
    public String executeHttpPost(String url, SortedMap<String, Object> parameters) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-type", "application/xml");
        request.setHeader("Accept", "application/xml");
        request.setEntity(new StringEntity(transferMapToXml(parameters), "UTF-8"));
        HttpResponse response = client.execute(request);
        return readResponse(response);
    }

    public String createSign(SortedMap<String, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        return encodeMD5(sb.toString());
    }

    /**
     * 第二次签名
     * @param result 数据为微信返回给服务器的数据（XML的String）,再次签名后传回给客户端（app）使用
     * @param key 秘钥
     * @return
     * @throws IOException
     */
    public Map createSign2(String result, String key) throws IOException {
        SortedMap<String, Object> map = new TreeMap<String,Object>(transferXmlToMap(result));
        Map app = new HashMap();
        app.put("appid", map.get("appid"));
        app.put("partnerid", map.get("mch_id"));
        app.put("prepayid", map.get("prepay_id"));
        app.put("package", map.get("Sign=WxPay"));
        app.put("noncestr", map.get("nonce_str"));
        app.put("timestamp", new Date().getTime() / 1000);
        app.put("sign", createSign(new TreeMap<String, Object>(app), key));
        return app;
    }

    /**
     * 验证签名是否正确
     * @param parameters
     * @param key
     * @return
     * @throws IOException
     */
    public boolean checkSign(SortedMap<String, Object> parameters, String key) throws IOException {
        String signWx = parameters.get("sign").toString();
        if(signWx == null) return false;
        parameters.remove("sign"); //需要去掉原map中包含的sign字段在进行签名
        String signMe = createSign(parameters, key);
        return signWx.equals(signMe);
    }

    /**
     * 读取request body 内容作为字符串
     * @param request
     * @return
     * @throws IOException
     */
    public String readRequest(HttpServletRequest request) throws IOException {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String str;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((str = in.readLine()) != null) {
            sb.append(str);
        }
        in.close();
        inputStream.close();
        return sb.toString();
    }

    /**
     * 读取response 内容为字符串
     * @param response
     * @return
     * @throws IOException
     */
    public String readResponse(HttpResponse response) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String result = new String();
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        return result;
    }

    /**
     * 将map 转成 xml
     * @param map
     * @return
     */
    public String transferMapToXml(SortedMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String key : map.keySet()) {
            sb.append("<").append(key).append(">")
                    .append(map.get(key))
                    .append("</").append(key).append(">");
        }
        return sb.append("</xml>").toString();
    }

    /**
     * 将xml 转化成Map
     * @param strXml
     * @return
     * @throws IOException
     */
    public Map transferXmlToMap(String strXml) throws IOException {
        strXml = strXml.replaceFirst("ecoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strXml || "".equals(strXml)) {
            return null;
        }
        Map m = new HashMap();
        InputStream in = new ByteArrayInputStream(strXml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        try {
            doc = builder.build(in);
        } catch (JDOMException e) {
            throw new IOException(e.getMessage());
        }
        //解析DOM
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            }else{
                v = getChildrenText(children);
            }
            m.put(k, v);
        }
        //关闭流
        in.close();
        return m;
    }

    /**
     * 辅助transferXmlToMap 方法递归子节点数据
     * @param children
     * @return
     */
    private String getChildrenText(List<Element> children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator<Element> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<Element> list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    /**
     * 生成32位随机字符串，包含：数字，字母大小写
     * @return
     */
    public String gen32RandomString() {
        char[] dict = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            sb.append(String.valueOf(dict[(int) (Math.random() * 36)]));
        }
        return sb.toString();
    }

    /**
     * MD5签名
     * @param str
     * @return 签名后的字符串信息
     */
    public String encodeMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = (str).getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArry = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArry[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArry[index++] = hexDigits[b & 0xf];
        }
        //字符数组合成字符串返回
        return new String(resultCharArry);
    }


}
