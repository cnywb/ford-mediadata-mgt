package com.ford.mediadata.mgt.service.trigger;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.common.constants.DateFormatUtils;
import com.ford.mediadata.mgt.service.mapping.CommonMapService;
import com.ford.mediadata.mgt.service.order.OrderTransService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Async
@Service("transSapTrigger")
public class TransSapTrigger extends BatchTriggerSupport {

	@Resource
	private OrderTransService orderTransService;

	@Resource
	private CommonMapService commonMapService;

	public void execute() {
		try {
			if (!isBatchServer()) {
				return;
			}
			log.info("批处理开始-SAP文件生成");
			Date execTime = new Date();
			String time = DateFormatUtils.YYYYMMDDHHMMSS.get().format(execTime);
			Integer batchNo = getNextBatchNo(execTime);
			Integer serialNo = getNextSerialNo();
			StringBuilder fileName = new StringBuilder();
			fileName.append("CAFCHN_LMC_BEFOREOB_").append(time).append("_BATCH").append(batchNo).append(".txt");
			Date importDateStartDate = DateTime.now().minusDays(7).toDate();
			Date importDateEndDate = new Date();
			Integer maxCount = 10000;
			Integer dataCount = orderTransService.executeTransSapTask(importDateStartDate, importDateEndDate, maxCount,
					fileName.toString(), time, formatSerialNo(serialNo));
			if (dataCount > 0) {
				updateBatchNo(execTime, batchNo);
				updateSerialNo(serialNo);
			}
			log.info("批处理结束-SAP文件生成，共处理记录{}条", dataCount);
		} catch (Exception e) {
			log.error("SAP文件生成异常：{}", e.getMessage(), e);
		}
	}

	private String formatSerialNo(Integer serialNo) {
		final Integer length = 5;
		String serialNoString = serialNo.toString();
		return StringUtils.leftPad(serialNoString, length, "0");
	}

	private String getSerialNoKey() {
		StringBuilder mapKey = new StringBuilder();
		mapKey.append("TransSapSerialNo");
		return mapKey.toString();
	}

	private Integer getNextSerialNo() {
		Integer serialNo = commonMapService.getInteger(getSerialNoKey());
		if (serialNo == null) {
			return 1;
		} else if (serialNo >= 99999) {
			return 1;
		} else {
			return serialNo + 1;
		}
	}

	private void updateSerialNo(Integer serialNo) {
		commonMapService.set(getSerialNoKey(), serialNo);
	}

	private String getBatchNoKey(Date execTime) {
		StringBuilder mapKey = new StringBuilder();
		mapKey.append("TransSapBatchNo");
		mapKey.append(DateFormatUtils.YYYYMMDD.get().format(execTime));
		return mapKey.toString();
	}

	private Integer getNextBatchNo(Date execTime) {
		Integer batchNo = commonMapService.getInteger(getBatchNoKey(execTime));
		if (batchNo == null) {
			return 1;
		} else {
			return batchNo + 1;
		}
	}

	private void updateBatchNo(Date execTime, Integer batchNo) {
		commonMapService.set(getBatchNoKey(execTime), batchNo);
	}

}
