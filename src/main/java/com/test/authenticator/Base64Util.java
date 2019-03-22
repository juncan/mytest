/**   
 * 优芽教育   版权所有
 * Copyright YOYA  EDU
 * All right reserved.
 *====================================================
 * 文件名称: Base64Util.java 
 * 修订记录：
 * No    日期				    功能描述
 * 1.    2018年6月28日 上午10:56:25		TODO
 *====================================================
 */
package com.test.authenticator;

import org.apache.commons.codec.binary.Base64;

/**
 * 类名: Base64Util
 * 类描述: Base64 加密解密字符串
 * @version：(当前版本)
 * @since (从哪个版本引入)
 */
public class Base64Util {

	/**
	 * <p>函数名称: decodeStr</p>
	 * <p>函数描述: 使用Base64加密</p>
	 * @param encodeStr
	 * @return String
	 */
	public static String decodeStr(String encodeStr){
        byte[] b=encodeStr.getBytes();
        Base64 base64=new Base64();
        b=base64.decode(b);
        String s=new String(b);
        return s;
    }
	
	/**
	 * <p>函数名称: encodeStr</p>
	 * <p>函数描述: 使用Base64加密算法加密字符串</p>
	 * @param plainText
	 * @return  String
	 */
    public static String encodeStr(String plainText){
        byte[] b=plainText.getBytes();
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }
}
