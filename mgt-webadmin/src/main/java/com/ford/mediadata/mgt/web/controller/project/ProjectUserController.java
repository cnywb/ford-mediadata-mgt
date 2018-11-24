package com.ford.mediadata.mgt.web.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntityCondition;
import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;
import com.ford.mediadata.mgt.service.dealer.DealerService;
import com.ford.mediadata.mgt.service.project.ProjectUserDealerService;
import com.ford.mediadata.mgt.service.project.ProjectUserService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.project.vo.BatchCreateProjectUserRequest;
import com.ford.mediadata.mgt.web.controller.project.vo.PageProjectUserRequest;
import com.google.common.collect.Lists;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 项目活动用户
 * 
 * @author chunliang.you
 *
 */
@Controller
public class ProjectUserController {

	@Resource
	private DealerService dealerService;

	@Resource
	private ProjectUserService projectUserService;

	@Resource
	private ProjectUserDealerService projectUserDealerService;

	@ApiService(transCode = "pageProjectUser")
	public Page<ProjectUserEntity> page(PageProjectUserRequest req) {
		ProjectUserEntityCondition condition = new ProjectUserEntityCondition();
		BeanUtils.copyProperties(req, condition);
		return projectUserService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "editProjectUser")
	public void edit(ProjectUserEntity entity) {
		if (entity.getId() == null) {
			projectUserService.save(entity);
		} else {
			projectUserService.update(entity);
		}
	}

	@ApiService(transCode = "removeProjectUser")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				projectUserService.delete(data.getId());
			}
		}
	}

	@ApiService(transCode = "removeProjectUserByCondition")
	public void removeByCondition(ProjectUserEntityCondition condition) {
		Assert.notNull(condition, "请求为空");
		Assert.hasText(condition.getProjectCode(), "项目活动代码为空");
		List<ProjectUserEntity> list = projectUserService.list(condition);
		for (ProjectUserEntity entity : list) {
			projectUserService.delete(entity.getId());
		}
	}

	@ApiService(transCode = "batchCreateProjectUser")
	public void batchCreate(BatchCreateProjectUserRequest req) {
		Assert.notNull(req, "请求为空");
		Assert.hasText(req.getProjectCode(), "项目活动代码为空");
		Assert.notNull(req.getRole(), "项目活动代码为空");
		Assert.hasText(req.getUserNamePrefix(), "用户名前缀为空");
		Assert.hasText(req.getPassword(), "密码为空");
		Assert.notNull(req.getBeginIndex(), "用户编号起始值为空");
		Assert.notNull(req.getEndIndex(), "用户编号结束值为空");
		Assert.isTrue(req.getBeginIndex() >= 0, "用户编号起始值必须大于等于0");
		Assert.isTrue(req.getEndIndex() <= 999, "用户编号结束值必须小于等于999");
		Assert.isTrue(req.getBeginIndex() <= req.getEndIndex(), "用户编号结束值必须大于等于用户编号起始值");
		List<DealerEntity> dealerList = Lists.newArrayList();
		if (StringUtils.isNotEmpty(req.getDealerCodes())) {
			for (String dealerCode : req.getDealerCodes().split(",")) {
				Assert.hasText(dealerCode, "关联经销商代码格式不正确");
				DealerEntity dealer = dealerService.findByCode(dealerCode);
				Assert.notNull(dealer, String.format("经销商[%s]不存在", dealerCode));
				dealerList.add(dealer);
			}
		}
		for (int index = req.getBeginIndex(); index <= req.getEndIndex(); index++) {
			StringBuilder usernameBuilder = new StringBuilder();
			usernameBuilder.append(req.getUserNamePrefix());
			if (index < 100) {
				usernameBuilder.append("0");
			}
			if (index < 10) {
				usernameBuilder.append("0");
			}
			usernameBuilder.append(index);
			ProjectUserEntity userEntity = new ProjectUserEntity();
			userEntity.setProjectCode(req.getProjectCode());
			userEntity.setRole(req.getRole());
			userEntity.setEventProvince(req.getEventProvince());
			userEntity.setEventCity(req.getEventCity());
			userEntity.setUsername(usernameBuilder.toString());
			userEntity.setPassword(req.getPassword());
			userEntity.setSerial(req.getSerial());
			projectUserService.save(userEntity);
			for (DealerEntity dealer : dealerList) {
				ProjectUserDealerEntity userDealerEntity = new ProjectUserDealerEntity();
				userDealerEntity.setProjectUserId(userEntity.getId());
				userDealerEntity.setName(dealer.getName());
				userDealerEntity.setCode(dealer.getCode());
				userDealerEntity.setProvince(dealer.getProvince());
				userDealerEntity.setCity(dealer.getCity());
				projectUserDealerService.save(userDealerEntity);
			}
		}
	}

}
