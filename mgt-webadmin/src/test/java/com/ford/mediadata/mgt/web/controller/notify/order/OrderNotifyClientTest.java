package com.ford.mediadata.mgt.web.controller.notify.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.dto.order.OrderNotifyReq;
import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderNotifyClientTest {

	private static final String SIGN = "sign";
	private static final String PARAM = "param";
	private static final String MD5 = "MD5";
	private static final String UTF8 = "UTF-8";

	private static final String COMMON_ORDER_URI = "http://139.224.227.225:8080/mgt/notify/order/commonOrder.do";
	private static final String CALL_ORDER_URI = "http://139.224.227.225:8080/mgt/notify/order/callOrder.do";
	private static final String SECRET = "20E04301E062D7EF607784DE9979A9F7";

	@Test
	public void testCommonOrder() {
		try {
			Long orderId = Long.valueOf(RandomStringUtils.randomNumeric(10));
			// 新记录
			CommonOrderNotify req = new CommonOrderNotify();
			req.setDate(DateTime.now().toString("yyyyMMddHHmmssSSS"));
			req.setDataType(0);
			req.setOrderId(orderId);
			req.setOrderTypeId(1);
			req.setOrderTypeName("新车");
			req.setOrderBusinessOpportunityCreateTime(new Date());
			req.setOrderRemark("备注");
			req.setUserName("测试人");
			req.setUserMobile("13812345678");
			req.setCarId("focus");
			req.setCarColor("白色");
			req.setCarName("车名");
			req.setCarTypeName("豪华");
			req.setAccountName("account");
			req.setBrandId(1L);
			req.setBrandName("福特");
			req.setCityId(1L);
			req.setCityName("上海");
			req.setCsId(1L);
			req.setCsName("福克斯");
			req.setDealerId(1L);
			req.setDealerName("经销商A");
			req.setFistFollowTime(new Date());
			req.setYearType(2017);
			req.setYeferPrice(new BigDecimal("150000"));
			req.setUserGender(0);
			req.setUserMail("1234@gmail.com");
			req.setProvinceId(1L);
			req.setProvinceName("上海");
			req.setStatus(0);
			req.setSign(getSignature(req));
			post(COMMON_ORDER_URI, JSON.toJSONString(req));
			// 更新
//			req.setDataType(1);
//			req.setUserName("测试人(UPDATE)");
//			req.setSign(getSignature(req));
//			post(COMMON_ORDER_URI, JSON.toJSONString(req));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void testCallOrder() {
		try {
			Long orderId = Long.valueOf(RandomStringUtils.randomNumeric(10));
			// 新记录
			CallOrderNotify req = new CallOrderNotify();
			req.setDate(DateTime.now().toString("yyyyMMddHHmmssSSS"));
			req.setDataType(0);
			req.setCallBusinessOpportunityId(orderId);
			req.setAgentName("坐席");
			req.setCallBeginTime(new Date());
			req.setCallBusinessOpportunityCreateTime(new Date());
			req.setCalleeRealNumber("13887654321");
			req.setCallerDuration(3600L);
			req.setCallerPhoneNumber("13812345678");
			req.setCallFinishTime(new Date());
			req.setCityId(1L);
			req.setCityName("上海");
			req.setDealerFollowStatus(0);
			req.setDealerId(1234L);
			req.setFistFollowTime(new Date());
			req.setProvinceId(1L);
			req.setProvinceName("上海");
			req.setReferPrice(new BigDecimal("150000"));
			req.setWaitDuration(3600L);
			req.setYearType(2017);
			req.setSign(getSignature(req));
			post(CALL_ORDER_URI, JSON.toJSONString(req));
			// 更新
//			req.setDataType(1);
//			req.setAgentName("坐席(UPDATE)");
//			req.setSign(getSignature(req));
//			post(CALL_ORDER_URI, JSON.toJSONString(req));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 签名
	 * 
	 * @param req
	 * @return
	 */
	private String getSignature(OrderNotifyReq req) {
		Map<String, String> params = new HashMap<>();
		params.put("date", req.getDate());
		params.put("dataType", "" + req.getDataType());
		if (req instanceof CommonOrderNotify) {
			CommonOrderNotify order = (CommonOrderNotify) req;
			params.put("orderId", "" + order.getOrderId());
		} else if (req instanceof CallOrderNotify) {
			CallOrderNotify order = (CallOrderNotify) req;
			params.put("callBusinessOpportunityId", "" + order.getCallBusinessOpportunityId());
		}
		Map<String, String> result = sign(params, SECRET);
		String signResult = result.get(SIGN);
		return signResult;
	}

	/**
	 * 发送请求
	 * 
	 * @param uri
	 * @param requestBody
	 */
	private void post(String uri, String requestBody) {
		log.info("请求：{} {}", uri, requestBody);
		RequestBuilder requestBuilder = RequestBuilder.post();
		requestBuilder.setUri(uri);
		RequestConfig requestConfig = RequestConfig.DEFAULT;
		requestBuilder.setConfig(requestConfig);
		requestBuilder.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
		HttpUriRequest request = requestBuilder.build();
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = HttpClients.createDefault();
			response = client.execute(request);
			String responseBody = EntityUtils.toString(response.getEntity(), UTF8);
			log.info("响应：{}", responseBody);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(client);
			IOUtils.closeQuietly(response);
		}
	}

	public static HashMap<String, String> sign(Map<String, String> paramValues, String secret) {
		try {
			HashMap<String, String> signMap = new HashMap<>();
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			Collections.sort(paramNames);
			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			byte[] md5Digest = getMD5Digest(sb.toString());
			String sign = byte2hex(md5Digest);
			signMap.put(PARAM, sb.toString());
			signMap.put(SIGN, sign);
			return signMap;
		} catch (IOException e) {
			throw new RuntimeException("加密签名计算错误", e);
		}

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
