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
@Table(name = "MD_CSH_CALL", uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_ID") })
public class CshCallEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_CSH_CALL")
	@SequenceGenerator(name = "SEQ_MD_CSH_CALL", allocationSize = 1, sequenceName = "SEQ_MD_CSH_CALL")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOAD_TIME")
	private Date uploadTime;

	@Column(name = "ORDER_ID")
	private String orderId;

	@Column(name = "DEALER_ID")
	private String dealerId;

	@Column(name = "DEALER_NAME")
	private String dealerName;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

	@Column(name = "OPERATION_BRAND")
	private String operationBrand;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "CALL_DATE_TIME")
	private Date callDateTime;

	@Column(name = "CALLER_PHONE_NUMBER")
	private String callerPhoneNumber;

	@Column(name = "CALLEE_REAL_NUMBER")
	private String calleeRealNumber;

	@Column(name = "FOO_PHONE")
	private String fooPhone;

	@Column(name = "CALLER_DURATION")
	private String callerDuration;

	@Column(name = "WAIT_DURATION")
	private String waitDuration;

	@Column(name = "IS_SUCCESS")
	private String isSuccess;

	@Column(name = "CALLER_PROVINCE")
	private String callerProvince;

	@Column(name = "CALLER_CITY")
	private String callerCity;

	@Column(name = "FOO_STATUS")
	private String fooStatus;

	@Column(name = "CUTOFF")
	private String cutoff;

}