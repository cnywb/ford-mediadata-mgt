package com.ford.mediadata.mgt.web.controller.project.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProjectCityRequest {

	private String projectCode;

	private List<ProvinceCity> selectedProjectCityList;

	@Getter
	@Setter
	public static class ProvinceCity {

		private String province;

		private String city;

	}

}
