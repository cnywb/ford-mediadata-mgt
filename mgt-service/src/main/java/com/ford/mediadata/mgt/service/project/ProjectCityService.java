package com.ford.mediadata.mgt.service.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;

/**
 * 项目活动城市服务
 * 
 * @author chunliang.you
 *
 */
public interface ProjectCityService {

	List<ProjectCityEntity> list(ProjectCityEntity condition);

	void save(ProjectCityEntity entity);

	void update(ProjectCityEntity entity);

	void delete(Long id);

	List<ProjectCityEntity> findByProjectCode(String projectCode);

}
