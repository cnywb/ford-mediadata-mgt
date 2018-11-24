package com.ford.mediadata.mgt.service.project.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.repository.project.ProjectUserDealerRepository;
import com.ford.mediadata.mgt.service.project.ProjectUserDealerService;

/**
 * 项目活动用户关联经销商
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class ProjectUserDealerServiceImpl implements ProjectUserDealerService {

	@Resource
	private ProjectUserDealerRepository projectUserDealerRepository;

	@Override
	public List<ProjectUserDealerEntity> list(ProjectUserDealerEntity condition) {
		return projectUserDealerRepository.list(condition);
	}

	@CacheEvict(cacheNames = {
			"projectCache" }, key = "'projectUserDealer_findByProjectUserId_' + #entity.getProjectUserId()")
	@Override
	public void save(ProjectUserDealerEntity entity) {
		projectUserDealerRepository.save(entity);
	}

	@CacheEvict(cacheNames = {
			"projectCache" }, key = "'projectUserDealer_findByProjectUserId_' + #entity.getProjectUserId()")
	@Override
	public void update(ProjectUserDealerEntity entity) {
		projectUserDealerRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "projectCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		projectUserDealerRepository.delete(id);
	}

	@Cacheable(cacheNames = { "projectCache" }, key = "'projectUserDealer_findByProjectUserId_' + #projectUserId")
	@Override
	public List<ProjectUserDealerEntity> findByProjectUserId(Long projectUserId) {
		Assert.notNull(projectUserId, "用户ID为空");
		ProjectUserDealerEntity condition = new ProjectUserDealerEntity();
		condition.setProjectUserId(projectUserId);
		return projectUserDealerRepository.list(condition);
	}

}
