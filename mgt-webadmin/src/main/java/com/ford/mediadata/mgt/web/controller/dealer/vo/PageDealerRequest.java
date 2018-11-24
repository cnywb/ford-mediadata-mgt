package com.ford.mediadata.mgt.web.controller.dealer.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDealerRequest extends CommonGetReq {

	private String code;

	private String name;

	private String province;

	private String city;

}
