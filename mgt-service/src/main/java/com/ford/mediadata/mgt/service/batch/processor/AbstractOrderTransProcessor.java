package com.ford.mediadata.mgt.service.batch.processor;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.common.constants.SequenceConstants;
import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;
import com.ford.mediadata.mgt.entity.order.OrderTransEntity;
import com.ford.mediadata.mgt.service.mapping.MediaMappingService;
import com.ford.mediadata.mgt.service.order.OrderTransService;
import com.ford.mediadata.mgt.service.order.SequenceService;
import com.google.common.collect.Sets;

@Service
public abstract class AbstractOrderTransProcessor {

	final private String SYMBOL = "P_12";

	final private ThreadLocal<Set<String>> orderCache = new ThreadLocal<Set<String>>() {
		@Override
		protected Set<String> initialValue() {
			Set<String> set = Sets.newHashSet();
			return set;
		}
	};

	@Resource
	private OrderTransService orderTransService;

	@Resource
	private SequenceService sequenceService;

	@Resource
	private MediaMappingService mediaMappingService;

	abstract MediaSourceEnum getMediaSource();

	void setMediaParam(OrderTransEntity entity) {
		entity.setMediaSource(getMediaSource());
		entity.setCampaignChannelId(getMediaSource().getCampaignChannelId());
	}

	void setDefaultParam(OrderTransEntity entity) {
		entity.setLocalToDealerId(sequenceService.nextval(SequenceConstants.LMC_ORDER_ID).toString());
		entity.setImportDate(new Date());
		entity.setSymbol(SYMBOL);
		entity.setSapFileStatus(0);
	}

	String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	String mapValue(MappingTypeEnum mappingType, Object mediaValue) {
		if (mediaValue == null || StringUtils.isEmpty(mediaValue.toString())) {
			return null;
		}
		MediaMappingEntity mappingEntity = mediaMappingService.mappingMedia(getMediaSource().getMediaType(),
				mappingType, mediaValue.toString());
		if (mappingEntity == null) {
			return null;
		}
		return mappingEntity.getSysValue();
	}

	Long parseDuration(String duration) {
		if (StringUtils.isEmpty(duration)) {
			return null;
		}
		duration = duration.replaceAll("(?:时|分|秒)", ":");
		String[] timeArray = duration.split(":");
		Long time = 0L;
		time += Long.valueOf(timeArray[0]) * 3600;
		time += Long.valueOf(timeArray[1]) * 60;
		time += Long.valueOf(timeArray[2]);
		return time;
	}

	OrderTransEntity createOrGetInitOrder(Object orderId) {
		if (orderId != null && StringUtils.isNotEmpty(orderId.toString())) {
			if (orderCache.get().contains(orderId.toString())) {
				return null;
			}
			OrderTransEntity order = orderTransService.findByOrderId(orderId.toString());
			if (order != null) {
				orderCache.get().add(orderId.toString());
				return order;
			}
		}
		OrderTransEntity order = new OrderTransEntity();
		if (orderId != null) {
			order.setOrderId(orderId.toString());
			orderCache.get().add(orderId.toString());
		}
		return order;
	}

}
