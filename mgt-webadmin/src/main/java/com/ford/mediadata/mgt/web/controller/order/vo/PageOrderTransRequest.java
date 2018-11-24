package com.ford.mediadata.mgt.web.controller.order.vo;

import java.util.Date;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageOrderTransRequest extends CommonGetReq {

	private String orderId;

	private String campaignChannelId;

	private Date importDateStartDate;

	private Date importDateEndDate;
	
	private String localToDealerId;
	
	private Long refId;
	
	private Date orderBusinessOpportunityCreateTimeStartDate;
	
	private Date orderBusinessOpportunityCreateTimeEndDate;

}
