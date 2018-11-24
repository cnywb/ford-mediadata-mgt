package com.ford.mediadata.mgt.service.order;

import java.util.Date;
import java.util.List;

import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

/**
 * 数据转换中间表服务
 * 
 * @author chunliang.you
 *
 */
public interface OrderTransService extends Service {

	Page<OrderTransEntity> findByGridPage(GridPage page, OrderTransEntityCondition condition);

	List<OrderTransEntity> list(OrderTransEntityCondition condition);

	void save(OrderTransEntity entity);

	void update(OrderTransEntity entity);

	void delete(Long id);

	Integer executeTransSapTask(Date importDateStartDate, Date importDateEndDate, Integer maxCount, String sapFileName, String time, String serialNo) throws Exception;
	
	OrderTransEntity findByOrderId(String orderId);

}