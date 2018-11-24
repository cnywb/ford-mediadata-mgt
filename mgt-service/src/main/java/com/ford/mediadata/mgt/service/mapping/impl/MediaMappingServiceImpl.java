package com.ford.mediadata.mgt.service.mapping.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;
import com.ford.mediadata.mgt.entity.media.MediaTypeEnum;
import com.ford.mediadata.mgt.repository.mapping.MediaMappingRepository;
import com.ford.mediadata.mgt.service.mapping.MediaMappingService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 媒体渠道MAPPING
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class MediaMappingServiceImpl implements MediaMappingService {

	@Resource
	private MediaMappingRepository mediaMappingRepository;

	@Override
	public Page<MediaMappingEntity> findByGridPage(GridPage page, MediaMappingEntity condition) {
		return mediaMappingRepository.findByGridPage(page, condition);
	}

	@Override
	public List<MediaMappingEntity> list(MediaMappingEntity condition) {
		return mediaMappingRepository.list(condition);
	}

	@CacheEvict(cacheNames = { "mappingCache" }, allEntries = true)
	@Override
	public void save(MediaMappingEntity entity) {
		mediaMappingRepository.save(entity);
	}

	@CacheEvict(cacheNames = { "mappingCache" }, allEntries = true)
	@Override
	public void update(MediaMappingEntity entity) {
		mediaMappingRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "mappingCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		mediaMappingRepository.delete(id);
	}

	@Cacheable(cacheNames = {
			"mappingCache" }, key = "'mappingMedia_' + #mediaType.name() + '_' + #mappingType.name() + '_' + #mediaValue")
	@Override
	public MediaMappingEntity mappingMedia(MediaTypeEnum mediaType, MappingTypeEnum mappingType, String mediaValue) {
		Assert.notNull(mediaType, "mediaType为空");
		Assert.notNull(mappingType, "mappingType为空");
		Assert.hasText(mediaValue, "mediaValue为空");
		MediaMappingEntity condition = new MediaMappingEntity();
		condition.setMediaType(mediaType);
		condition.setMappingType(mappingType);
		condition.setMediaValue(mediaValue);
		List<MediaMappingEntity> list = mediaMappingRepository.list(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
