package com.ford.mediadata.mgt.service.order.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.service.excel.ExcelDataSupportService;
import com.ford.mediadata.mgt.service.order.CallOrderService;

/**
 * Created by Administrator on 2017/1/10.
 */
@Service(value = "callOrderResourceExeclService")
public class CallOrderResourceExcelServiceImpl implements ExcelDataSupportService {

	@Autowired
	private CallOrderService callOrderService;

	/**
	 * 查询数据
	 *
	 * @param queryFields
	 *            提供的查询字段,要求返回的结果数组必须与该字段下标匹配
	 * @param from
	 *            查询那个类
	 * @param where
	 *            where条件
	 * @param orderBy
	 *            排序条件
	 * @param whereValue
	 *            页面上传递的参数值.
	 * @return
	 */
	@Override
	public List<Object[]> getExportDataList(String queryFields, String from, String where, String orderBy,
			Map<String, String> whereValue) {
		String agentName = whereValue.get("agentName");
		String cityName = whereValue.get("cityName");
		String provinceName = whereValue.get("provinceName");
		String dealerFollowStatus = whereValue.get("dealerFollowStatus");
		String callBusinessOpportunityId = whereValue.get("callBusinessOpportunityId");
		String createTimeStartDate = whereValue.get("createTimeStartDate");
		String createTimeEndDate = whereValue.get("createTimeEndDate");
		String createdDateStartDate = whereValue.get("createdDateStartDate");
		String createdDateEndDate = whereValue.get("createdDateEndDate");
		String updatedDateStartDate = whereValue.get("updatedDateStartDate");
		String updatedDateEndDate = whereValue.get("updatedDateEndDate");
		Long id = StringUtils.isEmpty(whereValue.get("id"))?null:Long.valueOf(whereValue.get("id"));
		List<Object[]> objects = callOrderService.findByCallResource(agentName, cityName, provinceName,
				dealerFollowStatus, callBusinessOpportunityId, createTimeStartDate, createTimeEndDate,
				createdDateStartDate, createdDateEndDate, updatedDateStartDate, updatedDateEndDate, id);
		return objects;
	}
}
