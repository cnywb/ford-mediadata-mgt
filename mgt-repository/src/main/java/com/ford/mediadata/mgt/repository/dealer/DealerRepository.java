package com.ford.mediadata.mgt.repository.dealer;

import java.util.List;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 经销商仓储
 * 
 * @author chunliang.you
 *
 */
public interface DealerRepository extends JpaRepository<DealerEntity, Long> {

	Page<DealerEntity> findByGridPage(GridPage page, DealerEntity condition);

	List<DealerEntity> list(DealerEntity condition);

}