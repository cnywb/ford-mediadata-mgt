package com.ford.mediadata.mgt.web.controller.notify.order;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.dto.order.OrderNotifyConstants;
import com.ford.mediadata.mgt.dto.order.OrderNotifyError.DefaultNotifyError;
import com.ford.mediadata.mgt.dto.order.OrderNotifyException;
import com.ford.mediadata.mgt.dto.order.OrderNotifyReq;
import com.ford.mediadata.mgt.dto.order.OrderNotifyResp;
import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;
import com.ford.mediadata.mgt.service.order.CallOrderService;
import com.ford.mediadata.mgt.service.order.CommonOrderService;

import io.dabing.common.sign.ParamSignUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notify/order")
public class OrderNotifyController {

	/**
	 * 签名密码
	 */
	private static final String SECRET = "20E04301E062D7EF607784DE9979A9F7";

	@Resource
	private CommonOrderService commonOrderService;

	@Resource
	private CallOrderService callOrderService;

	/**
	 * 订单接口
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("commonOrder")
	@ResponseBody
	public OrderNotifyResp commonOrder(@RequestBody(required = false) CommonOrderNotify req) {
		try {
			// 基础字段校验
			preValidate(req);
			// 订单类商机ID校验
			if (req.getOrderId() == null) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E005);
			}
			// 订单类型校验
			if (req.getOrderTypeId() == null) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E006);
			}
			// 订购者名称校验
			if (StringUtils.isEmpty(req.getUserName())) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E007);
			}
			// 订购者电话校验
			if (StringUtils.isEmpty(req.getUserMobile())) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E008);
			}
			// 车款ID校验
			if (req.getCarId() == null) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E009);
			}
			// 城市ID校验
			if (req.getCityId() == null) {
				throw new OrderNotifyException(CommonOrderNotify.CommonOrderNotifyError.E010);
			}
			/**
			 * 20170206 逻辑修改，不使用易车的DataType做新增或更新的逻辑判断 // 数据状态校验
			 * CommonOrderEntity existOrder =
			 * commonOrderService.findByOrderId(req.getOrderId()); if
			 * (req.getDataType() == OrderNotifyConstants.DATA_TYPE_NEW &&
			 * existOrder != null) { throw new
			 * OrderNotifyException(DefaultNotifyError.E004); } else if
			 * (req.getDataType() == OrderNotifyConstants.DATA_TYPE_UPDATE &&
			 * existOrder == null) { throw new
			 * OrderNotifyException(DefaultNotifyError.E004); }
			 */
			/** 20170206 逻辑修改，根据数据库数据判断做新增或更新 */
			/*CommonOrderEntity existOrder = commonOrderService.findByOrderId(req.getOrderId());
			if (existOrder == null) {
				req.setDataType(OrderNotifyConstants.DATA_TYPE_NEW);
			} else {
				req.setDataType(OrderNotifyConstants.DATA_TYPE_UPDATE);
			}*/
			/** 20170206 逻辑修改 END */
			/** 20170410 逻辑修改，请求只做插入，不做更新 */
			// 保存/更新
//			if (req.getDataType() == OrderNotifyConstants.DATA_TYPE_NEW) {
				commonOrderService.saveCommonOrder(req);
//			} else if (req.getDataType() == OrderNotifyConstants.DATA_TYPE_UPDATE) {
//				commonOrderService.updateCommonOrder(req);
//			}
			return new OrderNotifyResp(DefaultNotifyError.E000);
		} catch (OrderNotifyException e) {
			log.error("订单处理异常：{}", e.getMessage());
			return new OrderNotifyResp(e.getOrderNotifyError());
		} catch (Exception e) {
			log.error("订单处理异常", e);
			return new OrderNotifyResp(DefaultNotifyError.E999);
		}
	}

	/**
	 * 话单接口
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("callOrder")
	@ResponseBody
	public OrderNotifyResp callOrder(@RequestBody(required = false) CallOrderNotify req) {
		try {
			// 基础字段校验
			preValidate(req);
			// 通话类商机ID校验
			if (req.getCallBusinessOpportunityId() == null) {
				throw new OrderNotifyException(CallOrderNotify.CallOrderNotifyError.E005);
			}
			// 经销商代码校验
			if (req.getDealerId() == null) {
				throw new OrderNotifyException(CallOrderNotify.CallOrderNotifyError.E006);
			}
			// 主叫电话号码校验
			if (StringUtils.isEmpty(req.getCallerPhoneNumber())) {
				throw new OrderNotifyException(CallOrderNotify.CallOrderNotifyError.E007);
			}
			// 城市ID校验
			if (req.getCityId() == null) {
				throw new OrderNotifyException(CallOrderNotify.CallOrderNotifyError.E008);
			}
			/**
			 * 20170206 逻辑修改，不使用易车的DataType做新增或更新的逻辑判断 // 数据状态校验 CallOrderEntity
			 * existOrder = callOrderService
			 * .findByCallBusinessOpportunityId(req.getCallBusinessOpportunityId());
			 * if (req.getDataType() == OrderNotifyConstants.DATA_TYPE_NEW &&
			 * existOrder != null) { throw new
			 * OrderNotifyException(DefaultNotifyError.E004); } else if
			 * (req.getDataType() == OrderNotifyConstants.DATA_TYPE_UPDATE &&
			 * existOrder == null) { throw new
			 * OrderNotifyException(DefaultNotifyError.E004); }
			 */
			/** 20170206 逻辑修改，根据数据库数据判断做新增或更新 */
			/*CallOrderEntity existOrder = callOrderService
					.findByCallBusinessOpportunityId(req.getCallBusinessOpportunityId());
			if (existOrder == null) {
				req.setDataType(OrderNotifyConstants.DATA_TYPE_NEW);
			} else {
				req.setDataType(OrderNotifyConstants.DATA_TYPE_UPDATE);
			}*/
			/** 20170206 逻辑修改 END */
			/** 20170410 逻辑修改，请求只做插入，不做更新 */
			// 保存/更新
