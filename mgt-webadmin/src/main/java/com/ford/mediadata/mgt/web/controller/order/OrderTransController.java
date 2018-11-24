package com.ford.mediadata.mgt.web.controller.order;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;
import com.ford.mediadata.mgt.service.order.OrderTransService;
import com.ford.mediadata.mgt.web.controller.order.vo.PageOrderTransRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 数据转换中间表
 * 
 * @author chunliang.you
 *
 */
@Controller
public class OrderTransController {

	@Resource
	private OrderTransService orderTransService;

	@ApiService(transCode = "pageOrderTrans")
	public Page<OrderTransEntity> page(PageOrderTransRequest req) {
		OrderTransEntityCondition condition = new OrderTransEntityCondition();
		BeanUtils.copyProperties(req, condition);
		if (condition.getImportDateEndDate() != null) {
			DateTime translatedDateTime = new DateTime(condition.getImportDateEndDate().getTime());
			translatedDateTime = translatedDateTime.plusDays(1).minusSeconds(1);
			condition.setImportDateEndDate(translatedDateTime.toDate());
		}
		if (condition.getOrderBusinessOpportunityCreateTimeEndDate() != null) {
			DateTime translatedDateTime = new DateTime(
					condition.getOrderBusinessOpportunityCreateTimeEndDate().getTime());
			translatedDateTime = translatedDateTime.plusDays(1).minusSeconds(1);
			condition.setOrderBusinessOpportunityCreateTimeEndDate(translatedDateTime.toDate());
		}
		return orderTransService.findByGridPage(req.getPage(), condition);
	}

}
