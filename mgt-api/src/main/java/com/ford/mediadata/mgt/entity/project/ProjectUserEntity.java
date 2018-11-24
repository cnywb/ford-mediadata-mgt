package com.ford.mediadata.mgt.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "MD_PROJECT_USER")
public class ProjectUserEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_PROJECT_USER")
	@SequenceGenerator(name = "SEQ_MD_PROJECT_USER", allocationSize = 1, sequenceName = "SEQ_MD_PROJECT_USER")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PROJECT_CODE")
	private String projectCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private ProjectUserRoleEnum role;

	@Column(name = "EVENT_PROVINCE")
	private String eventProvince;

	@Column(name = "EVENT_CITY")
	private String eventCity;

	@Column(name = "SERIAL")
	private String serial;

}
