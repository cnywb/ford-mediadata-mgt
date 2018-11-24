package com.ford.mediadata.mgt.repository.mapping;

import java.util.List;

import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 媒体渠道MAPPING
 * 
 * @author chunliang.you
 *
 */
public interface MediaMappingRepository extends JpaRepository<MediaMappingEntity, Long> {

	Page<MediaMappingEntity> findByGridPage(GridPage page, MediaMappingEntity condition);

	List<MediaMappingEntity> list(MediaMappingEntity condition);

}