package com.ford.mediadata.mgt.web.controller.batch.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateBatchRequest extends CommonGetReq {

	private String jobName;

	private String startDate;

	private String endDate;

}