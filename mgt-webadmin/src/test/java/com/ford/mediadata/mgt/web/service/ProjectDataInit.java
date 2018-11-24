package com.ford.mediadata.mgt.web.service;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.entity.project.ProjectEntity;
import com.ford.mediadata.mgt.service.project.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectDataInit extends ApplicationContextTest {

	final private String PROEJCT_CODE = "2017_DREAM_WORKS";
	final private String PROEJCT_NAME = "2017梦工厂";

	private ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	@Resource
	private ProjectService projectService;

	@Test
	public void initProject() {
		try {
			ProjectEntity entity = new ProjectEntity();
			entity.setBeginTime(format.get().parse("2017-01-01"));
			entity.setEndTime(format.get().parse("2018-01-01"));
			entity.setCode(PROEJCT_CODE);
			entity.setName(PROEJCT_NAME);
			projectService.save(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
