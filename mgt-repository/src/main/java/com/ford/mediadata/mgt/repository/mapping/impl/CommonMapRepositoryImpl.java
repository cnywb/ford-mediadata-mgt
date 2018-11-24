package com.ford.mediadata.mgt.repository.mapping.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.mapping.CommonMapEntity;
import com.ford.mediadata.mgt.entity.mapping.CommonMapEntityCondition;
import com.ford.mediadata.mgt.repository.mapping.CommonMapRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * K/V MAP
 * 
 * @author chunliang.you
 *
 */
@Repository
public class CommonMapRepositoryImpl extends DefaultJpaRepository<CommonMapEntity, Long>
		implements CommonMapRepository {

	@Override
	public Page<CommonMapEntity> findByGridPage(GridPage page, CommonMapEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<CommonMapEntity> list(CommonMapEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(CommonMapEntityCondition condition) {
		return StringQuery.newQuery().query("from CommonMapEntity t where 1 = 1 ")
				// mapKey
				.predicateHasText(condition.getMapKey()).query(" and t.mapKey = :mapKey")
				.param("mapKey", condition.getMapKey())
				// mapKeyLike
				.predicateHasText(condition.getMapKeyLike()).query(" and t.mapKey like :mapKeyLike")
				.likeParam("mapKeyLike", condition.getMapKeyLike())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}