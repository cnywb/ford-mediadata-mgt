package com.ford.mediadata.mgt.web.controller.mapping;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.mapping.CommonMapEntity;
import com.ford.mediadata.mgt.entity.mapping.CommonMapEntityCondition;
import com.ford.mediadata.mgt.service.mapping.CommonMapService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.mapping.vo.PageCommonMapRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * MAP表
 * 
 * @author chunliang.you
 *
 */
@Controller
public class CommonMapController {

	@Autowired
	private CommonMapService commonMapService;

	@ApiService(transCode = "pageCommonMap")
	public Page<CommonMapEntity> page(PageCommonMapRequest req) {
		CommonMapEntityCondition condition = new CommonMapEntityCondition();
		BeanUtils.copyProperties(req, condition);
		return commonMapService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "editCommonMap")
	public void edit(CommonMapEntity entity) {
		if (entity.getId() == null) {
			commonMapService.save(entity);
		} else {
			commonMapService.update(entity);
		}
	}

	@ApiService(transCode = "removeCommonMap")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				commonMapService.delete(data.getId());
			}
		}
	}

}
