package com.ford.mediadata.mgt.service.dealer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.repository.dealer.DealerRepository;
import com.ford.mediadata.mgt.service.dealer.DealerService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

/**
 * 经销商服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class DealerServiceImpl implements DealerService {

	@Resource
	private DealerRepository dealerRepository;

	@Override
	public Page<DealerEntity> findByGridPage(GridPage page, DealerEntity condition) {
		return dealerRepository.findByGridPage(page, condition);
	}

	@CacheEvict(cacheNames = { "dealerCache" }, allEntries = true)
	@Override
	public void save(DealerEntity entity) {
		DealerEntity condition = new DealerEntity();
		condition.setCode(entity.getCode());
		List<DealerEntity> list = dealerRepository.list(condition);
		Assert.isTrue(CollectionUtils.isEmpty(list), "经销商代码已存在");
		dealerRepository.save(entity);
	}

	@CacheEvict(cacheNames = { "dealerCache" }, allEntries = true)
	@Override
	public void update(DealerEntity entity) {
		dealerRepository.update(entity);
	}

	@CacheEvict(cacheNames = { "dealerCache" }, allEntries = true)
	@Override
	public void delete(Long id) {
		dealerRepository.delete(id);
	}

	@Override
	public DealerEntity findById(Long id) {
		return dealerRepository.get(id);
	}

	@Cacheable(cacheNames = { "dealerCache" }, key = "'dealer_findAll'")
	@Override
	public List<DealerEntity> findAll() {
		DealerEntity condition = new DealerEntity();
		return dealerRepository.list(condition);
	}

	@Override
	public DealerEntity findByCode(String code) {
		Assert.hasText(code, "经销商代码为空");
		DealerEntity condition = new DealerEntity();
		condition.setCode(code);
		List<DealerEntity> list = dealerRepository.list(condition);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

}
