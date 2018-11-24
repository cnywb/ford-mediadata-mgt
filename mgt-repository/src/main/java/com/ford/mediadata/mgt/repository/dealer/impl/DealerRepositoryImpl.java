package com.ford.mediadata.mgt.repository.dealer.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.repository.dealer.DealerRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 经销商仓储
 * 
 * @author chunliang.you
 *
 */
@Repository
public class DealerRepositoryImpl extends DefaultJpaRepository<DealerEntity, Long> implements DealerRepository {

	@Override
	public Page<DealerEntity> findByGridPage(GridPage page, DealerEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	@Override
	public List<DealerEntity> list(DealerEntity condition) {
		StringQuery query = buildQuery(condition);
		return find(query);
	}

	private StringQuery buildQuery(DealerEntity condition) {
		return StringQuery.newQuery().query("from DealerEntity t where 1 = 1 ")
				// code
				.predicateHasText(condition.getCode()).query(" and t.code = :code").param("code", condition.getCode())
				// name
				.predicateHasText(condition.getName()).query(" and t.name like :name")
				.likeParam("name", condition.getName())
				// province
				.predicateHasText(condition.getProvince()).query(" and t.province like :province")
				.likeParam("province", condition.getProvince())
				// city
				.predicateHasText(condition.getCity()).query(" and t.city like :city")
				.likeParam("city", condition.getCity())
				// order
				.predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}