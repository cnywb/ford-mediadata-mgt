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
@Table(name = "MD_PROJECT_CITY")
public class ProjectCityEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_PROJECT_CITY")
	@SequenceGenerator(name = "SEQ_MD_PROJECT_CITY", allocationSize = 1, sequenceName = "SEQ_MD_PROJECT_CITY")
	private Long id;

	@Column(name = "PROJECT_CODE")
	private String projectCode;

	@Column(name = "PROVINCE")
	private String province;

	@Column(name = "CITY")
	private String city;

}
