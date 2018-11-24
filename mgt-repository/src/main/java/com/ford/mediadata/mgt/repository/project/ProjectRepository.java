package com.ford.mediadata.mgt.repository.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 项目活动仓储
 * 
 * @author chunliang.you
 *
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

	Page<ProjectEntity> findByGridPage(GridPage page, ProjectEntity condition);

	List<ProjectEntity> list(ProjectEntity condition);

}