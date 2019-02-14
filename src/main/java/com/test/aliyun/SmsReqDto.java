package com.test.aliyun;

/**
 * @author wujc
 * @ClassName SmsReqDto
 * @Description: TODO
 * @create 2018-11-09 15:24
 */
public class SmsReqDto {
    private String area_code;//手机区号
    private String phone;//手机号码
    private String template_code; //要发送的短信模板编码
    private String json_data; //要发送的短信数据

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
    }
}
