package com.ford.mediadata.mgt.repository.order.impl;

import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import com.ford.mediadata.mgt.repository.order.CallOrderRepository;
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
 * 话单仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class CallOrderRepositoryImpl extends DefaultJpaRepository<CallOrderEntity, Long>
		implements CallOrderRepository {

	@Override
	public CallOrderEntity findByCallBusinessOpportunityId(Long callBusinessOpportunityId) {
		StringQuery query = StringQuery.newQuery().query("from CallOrderEntity t where t.deleted = false")
				// orderId
				.predicateNotNull(callBusinessOpportunityId)
				.query(" and t.callBusinessOpportunityId = :callBusinessOpportunityId")
				.inParam("callBusinessOpportunityId", callBusinessOpportunityId)
				// build
				.build();
		List<CallOrderEntity> list = find(query);
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
	public Page<CallOrderEntity> pagingBy(GridPage page) {
		StringQuery query = StringQuery.newQuery()
				.query("from CallOrderEntity where 1 = 1 ")
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
	 *  @param agentName          座席
	 * @param provinceName       省名称
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
	@Override
	public Page<CallOrderEntity> doPagingBy(GridPage page, String agentName, String provinceName, Integer dealerFollowStatus, String cityName, Long callBusinessOpportunityId, Date createTimeStartDate, Date createTimeEndDate, Date createdDateStartDate, Date createdDateEndDate, Date updatedDateStartDate, Date updatedDateEndDate, Long id) {
		StringQuery query = StringQuery.newQuery()
				.query("from CallOrderEntity where 1 = 1")
				.predicateNotNull(id)
				.query(" and id = :id ")
				.param("id", id)
				.predicateHasText (agentName)
				.query (" and agentName = :agentName")
				.inParam ("agentName",agentName)
				.predicateNotNull (callBusinessOpportunityId)
				.query (" and callBusinessOpportunityId = :callBusinessOpportunityId")
				.inParam ("callBusinessOpportunityId",callBusinessOpportunityId)
				.predicateHasText (provinceName)
				.query (" and provinceName = :provinceName")
				.inParam ("provinceName",provinceName)
				.predicateNotNull (dealerFollowStatus)
				.query (" and dealerFollowStatus = :dealerFollowStatus")
				.inParam ("dealerFollowStatus",dealerFollowStatus)
				.predicateHasText (cityName)
				.query (" and cityName = :cityName")
				.inParam ("cityName",cityName)


				.predicateNotNull (createTimeStartDate)
				.query(" and  callBusinessOpportunityCreateTime  >=  :createTimeStartDate")
				.inParam ("createTimeStartDate", createTimeStartDate)
				.predicateNotNull (createTimeEndDate)
				.query(" and  callBusinessOpportunityCreateTime  <=  :createTimeEndDate")
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
	 * @param callBusinessOpportunityId   通话类商机Id
	 * @param agentName          坐席
	 * @param provinceName       省名称
	 * @param cityName           市名称
	 * @param dealerFollowStatus 状态
	 * @param createTimeEndDate
	 * @param createdDateStartDate
	 * @param createdDateEndDate
	 * @param updatedDateStartDate
	 * @param updatedDateEndDate
	 */
	@Override
	public List findByCallResource(String agentName, String provinceName, String cityName, String dealerFollowStatus,String callBusinessOpportunityId,String createTimeStartDate,String createTimeEndDate,String createdDateStartDate,String createdDateEndDate,String updatedDateStartDate, String updatedDateEndDate, Long id) {
		StringQuery query = StringQuery.newQuery()
				.query("SELECT ")
				.query("t.id id, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.created_date, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') createdDate, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.updated_date, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') updatedDate, ")
				.query("t.call_order_id callBusinessOpportunityId, ")
				.query("t.dealer_id dealerId, ")
				.query("t.caller_phone_number callerPhoneNumber, ")
				.query("t.callee_real_number calleeRealNumber, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.call_begi_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') callBeginTime, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.call_finish_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') callFinishTime, ")
				.query("t.city_id cityId, ")
				.query("t.province_id provinceId, ")
				.query("t.caller_duration callerDuration, ")
				.query("t.agent_name agentName, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.fist_follow_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') fistFollowTime, ")
				.query("case t.dealer_follow_status when 0 then '已处理' when 1 then '未处理' else '' end dealerFollowStatus, ")
				.query("t.wait_duration waitDuration, ")
				.query("t.province_name provinceName, ")
				.query("t.city_name cityName, ")
				.query("TO_TIMESTAMP(TO_CHAR(t.opportunity_create_time, 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') opportunityCreateTime, ")
				.query("t.refer_price referPrice, ")
				.query("t.year_type yearType ")
				.query("FROM ")
				.query("MD_CALL_ORDER t ")
				.query("WHERE 1=1 ")
				.predicateNotNull(id)
				.query("and t.id = :id ")
				.param("id", id)
				.predicateHasText(callBusinessOpportunityId)
				.query("and t.call_order_id = :callBusinessOpportunityId ")
				.param("callBusinessOpportunityId",callBusinessOpportunityId)
				.predicateHasText(agentName)
				.query("and t.agent_name = :agentName ")
				.param("agentName",agentName)
				.predicateHasText(provinceName)
				.query("and t.province_name = :provinceName ")
				.param("provinceName",provinceName)
				.predicateHasText(cityName)
				.query("and t.city_name = :cityName ")
				.param("cityName",cityName)
				.predicateHasText(dealerFollowStatus)
				.query("and t.dealer_follow_status = :dealerFollowStatus ")
				.param("dealerFollowStatus",dealerFollowStatus)

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