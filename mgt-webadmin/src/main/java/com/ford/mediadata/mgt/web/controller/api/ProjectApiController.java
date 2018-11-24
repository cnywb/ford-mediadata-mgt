package com.ford.mediadata.mgt.web.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;
import com.ford.mediadata.mgt.entity.project.ProjectEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.service.project.ProjectCityService;
import com.ford.mediadata.mgt.service.project.ProjectService;
import com.ford.mediadata.mgt.service.project.ProjectUserDealerService;
import com.ford.mediadata.mgt.service.project.ProjectUserService;
import com.ford.mediadata.mgt.web.controller.api.vo.ApiResponse;
import com.ford.mediadata.mgt.web.controller.api.vo.basic.CityVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectCityVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectUserVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectUserVO.DealerVO;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api")
public class ProjectApiController {

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectCityService projectCityService;

	@Resource
	private ProjectUserService projectUserService;

	@Resource
	private ProjectUserDealerService projectUserDealerService;

	@RequestMapping(value = "/project/city", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<ProjectCityVO> getProjectCity(@RequestParam(required = false) String projectCode) {
		try {
			ProjectCityVO projectCityVO = new ProjectCityVO();
			// 查询活动信息
			ProjectEntity project = projectService.findByCode(projectCode);
			Assert.notNull(project, "项目活动不存在");
			projectCityVO.setProjectCode(project.getCode());
			projectCityVO.setBeginTime(project.getBeginTime());
			projectCityVO.setEndTime(project.getEndTime());
			// 查询活动城市
			List<CityVO> cityVOList = Lists.newArrayList();
			List<ProjectCityEntity> cityEntityList = projectCityService.findByProjectCode(projectCode);
			for (ProjectCityEntity cityEntity : cityEntityList) {
				CityVO cityVO = new CityVO();
				cityVO.setName(cityEntity.getCity());
				cityVOList.add(cityVO);
			}
			projectCityVO.setCityList(cityVOList);
			return ApiResponse.newSuccessResponse(ProjectCityVO.class, projectCityVO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ApiResponse.newErrorResponse(ProjectCityVO.class, e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/project/user", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List> getProjectUser(@RequestParam(required = false) String projectCode) {
		try {
			List<ProjectUserVO> projectUserVOList = Lists.newArrayList();
			// 查询用户
			List<ProjectUserEntity> userEntityList = projectUserService.findByProjectCode(projectCode);
			for (ProjectUserEntity userEntity : userEntityList) {
				ProjectUserVO projectUserVO = new ProjectUserVO();
				projectUserVO.setUsername(userEntity.getUsername());
				projectUserVO.setPassword(userEntity.getPassword());
				projectUserVO.setEventCity(userEntity.getEventCity());
				projectUserVO.setSerial(userEntity.getSerial());
				projectUserVO.setRole(userEntity.getRole());
				List<DealerVO> dealers = Lists.newArrayList();
				// 查询关联经销商
				List<ProjectUserDealerEntity> userDealerEntityList = projectUserDealerService
						.findByProjectUserId(userEntity.getId());
				for (ProjectUserDealerEntity userDealerEntity : userDealerEntityList) {
					DealerVO dealerVO = new DealerVO();
					dealerVO.setProvince(userDealerEntity.getProvince());
					dealerVO.setCity(userDealerEntity.getCity());
					dealerVO.setCode(userDealerEntity.getCode());
					dealerVO.setName(userDealerEntity.getName());
					dealers.add(dealerVO);
				}
				projectUserVO.setDealers(dealers);
				projectUserVOList.add(projectUserVO);
			}
			return ApiResponse.newSuccessResponse(List.class, projectUserVOList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ApiResponse.newErrorResponse(List.class, e.getMessage());
		}
	}

}
