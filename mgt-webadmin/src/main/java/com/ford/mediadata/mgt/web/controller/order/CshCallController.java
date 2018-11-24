package com.ford.mediadata.mgt.web.controller.order;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshCallEntityCondition;
import com.ford.mediadata.mgt.service.order.CshCallService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.order.vo.PageCshCallRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 车商汇话单
 * 
 * @author chunliang.you
 *
 */
@Controller
public class CshCallController {

	@Resource
	private CshCallService cshCallService;

	@ApiService(transCode = "pageCshCall")
	public Page<CshCallEntity> page(PageCshCallRequest req) {
		CshCallEntityCondition condition = new CshCallEntityCondition();
		BeanUtils.copyProperties(req, condition);
		if (condition.getUploadTimeEndDate() != null) {
			DateTime translatedDateTime = new DateTime(condition.getUploadTimeEndDate().getTime());
			translatedDateTime = translatedDateTime.plusDays(1).minusSeconds(1);
			condition.setUploadTimeEndDate(translatedDateTime.toDate());
		}
		return cshCallService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "removeCshCall")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				cshCallService.delete(data.getId());
			}
		}
	}

}
