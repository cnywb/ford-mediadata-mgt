package com.ford.mediadata.mgt.service.order;

import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;
import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单服务
 * 
 * @author chunliang.you
 *
 */
public interface CommonOrderService extends Service {

	/**
	 * 根据GridPage对象按分页查找BatchTask
	 *
	 * @param page 分页对象，里面有关键字进行模糊匹配服务名称
	 * @return page
	 */
	Page<CommonOrderEntity> doPagingBy(GridPage page);

	/**
	 * 分页查询
	 * @param page
	 * @param orderTypeName 订单类型名称
	 * @param provinceName 省名称
	 * @param status 状态
	 * @param userName 订购者名称
	 * @param cityName 市名称
	 * @param accountName 销售顾问
	 * @param orderId 订单类商机ID
	 * @param createTimeStartDate
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	Page<CommonOrderEntity> doPagingBy(GridPage page, String orderTypeName, String provinceName, Integer status, String userName, String cityName, String accountName, Long orderId, Date createTimeStartDate, Date createTimeEndDate, Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id);

	/**
	 * 保存
	 * 
	 * @param commonOrder
	 */
	void saveCommonOrder(CommonOrderNotify commonOrder);

	/**
	 * 更新
	 * 
	 * @param commonOrder
	 */
//	void updateCommonOrder(CommonOrderNotify commonOrder);

	/**
	 * 根据订单类商机ID查询
	 * 
	 * @param orderId
	 * @return
	 */
//	CommonOrderEntity findByOrderId(Long orderId);

	/**
	 * execl导出
	 * @param orderId 订单类商机ID
	 * @param orderTypeName 订单类型名称
	 * @param provinceName 省名称
	 * @param status 状态
	 * @param userName 订购者名称
	 * @param cityName 市名称
	 * @param accountName 销售顾问
	 * @param createTimeStartDate
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	List<Object[]> findByCommonResource(String userName,String orderTypeName,String provinceName,String status, String cityName , String accountName,String orderId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id);

}