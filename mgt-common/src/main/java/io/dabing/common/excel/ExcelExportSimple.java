/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExcelExportSimple.java 9552 2015年1月27日 下午6:10:31 WangLijun$
 */
package io.dabing.common.excel;

import io.dabing.common.util.NumberUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * <p>
 * Title: 简单Excel导出功能
 * </p>
 * <p>
 * Description: 简单Excel导出功能
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ExcelExportSimple<T> extends ExcelExport<T> {


	/***
	 * 根据标签设置标题
	 * @param sheet Excel工作表
	 * @param title 标签
	 * @param headers 标签头
	 * @param index 行索引
	 */
	protected void setHeader(HSSFSheet sheet, String title, List<Header> headers,
							 Integer index) {

		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(index++);
		HSSFRow row = sheet.createRow(index++);
		for (int i = 0; i < headers.size(); i++) {
			Header header=headers.get(i);
			Cell titleCell = rowTitle.createCell(i);
			if (i == 0) {
				rowTitle.createCell(i).setCellValue(new HSSFRichTextString(title));
				titleCell.setCellValue(title);
			}
			// 设置标签样式
			titleCell.setCellStyle(this.getHeaderTitleStytle());
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
		    //设置宽度
			if(header.getWidth()!=null&&header.getWidth()>0){
				sheet.setColumnWidth(cell.getColumnIndex(),header.getWidth());
			 
			}
			HSSFRichTextString text = new HSSFRichTextString(header.getName());
			cell.setCellValue(text);
			
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,(headers.size() - 1)));
	}
}
