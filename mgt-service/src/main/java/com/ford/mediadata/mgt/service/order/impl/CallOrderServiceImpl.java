package com.ford.mediadata.mgt.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.dto.order.detail.CallOrderNotify;
import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import com.ford.mediadata.mgt.repository.order.CallOrderRepository;
import com.ford.mediadata.mgt.service.order.CallOrderService;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 话单服务
 * 
 * @author chunliang.you
 *
 */
@Slf4j
@Transactional
@Service
public class CallOrderServiceImpl extends AbstractService implements CallOrderService {

	@Resource
	private CallOrderRepository callOrderRepository;


	/**
	 * 根据GridPage对象按分页查找BatchTask
	 *
	 * @param page 分页对象，里面有关键字进行模糊匹配服务名称
	 * @return page
	 */
	@Override
	public Page<CallOrderEntity> doPagingBy(GridPage page) {
		return callOrderRepository.pagingBy(page);
	}

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param agentName                 座席
	 * @param provinceName              省名称
	 * @param dealerFollowStatus        最近状态
	 * @param cityName                  城市名称
	 * @param callBusinessOpportunityId 通话类商机Id
	 * @param createTimeStartDate
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	@Override
	public Page<CallOrderEntity> doPagingBy(GridPage page, String agentName, String provinceName, Integer dealerFollowStatus, String cityName, Long callBusinessOpportunityId,Date createTimeStartDate, Date createTimeEndDate, Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id) {
		return callOrderRepository.doPagingBy(page,agentName,provinceName,dealerFollowStatus,cityName,callBusinessOpportunityId,createTimeStartDate,createTimeEndDate,createdDateStartDate,createdDateEndDate,updatedDateStartDate,updatedDateEndDate, id);
	}

	@Override
	public void saveCallOrder(CallOrderNotify callOrder) {
		Assert.notNull(callOrder);
		if (log.isDebugEnabled()) {
			log.debug("保存话单{}", JSON.toJSONString(callOrder));
		}
		CallOrderEntity entity = new CallOrderEntity();
		BeanUtils.copyProperties(callOrder, entity);
		callOrderRepository.save(entity);
	}

//	@Override
//	public void updateCallOrder(CallOrderNotify callOrder) {
//		Assert.notNull(callOrder);
//		if (log.isDebugEnabled()) {
//			log.debug("更新话单{}", JSON.toJSONString(callOrder));
//		}
//		CallOrderEntity existOrder = findByCallBusinessOpportunityId(callOrder.getCallBusinessOpportunityId());
//		BeanUtils.copyProperties(callOrder, existOrder);
//		callOrderRepository.update(existOrder);
//	}

//	@Override
//	public CallOrderEntity findByCallBusinessOpportunityId(Long callBusinessOpportunityId) {
//		Assert.notNull(callBusinessOpportunityId);
//		return callOrderRepository.findByCallBusinessOpportunityId(callBusinessOpportunityId);
//	}

	/**
	 * execl导出
	 * @param callBusinessOpportunityId   通话类商机Id
	 * @param dealerFollowStatus   状态
	 * @param agentName                 坐席
	 * @param cityName                  市名称
	 * @param provinceName              省名称
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	@Override
	public List<Object[]> findByCallResource(String dealerFollowStatus, String agentName, String cityName, String provinceName,String callBusinessOpportunityId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id) {
		return callOrderRepository.findByCallResource(agentName,cityName,provinceName,dealerFollowStatus,callBusinessOpportunityId,createTimeStartDate,createTimeEndDate,createdDateStartDate,createdDateEndDate,updatedDateStartDate,updatedDateEndDate, id);
	}

}