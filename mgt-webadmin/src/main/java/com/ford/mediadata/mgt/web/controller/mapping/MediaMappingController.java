package com.ford.mediadata.mgt.web.controller.mapping;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;
import com.ford.mediadata.mgt.service.mapping.MediaMappingService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.mapping.vo.PageMediaMappingRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 媒体数据映射表
 * 
 * @author chunliang.you
 *
 */
@Controller
public class MediaMappingController {

	@Autowired
	private MediaMappingService mediaMappingService;

	@ApiService(transCode = "pageMediaMapping")
	public Page<MediaMappingEntity> page(PageMediaMappingRequest req) {
		MediaMappingEntity condition = new MediaMappingEntity();
		BeanUtils.copyProperties(req, condition);
		return mediaMappingService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "editMediaMapping")
	public void edit(MediaMappingEntity entity) {
		if (entity.getId() == null) {
			mediaMappingService.save(entity);
		} else {
			mediaMappingService.update(entity);
		}
	}

	@ApiService(transCode = "removeMediaMapping")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				mediaMappingService.delete(data.getId());
			}
		}
	}

}
