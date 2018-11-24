package com.ford.mediadata.mgt.web.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Test;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.service.order.SequenceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SequenceServiceTest extends ApplicationContextTest {

	@Resource
	private SequenceService sequenceService;

	private ExecutorService service = Executors.newFixedThreadPool(10);

	@Test
	public void execute() {
		try {
			for (int i = 0; i < 10; i++) {
				SeqThread thread = new SeqThread();
				service.execute(thread);
			}
			Thread.sleep(60000);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public class SeqThread implements Runnable {

		@Override
		public void run() {
			for (;;)
				log.info(sequenceService.nextval("TEST").toString());
		}

	}

}