package com.ford.mediadata.mgt.service.cache.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.service.cache.CacheService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

	@CacheEvict(cacheNames = { "dealerCache" }, allEntries = true)
	@Override
	public void cleanDealerCache() {
		log.info("cleanDealerCache");
	}

	@CacheEvict(cacheNames = { "projectCache" }, allEntries = true)
	@Override
	public void cleanProjectCache() {
		log.info("cleanProjectCache");
	}

}
