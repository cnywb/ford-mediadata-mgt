package com.ford.mediadata.mgt.repository.order;

import java.util.List;

import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 数据转换中间表仓储
 * 
 * @author chunliang.you
 *
 */
public interface OrderTransRepository extends JpaRepository<OrderTransEntity, Long> {

	Page<OrderTransEntity> findByGridPage(GridPage page, OrderTransEntityCondition condition);

	List<OrderTransEntity> list(OrderTransEntityCondition condition);

}