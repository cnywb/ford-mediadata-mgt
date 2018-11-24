package com.ford.mediadata.mgt.web.controller.batch;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.common.constants.DateFormatUtils;
import com.ford.mediadata.mgt.common.constants.ThreadUtils;
import com.ford.mediadata.mgt.entity.batch.JobExecutionEntity;
import com.ford.mediadata.mgt.repository.batch.BatchRepository;
import com.ford.mediadata.mgt.web.controller.batch.vo.ActivateBatchRequest;
import com.ford.mediadata.mgt.web.controller.batch.vo.PageBatchRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * 批处理
 * 
 * @author chunliang.you
 *
 */
@Slf4j
@Controller
public class BatchController {

	@Resource
	private BatchRepository batchRepository;

	@Resource
	private JobLauncher jobLauncher;

	@Resource
	private ApplicationContext applicationContext;

	@ApiService(transCode = "pageBatch")
	public Page<JobExecutionEntity> page(PageBatchRequest req) throws ParseException {
		String jobName = null;
		Date createTimeStartDate = null;
		Date createTimeEndDate = null;
		if (StringUtils.isNotEmpty(req.getJobName())) {
			jobName = req.getJobName();
		}
		if (StringUtils.isNotEmpty(req.getCreateTimeStartDate())) {
			createTimeStartDate = DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get()
					.parse(req.getCreateTimeStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(req.getCreateTimeEndDate())) {
			createTimeEndDate = DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get()
					.parse(req.getCreateTimeEndDate() + " 23:59:59");
		}
		return batchRepository.findByGridPage(req.getPage(), jobName, createTimeStartDate, createTimeEndDate);
	}

	@ApiService(transCode = "activateBatch")
	public void activate(final ActivateBatchRequest req) throws ParseException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		log.info("用户发起批处理：{}", JSON.toJSONString(req));
		Assert.hasText(req.getJobName(), "批处理为空");
		Assert.hasText(req.getStartDate(), "日期范围为空");
		Assert.hasText(req.getEndDate(), "日期范围为空");
		Date startDate = DateFormatUtils.YYYY_MM_DD.get().parse(req.getStartDate());
		Date endDate = DateFormatUtils.YYYY_MM_DD.get().parse(req.getEndDate());
		Assert.isTrue(startDate.getTime() <= endDate.getTime(), "起始日期不能晚于结束日期");
		ThreadUtils.of(new Runnable() {
			@Override
			public void run() {
				try {
					Job job = applicationContext.getBean(req.getJobName(), Job.class);
					DateTime dayIndex = new DateTime(DateFormatUtils.YYYY_MM_DD.get().parse(req.getStartDate()));
					while (true) {
						DateTime beginTime = dayIndex.withMillisOfDay(0);
						DateTime endTime = beginTime.plusDays(1).minusMillis(1);
						try {
							log.info("执行批处理：{}", JSON.toJSONString(req));
							log.info("参数：{}-{}", DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().format(beginTime.toDate()),
									DateFormatUtils.YYYY_MM_DD_HH_MM_SS.get().format(endTime.toDate()));
							JobParametersBuilder builder = new JobParametersBuilder();
							builder.addDate("beginTime", beginTime.toDate());
							builder.addDate("endTime", endTime.toDate());
							jobLauncher.run(job, builder.toJobParameters());
						} catch (Exception e) {
							log.error("执行批处理异常", e);
						}
						if (DateFormatUtils.YYYY_MM_DD.get().format(dayIndex.toDate()).equals(req.getEndDate())) {
							break;
						}
						dayIndex = dayIndex.plusDays(1);
					}
				} catch (Exception e) {
					log.error("用户发起批处理异常：{}", JSON.toJSONString(req), e);
				}
			}
		}).start();

	}

}
