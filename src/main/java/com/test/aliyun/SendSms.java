package com.test.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wujc
 * @ClassName SendSms
 * @Description: 阿里发送短信
 * @create 2018-11-09 15:23
 */
public class SendSms {
    private static final Logger logger = LoggerFactory.getLogger(SendSms.class);
    public static void send(SmsReqDto smsReqDto) throws ClientException {
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi"; //短信api产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com"; //短信api产品域名（接口地址固定，无需修改）
            // 替换成你的ak
            final String accessKeyId = "";
            final String accesskeySecret = "";
            //初始化ascClient,暂不支持多region
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accesskeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填：待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码，批量调用相对于单条调用及时性稍有延迟，验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(smsReqDto.getPhone());
            //必填：短信签名-可在短信控制台找到
            request.setSignName("");
            //必填：短信模板-可在短信控制台找到
            request.setTemplateCode(smsReqDto.getTemplate_code());
            //可选：模板中的变量替换成json串，如模板内容为“亲爱的${name},你的验证码为$(code)”时，此处的值为
            //友情提示：如果JSON中需要带换行符，请参照标准的JSON协议对换行符的要求，比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n，否则会导致JSON在服务器中解析失败
            request.setTemplateParam(smsReqDto.getJson_data());
            //可选-上行短信扩展码（无特殊需求用户请忽略此字段）
            //request.setSmsUpExtendCode("90997");
            // 可选：outId为提供给业务方扩展，最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");
            // 请求失败这里会抛出ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            //请求成功
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return;
            }
            throw new RuntimeException("短信发送失败：" + sendSmsResponse.getCode());

        } catch (Exception e) {
            logger.error("send sms error..." + e);
            throw new RuntimeException("你的操作过于频繁，请稍后再试");
        }
    }
}
