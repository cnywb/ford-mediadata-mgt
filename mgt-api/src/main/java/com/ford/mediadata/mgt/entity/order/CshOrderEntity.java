package com.ford.mediadata.mgt.entity.order;

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
@Table(name = "MD_CSH_ORDER", uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_ID") })
public class CshOrderEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_CSH_ORDER")
	@SequenceGenerator(name = "SEQ_MD_CSH_ORDER", allocationSize = 1, sequenceName = "SEQ_MD_CSH_ORDER")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOAD_TIME")
	private Date uploadTime;

	@Column(name = "ORDER_ID")
	private String orderId;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

	@Column(name = "DISTRICTION")
	private String distriction;

	@Column(name = "NAME")
	private String name;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "CAR_MODEL")
	private String carModel;

	@Column(name = "CAR_MODEL_DETAIL")
	private String carModelDetail;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "ASSIGNED_DEALER_ID")
	private String assignedDealerId;

	@Column(name = "ASSIGNED_DEALER_NAME")
	private String assignedDealerName;

	@Column(name = "PROCEED_DEALER_ID")
	private String proceedDealerId;

	@Column(name = "PROCEED_DEALER_NAME")
	private String proceedDealerName;

	@Column(name = "ORDER_DATE_TIME")
	private Date orderDateTime;

	@Column(name = "PROCEED_DATE_TIME")
	private Date proceedDateTime;

	@Column(name = "IS_TEST_DRIVE")
	private String isTestDrive;

}