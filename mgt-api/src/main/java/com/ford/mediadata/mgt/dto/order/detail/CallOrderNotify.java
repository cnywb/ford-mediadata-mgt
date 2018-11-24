package com.ford.mediadata.mgt.dto.order.detail;

import java.math.BigDecimal;
import java.util.Date;

import com.ford.mediadata.mgt.dto.order.OrderNotifyError;
import com.ford.mediadata.mgt.dto.order.OrderNotifyReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 订单接口
 * 
 * @author chunliang.you
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CallOrderNotify extends OrderNotifyReq {

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

	@Getter
	@AllArgsConstructor
	public enum CallOrderNotifyError implements OrderNotifyError {

		// 005
		E005("005", "通话类商机ID不为空"),
		// 006
		E006("006", "经销商代码不为空"),
		// 007
		E007("007", "主叫电话号码不为空"),
		// 008
		E008("008", "城市ID不为空"),
		// EOF
		;

		private String code;

		private String message;

	}

}
