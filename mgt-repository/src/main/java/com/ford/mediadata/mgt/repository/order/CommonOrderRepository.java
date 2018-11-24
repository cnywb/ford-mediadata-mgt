package com.ford.mediadata.mgt.repository.order;

import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * 订单仓储
 * 
 * @author chunliang.you
 *
 */
public interface CommonOrderRepository extends JpaRepository<CommonOrderEntity, Long> {

	/**
	 * 根据订单类商机ID查询
	 * 
	 * @param orderId
	 * @return
	 */
	CommonOrderEntity findByOrderId(Long orderId);


	/**
	 * 根据GridPage对象按分页查找服务
	 *
	 * @param page 分页对象，里有关键字keyWord,供模糊匹配
	 * @return 分页结果数据对象集合
	 */
	Page<CommonOrderEntity> pagingBy(GridPage page);

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
	List findByCommonResource(String userName, String orderTypeName, String provinceName, String status, String cityName , String accountName,String orderId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id);
}