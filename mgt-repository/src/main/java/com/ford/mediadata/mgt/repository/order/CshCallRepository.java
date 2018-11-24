package com.ford.mediadata.mgt.repository.order;

import java.util.List;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshCallEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 车商汇订单仓储
 * 
 * @author chunliang.you
 *
 */
public interface CshCallRepository extends JpaRepository<CshCallEntity, Long> {

	Page<CshCallEntity> findByGridPage(GridPage page, CshCallEntityCondition condition);

	List<CshCallEntity> list(CshCallEntityCondition condition);

}