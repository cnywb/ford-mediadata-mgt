package com.ford.mediadata.mgt.service.order.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.common.constants.DateFormatUtils;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntityCondition;
import com.ford.mediadata.mgt.repository.order.OrderTransRepository;
import com.ford.mediadata.mgt.service.order.OrderTransService;
import com.google.common.collect.Lists;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import lombok.Cleanup;

/**
 * 数据转换中间表服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class OrderTransServiceImpl extends AbstractService implements OrderTransService {

	@Resource
	private OrderTransRepository orderTransRepository;

	@Override
	public Page<OrderTransEntity> findByGridPage(GridPage page, OrderTransEntityCondition condition) {
		return orderTransRepository.findByGridPage(page, condition);
	}

	@Override
	public List<OrderTransEntity> list(OrderTransEntityCondition condition) {
		return orderTransRepository.list(condition);
	}

	@Override
	public void save(OrderTransEntity entity) {
		orderTransRepository.save(entity);
	}

	@Override
	public void update(OrderTransEntity entity) {
		orderTransRepository.update(entity);
	}

	@Override
	public void delete(Long id) {
		orderTransRepository.delete(id);
	}

	@Override
	public Integer executeTransSapTask(Date importDateStartDate, Date importDateEndDate, Integer maxCount,
			String sapFileName, String time, String serialNo) throws Exception {
		String filePath = "/datadrive/sap/" + sapFileName;
		Page<OrderTransEntity> transSapOrderPage = getTransSapOrder(importDateStartDate, importDateEndDate, maxCount);
		if (transSapOrderPage.getContent().size() == 0) {
			return transSapOrderPage.getContent().size();
		}
		List<String> lines = generateFileLines(transSapOrderPage.getContent(), time, serialNo);
		@Cleanup
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		for (String line : lines) {
			IOUtils.write(line, fos, Charset.forName("UTF-8"));
			IOUtils.write(IOUtils.LINE_SEPARATOR, fos, Charset.forName("UTF-8"));
		}
		fos.flush();
		Date sapFileProcTime = DateFormatUtils.YYYYMMDDHHMMSS.get().parse(time);
		for (OrderTransEntity entity : transSapOrderPage.getContent()) {
			entity.setSapFileStatus(1);
			entity.setSapFileName(sapFileName);
			entity.setSapFileProcTime(sapFileProcTime);
		}
		return transSapOrderPage.getContent().size();
	}

	private Page<OrderTransEntity> getTransSapOrder(Date importDateStartDate, Date importDateEndDate,
			Integer maxCount) {
		OrderTransEntityCondition condition = new OrderTransEntityCondition();
		condition.setImportDateStartDate(importDateStartDate);
		condition.setImportDateEndDate(importDateEndDate);
		condition.setSapFileStatus(0);
		condition.setOrderBy("importDate");
		GridPage page = new GridPage();
		page.setPageNumber(1);
		page.setPageSize(maxCount);
		return orderTransRepository.findByGridPage(page, condition);
	}

	private List<String> generateFileLines(List<OrderTransEntity> entityList, String time, String serialNo) {
		List<String> lines = Lists.newArrayList();
		{
			// 文件头
			String header = buildHeader(time, serialNo);
			lines.add(header);
		}
		{
			// 标题行
			String title = buildTitle();
			lines.add(title);
		}
		{
			// 数据行
			for (OrderTransEntity entity : entityList) {
				String content = buildContent(entity);
				lines.add(content);
			}
		}
		{
			// 文件尾
			String footer = buildFooter(time, entityList.size());
			lines.add(footer);
		}
		return lines;
	}

	private String buildHeader(String time, String serialNo) {
		String text = "AA00|CHN|LMC|BEFOREOB||%s|%s|P";
		return String.format(text, time, serialNo);
	}

	private String buildTitle() {
		List<String> titles = Lists.newArrayList();
		titles.add("IDENTIFIER");
		titles.add("receive_Date");
		titles.add("updateDate");
		titles.add("orderId");
		titles.add("orderTypeId");
		titles.add("orderTypeName");
		titles.add("name");
		titles.add("mobile");
		titles.add("gender");
		titles.add("email");
		titles.add("carColor");
		titles.add("interested_Car_Model");
		titles.add("interested_Car_Model_Detail");
		titles.add("accountName");
		titles.add("status");
		titles.add("fistFollowTime");
		titles.add("province");
		titles.add("city");
		titles.add("create_Date");
		titles.add("assign_Dealer_Name");
		titles.add("assign_Dealer_Code");
		titles.add("orderRemark");
		titles.add("proceedDealerId");
		titles.add("proceedDealerName");
		titles.add("proceedDateTime");
		titles.add("isTestDrive");
		titles.add("calleeRealNumber");
		titles.add("callBeginTime");
		titles.add("callFinishTime");
		titles.add("callerDuration");
		titles.add("agentName");
		titles.add("dealerFollowStatus");
		titles.add("waitDuration");
		titles.add("operationBrand");
		titles.add("400phone");
		titles.add("isSuccess");
		titles.add("callerProvince");
		titles.add("callerCity");
		titles.add("400status");
		titles.add("cutoff");
		titles.add("referPrice");
		titles.add("yearType");
		titles.add("mediaSource");
		titles.add("Campaign_Channel_ID");
		titles.add("Local_To_Dealer_ID");
		titles.add("import_Date");
		titles.add("Symbol");
		titles.add("verify_status");
		StringBuilder text = new StringBuilder();
		for (String title : titles) {
			text.append("\"");
			text.append(title);
			text.append("\"|");
		}
		text.delete(text.length() - 1, text.length());
		return text.toString();
	}

	private String buildContent(OrderTransEntity entity) {
		List<String> contents = Lists.newArrayList();
		contents.add("CC00");
		contents.add(toStringSap(entity.getDataCreateDate()));
		contents.add(toStringSap(entity.getDataUpdateDate()));
		contents.add(toStringSap(entity.getOrderId()));
		contents.add(toStringSap(entity.getOrderTypeId()));
		contents.add(toStringSap(entity.getOrderTypeName()));
		contents.add(toStringSap(entity.getUserName()));
		contents.add(toStringSap(entity.getUserMobile()));
		contents.add(formatGenderSap(entity.getUserGender()));
		contents.add(toStringSap(entity.getUserMail()));
		contents.add(toStringSap(entity.getCarColor()));
		contents.add(toStringSap(entity.getCsName()));
		contents.add(toStringSap(entity.getCarName()));
		contents.add(toStringSap(entity.getAccountName()));
		contents.add(toStringSap(entity.getStatus()));
		contents.add(toStringSap(entity.getFistFollowTime()));
		contents.add(toStringSap(entity.getProvinceName()));
		contents.add(toStringSap(entity.getCityName()));
		contents.add(toStringSap(entity.getOrderBusinessOpportunityCreateTime()));
		contents.add(toStringSap(entity.getDealerName()));
		contents.add(toStringSap(entity.getDealerId()));
		contents.add(toStringSap(entity.getOrderRemark()));
		contents.add(toStringSap(entity.getProceedDealerId()));
		contents.add(toStringSap(entity.getProceedDealerName()));
		contents.add(toStringSap(entity.getProceedDateTime()));
		contents.add(toStringSap(entity.getIsTestDrive()));
		contents.add(toStringSap(entity.getCalleeRealNumber()));
		contents.add(toStringSap(entity.getCallBeginTime()));
		contents.add(toStringSap(entity.getCallFinishTime()));
		contents.add(toStringSap(entity.getCallerDuration()));
		contents.add(toStringSap(entity.getAgentName()));
		contents.add(toStringSap(entity.getDealerFollowStatus()));
		contents.add(toStringSap(entity.getWaitDuration()));
		contents.add(toStringSap(entity.getOperationBrand()));
		contents.add(toStringSap(entity.getFooPhone()));
		contents.add(toStringSap(entity.getIsSuccess()));
		contents.add(toStringSap(entity.getCallerProvince()));
		contents.add(toStringSap(entity.getCallerCity()));
		contents.add(toStringSap(entity.getFooStatus()));
		contents.add(toStringSap(entity.getCutoff()));
		contents.add(toStringSap(entity.getReferPrice()));
		contents.add(toStringSap(entity.getYearType()));
		contents.add(toStringSap(entity.getMediaSource()));
		contents.add(toStringSap(entity.getCampaignChannelId()));
		contents.add(toStringSap(entity.getLocalToDealerId()));
		contents.add(toStringSap(entity.getImportDate()));
		contents.add(toStringSap(entity.getSymbol()));
		contents.add("");
		StringBuilder text = new StringBuilder();
		for (String content : contents) {
			text.append(content);
			text.append("|");
		}
		text.delete(text.length() - 1, text.length());
		return text.toString();
	}

	private String buildFooter(String time, Integer contentCount) {
		String text = "ZZ99|CHN|LMC|BEFOREOB|%s|%s|%s";
		return String.format(text, time, contentCount, contentCount + 2);
	}

	private String toStringSap(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof Date) {
			return DateFormatUtils.YYYYMMDDHHMMSS.get().format(obj);
		} else {
			return obj.toString();
		}
	}

	private String formatGenderSap(Integer gender) {
		if (gender == null) {
			return "";
		} else if (0 == gender) {
			return "Female";
		} else if (1 == gender) {
			return "Male";
		} else {
			return "";
		}
	}

	@Override
	public OrderTransEntity findByOrderId(String orderId) {
		Assert.hasText(orderId);
		OrderTransEntityCondition condition = new OrderTransEntityCondition();
		condition.setOrderId(orderId);
		List<OrderTransEntity> list = orderTransRepository.list(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}