package com.ford.mediadata.mgt.web.controller.order.vo;

import java.util.Date;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCshOrderRequest extends CommonGetReq {
	
	private Long id;

	private String orderId;

	private Date uploadTimeStartDate;

	private Date uploadTimeEndDate;

}
