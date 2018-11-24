package com.ford.mediadata.mgt.service.order.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.service.excel.ExcelDataSupportService;
import com.ford.mediadata.mgt.service.order.CommonOrderService;

/**
 * Created by Administrator on 2017/1/10.
 */
@Service(value = "commonOrderResourceExeclService")
public class CommonOrderResourceExcelServiceImpl implements ExcelDataSupportService {

	@Autowired
	private CommonOrderService commonOrderService;

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
		String orderTypeName = whereValue.get("orderTypeName");
		String provinceName = whereValue.get("provinceName");
		String status = whereValue.get("Integer status");
		String cityName = whereValue.get("cityName");
		String accountName = whereValue.get("accountName");
		String userName = whereValue.get("userName");
		String orderId = whereValue.get("orderId");
		String createTimeStartDate = whereValue.get("createTimeStartDate");
		String createTimeEndDate = whereValue.get("createTimeEndDate");
		String createdDateStartDate = whereValue.get("createdDateStartDate");
		String createdDateEndDate = whereValue.get("createdDateEndDate");
		String updatedDateStartDate = whereValue.get("updatedDateStartDate");
		String updatedDateEndDate = whereValue.get("updatedDateEndDate");
		Long id = StringUtils.isEmpty(whereValue.get("id"))?null:Long.valueOf(whereValue.get("id"));
		List<Object[]> objects = commonOrderService.findByCommonResource(userName, orderTypeName, provinceName,
				accountName, cityName, status, orderId, createTimeStartDate, createTimeEndDate, createdDateStartDate,
				createdDateEndDate, updatedDateStartDate, updatedDateEndDate, id);
		return objects;
	}
}
