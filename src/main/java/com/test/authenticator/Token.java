/**   
 * 优芽教育   版权所有
 * Copyright YOYA  EDU
 * All right reserved.
 *====================================================
 * 文件名称: Token.java 
 * 修订记录：
 * No    日期				    功能描述
 * 1.    2019年3月7日 下午5:09:50		TODO
 *====================================================
 */
package com.test.authenticator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 类名: Token
 * 类描述: token类
 * @version：v1.0
 * @since (v1.0)
 */
public class Token {
	
	/**
	 * <p>函数名称: tokenProvider</p>
	 * <p>函数描述: token提供者</p>
	 * @param  key  一般是看不到的机器码
	 * @return JwtTokenProvider
	 */
	public static JwtTokenProvider tokenProvider(String key){
		return new JwtTokenProvider(key);
	}
	
	/**
	 * <p>函数名称: getToken</p>
	 * <p>函数描述: 生成token</p>
	 * @param   key
	 * @param   obj  自定义负载
	 * @return  token
	 */
	public static String getToken(String key,JSONObject obj){
		JwtTokenProvider jwtTokenProvider = Token.tokenProvider(key);
		return jwtTokenProvider.createToken(obj);
	}
	
	/**
	 * <p>函数名称: parseToken</p>
	 * <p>函数描述: 解析token</p>
	 * @param  key
	 * @param  token
	 * @return Claims
	 */
	public static Claims parseToken(String key,String token){
		JwtTokenProvider tokenProvider = Token.tokenProvider(key);
		return tokenProvider.parseToken(token);
	}
	
	/**
	 * <p>函数名称: encodeSign</p>
	 * <p>函数描述: 加密</p>
	 * @param siteId
	 * @param num
	 * @param terminalId
	 * @return
	 */
	public static String encodeSign(String siteId,String num,String terminalId){
		String token = Token.getToken(num+terminalId, payLoad(siteId));
		JSONObject sign = new JSONObject();
		sign.put("token", token);
		sign.put("expires_in", 7200);
		sign.put("terminal_id", terminalId);
		return Base64Util.encodeStr(sign.toJSONString());
	}
	
	/**
	 * <p>函数名称: decodeSign</p>
	 * <p>函数描述: 解密</p>
	 * @param txt
	 * @return
	 */
	public static JSONObject decodeSign(String txt){
		try {
			return JSON.parseObject(Base64Util.decodeStr(txt));
		} catch (Exception e) {
			return JSON.parseObject("{'error':'1'}");
		}
	}
	
	/**
	 * <p>函数名称: payLoad</p>
	 * <p>函数描述: TODO(简单描述)</p>
	 * @param siteId
	 * @return JSONObject
	 */
	public static JSONObject payLoad(String siteId){
		JSONObject sign = new JSONObject();
		sign.put("site_id", siteId);
		sign.put("time", DateFormatUtils.format(new Date(),"yyyyMMddHHmmss"));
		return sign;
	}
	
	
	public static void main(String[] args){
		String key ="GKDIJ-JGLHT-MNBGA-QODRR-EHIEY-JIMLR-MJBHB-OQJRI";
		JSONObject site = new JSONObject();
		site.put("siteId", "59b6022e0cf282cdb49be9c000000000");
		site.put("code", "xmxx");
		site.put("expressTime", DateFormatUtils.format(new Date(),"yyyyMMddHHmmss"));
		String sign = Token.getToken(key, site);
		System.out.println(sign);
		System.out.println("");
		Claims decodeSign = Token.parseToken(key, "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNqqVkqtKChKLS4OycxNVbJSMjIwtDQwNjIyNDG1MLZQ0lFKzk8BiVfkVlQAecWZJameKUC-qWWSmYGRUapBcpqRhVFySpKJZVKqZbIBDCjVAgAAAP__.coJWlpxFODi2CUxg1Z4BY7cqI_OUqP7kiUusFkWEs4kXaKgciwNvtNjhNzW5Mz5kXz3NJIXp64VYZBrlpTQoGg"
		);
		System.out.println(decodeSign);
	}

}
