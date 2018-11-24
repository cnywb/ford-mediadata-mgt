package com.ford.mediadata.mgt.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.dto.order.OrderNotifyException;
import com.ford.mediadata.mgt.dto.order.detail.CommonOrderNotify;
import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import com.ford.mediadata.mgt.repository.order.CommonOrderRepository;
import com.ford.mediadata.mgt.service.order.CommonOrderService;
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
 * 订单服务
 * 
 * @author chunliang.you
 *
 */
@Slf4j
@Transactional
@Service
public class CommonOrderServiceImpl extends AbstractService implements CommonOrderService {

	@Resource
	private CommonOrderRepository commonOrderRepository;


	/**
	 * 根据GridPage对象按分页查找BatchTask
	 *
	 * @param page 分页对象，里面有关键字进行模糊匹配服务名称
	 * @return page
	 */
	@Override
	public Page<CommonOrderEntity> doPagingBy(GridPage page) {
		return commonOrderRepository.pagingBy(page);
	}

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param orderTypeName 订单类型名称
	 * @param provinceName  省名称
	 * @param status        状态
	 * @param userName      订购者名称
	 * @param cityName      市名称
	 * @param accountName   销售顾问
	 * @param orderId       订单类商机ID
	 */
	@Override
	public Page<CommonOrderEntity> doPagingBy(GridPage page, String orderTypeName, String provinceName, Integer status, String userName, String cityName, String accountName, Long orderId ,Date createTimeStartDate, Date createTimeEndDate, Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id) {
		return commonOrderRepository.doPagingBy(page,orderTypeName,provinceName,status,userName,cityName,accountName,orderId,createTimeStartDate,createTimeEndDate,createdDateStartDate,createdDateEndDate,updatedDateStartDate,updatedDateEndDate, id);
	}


	@Override
	public void saveCommonOrder(CommonOrderNotify commonOrder) throws OrderNotifyException {
		Assert.notNull(commonOrder);
		if (log.isDebugEnabled()) {
			log.debug("保存订单{}", JSON.toJSONString(commonOrder));
		}
		CommonOrderEntity entity = new CommonOrderEntity();
		BeanUtils.copyProperties(commonOrder, entity);
		commonOrderRepository.save(entity);
	}

//	@Override
//	public void updateCommonOrder(CommonOrderNotify commonOrder) throws OrderNotifyException {
//		Assert.notNull(commonOrder);
//		if (log.isDebugEnabled()) {
//			log.debug("更新订单{}", JSON.toJSONString(commonOrder));
//		}
//		CommonOrderEntity existOrder = findByOrderId(commonOrder.getOrderId());
//		BeanUtils.copyProperties(commonOrder, existOrder);
//		commonOrderRepository.update(existOrder);
//	}

//	@Override
//	public CommonOrderEntity findByOrderId(Long orderId) {
//		Assert.notNull(orderId);
//		return commonOrderRepository.findByOrderId(orderId);
//	}

	/**
	 * execl导出
	 * @param orderId 订单类商机ID
	 * @param userName      订购者名称
	 * @param orderTypeName 订单类型名称
	 * @param provinceName  省名称
	 * @param status        状态
	 * @param cityName      市名称
	 * @param accountName   销售顾问
	 * @param createTimeStartDate
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	@Override
	public List<Object[]> findByCommonResource(String userName, String orderTypeName, String provinceName, String status, String cityName, String accountName,String orderId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id) {
		return commonOrderRepository.findByCommonResource(orderTypeName,userName,provinceName,cityName,status,accountName,orderId,createTimeStartDate,createTimeEndDate,createdDateStartDate,createdDateEndDate,updatedDateStartDate,updatedDateEndDate, id);
	}

}