package com.ford.mediadata.mgt.repository.order;

import java.util.List;

import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 车商汇订单仓储
 * 
 * @author chunliang.you
 *
 */
public interface CshOrderRepository extends JpaRepository<CshOrderEntity, Long> {

	Page<CshOrderEntity> findByGridPage(GridPage page, CshOrderEntityCondition condition);

	List<CshOrderEntity> list(CshOrderEntityCondition condition);

}