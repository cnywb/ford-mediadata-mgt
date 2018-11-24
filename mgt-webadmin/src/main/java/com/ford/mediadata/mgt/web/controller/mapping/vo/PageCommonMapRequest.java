package com.ford.mediadata.mgt.web.controller.mapping.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCommonMapRequest extends CommonGetReq {

	private String mapKeyLike;

}
