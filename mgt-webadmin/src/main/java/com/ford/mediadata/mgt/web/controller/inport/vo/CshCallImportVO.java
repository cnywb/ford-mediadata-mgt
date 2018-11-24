package com.ford.mediadata.mgt.web.controller.inport.vo;

import java.util.Date;

import com.ford.mediadata.mgt.service.excel.impl.ExcelField;
import com.ford.mediadata.mgt.service.excel.impl.ExcelImportBase;
import com.ford.mediadata.mgt.service.excel.impl.ExcelField.ColType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CshCallImportVO extends ExcelImportBase {

	@ExcelField(col = 0)
	private String orderId;

	@ExcelField(col = 1)
	private String dealerId;

	@ExcelField(col = 2)
	private String dealerName;

	@ExcelField(col = 3)
	private String province;

	@ExcelField(col = 4)
	private String city;

	@ExcelField(col = 5)
	private String operationBrand;

	@ExcelField(col = 6)
	private String brand;

	@ExcelField(col = 7, colType = ColType.DATE, dateFormat = "yyyy/MM/dd HH:mm:ss")
	private Date callDateTime;

	@ExcelField(col = 8)
	private String callerPhoneNumber;

	@ExcelField(col = 9)
	private String calleeRealNumber;

	@ExcelField(col = 10)
	private String fooPhone;

	@ExcelField(col = 11)
	private String callerDuration;

	@ExcelField(col = 12)
	private String waitDuration;

	@ExcelField(col = 13)
	private String isSuccess;

	@ExcelField(col = 14)
	private String callerProvince;

	@ExcelField(col = 15)
	private String callerCity;

	@ExcelField(col = 16)
	private String fooStatus;

	@ExcelField(col = 17)
	private String cutoff;

}