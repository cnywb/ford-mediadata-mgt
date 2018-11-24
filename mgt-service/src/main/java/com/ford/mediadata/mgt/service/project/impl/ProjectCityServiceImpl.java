package com.ford.mediadata.mgt.service.project.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;
import com.ford.mediadata.mgt.repository.project.ProjectCityRepository;
import com.ford.mediadata.mgt.service.project.ProjectCityService;

/**
 * 项目活动城市服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class ProjectCityServiceImpl implements ProjectCityService {

	@Resource
	private ProjectCityRepository projectCityRepository;

	@Override
	public List<ProjectCityEntity> list(ProjectCityEntity condition) {
		return projectCityRepository.list(condition);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'projectCity_findByProjectCode_' + #entity.getProjectCode()")
	@Override
	public void save(ProjectCityEntity entity) {
		projectCityRepository.save(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'projectCity_findByProjectCode_' + #entity.getProjectCode()")
	@Override
	public void update(ProjectCityEntity entity) {
		projectCityRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		projectCityRepository.delete(id);
	}

	@Cacheable(cacheNames = { "projectCache" }, key = "'projectCity_findByProjectCode_' + #projectCode")
	@Override
	public List<ProjectCityEntity> findByProjectCode(String projectCode) {
		Assert.isTrue(StringUtils.isNotEmpty(projectCode), "项目活动代码为空");
		ProjectCityEntity condition = new ProjectCityEntity();
		condition.setProjectCode(projectCode);
		return projectCityRepository.list(condition);
	}

}
