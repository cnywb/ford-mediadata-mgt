package com.ford.mediadata.mgt.service.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 项目活动服务
 * 
 * @author chunliang.you
 *
 */
public interface ProjectService {

	Page<ProjectEntity> findByGridPage(GridPage page, ProjectEntity condition);

	List<ProjectEntity> list(ProjectEntity condition);

    void save(ProjectEntity entity);

    void update(ProjectEntity entity);

    void delete(Long id);
	
	ProjectEntity findByCode(String code);

}
