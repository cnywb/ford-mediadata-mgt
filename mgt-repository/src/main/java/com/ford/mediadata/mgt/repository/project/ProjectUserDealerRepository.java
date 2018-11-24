package com.ford.mediadata.mgt.repository.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;

import io.dabing.core.repository.JpaRepository;

/**
 * 活动用户关联经销商
 * 
 * @author chunliang.you
 *
 */
public interface ProjectUserDealerRepository extends JpaRepository<ProjectUserDealerEntity, Long> {

	List<ProjectUserDealerEntity> list(ProjectUserDealerEntity condition);

}