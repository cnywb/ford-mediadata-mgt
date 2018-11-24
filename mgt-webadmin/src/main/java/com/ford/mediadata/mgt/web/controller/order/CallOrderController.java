package com.ford.mediadata.mgt.web.controller.order;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import com.ford.mediadata.mgt.service.order.CallOrderService;
import com.ford.mediadata.mgt.web.controller.order.vo.CallOrderEntiyReq;
import com.ford.mediadata.mgt.web.controller.order.vo.CallOrderEntiyResp;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * Created by Administrator on 2016/12/27.
 */
@Controller
public class CallOrderController {

	@Resource
	private CallOrderService service;

	/**
	 * 按关键字分页查询对象 调用batchTaskPage
	 *
	 * @param req
	 *            请求入参对象(包含了keyWord关键字)
	 * @return
	 */
	@ApiService(transCode = "callOrderPage")
	public Page<CallOrderEntiyResp> batchTaskPage(CallOrderEntiyReq req) {

		Page<CallOrderEntity> pages = service.doPagingBy(req.getPage(), req.getAgentName(), req.getProvinceName(),
				req.getDealerFollowStatus(), req.getCityName(), req.getCallBusinessOpportunityId(),
				req.getCreateTimeStartDate(), req.getCreateTimeEndDate(), req.getCreatedDateStartDate(),
				req.getCreatedDateEndDate(), req.getUpdatedDateStartDate(), req.getUpdatedDateEndDate(), req.getId());
		Page<CallOrderEntiyResp> respS = pages.map(new Converter<CallOrderEntity, CallOrderEntiyResp>() {
			public CallOrderEntiyResp convert(CallOrderEntity source) {
				CallOrderEntiyResp resp = new CallOrderEntiyResp();
				if (source != null) {
					BeanUtils.copyProperties(source, resp);
				}
				return resp;
			}
		});
		return respS;
	}
}
