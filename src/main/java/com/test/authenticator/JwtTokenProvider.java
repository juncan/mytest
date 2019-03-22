/**   
 * 优芽教育   版权所有
 * Copyright YOYA  EDU
 * All right reserved.
 *====================================================
 * 文件名称: JwtTokenProvider.java 
 * 修订记录：
 * No    日期				    功能描述
 * 1.    2019年3月7日 下午5:10:39		TODO
 *====================================================
 */
package com.test.authenticator;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;

/**
 * 类名: JwtTokenProvider
 * 类描述: token提供者
 * @version：v1.0
 * @since v1.0
 */
public class JwtTokenProvider {
    SecretKeySpec key;
 
    /**
     * <p>名称: JwtTokenProvider</p>
     * <p>描述: (简单描述)</p>
     * @param key  密钥(例如：GKDIJ-JGLHT-MNBGA-QODRR-EHIEY-JIMLR-MJBHB-OQJRI)
     */
    public JwtTokenProvider(String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        this.key = secretKeySpec;
    }
 
    /**
     * <p>函数名称: createToken</p>
     * <p>函数描述: 生成token</p>
     * @param obj
     * @return String
     */
    public String createToken(JSONObject obj) {
        String compactJws = Jwts.builder().setPayload(obj.toJSONString())
                .compressWith(CompressionCodecs.DEFLATE).signWith(SignatureAlgorithm.HS512, key).compact();
        return compactJws;
    }
 
    /**
     * <p>函数名称: parseToken</p>
     * <p>函数描述: token转换为Claims</p>
     * @param   token
     * @return  Claims
     */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
}