package com.ford.mediadata.mgt.service.order;

import java.util.List;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshCallEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 车商汇话单服务
 * 
 * @author chunliang.you
 *
 */
public interface CshCallService extends BatchSaveService<CshCallEntity> {

	Page<CshCallEntity> findByGridPage(GridPage page, CshCallEntityCondition condition);

	List<CshCallEntity> list(CshCallEntityCondition condition);

	void save(CshCallEntity entity);

	void update(CshCallEntity entity);

	void delete(Long id);

//	CshCallEntity findByOrderId(String orderId);

}