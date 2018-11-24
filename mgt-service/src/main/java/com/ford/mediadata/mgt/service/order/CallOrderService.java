package com.ford.mediadata.mgt.service.order;

import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.Date;
import java.util.List;

/**
 * 话单服务
 * 
 * @author chunliang.you
 *
 */
public interface CallOrderService extends Service {


	/**
	 * 根据GridPage对象按分页查找BatchTask
	 *
	 * @param page 分页对象，里面有关键字进行模糊匹配服务名称
	 * @return page
	 */
	Page<CallOrderEntity> doPagingBy(GridPage page);

	/**
	 * 分页查询
	 * @param page
	 * @param agentName 座席
	 * @param provinceName 省名称
	 * @param dealerFollowStatus 最近状态
	 * @param cityName 城市名称
	 * @param callBusinessOpportunityId 通话类商机Id
	 * @param createTimeStartDate
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	Page<CallOrderEntity> doPagingBy(GridPage page, String agentName, String provinceName, Integer dealerFollowStatus, String cityName, Long callBusinessOpportunityId, Date createTimeStartDate, Date createTimeEndDate, Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id);


	/**
	 * 保存
	 * 
	 * @param callOrder
	 */
	void saveCallOrder(CallOrderNotify callOrder);

	/**
	 * 更新
	 * 
	 * @param callOrder
	 */
//	void updateCallOrder(CallOrderNotify callOrder);

	/**
	 * 根据通话类商机ID查询
	 * 
	 * @param callBusinessOpportunityId
	 * @return
	 */
//	CallOrderEntity findByCallBusinessOpportunityId(Long callBusinessOpportunityId);


	/**
	 * execl导出
	 * @param callBusinessOpportunityId   通话类商机Id
	 * @param dealerFollowStatus
	 * @param agentName      坐席
	 * @param cityName      市名称
	 * @param provinceName  省名称
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	List<Object[]> findByCallResource(String dealerFollowStatus, String agentName, String cityName, String provinceName,String callBusinessOpportunityId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id);


}