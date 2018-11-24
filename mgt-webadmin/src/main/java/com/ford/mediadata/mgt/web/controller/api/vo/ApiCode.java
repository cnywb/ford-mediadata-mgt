package com.ford.mediadata.mgt.web.controller.api.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiCode {
	SUCCESS("000"), ERROR("999");
	private String code;
}