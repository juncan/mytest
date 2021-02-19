package com.core.utils;

/**
 * @Classname ShareCodeUtil
 * @Description 邀请码生成工具类
 * @Date 2020/11/4 11:09
 * @Created by wujc
 */
public class ShareCodeUtil {
	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] r=new char[]{'F', 'L', 'G', 'W', '5', 'X', 'C', '3', '9', 'Z', 'M', '6', '7', 'Y', 'R', 'T', '2', 'H', 'S', '8', 'D', 'V', 'E', 'J', '4', 'K', 'Q', 'P', 'U', 'A', 'N', 'B'};

	/** 进制长度 */
	private static final int binLen=r.length;

	/** 邀请码长度 */
	private static final int sLength=6;

	/** 补位字符串 */
	private static final String covering="atgsghj";


	/**
	 *
	 * @param id ID
	 * @return 随机码
	 */
	public static String idToCode(long id) {
		char[] buf=new char[32];
		int charPos=32;

		while((id / binLen) > 0) {
			int ind=(int)(id % binLen);
			buf[--charPos]=r[ind];
			id /= binLen;
		}
		buf[--charPos]=r[(int)(id % binLen)];
		String str=new String(buf, charPos, (32 - charPos));
		// 不够长度的自动随机补全
		if(str.length() < sLength) {
			StringBuilder sb=new StringBuilder();
			sb.append(covering.subSequence(0, sLength-str.length()));
			str+=sb.toString();
		}
		return str.toUpperCase();
	}
	public static String idToCode(String id){
		long idL = Long.parseLong(id);
		return idToCode(idL);
	}

}
