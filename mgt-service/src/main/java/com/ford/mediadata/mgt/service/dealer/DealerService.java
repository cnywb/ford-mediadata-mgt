package com.ford.mediadata.mgt.service.dealer;

import java.util.List;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 经销商服务
 * 
 * @author chunliang.you
 *
 */
public interface DealerService {

	Page<DealerEntity> findByGridPage(GridPage page, DealerEntity condition);

    void save(DealerEntity entity);

    void update(DealerEntity entity);

    void delete(Long id);
    
    DealerEntity findById(Long id);
    
    List<DealerEntity> findAll();
    
    DealerEntity findByCode(String code);

}
