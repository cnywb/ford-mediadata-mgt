package com.ford.mediadata.mgt.entity.batch;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MD_JOB_EXECUTION_PARAMS")
public class JobExecutionParamsEntity {

	@JSONField(serialize = false)
	@EmbeddedId
	private JobExecutionParamsEntityPK id;

	@JSONField(serialize = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_EXECUTION_ID", insertable = false, updatable = false)
	private JobExecutionEntity jobExecution;

	@Column(name = "TYPE_CD")
	private String typeCd;

	@Column(name = "KEY_NAME", insertable = false, updatable = false)
	private String keyName;

	@Column(name = "STRING_VAL")
	private String stringVal;

	@Column(name = "DATE_VAL")
	private Date dateVal;

	@Data
	@Embeddable
	public static class JobExecutionParamsEntityPK implements Serializable {

		private static final long serialVersionUID = 3088178235738204983L;

		@Column(name = "JOB_EXECUTION_ID")
		private Long jobExecutionId;

		@Column(name = "KEY_NAME")
		private String keyName;

	}

}