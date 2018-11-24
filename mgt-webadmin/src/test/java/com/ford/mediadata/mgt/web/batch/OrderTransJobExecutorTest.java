package com.ford.mediadata.mgt.web.batch;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.ford.mediadata.mgt.ApplicationContextTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderTransJobExecutorTest extends ApplicationContextTest {

	@Resource
	private JobLauncher jobLauncher;

	@Resource
	private Job commonOrderToObJob;

	@Resource
	private Job callOrderToObJob;

	@Resource
	private Job cshOrderToObJob;

	@Resource
	private Job cshCallToObJob;

	@Test
	public void commonOrderToObJob() {
		try {
			DateTime baseDate = DateTime.now().minusDays(0);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(commonOrderToObJob, builder.toJobParameters());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void callOrderToObJob() {
		try {
			DateTime baseDate = DateTime.now().minusDays(0);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(callOrderToObJob, builder.toJobParameters());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void cshOrderToObJob() {
		try {
			DateTime baseDate = DateTime.now().minusDays(0);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(cshOrderToObJob, builder.toJobParameters());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void cshCallToObJob() {
		try {
			DateTime baseDate = DateTime.now().minusDays(0);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(cshCallToObJob, builder.toJobParameters());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
