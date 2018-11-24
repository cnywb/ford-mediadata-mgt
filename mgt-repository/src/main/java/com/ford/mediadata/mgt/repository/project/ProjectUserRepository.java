package com.ford.mediadata.mgt.repository.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 活动用户仓储
 * 
 * @author chunliang.you
 *
 */
public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity, Long> {

	Page<ProjectUserEntity> findByGridPage(GridPage page, ProjectUserEntityCondition condition);

	List<ProjectUserEntity> list(ProjectUserEntityCondition condition);

}