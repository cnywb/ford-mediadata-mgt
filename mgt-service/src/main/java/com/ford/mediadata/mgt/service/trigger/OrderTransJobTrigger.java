package com.ford.mediadata.mgt.service.trigger;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Async
@Service("orderTransJobTrigger")
public class OrderTransJobTrigger extends BatchTriggerSupport {

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

	public void commonOrderToObJob() {
		try {
			if (!isBatchServer()) {
				return;
			}
			log.info("批处理开始-易湃订单转换");
			DateTime baseDate = DateTime.now().minusDays(1);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(commonOrderToObJob, builder.toJobParameters());
			log.info("批处理结束-易湃订单转换");
		} catch (Exception e) {
			log.error("易湃订单转换批处理异常：{}", e.getMessage(), e);
		}
	}

	public void callOrderToObJob() {
		try {
			if (!isBatchServer()) {
				return;
			}
			log.info("批处理开始-易湃话单转换");
			DateTime baseDate = DateTime.now().minusDays(1);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(callOrderToObJob, builder.toJobParameters());
			log.info("批处理结束-易湃话单转换");
		} catch (Exception e) {
			log.error("易湃话单转换批处理异常：{}", e.getMessage(), e);
		}
	}

	public void cshOrderToObJob() {
		try {
			if (!isBatchServer()) {
				return;
			}
			log.info("批处理开始-车商汇订单转换");
			DateTime baseDate = DateTime.now().minusDays(1);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(cshOrderToObJob, builder.toJobParameters());
			log.info("批处理结束-车商汇订单转换");
		} catch (Exception e) {
			log.error("车商汇订单转换批处理异常：{}", e.getMessage(), e);
		}
	}

	public void cshCallToObJob() {
		try {
			if (!isBatchServer()) {
				return;
			}
			log.info("批处理开始-车商汇话单转换");
			DateTime baseDate = DateTime.now().minusDays(1);
			DateTime beginTime = baseDate.withMillisOfDay(0);
			DateTime endTime = beginTime.plusDays(1).minusMillis(1);
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("beginTime", beginTime.toDate());
			builder.addDate("endTime", endTime.toDate());
			jobLauncher.run(cshCallToObJob, builder.toJobParameters());
			log.info("批处理结束-车商汇话单转换");
		} catch (Exception e) {
			log.error("车商汇话单转换批处理异常：{}", e.getMessage(), e);
		}
	}

}
