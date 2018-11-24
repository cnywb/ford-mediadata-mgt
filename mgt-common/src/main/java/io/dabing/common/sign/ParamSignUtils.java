package io.dabing.common.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by wanglijun on 16/12/12.
 */
@Slf4j
public class ParamSignUtils {
	/**
	 * 签名参数
	 */
	public static final String SIGN = "sign";

	/** 参数 */
	public static final String PARAM = "param";

	/** MD5 */
	public static final String MD5 = "MD5";

	/** SHA-1 */
	public static final String SHA1 = "SHA-1";

	/***/
	private static final String UTF8 = "UTF-8";

	public static void main(String[] args) {
		HashMap<String, String> signMap = new HashMap<String, String>();
		signMap.put("a", "01");
		signMap.put("c", "02");
		signMap.put("b", "03");
		String secret = "test";
		HashMap SignHashMap = ParamSignUtils.sign(signMap, secret);
		System.out.println("SignHashMap:" + SignHashMap);
		List<String> ignoreParamNames = new ArrayList<String>();
		ignoreParamNames.add("a");
		HashMap SignHashMap2 = ParamSignUtils.sign(signMap, ignoreParamNames, secret);
		System.out.println("SignHashMap2:" + SignHashMap2);
	}

	public static HashMap<String, String> sign(Map<String, String> paramValues, String secret) {
		return sign(paramValues, null, secret);
	}

	/**
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	public static HashMap<String, String> sign(Map<String, String> paramValues, List<String> ignoreParamNames,
			String secret) {
		try {
			log.info("签名字段:{}，忽略字段:{}，密钥:{}", JSON.toJSONString(paramValues), JSON.toJSONString(ignoreParamNames),
					secret);
			HashMap<String, String> signMap = new HashMap<>();
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (!CollectionUtils.isEmpty(ignoreParamNames)) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);
			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			log.debug("签名字符串:{}", sb.toString());
			byte[] md5Digest = getMD5Digest(sb.toString());
			String sign = byte2hex(md5Digest);
			log.debug("签名结果:{}", sign);
			signMap.put(PARAM, sb.toString());
			signMap.put(SIGN, sign);
			return signMap;
		} catch (IOException e) {
			throw new RuntimeException("加密签名计算错误", e);
		}

	}

	public static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName), UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(SHA1);
			bytes = md.digest(data.getBytes(UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	private static byte[] getMD5Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			bytes = md.digest(data.getBytes(UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}
