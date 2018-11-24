/*
 * Copyright (c)  2016, 
 * All rights reserved.
 * ExcelImportResult.java 2016-05-13 下午4:42
 */

package com.ford.mediadata.mgt.service.excel.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelImportResult<T extends ExcelImportBase> {

	private int count;

	private int successCount;

	private List<T> result;

}