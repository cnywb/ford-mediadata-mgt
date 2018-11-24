package com.ford.mediadata.mgt.web.controller.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/27.
 */
@Getter
@Setter
public class CallOrderEntiyResp {
	
	private Long id;

	/**
	 * 通话类商机Id
	 */
	private Long callBusinessOpportunityId;

	/**
	 * 经销商代码
	 */
	private Long dealerId;

	/**
	 * 主叫电话号码
	 */
	private String callerPhoneNumber;

	/**
	 * 被叫电话号码
	 */
	private String calleeRealNumber;

	/**
	 * 通话开始时间
	 */
	private Date callBeginTime;

	/**
	 * 通话结束时间
	 */
	private Date callFinishTime;

	/**
	 * 城市ID
	 */
	private Long cityId;

	/**
	 * 省ID
	 */
	private Long provinceId;

	/**
	 * 通话时长<br/>
	 * 单位：秒
	 */
	private Long callerDuration;

	/**
	 * 座席
	 */
	private String agentName;

	/**
	 * 跟近时间
	 */
	private Date fistFollowTime;

	/**
	 * 跟近状态<br/>
	 * 0：未跟进 1：已跟进 2：无效
	 */
	private Integer dealerFollowStatus;

	/**
	 * 等待时长 单位：秒
	 */
	private Long waitDuration;

	/**
	 * 省份
	 */
	private String provinceName;

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	 * 通话类商机创建时间
	 */
	private Date callBusinessOpportunityCreateTime;

	/**
	 * 订购价格
	 */
	private BigDecimal referPrice;

	/**
	 * 上市年份
	 */
	private Integer yearType;

	/**
	 * 数据创建时间
	 */
	private Date createdDate;
	/**
	 * 数据跟新时间
	 */
	private Date updatedDate;

}
