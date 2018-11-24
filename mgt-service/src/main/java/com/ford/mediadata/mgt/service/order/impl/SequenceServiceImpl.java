package com.ford.mediadata.mgt.service.order.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.service.mapping.CommonMapService;
import com.ford.mediadata.mgt.service.order.SequenceService;
import com.google.common.collect.Maps;

import io.dabing.core.service.AbstractService;

/**
 * 序列服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class SequenceServiceImpl extends AbstractService implements SequenceService {

	@Resource
	private CommonMapService commonMapService;

	private Map<String, SequenceCache> sequenceCache = Maps.newConcurrentMap();

	private Object lock = new Object();

	@Override
	public Long nextval(String sequenceName) {
		Assert.hasText(sequenceName, "序列名不能为空");
		if (!sequenceCache.containsKey(sequenceName)) {
			synchronized (lock) {
				if (!sequenceCache.containsKey(sequenceName)) {
					SequenceCache cache = new SequenceCache(commonMapService, sequenceName);
					sequenceCache.put(sequenceName, cache);
				}
			}
		}
		return sequenceCache.get(sequenceName).nextval();
	}

	private class SequenceCache {

		private Object lock;

		private CommonMapService commonMapService;

		private String sequenceName;

		private Long currentValue;

		private Long endValue;

		public SequenceCache(CommonMapService commonMapService, String sequenceName) {
			Assert.hasText(sequenceName, "序列名不能为空");
			this.lock = new Object();
			this.commonMapService = commonMapService;
			this.sequenceName = sequenceName;
		}

		public Long nextval() {
			synchronized (this.lock) {
				if (this.currentValue == null || this.currentValue >= this.endValue) {
					final Long valuePerCache = 100L;
					final String mapKey = "SEQ_" + this.sequenceName;
					Long currentValueInDB = commonMapService.getLong(mapKey);
					if (currentValueInDB == null) {
						currentValueInDB = 0L;
					}
					Long endValueInDB = currentValueInDB + valuePerCache;
					commonMapService.set(mapKey, endValueInDB);
					this.currentValue = currentValueInDB;
					this.endValue = endValueInDB;
				}
				this.currentValue = this.currentValue + 1L;
				return this.currentValue;
			}
		}

	}

}