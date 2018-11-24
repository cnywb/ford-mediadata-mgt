package com.ford.mediadata.mgt.service.order;

import java.util.List;

import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 车商汇订单服务
 * 
 * @author chunliang.you
 *
 */
public interface CshOrderService extends BatchSaveService<CshOrderEntity> {

	Page<CshOrderEntity> findByGridPage(GridPage page, CshOrderEntityCondition condition);

	List<CshOrderEntity> list(CshOrderEntityCondition condition);

	void save(CshOrderEntity entity);

	void update(CshOrderEntity entity);

	void delete(Long id);

//	CshOrderEntity findByOrderId(String orderId);

}