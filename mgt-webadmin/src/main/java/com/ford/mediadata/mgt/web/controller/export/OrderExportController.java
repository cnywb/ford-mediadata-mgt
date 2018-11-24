package com.ford.mediadata.mgt.web.controller.export;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ford.mediadata.mgt.common.constants.DateFormatUtils;
import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;
import com.ford.mediadata.mgt.service.excel.ExcelService;
import com.ford.mediadata.mgt.service.order.OrderTransService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/export/order")
public class OrderExportController {

	@Resource
	private ExcelService excelService;

	@Resource
	private OrderTransService orderTransService;

	/**
	 * 话单导出
	 */
	@RequestMapping(value = "/callOrder")
	public void callOrderListExport(HttpServletRequest request, HttpServletResponse response) {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return;
		}
		excelService.exportData(ExcelList.B_CALLORDER, request, response);
	}

	/**
	 * 订单导出
	 */
	@RequestMapping(value = "/commomOrder")
	public void commonOrderListExport(HttpServletRequest request, HttpServletResponse response) {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return;
		}
		excelService.exportData(ExcelList.B_COMMONORDER, request, response);
	}

	/**
	 * 中间表导出
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/orderTrans")
	public void orderTransListExport(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return;
		}
		OrderTransEntityCondition condition = new OrderTransEntityCondition();
		if (StringUtils.isNotEmpty(request.getParameter("orderId"))) {
			condition.setOrderId(request.getParameter("orderId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("campaignChannelId"))) {
			condition.setCampaignChannelId(request.getParameter("campaignChannelId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("importDateStartDate"))) {
			String startDateString = request.getParameter("importDateStartDate");
			condition.setImportDateStartDate(
					DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().parse(startDateString + " 00:00:00"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("importDateEndDate"))) {
			String endDateString = request.getParameter("importDateEndDate");
			condition
					.setImportDateEndDate(DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().parse(endDateString + " 23:59:59"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("localToDealerId"))) {
			condition.setLocalToDealerId(request.getParameter("localToDealerId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("refId"))) {
			condition.setRefId(Long.valueOf(request.getParameter("refId")));
		}
		if (StringUtils.isNotEmpty(request.getParameter("orderBusinessOpportunityCreateTimeStartDate"))) {
			String startDateString = request.getParameter("orderBusinessOpportunityCreateTimeStartDate");
			condition.setOrderBusinessOpportunityCreateTimeStartDate(
					DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().parse(startDateString + " 00:00:00"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("orderBusinessOpportunityCreateTimeEndDate"))) {
			String endDateString = request.getParameter("orderBusinessOpportunityCreateTimeEndDate");
			condition.setOrderBusinessOpportunityCreateTimeEndDate(
					DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().parse(endDateString + " 23:59:59"));
		}
		List<OrderTransEntity> entityList = orderTransService.list(condition);
		List<Object[]> list = Lists.newArrayList();
		for (OrderTransEntity entity : entityList) {
			Object[] array = parseOrderTransEntity(entity);
			list.add(array);
		}
		excelService.exportData(list, "线索数据", orderTransColumn, response);
	}

	private String[] orderTransColumn = { "创建日期", "更新日期", "线索ID", "订单类型", "姓名", "手机号", "性别", "邮件", "颜色", "车型", "车名称",
			"销售顾问", "线索处理状态", "首次处理时间", "省份", "城市", "线索创建时间", "承接经销商名称", "承接经销商代码", "备注", "处理经销商ID", "处理经销商名称", "处理时间",
			"是否试驾", "被叫电话号码", "通话开始时间", "通话结束时间", "通话时长(秒)", "座席", "跟近状态", "等待时长(秒)", "经营厂商", "被叫400", "是否成功", "主叫省份",
			"主叫城市", "400状态", "谁先挂断", "价格", "年份", "平台", "活动渠道", "LMCID", "LMC处理时间", "关联ID" };

	private Object[] parseOrderTransEntity(OrderTransEntity entity) {
		Object[] array = new Object[orderTransColumn.length];
		array[0] = toString(entity.getDataCreateDate());
		array[1] = toString(entity.getDataUpdateDate());
		array[2] = toString(entity.getOrderId());
		array[3] = toString(entity.getOrderTypeName());
		array[4] = toString(entity.getUserName());
		array[5] = toString(entity.getUserMobile());
		array[6] = parseGender(entity.getUserGender());
		array[7] = toString(entity.getUserMail());
		array[8] = toString(entity.getCarColor());
		array[9] = toString(entity.getCsName());
		array[10] = toString(entity.getCarName());
		array[11] = toString(entity.getAccountName());
		array[12] = toString(entity.getStatus());
		array[13] = toString(entity.getFistFollowTime());
		array[14] = toString(entity.getProvinceName());
		array[15] = toString(entity.getCityName());
		array[16] = toString(entity.getOrderBusinessOpportunityCreateTime());
		array[17] = toString(entity.getDealerName());
		array[18] = toString(entity.getDealerId());
		array[19] = toString(entity.getOrderRemark());
		array[20] = toString(entity.getProceedDealerId());
		array[21] = toString(entity.getProceedDealerName());
		array[22] = toString(entity.getProceedDateTime());
		array[23] = toString(entity.getIsTestDrive());
		array[24] = toString(entity.getCalleeRealNumber());
		array[25] = toString(entity.getCallBeginTime());
		array[26] = toString(entity.getCallFinishTime());
		array[27] = toString(entity.getCallerDuration());
		array[28] = toString(entity.getAgentName());
		array[29] = parseDealerFollowStatus(entity.getDealerFollowStatus());
		array[30] = toString(entity.getWaitDuration());
		array[31] = toString(entity.getOperationBrand());
		array[32] = toString(entity.getFooPhone());
		array[33] = toString(entity.getIsSuccess());
		array[34] = toString(entity.getCallerProvince());
		array[35] = toString(entity.getCallerCity());
		array[36] = toString(entity.getFooStatus());
		array[37] = toString(entity.getCutoff());
		array[38] = toString(entity.getReferPrice());
		array[39] = toString(entity.getYearType());
		array[40] = toString(entity.getMediaSource());
		array[41] = toString(entity.getCampaignChannelId());
		array[42] = toString(entity.getLocalToDealerId());
		array[43] = toString(entity.getImportDate());
		array[44] = toString(entity.getRefId());
		return array;
	}

	private String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof Date) {
			return DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().format(obj);
		}
		return obj.toString();
	}

	private String parseGender(Integer value) {
		if (value == null) {
			return "";
		}
		if (0 == value) {
			return "女";
		}
		if (1 == value) {
			return "男";
		}
		if (-1 == value) {
			return "未知";
		}
		return "";
	}

	private String parseDealerFollowStatus(Integer value) {
		if (value == null) {
			return "";
		}
		if (0 == value) {
			return "未跟进";
		}
		if (1 == value) {
			return "已跟进";
		}
		if (2 == value) {
			return "无效";
		}
		return "";
	}

}
