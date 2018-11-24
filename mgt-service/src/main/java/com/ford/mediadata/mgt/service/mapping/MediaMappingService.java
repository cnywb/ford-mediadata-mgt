package com.ford.mediadata.mgt.service.mapping;

import java.util.List;

import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;
import com.ford.mediadata.mgt.entity.media.MediaTypeEnum;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 媒体渠道MAPPING
 * 
 * @author chunliang.you
 *
 */
public interface MediaMappingService {

	Page<MediaMappingEntity> findByGridPage(GridPage page, MediaMappingEntity condition);

	List<MediaMappingEntity> list(MediaMappingEntity condition);

	void save(MediaMappingEntity entity);

	void update(MediaMappingEntity entity);

	void delete(Long id);

	MediaMappingEntity mappingMedia(MediaTypeEnum mediaType, MappingTypeEnum mappingType, String mediaValue);

}
