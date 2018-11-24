
package com.ford.mediadata.mgt.entity.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ford.mediadata.mgt.entity.VersionEntity;
import com.ford.mediadata.mgt.entity.media.MediaSourceEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 线索转换中间表
 * 
 * @author chunliang.you
 *
 */
@Getter
@Setter
@Entity
@Table(name = "MD_ORDER_TRANS")
public class OrderTransEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_ORDER_TRANS")
	@SequenceGenerator(name = "SEQ_MD_ORDER_TRANS", allocationSize = 1, sequenceName = "SEQ_MD_ORDER_TRANS")
	private Long id;

	/**
	 * 数据创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CREATE_DATE")
	private Date dataCreateDate;

	/**
	 * 数据更新日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_UPDATE_DATE")
	private Date dataUpdateDate;

	/**
	 * 订单类商机ID
	 */
	@Column(name = "ORDER_ID")
	private String orderId;

	/**
	 * 订单类型<br/>
	 * 1新车，2试驾，6置换
	 */
	@Column(name = "ORDER_TYPE_ID")
	private Integer orderTypeId;

	/**
	 * 订单类型名称<br/>
	 * 新车，试驾，置换
	 */
	@Column(name = "ORDER_TYPE_NAME")
	private String orderTypeName;

	/**
	 * 订购者名称
	 */
	@Column(name = "USER_NAME")
	private String userName;

	/**
	 * 订购者电话
	 */
	@Column(name = "USER_MOBILE")
	private String userMobile;

	/**
	 * 订购者性别<br/>
	 * 0女，1男，-1未知
	 */
	@Column(name = "USER_GENDER")
	private Integer userGender;

	/**
	 * 订购者邮件
	 */
	@Column(name = "USER_MAIL")
	private String userMail;

	/**
	 * 车型颜色
	 */
	@Column(name = "CAR_COLOR")
	private String carColor;

	/**
	 * 车型名称
	 */
	@Column(name = "CS_NAME")
	private String csName;

	/**
	 * 车名称
	 */
	@Column(name = "CAR_NAME")
	private String carName;

	/**
	 * 销售顾问
	 */
	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	/**
	 * 线索在易车的处理状态
	 */
	@Column(name = "STATUS")
	private Integer status;

	/**
	 * 首次处理时间
	 */
	@Column(name = "FIST_FOLLOW_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fistFollowTime;

	/**
	 * 省份名称
	 */
	@Column(name = "PROVINCE_NAME")
	private String provinceName;

	/**
	 * 城市名称
	 */
	@Column(name = "CITY_NAME")
	private String cityName;

	/**
	 * 订单类商机创建时间
	 */
	@Column(name = "OPPORTUNITY_CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderBusinessOpportunityCreateTime;

	/**
	 * 经销商名称
	 */
	@Column(name = "DEALER_NAME")
	private String dealerName;

	/**
	 * 经销商ID
	 */
	@Column(name = "DEALER_ID")
	private String dealerId;

	/**
	 * 订单备注信息
	 */
	@Column(name = "ORDER_REMARK")
	private String orderRemark;

	/**
	 * 处理经销商Id
	 */
	@Column(name = "PROCEED_DEALER_ID")
	private String proceedDealerId;

	/**
	 * 处理经销商
	 */
	@Column(name = "PROCEED_DEALER_NAME")
	private String proceedDealerName;

	/**
	 * 处理时间
	 */
	@Column(name = "PROCEED_DATE_TIME")
	private Date proceedDateTime;

	/**
	 * 是否试驾
	 */
	@Column(name = "IS_TEST_DRIVE")
	private String isTestDrive;

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
	 * 经营厂商
	 */
	@Column(name = "OPERATION_BRAND")
	private String operationBrand;

	/**
	 * 被叫400
	 */
	@Column(name = "FOO_PHONE")
	private String fooPhone;

	/**
	 * 是否成功
	 */
	@Column(name = "IS_SUCCESS")
	private String isSuccess;

	/**
	 * 主叫省份
	 */
	@Column(name = "CALLER_PROVINCE")
	private String callerProvince;

	/**
	 * 主叫城市
	 */
	@Column(name = "CALLER_CITY")
	private String callerCity;

	/**
	 * 400状态
	 */
	@Column(name = "FOO_STATUS")
	private String fooStatus;

	/**
	 * 谁先挂断
	 */
	@Column(name = "CUTOFF")
	private String cutoff;

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

	/**
	 * 媒体来源
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "MEDIA_SOURCE")
	private MediaSourceEnum mediaSource;

	/**
	 * 活动渠道ID
	 */
	@Column(name = "CAMPAIGN_CHANNEL_ID")
	private String campaignChannelId;

	/**
	 * LMC ID
	 */
	@Column(name = "LOCAL_TO_DEALER_ID")
	private String localToDealerId;

	/**
	 * LMC处理时间
	 */
	@Column(name = "IMPORT_DATE")
	private Date importDate;

	/**
	 * 流程标签
	 */
	@Column(name = "SYMBOL")
	private String symbol;

	/**
	 * SAP文件状态<br/>
	 * 0未生成，1已生成
	 */
	@Column(name = "SAP_FILE_STATUS")
	private Integer sapFileStatus;

	/**
	 * SAP文件名
	 */
	@Column(name = "SAP_FILE_NAME")
	private String sapFileName;

	/**
	 * SAP文件生成时间
	 */
	@Column(name = "SAP_FILE_PROC_TIME")
	private Date sapFileProcTime;

	/**
	 * 关联ID
	 */
	@Column(name = "REF_ID")
	private Long refId;

}
