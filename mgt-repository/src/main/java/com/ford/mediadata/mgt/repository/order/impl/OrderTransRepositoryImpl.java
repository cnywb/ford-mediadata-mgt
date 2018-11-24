package com.ford.mediadata.mgt.repository.order.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;
import com.ford.mediadata.mgt.repository.order.OrderTransRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 数据转换中间表仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class OrderTransRepositoryImpl extends DefaultJpaRepository<OrderTransEntity, Long>
		implements OrderTransRepository {

	@Override
	public Page<OrderTransEntity> findByGridPage(GridPage page, OrderTransEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<OrderTransEntity> list(OrderTransEntityCondition condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(OrderTransEntityCondition condition) {
		return StringQuery.newQuery().query("from OrderTransEntity t where 1 = 1 ")
				// codeId
				.predicateHasText(condition.getOrderId()).query(" and t.orderId = :orderId")
				.param("orderId", condition.getOrderId())
				// campaignChannelId
				.predicateHasText(condition.getCampaignChannelId())
				.query(" and t.campaignChannelId = :campaignChannelId")
				.param("campaignChannelId", condition.getCampaignChannelId())
				// importDateStartDate
				.predicateNotNull(condition.getImportDateStartDate()).query(" and t.importDate >= :importDateStartDate")
				.param("importDateStartDate", condition.getImportDateStartDate())
				// importDateEndDate
				.predicateNotNull(condition.getImportDateEndDate()).query(" and t.importDate <= :importDateEndDate")
				.param("importDateEndDate", condition.getImportDateEndDate())
				// sapFileStatus
				.predicateNotNull(condition.getSapFileStatus()).query(" and t.sapFileStatus = :sapFileStatus")
				.param("sapFileStatus", condition.getSapFileStatus())
				// localToDealerId
				.predicateHasText(condition.getLocalToDealerId()).query(" and t.localToDealerId = :localToDealerId")
				.param("localToDealerId", condition.getLocalToDealerId())
				// refId
				.predicateNotNull(condition.getRefId()).query(" and t.refId = :refId")
				.param("refId", condition.getRefId())
				// orderBusinessOpportunityCreateTimeStartDate
				.predicateNotNull(condition.getOrderBusinessOpportunityCreateTimeStartDate())
				.query(" and t.orderBusinessOpportunityCreateTime >= :orderBusinessOpportunityCreateTimeStartDate")
				.param("orderBusinessOpportunityCreateTimeStartDate",
						condition.getOrderBusinessOpportunityCreateTimeStartDate())
				// orderBusinessOpportunityCreateTimeEndDate
				.predicateNotNull(condition.getOrderBusinessOpportunityCreateTimeEndDate())
				.query(" and t.orderBusinessOpportunityCreateTime <= :orderBusinessOpportunityCreateTimeEndDate")
				.param("orderBusinessOpportunityCreateTimeEndDate",
						condition.getOrderBusinessOpportunityCreateTimeEndDate())
				// order
				.predicateHasText(condition.getOrderBy()).query(" order by t." + condition.getOrderBy())
				.predicateNotHasText(condition.getOrderBy()).query(" order by t.id desc")
				// build
				.build();
	}

}