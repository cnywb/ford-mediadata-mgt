package com.ford.mediadata.mgt.repository.project.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.repository.project.ProjectUserDealerRepository;

import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 活动用户关联经销商
 * 
 * @author chunliang.you
 *
 */
@Repository
public class ProjectUserDealerRepositoryImpl extends DefaultJpaRepository<ProjectUserDealerEntity, Long>
		implements ProjectUserDealerRepository {

	@Override
	public List<ProjectUserDealerEntity> list(ProjectUserDealerEntity condition) {
		StringQuery query = StringQuery.newQuery().query("from ProjectUserDealerEntity t where 1 = 1 ")
				// projectUserId
				.predicateNotNull(condition.getProjectUserId()).query(" and t.projectUserId = :projectUserId")
				.param("projectUserId", condition.getProjectUserId())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
		return find(query);
	}

}