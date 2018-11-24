package com.ford.mediadata.mgt.repository.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;

import io.dabing.core.repository.JpaRepository;

/**
 * 活动城市仓储
 * 
 * @author chunliang.you
 *
 */
public interface ProjectCityRepository extends JpaRepository<ProjectCityEntity, Long> {
	
	List<ProjectCityEntity> list(ProjectCityEntity condition);

}