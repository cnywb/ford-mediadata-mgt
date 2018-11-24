package com.ford.mediadata.mgt.web.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;
import com.ford.mediadata.mgt.service.project.ProjectCityService;
import com.ford.mediadata.mgt.web.controller.project.vo.EditProjectCityRequest;
import com.ford.mediadata.mgt.web.controller.project.vo.EditProjectCityRequest.ProvinceCity;

import io.dabing.core.web.annotation.ApiService;

/**
 * 项目活动城市
 * 
 * @author chunliang.you
 *
 */
@Controller
public class ProjectCityController {

	@Resource
	private ProjectCityService projectCityService;

	@ApiService(transCode = "listProjectCity")
	public List<ProjectCityEntity> list(ProjectCityEntity condition) {
		Assert.isTrue(StringUtils.isNotEmpty(condition.getProjectCode()), "项目活动代码为空");
		return projectCityService.list(condition);
	}

	@ApiService(transCode = "editProjectCity")
	public void edit(EditProjectCityRequest req) {
		List<ProjectCityEntity> toDeletEntities = projectCityService.findByProjectCode(req.getProjectCode());
		for (ProjectCityEntity toDeletEntitie : toDeletEntities) {
			projectCityService.delete(toDeletEntitie.getId());
		}
		if (CollectionUtils.isNotEmpty(req.getSelectedProjectCityList())) {
			for (ProvinceCity provinceCity : req.getSelectedProjectCityList()) {
				if (provinceCity != null && provinceCity.getCity() != null) {
					ProjectCityEntity toSaveEntity = new ProjectCityEntity();
					toSaveEntity.setProjectCode(req.getProjectCode());
					toSaveEntity.setProvince(provinceCity.getProvince());
					toSaveEntity.setCity(provinceCity.getCity());
					projectCityService.save(toSaveEntity);
				}
			}
		}
	}

}
