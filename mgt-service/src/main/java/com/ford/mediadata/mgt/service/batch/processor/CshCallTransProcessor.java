package com.ford.mediadata.mgt.service.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;
import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CshCallTransProcessor extends AbstractOrderTransProcessor
		implements ItemProcessor<CshCallEntity, OrderTransEntity> {

	@Override
	MediaSourceEnum getMediaSource() {
		return MediaSourceEnum.CheshanghuiCall;
	}

	@Override
	public OrderTransEntity process(CshCallEntity paramI) throws Exception {
		// 对象转换
		OrderTransEntity target = createOrGetInitOrder(paramI.getOrderId());
		if (target == null) {
			return null;
		}
		if (target.getDataCreateDate() == null) {
			target.setDataCreateDate(paramI.getCreatedDate());
		}
		target.setDataUpdateDate(paramI.getCreatedDate());
		target.setUserMobile(paramI.getCallerPhoneNumber());
		target.setProvinceName(paramI.getProvince());
		target.setCityName(paramI.getCity());
		target.setOrderBusinessOpportunityCreateTime(paramI.getCallDateTime());
		target.setDealerName(paramI.getDealerName());
		target.setDealerId(mapValue(MappingTypeEnum.DEALER, paramI.getDealerId()));
		target.setCalleeRealNumber(paramI.getCalleeRealNumber());
		target.setCallBeginTime(paramI.getCallDateTime());
		target.setCallerDuration(parseDuration(paramI.getCallerDuration()));
		target.setWaitDuration(parseDuration(paramI.getWaitDuration()));
		target.setOperationBrand(paramI.getOperationBrand());
		target.setFooPhone(paramI.getFooPhone());
		target.setIsSuccess(paramI.getIsSuccess());
		target.setCallerProvince(paramI.getCallerProvince());
		target.setCallerCity(paramI.getCallerCity());
		target.setFooStatus(paramI.getFooStatus());
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