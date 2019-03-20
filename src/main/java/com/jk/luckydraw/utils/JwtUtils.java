package com.jk.luckydraw.utils;
import com.jk.luckydraw.config.ConstantConf;
import com.jk.luckydraw.domain.jkjw.CheckResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * jwt加密和解密的工具类
 */
public class JwtUtils {
    /**
     * 签发JWT
     * @param id
     * @param subject
     * @param ttlMillis
     * @param obj
     * @return
     */
	public static String createJWT(String id, String subject, long ttlMillis,HashMap<String, Object> obj) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey secretKey = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setSubject(subject)   // 主题
				.setIssuer("金科教育")     // 签发者
				.setIssuedAt(now)      // 签发时间
				.setClaims(obj)
				.signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate); // 过期时间
		}
		return builder.compact();
	}
	/**
	 * 验证JWT
	 * @param jwtStr
	 * @return
	 */
	public static CheckResult validateJWT(String jwtStr) {
		CheckResult checkResult = new CheckResult();
		Claims claims = null;
		try {
			claims = parseJWT(jwtStr);
			checkResult.setSuccess(true);
			checkResult.setClaims(claims);
		} catch (ExpiredJwtException e) {
			checkResult.setErrCode(ConstantConf.JWT_ERRCODE_EXPIRE);
			checkResult.setSuccess(false);
		} catch (SignatureException e) {
			checkResult.setErrCode(ConstantConf.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		} catch (Exception e) {
			checkResult.setErrCode(ConstantConf.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		}
		return checkResult;
	}
	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.decodeBase64(ConstantConf.JWT_SECERT);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}
	
	/**
	 * 
	 * 解析JWT字符串
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws Exception {
		SecretKey secretKey = generalKey();
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody();
	}
	public static void main(String[] args) throws InterruptedException {
		//小明失效 10s
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","张三");
        String sc = createJWT("1","小明", ConstantConf.JWT_TTL,hashMap);
		System.out.println(sc);
		System.out.println(validateJWT(sc).getErrCode());
		//Thread.sleep(4000);
		System.out.println(validateJWT(sc).getClaims());
	}
}

