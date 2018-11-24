package com.ford.mediadata.mgt.entity.media;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MediaSourceEnum {

	// 易湃订单
	YiPaiOrder("9361", "易湃订单", MediaTypeEnum.YiPai),
	// 易湃话单
	YiPaiCall("9362", "易湃话单", MediaTypeEnum.YiPai),
	// 车商汇订单
	CheshanghuiOrder("9363", "车商汇订单", MediaTypeEnum.Cheshanghui),
	// 车商汇话单
	CheshanghuiCall("9364", "车商汇话单", MediaTypeEnum.Cheshanghui);

	@Getter
	private String campaignChannelId;

	@Getter
	private String name;

	@Getter
	private MediaTypeEnum mediaType;

}
