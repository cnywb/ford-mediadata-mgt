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

import com.ford.mediadata.mgt.entity.project.ProjectEntity;
import com.ford.mediadata.mgt.repository.project.ProjectRepository;
import com.ford.mediadata.mgt.service.project.ProjectService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 项目活动服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectRepository projectRepository;

	@Override
	public Page<ProjectEntity> findByGridPage(GridPage page, ProjectEntity condition) {
		return projectRepository.findByGridPage(page, condition);
	}

	@Override
	public List<ProjectEntity> list(ProjectEntity condition) {
		return projectRepository.list(condition);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'project_findByCode_' + #entity.getCode()")
	@Override
	public void save(ProjectEntity entity) {
		ProjectEntity condition = new ProjectEntity();
		condition.setCode(entity.getCode());
		List<ProjectEntity> list = projectRepository.list(condition);
		Assert.isTrue(CollectionUtils.isEmpty(list), "项目活动代码已存在");
		projectRepository.save(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, key = "'project_findByCode_' + #entity.getCode()")
	@Override
	public void update(ProjectEntity entity) {
		projectRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		projectRepository.delete(id);
	}

	@Cacheable(cacheNames = { "projectCache" }, key = "'project_findByCode_' + #code")
	@Override
	public ProjectEntity findByCode(String code) {
		Assert.isTrue(StringUtils.isNotEmpty(code), "项目活动代码为空");
		ProjectEntity condition = new ProjectEntity();
		condition.setCode(code);
		List<ProjectEntity> list = projectRepository.list(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
