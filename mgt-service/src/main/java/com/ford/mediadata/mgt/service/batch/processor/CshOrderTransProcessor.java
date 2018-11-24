package com.ford.mediadata.mgt.service.batch.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;
import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CshOrderTransProcessor extends AbstractOrderTransProcessor
		implements ItemProcessor<CshOrderEntity, OrderTransEntity> {

	@Override
	MediaSourceEnum getMediaSource() {
		return MediaSourceEnum.CheshanghuiOrder;
	}

	@Override
	public OrderTransEntity process(CshOrderEntity paramI) throws Exception {
		// 车型过滤
		String carModel = mapValue(MappingTypeEnum.CAR_MODEL, paramI.getCarModel());
		if (StringUtils.isEmpty(carModel)) {
			return null;
		}
		// 对象转换
		OrderTransEntity target = createOrGetInitOrder(paramI.getOrderId());
		if (target == null) {
			return null;
		}
		if (target.getDataCreateDate() == null) {
			target.setDataCreateDate(paramI.getCreatedDate());
		}
		target.setDataUpdateDate(paramI.getCreatedDate());
		target.setUserName(paramI.getName());
		target.setUserMobile(paramI.getMobile());
		target.setCsName(carModel);
		target.setCarName(paramI.getCarModelDetail());
		target.setFistFollowTime(paramI.getProceedDateTime());
		target.setProvinceName(paramI.getProvince());
		target.setCityName(paramI.getCity());
		target.setOrderBusinessOpportunityCreateTime(paramI.getOrderDateTime());
		target.setDealerName(paramI.getAssignedDealerName());
		target.setDealerId(mapValue(MappingTypeEnum.DEALER, paramI.getAssignedDealerId()));
		target.setOrderRemark(paramI.getRemark());
		target.setProceedDealerId(mapValue(MappingTypeEnum.DEALER, paramI.getProceedDealerId()));
		target.setProceedDealerName(paramI.getProceedDealerName());
		target.setIsTestDrive(paramI.getIsTestDrive());
		target.setRefId(paramI.getId());
		/**
		 * 转化的文件，如果“承接经销商ID”和“承接经销商名称“为空，需要查看”处理经销商ID“和“处理经销商名称“是否为空，如果不为空，则需要用”
		 * 处理经销商ID“和“处理经销商名称“填充“承接经销商ID”和“承接经销商名称“
		 */
		if (StringUtils.isEmpty(target.getDealerId()) && StringUtils.isNotEmpty(target.getProceedDealerId())) {
			target.setDealerId(target.getProceedDealerId());
		}
		if (StringUtils.isEmpty(target.getDealerName()) && StringUtils.isNotEmpty(target.getProceedDealerName())) {
			target.setDealerName(target.getProceedDealerName());
		}
		setMediaParam(target);
		setDefaultParam(target);
		if (log.isDebugEnabled()) {
			log.debug("转换数据 {} {} ==> {}", getMediaSource().getName(), JSON.toJSONString(paramI),
					JSON.toJSONString(target));
		}
		return target;
	}

}