package com.ford.mediadata.mgt.repository.mapping.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.mapping.MediaMappingEntity;
import com.ford.mediadata.mgt.repository.mapping.MediaMappingRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 媒体渠道MAPPING
 * 
 * @author chunliang.you
 *
 */
@Repository
public class MediaMappingRepositoryImpl extends DefaultJpaRepository<MediaMappingEntity, Long>
		implements MediaMappingRepository {

	@Override
	public Page<MediaMappingEntity> findByGridPage(GridPage page, MediaMappingEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<MediaMappingEntity> list(MediaMappingEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(MediaMappingEntity condition) {
		return StringQuery.newQuery().query("from MediaMappingEntity t where 1 = 1 ")
				// mediaType
				.predicateNotNull(condition.getMediaType()).query(" and t.mediaType = :mediaType")
				.param("mediaType", condition.getMediaType())
				// mappingType
				.predicateNotNull(condition.getMappingType()).query(" and t.mappingType = :mappingType")
				.param("mappingType", condition.getMappingType())
				// mediaValue
				.predicateHasText(condition.getMediaValue()).query(" and t.mediaValue = :mediaValue")
				.param("mediaValue", condition.getMediaValue())
				// sysValue
				.predicateHasText(condition.getSysValue()).query(" and t.sysValue = :sysValue")
				.param("sysValue", condition.getSysValue())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}