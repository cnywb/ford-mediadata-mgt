package com.ford.mediadata.mgt.web.controller.order;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntityCondition;
import com.ford.mediadata.mgt.service.order.CshOrderService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.order.vo.PageCshOrderRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 车商汇订单
 * 
 * @author chunliang.you
 *
 */
@Controller
public class CshOrderController {

	@Resource
	private CshOrderService cshOrderService;

	@ApiService(transCode = "pageCshOrder")
	public Page<CshOrderEntity> page(PageCshOrderRequest req) {
		CshOrderEntityCondition condition = new CshOrderEntityCondition();
		BeanUtils.copyProperties(req, condition);
		if (condition.getUploadTimeEndDate() != null) {
			DateTime translatedDateTime = new DateTime(condition.getUploadTimeEndDate().getTime());
			translatedDateTime = translatedDateTime.plusDays(1).minusSeconds(1);
			condition.setUploadTimeEndDate(translatedDateTime.toDate());
		}
		return cshOrderService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "removeCshOrder")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				cshOrderService.delete(data.getId());
			}
		}
	}

}
