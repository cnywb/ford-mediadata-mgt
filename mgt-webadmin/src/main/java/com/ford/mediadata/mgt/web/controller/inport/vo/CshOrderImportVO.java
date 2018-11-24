package com.ford.mediadata.mgt.web.controller.inport.vo;

import java.util.Date;

import com.ford.mediadata.mgt.service.excel.impl.ExcelField;
import com.ford.mediadata.mgt.service.excel.impl.ExcelField.ColType;
import com.ford.mediadata.mgt.service.excel.impl.ExcelImportBase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CshOrderImportVO extends ExcelImportBase {

	@ExcelField(col = 0)
	private String orderId;

	@ExcelField(col = 1)
	private String province;

	@ExcelField(col = 2)
	private String city;

	@ExcelField(col = 3)
	private String distriction;

	@ExcelField(col = 4)
	private String name;

	@ExcelField(col = 5)
	private String mobile;

	@ExcelField(col = 6)
	private String brand;

	@ExcelField(col = 7)
	private String carModel;

	@ExcelField(col = 8)
	private String carModelDetail;

	@ExcelField(col = 9)
	private String remark;

	@ExcelField(col = 10)
	private String assignedDealerId;

	@ExcelField(col = 11)
	private String assignedDealerName;

	@ExcelField(col = 12)
	private String proceedDealerId;

	@ExcelField(col = 13)
	private String proceedDealerName;

	@ExcelField(col = 14, colType = ColType.DATE, dateFormat = "MM/dd/yyyy hh:mm:ss aa")
	private Date orderDateTime;

	@ExcelField(col = 15, colType = ColType.DATE, dateFormat = "MM/dd/yyyy hh:mm:ss aa")
	private Date proceedDateTime;

	@ExcelField(col = 16)
	private String isTestDrive;

}