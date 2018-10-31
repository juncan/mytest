package com.test.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.test.weixin.constant.WeiXinEnum;
import com.test.weixin.model.TemplateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 模板消息工具类
 *
 * @author administrator
 * @create 2018-06-29 9:03
 */
public class WX_TemplateMsgUtil {
    private static final Logger log = LoggerFactory.getLogger(WX_TemplateMsgUtil.class);

    /**
     * 封装模板消息
     *
     * @param param
     * @return
     */
    public static JSONObject packJsonmsg(Map<String, TemplateData> param) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, TemplateData> entry: param.entrySet()) {
            JSONObject keyJson = new JSONObject();
            TemplateData data = entry.getValue();
            keyJson.put("value", data.getValue());
            keyJson.put("color", data.getColor());
            json.put(entry.getKey(), keyJson);
        }
        return json;
    }

    /**
     * 根据模板的编号 新增并获取模板ID
     *
     * @param templateSerialNumber 模板库中模板的 "编号"
     * @return 模板ID
     */
    public static String getWXTemplateMsgId(String templateSerialNumber) {
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + WX_TokenUtil.getWXToken();
        JSONObject json = new JSONObject();
        json.put("template_id_short", templateSerialNumber);
        JSONObject result = WX_HttpsUtil.httpsRequest(tmpurl, "POST", json.toString());
        JSONObject resultJson = new JSONObject(result);
        String errmsg = (String) resultJson.get("errmsg");
        log.info("获取模板编号返回信息：" + errmsg);
        if (!"ok".equals(errmsg)) {
            return "error";
        }
        String templateId = (String) resultJson.get("template_id");
        return templateId;
    }


    /**
     * 发送微信消息(模板消息)
     *
     * @param toUser     用户 OpenID
     * @param templateId 模板消息ID
     * @param clickUrl   URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）。
     * @param topColor   标题颜色
     * @param data       详细内容
     * @return
     */
    public static String sendWechatMsgToUser(String toUser, String templateId, String clickUrl, String topColor, JSONObject data) {
        String tmpUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + WX_TokenUtil.getWXToken();
        JSONObject json = new JSONObject();
        json.put("touser", toUser);
        json.put("template_id", templateId);
        json.put("url", clickUrl);
        json.put("data", data);
        try {
            JSONObject result = WX_HttpsUtil.httpsRequest(tmpUrl, "POST", json.toString());
            JSONObject resultJson = new JSONObject(result);
            log.info("发送微信消息返回消息：" + resultJson.get("errcode"));
            String errmsg = (String) resultJson.get("errmsg");
            if (!"ok".equals(errmsg)) { //如果errmsg为ok，则代表发送成功，公众号推送消息给用户了
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            if (templateId != null) {
                //删除新增的微信模板
            }
        }
        return "success";
    }

    public static void main(String[] args) {
        //新增用户成功 - 推送微信消息
        //获取用户openId
        //String openId = getUserOpenId();
        senMsg("oEouy0nAkmGGZtOM_3wBDWlbOt7k");
    }

    private static String getUserOpenId() {
        String appId = "wx05279db4a17d80e4";
        String appSecret = "8825a297b19e913266496b53d524ef31";
        String state="";
        String wxurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_clientstate";
        JSONObject jsonObject =WX_HttpsUtil.httpsRequest(wxurl, "GET", null);
        return jsonObject.getString("openId");
    }

    private static void senMsg(String openId) {
        //用户是否订阅该公众号标识 (0代表此用户没有关注该公众号 1表示关注了该公众号)
        Integer state = WX_UserUtil.subscribeState(openId);
        // 绑定了微信并且关注了服务号的用户 , 注册成功-推送注册短信
        if (state == 1) {
            Map<String, TemplateData> param = new HashMap<>();
            param.put("first", new TemplateData("恭喜您注册成功！", "#696969"));
            param.put("keyword1", new TemplateData("15618551533", "#696969"));
            param.put("keyword2", new TemplateData("2017年05月06日", "#696969"));
            param.put("keyword3", new TemplateData("2017年05月06日", "#696969"));
            param.put("remark", new TemplateData("祝投资愉快！", "#696969"));
            //注册的微信-模板Id
            String regTempId = 	"Hr8fNUoMXH7kkotOMF1Hmm74QZ00qwBZjsp-4u5Pbrc";//getWXTemplateMsgId(WeiXinEnum.WX_TEMPLATE_MSG_NUMBER.USER_REGISTER_SUCCESS.getMsgNumber());
            //调用发送微信消息给用户的接口
            WX_TemplateMsgUtil.sendWechatMsgToUser(openId, regTempId, "", "#000000", packJsonmsg(param));
        }
    }
}
