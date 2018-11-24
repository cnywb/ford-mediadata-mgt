package com.ford.mediadata.mgt.web.controller.project;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.project.ProjectEntity;
import com.ford.mediadata.mgt.service.project.ProjectService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.project.vo.PageProjectRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 项目活动
 * 
 * @author chunliang.you
 *
 */
@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ApiService(transCode = "pageProject")
	public Page<ProjectEntity> page(PageProjectRequest req) {
		ProjectEntity condition = new ProjectEntity();
		BeanUtils.copyProperties(req, condition);
		return projectService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "editProject")
	public void edit(ProjectEntity entity) {
		if (entity.getId() == null) {
			projectService.save(entity);
		} else {
			projectService.update(entity);
		}
	}

	@ApiService(transCode = "removeProject")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				projectService.delete(data.getId());
			}
		}
	}

}
