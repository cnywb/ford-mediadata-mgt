package com.ford.mediadata.mgt.repository.project.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntityCondition;
import com.ford.mediadata.mgt.repository.project.ProjectUserRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 活动用户仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class ProjectUserRepositoryImpl extends DefaultJpaRepository<ProjectUserEntity, Long>
		implements ProjectUserRepository {

	@Override
	public Page<ProjectUserEntity> findByGridPage(GridPage page, ProjectUserEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<ProjectUserEntity> list(ProjectUserEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(ProjectUserEntityCondition condition) {
		return StringQuery.newQuery().query("from ProjectUserEntity t where 1 = 1 ")
				// projectCode
				.predicateHasText(condition.getProjectCode()).query(" and t.projectCode = :projectCode")
				.param("projectCode", condition.getProjectCode())
				// username
				.predicateHasText(condition.getUsername()).query(" and t.username = :username")
				.param("username", condition.getUsername())
				// usernameLike
				.predicateHasText(condition.getUsernameLike()).query(" and t.username like :usernameLike")
				.likeParam("usernameLike", condition.getUsernameLike())
				// role
				.predicateNotNull(condition.getRole()).query(" and t.role = :role").param("role", condition.getRole())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}