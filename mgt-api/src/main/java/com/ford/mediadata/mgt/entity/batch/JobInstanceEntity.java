package com.ford.mediadata.mgt.entity.batch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MD_JOB_INSTANCE")
public class JobInstanceEntity {

	@Id
	@Column(name = "JOB_INSTANCE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_JOB_INSTANCE")
	@SequenceGenerator(name = "SEQ_MD_JOB_INSTANCE", allocationSize = 1, sequenceName = "SEQ_MD_JOB_INSTANCE")
	private Long id;

	@JSONField(serialize = false)
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "jobInstance")
	private JobExecutionEntity jobExecution;

	@Column(name = "JOB_NAME")
	private String jobName;

}