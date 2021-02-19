package com.test.util;

import cn.hutool.core.util.StrUtil;

/**
 * @Classname ClientVersionUtil
 * @Description 版本号工具类
 * @Date 2020/6/29 11:07
 * @Created by wujc
 */
public class ClientVersionUtil {
	public static void main(String[] args) {
		//客户端传过来的版本号
		String clientVersion="5.1.0";
		//服务器数据库最新版本
		String serverVersion="4.1.0";
		//如果高于或者等于当前版本就返回true  否则 false
		boolean b = ClientVersionUtil.checkVersion(clientVersion,serverVersion);
		System.out.println(b);


	}


	public ClientVersionUtil() {
	}

	//判断版本工具类-如果高于或者等于当前版本就返回true  否则 false
	public static boolean CheckClientVersion(String clientVersion, String version) {
		try {
			if (StrUtil.isEmpty(version)) {
				return true;
			} else {
				String[] temp = version.split("\\.");
				String t = "";
				if (temp != null) {
					for(int i = 0; i < temp.length; ++i) {
						if (temp[i].length() == 1) {
							if (i == 0) {
								t = t + "0" + temp[i];
							} else {
								t = t + temp[i];
							}
						} else {
							t = t + temp[i];
						}

						if (i == temp.length - 1 && t.length() == 3) {
							t = t + "0";
						}
					}
				}

				if (t.length() > clientVersion.length()) {
					return false;
				} else if (t.charAt(0) > clientVersion.charAt(0)) {
					return false;
				} else if (t.charAt(0) < clientVersion.charAt(0)) {
					return true;
				} else if (t.charAt(1) > clientVersion.charAt(1)) {
					return false;
				} else if (t.charAt(1) < clientVersion.charAt(1)) {
					return true;
				} else if (t.charAt(2) > clientVersion.charAt(2)) {
					return false;
				} else if (t.charAt(2) < clientVersion.charAt(2)) {
					return true;
				} else if (t.charAt(3) > clientVersion.charAt(3)) {
					return false;
				} else {
					return t.charAt(3) < clientVersion.charAt(3) ? true : true;
				}
			}
		} catch (Exception var5) {
			return false;
		}
	}


	/**
	 * 版本号判断  如果第一个版本号小于第二个版本号  返回true
	 * @param curVer 现在的版本号
	 * @param speVer 比较的版本号
	 * @return curVer< speVer 返回true
	 */
	public static Boolean checkVersion(String curVer,String speVer){

		boolean flag = true;
		String[] curVers = curVer.split("\\.");
		String[] speVers = speVer.split("\\.");

		int[] iCur = new int[curVers.length];
		int[] iSper = new int[speVers.length];

		for(int i = 0;i < curVers.length;i++){
			iCur[i] = Integer.parseInt(curVers[i]);
		}
		for(int i = 0;i < speVers.length;i++){
			iSper[i] = Integer.parseInt(speVers[i]);
		}

		int iFlag = 0;
		if(curVers.length < speVers.length){
			iFlag = ClientVersionUtil.validata(iSper, iCur);
			if(iFlag > 0){
				flag = true;
			}else{
				flag = false;
			}
		}else{
			iFlag = ClientVersionUtil.validata(iCur, iSper);
			if(iFlag >= 0){
				flag = false;
			}else{
				flag = true;
			}
		}
		return flag;

	}
	public static int validata(int[] bigOne,int[] smallOne){
		int flag = 0;
		int temp = 0;
		for(int i = 0;i < bigOne.length;i++){
			if(i > smallOne.length-1){
				temp = 0;
			}else{
				temp = smallOne[i];
			}
			if(temp < bigOne[i]){
				flag = 1;
				return flag;
			}else if(temp > bigOne[i]){
				flag = -1;
				return flag;
			}else{
				if(i == bigOne.length-1){
					flag = 0;
				}else{
					continue;
				}
			}
		}
		return flag;
	}



}
