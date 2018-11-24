package com.ford.mediadata.mgt.repository.order;

import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * 话单仓储
 * 
 * @author chunliang.you
 *
 */
public interface CallOrderRepository extends JpaRepository<CallOrderEntity, Long> {

	/**
	 * 根据通话类商机ID查询
	 * 
	 * @param callBusinessOpportunityId
	 * @return
	 */
	CallOrderEntity findByCallBusinessOpportunityId(Long callBusinessOpportunityId);

	/**
	 * 根据GridPage对象按分页查找服务
	 *
	 * @param page 分页对象，里有关键字keyWord,供模糊匹配
	 * @return 分页结果数据对象集合
	 */
	Page<CallOrderEntity> pagingBy(GridPage page);

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
	 * execl导出
	 * @param callBusinessOpportunityId   通话类商机Id
	 * @param agentName          坐席
	 * @param cityName           市名称
	 * @param provinceName       省名称
	 * @param dealerFollowStatus 状态
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	List findByCallResource(String agentName,String provinceName, String cityName , String dealerFollowStatus,String callBusinessOpportunityId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id);

}