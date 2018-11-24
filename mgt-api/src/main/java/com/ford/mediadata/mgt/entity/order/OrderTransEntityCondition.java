
package com.ford.mediadata.mgt.entity.order;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTransEntityCondition extends OrderTransEntity {

	private Date importDateStartDate;

	private Date importDateEndDate;

	private String orderBy;
	
	private Date orderBusinessOpportunityCreateTimeStartDate;
	
	private Date orderBusinessOpportunityCreateTimeEndDate;

}
