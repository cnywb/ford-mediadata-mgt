package com.ford.mediadata.mgt.repository.project.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.project.ProjectEntity;
import com.ford.mediadata.mgt.repository.project.ProjectRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 项目活动仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class ProjectRepositoryImpl extends DefaultJpaRepository<ProjectEntity, Long> implements ProjectRepository {

	@Override
	public Page<ProjectEntity> findByGridPage(GridPage page, ProjectEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<ProjectEntity> list(ProjectEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(ProjectEntity condition) {
		return StringQuery.newQuery().query("from ProjectEntity t where 1 = 1 ")
				// code
				.predicateHasText(condition.getCode()).query(" and t.code = :code").param("code", condition.getCode())
				// name
				.predicateHasText(condition.getName()).query(" and t.name like :name")
				.likeParam("name", condition.getName())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}