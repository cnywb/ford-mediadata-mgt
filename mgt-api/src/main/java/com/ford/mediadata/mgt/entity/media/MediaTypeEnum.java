package com.ford.mediadata.mgt.entity.media;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public enum MediaTypeEnum {

	// 易湃
	YiPai("易湃"),
	// 车商汇
	Cheshanghui("车商汇");

	@Setter
	private String name;

}
