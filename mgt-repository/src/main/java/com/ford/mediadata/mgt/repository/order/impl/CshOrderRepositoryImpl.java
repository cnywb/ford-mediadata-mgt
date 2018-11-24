package com.ford.mediadata.mgt.repository.order.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntityCondition;
import com.ford.mediadata.mgt.repository.order.CshOrderRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 车商汇订单仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class CshOrderRepositoryImpl extends DefaultJpaRepository<CshOrderEntity, Long> implements CshOrderRepository {

	@Override
	public Page<CshOrderEntity> findByGridPage(GridPage page, CshOrderEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<CshOrderEntity> list(CshOrderEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(CshOrderEntityCondition condition) {
		return StringQuery.newQuery().query("from CshOrderEntity t where 1 = 1 ")
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