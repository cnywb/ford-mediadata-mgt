package com.ford.mediadata.mgt.web.controller.project.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProjectUserDealerRequest {

	private Long projectUserId;

	private List<DealerVO> selectedDealerList;

	@Getter
	@Setter
	public static class DealerVO {

		private String province;

		private String city;

		private String name;

		private String code;

	}

}
