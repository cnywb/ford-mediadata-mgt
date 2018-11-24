package com.ford.mediadata.mgt.repository.order.impl;

import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import com.ford.mediadata.mgt.repository.order.CommonOrderRepository;
import io.dabing.common.grid.GridPage;
import io.dabing.common.util.DateUtils;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 订单仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class CommonOrderRepositoryImpl extends DefaultJpaRepository<CommonOrderEntity, Long>
		implements CommonOrderRepository {

	@Override
	public CommonOrderEntity findByOrderId(Long orderId) {
		StringQuery query = StringQuery.newQuery().query("from CommonOrderEntity t where t.deleted = false")
				// orderId
				.predicateNotNull(orderId).query(" and t.orderId = :orderId").inParam("orderId", orderId)
				// build
				.build();
		List<CommonOrderEntity> list = find(query);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据GridPage对象按分页查找服务
	 *
	 * @param page 分页对象，里有关键字keyWord,供模糊匹配
	 * @return 分页结果数据对象集合
	 */
	@Override
	public Page<CommonOrderEntity> pagingBy(GridPage page) {
		StringQuery query = StringQuery.newQuery()
				.query("from CommonOrderEntity where 1 = 1 ")
				.predicateHasText(page.getKeyWord())
				.query(" and name like :name")
				.likeParam("name", page.getKeyWord())
				.predicate(Boolean.TRUE)
				.query(" and deleted = false")
				.query(" order by id desc").build();
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

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
	@Override
	public Page<CommonOrderEntity> doPagingBy(GridPage page, String orderTypeName, String provinceName, Integer status, String userName, String cityName, String accountName, Long orderId,Date createTimeStartDate, Date createTimeEndDate,Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id) {
		StringQuery query = StringQuery.newQuery()
				.query("from CommonOrderEntity where 1 = 1")
				.predicateNotNull(id)
				.query(" and id = :id ")
				.param("id", id)
				.predicateHasText (orderTypeName)
				.query (" and orderTypeName = :orderTypeName")
				.inParam ("orderTypeName",orderTypeName)
				.predicateHasText (userName)
				.query (" and userName = :userName")
				.inParam ("userName",userName)
				.predicateHasText (cityName)
				.query (" and cityName = :cityName")
				.inParam ("cityName",cityName)
				.predicateHasText (accountName)
				.query (" and accountName = :accountName")
				.inParam ("accountName",accountName)
				.predicateHasText (provinceName)
				.query (" and provinceName = :provinceName")
				.inParam ("provinceName",provinceName)
				.predicateNotNull (status)
				.query (" and status = :status")
				.inParam ("status",status)
				.predicateNotNull (orderId)
				.query (" and orderId = :orderId")
				.inParam ("orderId",orderId)

				.predicateNotNull (createTimeStartDate)
				.query(" and  orderBusinessOpportunityCreateTime  >=  :createTimeStartDate")
				.inParam ("createTimeStartDate", createTimeStartDate)
				.predicateNotNull (createTimeEndDate)
				.query(" and  orderBusinessOpportunityCreateTime  <=  :createTimeEndDate")
				.inParam ("createTimeEndDate", DateUtils.formatEndDate(createTimeEndDate))

				.predicateNotNull (createdDateStartDate)
				.query(" and  createdDate  >=  :createdDateStartDate")
				.inParam ("createdDateStartDate", createdDateStartDate)
				.predicateNotNull (createdDateEndDate)
				.query(" and  createdDate  <=  :createdDateEndDate")
				.inParam ("createdDateEndDate", DateUtils.formatEndDate(createdDateEndDate))

				.predicateNotNull (updatedDateStartDate)
				.query(" and  updatedDate  >=  :updatedDateStartDate")
				.inParam ("updatedDateStartDate", updatedDateStartDate)
				.predicateNotNull (updatedDateEndDate)
				.query(" and  updatedDate  <=  :updatedDateEndDate")
				.inParam ("updatedDateEndDate", DateUtils.formatEndDate(updatedDateEndDate))

				.predicate(Boolean.TRUE)
				.query(" order by id desc")
				.build();
		return find (query, PageRequest.newPage (page.getPageNumber (), page.getPageSize ()));
	}

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
	 *
	 */
	@Override
	public List findByCommonResource(String userName, String orderTypeName, String provinceName, String status, String cityName, String accountName , String orderId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id) {
		StringQuery query = StringQuery.newQuery()
				.query("SELECT ")
				.query("t.id id, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.created_date, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') createdDate, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.updated_date, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') updatedDate, ")
				.query("t.order_id orderId, ")
				.query("t.order_type_id orderTypeId, ")
				.query("t.order_type_name orderTypeName, ")
				.query("t.user_name userName, ")
				.query("t.user_mobile userMobile, ")
				.query("case t.user_gender when 0 then '女' when 1 then '男' else '' end userGender, ")
				.query("t.user_mail userMail, ")
				.query("t.car_color carColor, ")
				.query("t.brand_id brandId, ")
				.query("t.brand_name brandName, ")
				.query("t.cs_id csId, ")
				.query("t.cs_name csName, ")
				.query("t.account_name accountName, ")
				.query("case t.status when 0 then '未处理' when 1 then '已处理' else '' end status, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.fist_follow_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') fistFollowTime, ")
				.query("t.car_id carId, ")
				.query("t.car_type_name carTypeName, ")
				.query("t.car_name carName, ")
				.query("t.city_id cityId, ")
				.query("t.province_id provinceId, ")
				.query("t.province_name provinceName, ")
				.query("t.city_name cityName, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.opportunity_create_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') opportunityCreateTime, ")
				.query("t.dealer_name dealerName, ")
				.query("t.dealer_id dealerId, ")
				.query("t.order_remark orderRemark, ")
				.query("t.yefer_price yeferPrice, ")
				.query("t.year_type yearType ")
				.query("FROM ")
				.query("MD_COMMON_ORDER t ")
				.query("WHERE 1=1 ")
				.predicateNotNull(id)
				.query("and t.id = :id ")
				.param("id", id)
				.predicateHasText(orderId)
				.query("and t.order_id = :orderId ")
				.param("orderId",orderId)
				.predicateHasText(userName)
				.query("and t.user_name = :userName ")
				.param("userName",userName)
				.predicateHasText(orderTypeName)
				.query("and t.orderType_name = :orderTypeName ")
				.param("orderTypeName",orderTypeName)
				.predicateHasText(cityName)
				.query("and t.city_name = :cityName ")
				.param("cityName",cityName)
				.predicateHasText(provinceName)
				.query("and t.province_name = :provinceName ")
				.param("provinceName",provinceName)
				.predicateHasText(accountName)
				.query("and t.account_name = :accountName ")
				.param("accountName",accountName)
				.predicateHasText(status)
				.query("and t.status = :status ")
				.param("status",status)
				.predicateHasText(createTimeStartDate)
				.query("and t.opportunity_create_time >= to_date( :createTimeStartDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("createTimeStartDate", createTimeStartDate + " 00:00:00")
				.predicateHasText(createTimeEndDate)
				.query("and t.opportunity_create_time <= to_date( :createTimeEndDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("createTimeEndDate", createTimeEndDate + " 23:59:59")

				.predicateHasText(createdDateStartDate)
				.query("and t.created_date >= to_date( :createdDateStartDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("createdDateStartDate", createdDateStartDate + " 00:00:00")
				.predicateHasText(createdDateEndDate)
				.query("and t.created_date <= to_date( :createdDateEndDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("createdDateEndDate", createdDateEndDate + " 23:59:59")

				.predicateHasText(updatedDateStartDate)
				.query("and t.updated_date >= to_date( :updatedDateStartDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("updatedDateStartDate", updatedDateStartDate + " 00:00:00")
				.predicateHasText(updatedDateEndDate)
				.query("and t.updated_date <= to_date( :updatedDateEndDate, 'yyyy-mm-dd hh24:mi:ss') ")
				.param("updatedDateEndDate", updatedDateEndDate + " 23:59:59")
				.predicate(true)
				.query("order by t.id desc");
		return findBySql(query);
	}

}