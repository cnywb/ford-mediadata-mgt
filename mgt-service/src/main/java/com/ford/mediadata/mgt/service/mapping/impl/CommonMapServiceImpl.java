package com.ford.mediadata.mgt.service.mapping.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.mapping.CommonMapEntity;
import com.ford.mediadata.mgt.entity.mapping.CommonMapEntityCondition;
import com.ford.mediadata.mgt.repository.mapping.CommonMapRepository;
import com.ford.mediadata.mgt.service.mapping.CommonMapService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * K/V MAP
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class CommonMapServiceImpl implements CommonMapService {

	@Resource
	private CommonMapRepository commonMapRepository;

	@Override
	public Page<CommonMapEntity> findByGridPage(GridPage page, CommonMapEntityCondition condition) {
		return commonMapRepository.findByGridPage(page, condition);
	}

	@Override
	public List<CommonMapEntity> list(CommonMapEntityCondition condition) {
		return commonMapRepository.list(condition);
	}

	@Override
	public void save(CommonMapEntity entity) {
		commonMapRepository.save(entity);
	}

	@Override
	public void update(CommonMapEntity entity) {
		commonMapRepository.update(entity);
	}

	@Override
	public void delete(Long id) {
		commonMapRepository.delete(id);
	}

	@Override
	public CommonMapEntity findByMapKey(String mapKey) {
		Assert.hasText(mapKey, "mapKey为空");
		CommonMapEntityCondition condition = new CommonMapEntityCondition();
		condition.setMapKey(mapKey);
		List<CommonMapEntity> list = commonMapRepository.list(condition);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer getInteger(String mapKey) {
		CommonMapEntity entity = findByMapKey(mapKey);
		if (entity == null) {
			return null;
		}
		return Integer.valueOf(entity.getMapValue());
	}

	@Override
	public Long getLong(String mapKey) {
		CommonMapEntity entity = findByMapKey(mapKey);
		if (entity == null) {
			return null;
		}
		return Long.valueOf(entity.getMapValue());
	}

	@Override
	public String getString(String mapKey) {
		CommonMapEntity entity = findByMapKey(mapKey);
		if (entity == null) {
			return null;
		}
		return entity.getMapValue();
	}

	@Override
	public void set(String mapKey, Object mapValue) {
		Assert.notNull(mapValue, "mapValue为空");
		CommonMapEntity entity = findByMapKey(mapKey);
		if (entity == null) {
			entity = new CommonMapEntity();
			entity.setMapKey(mapKey);
		}
		entity.setMapValue(mapValue.toString());
		if (entity.getId() == null) {
			commonMapRepository.save(entity);
		} else {
			commonMapRepository.update(entity);
		}
	}

	@Override
	public void remove(String mapKey) {
		CommonMapEntity entity = findByMapKey(mapKey);
		if (entity == null) {
			return;
		}
		commonMapRepository.delete(entity.getId());
	}

}
