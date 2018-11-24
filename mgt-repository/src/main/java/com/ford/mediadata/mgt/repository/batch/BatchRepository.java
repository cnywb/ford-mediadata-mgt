package com.ford.mediadata.mgt.repository.batch;

import java.util.Date;

import com.ford.mediadata.mgt.entity.batch.JobExecutionEntity;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

/**
 * 批处理
 * 
 * @author chunliang.you
 *
 */
public interface BatchRepository extends JpaRepository<JobExecutionEntity, Long> {

	Page<JobExecutionEntity> findByGridPage(GridPage page, String jobName, Date createTimeStartDate,
			Date createTimeEndDate);

}