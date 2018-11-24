package com.ford.mediadata.mgt.entity.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.ford.mediadata.mgt.entity.VersionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MD_CALL_ORDER", uniqueConstraints = { @UniqueConstraint(columnNames = "CALL_ORDER_ID") })
public class CallOrderEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_CALL_ORDER")
	@SequenceGenerator(name = "SEQ_MD_CALL_ORDER", allocationSize = 1, sequenceName = "SEQ_MD_CALL_ORDER")
	private Long id;

	/**
	 * 通话类商机Id
	 */
	@Column(name = "CALL_ORDER_ID")
	private Long callBusinessOpportunityId;

	/**
	 * 经销商代码
	 */
	@Column(name = "DEALER_ID")
	private Long dealerId;

	/**
	 * 主叫电话号码
	 */
	@Column(name = "CALLER_PHONE_NUMBER")
	private String callerPhoneNumber;

	/**
	 * 被叫电话号码
	 */
	@Column(name = "CALLEE_REAL_NUMBER")
	private String calleeRealNumber;

	/**
	 * 通话开始时间
	 */
	@Column(name = "CALL_BEGI_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date callBeginTime;

	/**
	 * 通话结束时间
	 */
	@Column(name = "CALL_FINISH_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date callFinishTime;

	/**
	 * 城市ID
	 */
	@Column(name = "CITY_ID")
	private Long cityId;

	/**
	 * 省ID
	 */
	@Column(name = "PROVINCE_ID")
	private Long provinceId;

	/**
	 * 通话时长<br/>
	 * 单位：秒
	 */
	@Column(name = "CALLER_DURATION")
	private Long callerDuration;

	/**
	 * 座席
	 */
	@Column(name = "AGENT_NAME")
	private String agentName;

	/**
	 * 跟近时间
	 */
	@Column(name = "FIST_FOLLOW_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fistFollowTime;

	/**
	 * 跟近状态<br/>
	 * 0：未跟进 1：已跟进 2：无效
	 */
	@Column(name = "DEALER_FOLLOW_STATUS")
	private Integer dealerFollowStatus;

	/**
	 * 等待时长 单位：秒
	 */
	@Column(name = "WAIT_DURATION")
	private Long waitDuration;

	/**
	 * 省份
	 */
	@Column(name = "PROVINCE_NAME")
	private String provinceName;

	/**
	 * 城市名称
	 */
	@Column(name = "CITY_NAME")
	private String cityName;

	/**
	 * 通话类商机创建时间
	 */
	@Column(name = "OPPORTUNITY_CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date callBusinessOpportunityCreateTime;

	/**
	 * 订购价格
	 */
	@Column(name = "REFER_PRICE")
	private BigDecimal referPrice;

	/**
	 * 上市年份
	 */
	@Column(name = "YEAR_TYPE")
	private Integer yearType;

}
