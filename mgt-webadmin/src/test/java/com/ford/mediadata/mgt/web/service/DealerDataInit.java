package com.ford.mediadata.mgt.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.service.dealer.DealerService;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.DealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO.CityDealerVO;

import io.dabing.common.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DealerDataInit extends ApplicationContextTest {

	@Resource
	private DealerService dealerService;

	@Test
	public void initData() {
		try {
			String json = ResourceUtils.readString("api/getDealerTree.json");
			List<ProvinceDealerVO> provinceDealerVOList = JSON.parseArray(json, ProvinceDealerVO.class);
			for (ProvinceDealerVO province : provinceDealerVOList) {
				for (CityDealerVO city : province.getCityDealerList()) {
					for (DealerVO dealer : city.getDealerList()) {
						DealerEntity dealerEntity = new DealerEntity();
						dealerEntity.setProvince(province.getProvinceName());
						dealerEntity.setCity(city.getCityName());
						dealerEntity.setCode(dealer.getCode());
						dealerEntity.setName(dealer.getName());
						dealerService.save(dealerEntity);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
