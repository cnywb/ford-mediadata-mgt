package com.ford.mediadata.mgt.service.batch.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;
import com.ford.mediadata.mgt.entity.order.CommonOrderEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommonOrderTransProcessor extends AbstractOrderTransProcessor
		implements ItemProcessor<CommonOrderEntity, OrderTransEntity> {

	@Override
	MediaSourceEnum getMediaSource() {
		return MediaSourceEnum.YiPaiOrder;
	}

	@Override
	public OrderTransEntity process(CommonOrderEntity paramI) throws Exception {
		// 车型过滤
		String carModel = mapValue(MappingTypeEnum.CAR_MODEL, paramI.getCsName());
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
		target.setOrderTypeId(paramI.getOrderTypeId());
		target.setOrderTypeName(paramI.getOrderTypeName());
		target.setUserName(paramI.getUserName());
		target.setUserMobile(paramI.getUserMobile());
		target.setUserGender(paramI.getUserGender());
		target.setUserMail(paramI.getUserMail());
		target.setCarColor(paramI.getCarColor());
		target.setCsName(carModel);
		target.setCarName(paramI.getCarName());
		target.setAccountName(paramI.getAccountName());
		target.setStatus(paramI.getStatus());
		target.setFistFollowTime(paramI.getFistFollowTime());
		target.setProvinceName(paramI.getProvinceName());
		target.setCityName(paramI.getCityName());
		target.setOrderBusinessOpportunityCreateTime(paramI.getOrderBusinessOpportunityCreateTime());
		target.setDealerName(paramI.getDealerName());
		target.setDealerId(mapValue(MappingTypeEnum.DEALER, paramI.getDealerId()));
		target.setOrderRemark(paramI.getOrderRemark());
		target.setReferPrice(paramI.getYeferPrice());
		target.setYearType(paramI.getYearType());
		target.setRefId(paramI.getId());
		setMediaParam(target);
		setDefaultParam(target);
		if (log.isDebugEnabled()) {
			log.debug("转换数据 {} {} ==> {}", getMediaSource().getName(), JSON.toJSONString(paramI),
					JSON.toJSONString(target));
		}
		return target;
	}

}