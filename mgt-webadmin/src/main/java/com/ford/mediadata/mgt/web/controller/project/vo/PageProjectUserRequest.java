package com.ford.mediadata.mgt.web.controller.project.vo;

import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;
import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageProjectUserRequest extends CommonGetReq {

	private String projectCode;

	private String username;

	private String usernameLike;

	private ProjectUserRoleEnum role;

}
