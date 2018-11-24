package com.ford.mediadata.mgt.service.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;

/**
 * 项目活动用户关联经销商
 * 
 * @author chunliang.you
 *
 */
public interface ProjectUserDealerService {

	List<ProjectUserDealerEntity> list(ProjectUserDealerEntity condition);

	void save(ProjectUserDealerEntity entity);

    void update(ProjectUserDealerEntity entity);

	void delete(Long id);

	List<ProjectUserDealerEntity> findByProjectUserId(Long projectUserId);

}
