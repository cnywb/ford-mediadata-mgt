package com.ford.mediadata.mgt.web.controller.api.vo.project;

import java.util.List;

import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUserVO {

	private String username;

	private String password;

	private ProjectUserRoleEnum role;

	private String eventCity;

	private String serial;

	private List<DealerVO> dealers;

	@Getter
	@Setter
	public static class DealerVO {

		private String province;

		private String city;

		private String code;

		private String name;

	}

}
