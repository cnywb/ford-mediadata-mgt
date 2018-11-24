package com.ford.mediadata.mgt.web.controller.notify.order;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.dto.order.OrderNotifyReq;
import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;

import io.dabing.common.sign.ParamSignUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderNotifyControllerTest extends ApplicationContextTest {

	private static final String SECRET = "20E04301E062D7EF607784DE9979A9F7";

	@Resource
	private OrderNotifyController orderNotifyController;

	@Test
	public void testCommonOrder() {
		try {
			CommonOrderNotify req = new CommonOrderNotify();
			req.setDate("20161229");
			req.setDataType(0);
			req.setOrderId(123456789L);
			req.setOrderTypeId(1);
			req.setOrderTypeName("新车");
			req.setUserName("李杰");
			req.setUserMobile("13838438438");
			req.setCarId("focus");
			req.setCityId(1L);
			req.setSign(getSignature(req));
			orderNotifyController.commonOrder(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void testCallOrder() {
		try {
			CallOrderNotify req = new CallOrderNotify();
			req.setDate("20161229");
			req.setDataType(0);
			req.setCallBusinessOpportunityId(123456789L);
			req.setDealerId(1234L);
			req.setCallerPhoneNumber("13838438438");
			req.setCityId(1L);
			req.setSign(getSignature(req));
			orderNotifyController.callOrder(req);
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
		Map<String, String> result = ParamSignUtils.sign(params, SECRET);
		String signResult = result.get(ParamSignUtils.SIGN);
		return signResult;
	}

}
