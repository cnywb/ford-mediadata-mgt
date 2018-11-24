package com.ford.mediadata.mgt.entity.batch;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MD_JOB_EXECUTION")
public class JobExecutionEntity {

	@Id
	@Column(name = "JOB_EXECUTION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_JOB_EXECUTION")
	@SequenceGenerator(name = "SEQ_MD_JOB_EXECUTION", allocationSize = 1, sequenceName = "SEQ_MD_JOB_EXECUTION")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_INSTANCE_ID")
	private JobInstanceEntity jobInstance;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "jobExecution")
	private Set<JobExecutionParamsEntity> jobExecutionParams;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "START_TIME")
	private Date startTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "EXIT_CODE")
	private String exitCode;

	@Column(name = "EXIT_MESSAGE")
	private String exitMessage;

}