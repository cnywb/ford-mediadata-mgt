package com.ford.mediadata.mgt.repository.project.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;
import com.ford.mediadata.mgt.repository.project.ProjectCityRepository;

import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 活动城市仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class ProjectCityRepositoryImpl extends DefaultJpaRepository<ProjectCityEntity, Long>
		implements ProjectCityRepository {

	@Override
	public List<ProjectCityEntity> list(ProjectCityEntity condition) {
		StringQuery query = StringQuery.newQuery().query("from ProjectCityEntity t where 1 = 1 ")
				// code
				.predicateHasText(condition.getProjectCode()).query(" and t.projectCode = :projectCode")
				.param("projectCode", condition.getProjectCode())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
		return find(query);
	}

}