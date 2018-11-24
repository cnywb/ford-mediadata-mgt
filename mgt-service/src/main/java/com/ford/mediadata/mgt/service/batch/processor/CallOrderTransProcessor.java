package com.ford.mediadata.mgt.service.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;
import com.ford.mediadata.mgt.entity.order.CallOrderEntity;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallOrderTransProcessor extends AbstractOrderTransProcessor
		implements ItemProcessor<CallOrderEntity, OrderTransEntity> {

	@Override
	MediaSourceEnum getMediaSource() {
		return MediaSourceEnum.YiPaiCall;
	}

	@Override
	public OrderTransEntity process(CallOrderEntity paramI) throws Exception {
		// 对象转换
		OrderTransEntity target = createOrGetInitOrder(paramI.getCallBusinessOpportunityId());
		if (target == null) {
			return null;
		}
		if (target.getDataCreateDate() == null) {
			target.setDataCreateDate(paramI.getCreatedDate());
		}
		target.setDataUpdateDate(paramI.getCreatedDate());
		target.setUserMobile(paramI.getCallerPhoneNumber());
		target.setFistFollowTime(paramI.getFistFollowTime());
		target.setProvinceName(paramI.getProvinceName());
		target.setCityName(paramI.getCityName());
		target.setOrderBusinessOpportunityCreateTime(paramI.getCallBusinessOpportunityCreateTime());
		target.setDealerId(mapValue(MappingTypeEnum.DEALER, paramI.getDealerId()));
		target.setCalleeRealNumber(paramI.getCalleeRealNumber());
		target.setCallBeginTime(paramI.getCallBeginTime());
		target.setCallFinishTime(paramI.getCallFinishTime());
		target.setCallerDuration(paramI.getCallerDuration());
		target.setAgentName(paramI.getAgentName());
		target.setDealerFollowStatus(paramI.getDealerFollowStatus());
		target.setWaitDuration(paramI.getWaitDuration());
		target.setReferPrice(paramI.getReferPrice());
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