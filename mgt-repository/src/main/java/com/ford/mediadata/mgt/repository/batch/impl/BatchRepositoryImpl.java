package com.ford.mediadata.mgt.repository.batch.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ford.mediadata.mgt.entity.batch.JobExecutionEntity;
import com.ford.mediadata.mgt.repository.batch.BatchRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;

/**
 * 批处理
 * 
 * @author chunliang.you
 *
 */
@Transactional(readOnly = true)
@Repository
public class BatchRepositoryImpl extends DefaultJpaRepository<JobExecutionEntity, Long> implements BatchRepository {

	@Override
	public Page<JobExecutionEntity> findByGridPage(GridPage page, String jobName, Date createTimeStartDate,
			Date createTimeEndDate) {
		StringQuery query = buildQuery(jobName, createTimeStartDate, createTimeEndDate);
		return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
	}

	private StringQuery buildQuery(String jobName, Date createTimeStartDate, Date createTimeEndDate) {
		return StringQuery.newQuery().query("from JobExecutionEntity t where 1 = 1 ")
				// jobName
				.predicateHasText(jobName).query(" and t.jobInstance.jobName = :jobName").param("jobName", jobName)
				// createTimeStartDate
				.predicateNotNull(createTimeStartDate).query(" and t.createTime >= :createTimeStartDate")
				.param("createTimeStartDate", createTimeStartDate)
				// createTimeEndDate
				.predicateNotNull(createTimeEndDate).query(" and t.createTime <= :createTimeEndDate")
				.param("createTimeEndDate", createTimeEndDate).predicate(true).query(" order by t.id desc")
				// build
				.build();
	}

}