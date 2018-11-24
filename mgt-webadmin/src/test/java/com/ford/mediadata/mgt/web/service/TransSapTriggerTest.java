package com.ford.mediadata.mgt.web.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.service.trigger.TransSapTrigger;

public class TransSapTriggerTest extends ApplicationContextTest {

	@Resource
	private TransSapTrigger transSapTrigger;

	@Test
	public void execute() {
		transSapTrigger.execute();
	}

}