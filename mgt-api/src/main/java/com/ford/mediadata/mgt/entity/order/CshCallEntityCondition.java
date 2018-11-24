package com.ford.mediadata.mgt.entity.order;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CshCallEntityCondition extends CshCallEntity {

	private Date uploadTimeStartDate;

	private Date uploadTimeEndDate;
	
}
