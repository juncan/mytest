package com.test.weixin.config;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;


public class WXLoginConfig {
	public static String appid;
	public static String appSecret;
	public static String apiUrl;
	
	
	public WXLoginConfig() {
		//super();
		// TODO Auto-generated constructor stub
		try {
			SAXBuilder sb = new SAXBuilder();
			String xmlPath= null;
			try {
				xmlPath = WXLoginConfig.class.getResource("/").toURI().getPath()+"wxlogin.xml";
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			Document doc;
			doc = sb.build(new FileInputStream(xmlPath));
			Element config=doc.getRootElement();
			this.appid=config.getChildText("appid");
			this.appSecret=config.getChildText("appSecret");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
