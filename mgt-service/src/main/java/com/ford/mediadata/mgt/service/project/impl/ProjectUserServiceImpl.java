package com.ford.mediadata.mgt.service.project.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntityCondition;
import com.ford.mediadata.mgt.repository.project.ProjectUserRepository;
import com.ford.mediadata.mgt.service.project.ProjectUserService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 项目活动用户服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class ProjectUserServiceImpl implements ProjectUserService {

	@Resource
	private ProjectUserRepository projectUserRepository;

	@Override
	public Page<ProjectUserEntity> findByGridPage(GridPage page, ProjectUserEntityCondition condition) {
		return projectUserRepository.findByGridPage(page, condition);
	}

	@Override
	public List<ProjectUserEntity> list(ProjectUserEntityCondition condition) {
		return projectUserRepository.list(condition);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'projectUser_findByProjectCode_' + #entity.getProjectCode()")
	@Override
	public void save(ProjectUserEntity entity) {
		ProjectUserEntityCondition condition = new ProjectUserEntityCondition();
		condition.setProjectCode(entity.getProjectCode());
		condition.setUsername(entity.getUsername());
		List<ProjectUserEntity> list = projectUserRepository.list(condition);
		Assert.isTrue(CollectionUtils.isEmpty(list), "用户名已存在");
		projectUserRepository.save(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'projectUser_findByProjectCode_' + #entity.getProjectCode()")
	@Override
	public void update(ProjectUserEntity entity) {
		projectUserRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		projectUserRepository.delete(id);
	}

	@Cacheable(cacheNames = { "projectCache" }, key = "'projectUser_findByProjectCode_' + #projectCode")
	@Override
	public List<ProjectUserEntity> findByProjectCode(String projectCode) {
		Assert.isTrue(StringUtils.isNotEmpty(projectCode), "项目活动代码为空");
		ProjectUserEntityCondition condition = new ProjectUserEntityCondition();
		condition.setProjectCode(projectCode);
		return projectUserRepository.list(condition);
	}

}
