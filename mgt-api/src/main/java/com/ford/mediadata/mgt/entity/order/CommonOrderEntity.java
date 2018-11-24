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
@Table(name = "MD_COMMON_ORDER", uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_ID") })
public class CommonOrderEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_COMMON_ORDER")
	@SequenceGenerator(name = "SEQ_MD_COMMON_ORDER", allocationSize = 1, sequenceName = "SEQ_MD_COMMON_ORDER")
	private Long id;

	/**
	 * 订单类商机ID
	 */
	@Column(name = "ORDER_ID")
	private Long orderId;

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
	 * 品牌ID
	 */
	@Column(name = "BRAND_ID")
	private Long brandId;

	/**
	 * 品牌名称
	 */
	@Column(name = "BRAND_NAME")
	private String brandName;

	/**
	 * 车系ID
	 */
	@Column(name = "CS_ID")
	private Long csId;

	/**
	 * 车型名称
	 */
	@Column(name = "CS_NAME")
	private String csName;

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
	 * 车款Id
	 */
	@Column(name = "CAR_ID")
	private String carId;

	/**
	 * 车款Name
	 */
	@Column(name = "CAR_TYPE_NAME")
	private String carTypeName;

	/**
	 * 车名称
	 */
	@Column(name = "CAR_NAME")
	private String carName;

	/**
	 * 城市ID
	 */
	@Column(name = "CITY_ID")
	private Long cityId;

	/**
	 * 省份ID
	 */
	@Column(name = "PROVINCE_ID")
	private Long provinceId;

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
	private Long dealerId;

	/**
	 * 订单备注信息
	 */
	@Column(name = "ORDER_REMARK")
	private String orderRemark;

	/**
	 * 订购价格
	 */
	@Column(name = "YEFER_PRICE")
	private BigDecimal yeferPrice;

	/**
	 * 上市年份
	 */
	@Column(name = "YEAR_TYPE")
	private Integer yearType;

}
