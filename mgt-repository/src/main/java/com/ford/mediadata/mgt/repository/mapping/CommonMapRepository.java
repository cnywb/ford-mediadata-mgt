package com.ford.mediadata.mgt.repository.mapping;

import java.util.List;

import com.ford.mediadata.mgt.entity.mapping.CommonMapEntity;
import com.ford.mediadata.mgt.entity.mapping.CommonMapEntityCondition;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * K/V MAP
 * 
 * @author chunliang.you
 *
 */
public interface CommonMapRepository extends JpaRepository<CommonMapEntity, Long> {

	Page<CommonMapEntity> findByGridPage(GridPage page, CommonMapEntityCondition condition);

	List<CommonMapEntity> list(CommonMapEntityCondition condition);

}