package com.ford.mediadata.mgt.web.controller.project.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageProjectRequest extends CommonGetReq {

	private String code;

	private String name;

}
