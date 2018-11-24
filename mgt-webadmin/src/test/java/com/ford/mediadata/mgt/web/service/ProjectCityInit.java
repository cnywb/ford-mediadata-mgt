package com.ford.mediadata.mgt.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.entity.project.ProjectCityEntity;
import com.ford.mediadata.mgt.service.project.ProjectCityService;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO.CityDealerVO;
import com.google.common.collect.Lists;

import io.dabing.common.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectCityInit extends ApplicationContextTest {

	final private String PROEJCT_CODE = "2017_DREAM_WORKS";

	@Resource
	private ProjectCityService projectCityService;

	@Test
	public void initCity() {
		try {
			for (ProjectCityEntity entity : getDataList()) {
				projectCityService.save(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private List<ProjectCityEntity> getDataList() {
		List<ProjectCityEntity> dataList = Lists.newArrayList();
		String json = ResourceUtils.readString("api/getDealerTree.json");
		List<ProvinceDealerVO> provinceDealerVOList = JSON.parseArray(json, ProvinceDealerVO.class);
		for (ProvinceDealerVO provinceDealerVO : provinceDealerVOList) {
			for (CityDealerVO cityDealerVO : provinceDealerVO.getCityDealerList()) {
				ProjectCityEntity entity = new ProjectCityEntity();
				entity.setProjectCode(PROEJCT_CODE);
				entity.setProvince(provinceDealerVO.getProvinceName());
				entity.setCity(cityDealerVO.getCityName());
				dataList.add(entity);
			}
		}
		return dataList;
	}

}
