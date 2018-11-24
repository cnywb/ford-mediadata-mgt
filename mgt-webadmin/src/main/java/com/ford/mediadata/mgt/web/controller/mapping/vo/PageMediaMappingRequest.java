package com.ford.mediadata.mgt.web.controller.mapping.vo;

import com.ford.mediadata.mgt.entity.mapping.MappingTypeEnum;
import com.ford.mediadata.mgt.entity.media.MediaTypeEnum;
import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMediaMappingRequest extends CommonGetReq {

	private MediaTypeEnum mediaType;

	private MappingTypeEnum mappingType;

	private String mediaValue;

	private String sysValue;

}
