package com.ford.mediadata.mgt.service.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 项目活动用户服务
 * 
 * @author chunliang.you
 *
 */
public interface ProjectUserService {

	Page<ProjectUserEntity> findByGridPage(GridPage page, ProjectUserEntityCondition condition);

	List<ProjectUserEntity> list(ProjectUserEntityCondition condition);

	void save(ProjectUserEntity entity);

    void update(ProjectUserEntity entity);

	void delete(Long id);

	List<ProjectUserEntity> findByProjectCode(String projectCode);

}
