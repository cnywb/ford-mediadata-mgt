package com.ford.mediadata.mgt.service.mapping;

import java.util.List;

import com.ford.mediadata.mgt.entity.mapping.CommonMapEntity;
import com.ford.mediadata.mgt.entity.mapping.CommonMapEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * K/V MAP
 * 
 * @author chunliang.you
 *
 */
public interface CommonMapService {

	Page<CommonMapEntity> findByGridPage(GridPage page, CommonMapEntityCondition condition);

	List<CommonMapEntity> list(CommonMapEntityCondition condition);

	void save(CommonMapEntity entity);

	void update(CommonMapEntity entity);

	void delete(Long id);

	CommonMapEntity findByMapKey(String mapKey);

	Integer getInteger(String mapKey);

	Long getLong(String mapKey);

	String getString(String mapKey);

	void set(String mapKey, Object mapValue);

	void remove(String mapKey);

}
