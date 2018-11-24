package com.ford.mediadata.mgt.entity.project;

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
@Table(name = "MD_PROJECT_USER_DEALER")
public class ProjectUserDealerEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_PROJECT_USER_DEALER")
	@SequenceGenerator(name = "SEQ_MD_PROJECT_USER_DEALER", allocationSize = 1, sequenceName = "SEQ_MD_PROJECT_USER_DEALER")
	private Long id;

	@Column(name = "PROJECT_USER_ID")
	private Long projectUserId;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

}
