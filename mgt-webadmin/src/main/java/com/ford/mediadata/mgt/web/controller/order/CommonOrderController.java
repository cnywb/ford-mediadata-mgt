package com.ford.mediadata.mgt.web.controller.order;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import com.ford.mediadata.mgt.service.order.CommonOrderService;
import com.ford.mediadata.mgt.web.controller.order.vo.CommonOrderEntityReq;
import com.ford.mediadata.mgt.web.controller.order.vo.CommonOrderEntityResp;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * Created by Administrator on 2016/12/27.
 */
@Controller
public class CommonOrderController {

	@Resource
	private CommonOrderService service;

	/**
	 * 按关键字分页查询对象 调用batchTaskPage
	 *
	 * @param req
	 *            请求入参对象(包含了keyWord关键字)
	 * @return
	 */
	@ApiService(transCode = "commonOrderPage")
	public Page<CommonOrderEntityResp> batchTaskPage(CommonOrderEntityReq req) {

		Page<CommonOrderEntity> pages = service.doPagingBy(req.getPage(), req.getOrderTypeName(), req.getProvinceName(),
				req.getStatus(), req.getUserName(), req.getCityName(), req.getAccountName(), req.getOrderId(),
				req.getCreateTimeStartDate(), req.getCreateTimeEndDate(), req.getCreatedDateStartDate(),
				req.getCreatedDateEndDate(), req.getUpdatedDateStartDate(), req.getUpdatedDateEndDate(), req.getId());
		Page<CommonOrderEntityResp> respS = pages.map(new Converter<CommonOrderEntity, CommonOrderEntityResp>() {
			public CommonOrderEntityResp convert(CommonOrderEntity source) {
				CommonOrderEntityResp resp = new CommonOrderEntityResp();
				if (source != null) {
					BeanUtils.copyProperties(source, resp);
				}
				return resp;
			}
		});
		return respS;
	}
}