//			if (req.getDataType() == OrderNotifyConstants.DATA_TYPE_NEW) {
				callOrderService.saveCallOrder(req);
//			} else if (req.getDataType() == OrderNotifyConstants.DATA_TYPE_UPDATE) {
//				callOrderService.updateCallOrder(req);
//			}
			return new OrderNotifyResp(DefaultNotifyError.E000);
		} catch (OrderNotifyException e) {
			log.error("话单处理异常：{}", e.getMessage());
			return new OrderNotifyResp(e.getOrderNotifyError());
		} catch (Exception e) {
			log.error("话单处理异常", e);
			return new OrderNotifyResp(DefaultNotifyError.E999);
		}
	}

	/**
	 * 基础校验
	 * 
	 * @param req
	 * @return
	 */
	private void preValidate(OrderNotifyReq req) {
		if (req == null) {
			throw new OrderNotifyException(DefaultNotifyError.E998);
		}
		log.info("收到请求:{}", JSON.toJSONString(req));
		if (StringUtils.isEmpty(req.getDate())) {
			throw new OrderNotifyException(DefaultNotifyError.E001);
		}
		if (StringUtils.isEmpty(req.getSign())) {
			throw new OrderNotifyException(DefaultNotifyError.E002);
		}
		if (req.getDataType() != OrderNotifyConstants.DATA_TYPE_NEW
				&& req.getDataType() != OrderNotifyConstants.DATA_TYPE_UPDATE) {
			throw new OrderNotifyException(DefaultNotifyError.E004);
		}
		if (!signatureVerification(req)) {
			throw new OrderNotifyException(DefaultNotifyError.E003);
		}
	}

	/**
	 * 校验签名
	 * 
	 * @param req
	 * @return
	 */
	private Boolean signatureVerification(OrderNotifyReq req) {
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
		if (req.getSign().equals(signResult)) {
			return Boolean.TRUE;
		}
		log.info("计算签名[{}]与请求签名[{}]不符", signResult, req.getSign());
		return Boolean.FALSE;
	}

}
