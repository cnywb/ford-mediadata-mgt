package com.ford.mediadata.mgt.repository.order.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshCallEntityCondition;
import com.ford.mediadata.mgt.repository.order.CshCallRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 车商汇话单仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class CshCallRepositoryImpl extends DefaultJpaRepository<CshCallEntity, Long> implements CshCallRepository {

	@Override
	public Page<CshCallEntity> findByGridPage(GridPage page, CshCallEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<CshCallEntity> list(CshCallEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(CshCallEntityCondition condition) {
		return StringQuery.newQuery().query("from CshCallEntity t where 1 = 1 ")
				// id
				.predicateNotNull(condition.getId()).query(" and t.id = :id").param("id", condition.getId())
				// orderId
				.predicateHasText(condition.getOrderId()).query(" and t.orderId = :orderId")
				.param("orderId", condition.getOrderId())
				// uploadTimeStart
				.predicateNotNull(condition.getUploadTimeStartDate()).query(" and t.uploadTime >= :uploadTimeStartDate")
				.param("uploadTimeStartDate", condition.getUploadTimeStartDate())
				// uploadTimeEnd
				.predicateNotNull(condition.getUploadTimeEndDate()).query(" and t.uploadTime <= :uploadTimeEndDate")
				.param("uploadTimeEndDate", condition.getUploadTimeEndDate())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}