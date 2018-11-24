package com.ford.mediadata.mgt.web.controller.api.vo.dealer;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinceDealerVO {

	@JSONField(name = "p")
	private String provinceName;

	@JSONField(name = "c")
	private List<CityDealerVO> cityDealerList;
	
	@Getter
	@Setter
	public static class CityDealerVO {

		@JSONField(name = "n")
		private String cityName;

		@JSONField(name = "a")
		private List<DealerVO> dealerList;

	}

}
