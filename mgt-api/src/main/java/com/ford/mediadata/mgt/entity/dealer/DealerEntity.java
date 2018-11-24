package com.ford.mediadata.mgt.entity.dealer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ford.mediadata.mgt.entity.VersionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MD_DEALER")
public class DealerEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_DEALER")
	@SequenceGenerator(name = "SEQ_MD_DEALER", allocationSize = 1, sequenceName = "SEQ_MD_DEALER")
	private Long id;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

}
