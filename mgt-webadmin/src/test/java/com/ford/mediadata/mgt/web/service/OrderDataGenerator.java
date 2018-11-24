package com.ford.mediadata.mgt.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;
import com.ford.mediadata.mgt.service.order.CallOrderService;
import com.ford.mediadata.mgt.service.order.CommonOrderService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDataGenerator extends ApplicationContextTest {

	@Resource
	private CommonOrderService commonOrderService;

	@Resource
	private CallOrderService callOrderService;

	private ExecutorService service = Executors.newCachedThreadPool();

	@Test
	public void generateRandomOrder() {
		try {
			List<Future<String>> futureList = Lists.newArrayList();
			for (int i = 0; i < 5; i++) {
				OrderThread thread = new OrderThread();
				futureList.add(service.submit(thread));
			}
			for (Future<String> future : futureList) {
				log.info(future.get());
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
	}

	private class OrderThread implements Callable<String> {
		@Override
		public String call() throws Exception {
			for (int i = 0; i < 200000; i++) {
				try {
					CommonOrderNotify commonOrder = new CommonOrderNotify();
					commonOrder.setAccountName(randomString(10));
					commonOrder.setBrandId(randomLong(999999L));
					commonOrder.setBrandName(randomString(10));
					commonOrder.setCarColor(randomString(10));
					commonOrder.setCarId(randomString(10));
					commonOrder.setCarName(randomString(10));
					commonOrder.setCarTypeName(randomString(10));
					commonOrder.setCityId(randomLong(999999L));
					commonOrder.setCityName(randomString(10));
					commonOrder.setCsId(randomLong(999999L));
					commonOrder.setCsName(randomString(10));
					commonOrder.setDataType(1);
					commonOrder.setDate(randomString(10));
					commonOrder.setDealerId(randomLong(999999L));
					commonOrder.setDealerName(randomString(10));
					commonOrder.setFistFollowTime(new Date());
					commonOrder.setOrderBusinessOpportunityCreateTime(new Date());
					commonOrder.setOrderId(randomLong(9999999999L));
					commonOrder.setOrderRemark(randomString(10));
					commonOrder.setOrderTypeId(randomInt(9999));
					commonOrder.setOrderTypeName(randomString(10));
					commonOrder.setProvinceId(randomLong(999999L));
					commonOrder.setProvinceName(randomString(10));
					commonOrder.setStatus(0);
					commonOrder.setUserGender(0);
					commonOrder.setUserMail(randomString(10));
					commonOrder.setUserMobile(randomString(10));
					commonOrder.setUserName(randomString(10));
					commonOrder.setYearType(2017);
					commonOrder.setYeferPrice(new BigDecimal(randomInt(40)));
					commonOrderService.saveCommonOrder(commonOrder);
					log.info("commonOrder:{}", JSON.toJSONString(commonOrder));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				try {
					CallOrderNotify callOrder = new CallOrderNotify();
					callOrder.setAgentName(randomString(10));
					callOrder.setCallBeginTime(new Date());
					callOrder.setCallBusinessOpportunityCreateTime(new Date());
					callOrder.setCallBusinessOpportunityId(randomLong(9999999999L));
					callOrder.setCalleeRealNumber(randomString(10));
					callOrder.setCallerDuration(randomLong(999999L));
					callOrder.setCallerPhoneNumber(randomString(10));
					callOrder.setCallFinishTime(new Date());
					callOrder.setCityId(randomLong(999999L));
					callOrder.setCityName(randomString(10));
					callOrder.setDataType(1);
					callOrder.setDate(randomString(10));
					callOrder.setDealerFollowStatus(0);
					callOrder.setDealerId(randomLong(999999L));
					callOrder.setFistFollowTime(new Date());
					callOrder.setProvinceId(randomLong(999999L));
					callOrder.setProvinceName(randomString(10));
					callOrder.setReferPrice(new BigDecimal(randomInt(40)));
					callOrder.setWaitDuration(randomLong(999999L));
					callOrder.setYearType(2017);
					callOrderService.saveCallOrder(callOrder);
					log.info("callOrder:{}", JSON.toJSONString(callOrder));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			return "success";
		}
	}

	private Long randomLong(long maxVol) {
		Double d = Math.random() * maxVol;
		return d.longValue();
	}

	private Integer randomInt(int maxVol) {
		return randomLong(Long.valueOf(maxVol)).intValue();
	}

	private String randomString(int l) {
		String string = UUID.randomUUID().toString();
		string = string.replaceAll("-", "");
		if (string.length() > l) {
			return string.substring(0, l);
		} else {
			return string;
		}
	}

}