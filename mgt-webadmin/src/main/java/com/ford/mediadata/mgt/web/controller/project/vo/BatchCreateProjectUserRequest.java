package com.ford.mediadata.mgt.web.controller.project.vo;

import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchCreateProjectUserRequest {

	private String projectCode;

	private String eventProvince;

	private String eventCity;

	private String serial;

	private ProjectUserRoleEnum role;

	private String userNamePrefix;

	private String password;

	private Integer beginIndex;

	private Integer endIndex;

	private String dealerCodes;

}
