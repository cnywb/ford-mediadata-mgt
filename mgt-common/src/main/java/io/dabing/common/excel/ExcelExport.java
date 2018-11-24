/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExportExcel.java 9552 2015年1月26日 下午5:14:22 WangLijun$
 */
package io.dabing.common.excel;

import io.dabing.common.date.DateUtil;
import io.dabing.common.util.NumberUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 * Title: excel 通用导出功能
 * </p>
 * <p>
 * Description: excel 通用导出功能，根据泛型导出
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
public class ExcelExport<T> {
	/** 日志 */
	private final static Logger logger = LoggerFactory.getLogger(ExcelExport.class);
	/**默认*/
	public  final static String  DEFAULT_FONTNAME="微软雅黑";
	/** 工作薄 */
	public HSSFWorkbook workbook;
	/**单元格样式*/
	public HSSFCellStyle styleRow;
	/** 字体 默认为：微软雅黑 **/
	public String fontName =DEFAULT_FONTNAME;
	/**标题栏字体大小 */
	public Short titleFontSize=14;
	/**字体大小*/
	public Short fontSize=10;
	/**小数格式:默认保留两位小数*/
	public String decimalFmt="#,##0.00";
	/**全局日期格式:默认格式为：yyyy-MM-dd HH:mm:ss*/
	public String dataPattern= DateUtil.FORMAT_DATETIME_DEFAULT;
	/**默认Boolean转换*/
	public static Map<Object,Object> DEFAULT_BOOLEAN_MAP;
	/**默认宽度 15*/
	public Integer defaultColWidth=15;
	/**excel 全局配置*/
	public ExcelConfig excelConfig;
	/***
	 * 默认配置
	 */
	public ExcelExport() {
		excelConfig=new ExcelConfig();
		this.init();
	}
	/***
	 * 创建导出对象将Excel全局配置设置
	 * @param excelConfig Excel全局配置
	 */
	public ExcelExport(ExcelConfig  excelConfig){
		this.excelConfig=excelConfig;
		this.init();
	}
	/**初始化参数*/
	private void init(){
		this.fontName=excelConfig.getFontName();
		this.titleFontSize=excelConfig.getTitleFontSize();
		this.fontSize=excelConfig.getFontSize();
		this.decimalFmt=excelConfig.getDecimalFmt();
		this.dataPattern=excelConfig.getDataPattern();
		DEFAULT_BOOLEAN_MAP=this.excelConfig.getBooleanMap();
	}

	/**
	 * @return excel 全局配置
	 */
	public ExcelConfig getExcelConfig() {
		return excelConfig;
	}


	/**
	 * @param excelConfig 设置Excel全局配置
	 */
	public void setExcelConfig(ExcelConfig excelConfig) {
		this.excelConfig = excelConfig;
	}



	/***
	 * 创建工作薄
	 */
	public void createWorkBook() {
		this.workbook = new HSSFWorkbook();
	}

	/***
	 *
	 * @param title
	 *            表格标题
	 * @return
	 */
	public HSSFSheet createSheet(String title) {
		HSSFSheet sheet = this.workbook.createSheet(title);
		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth(this.defaultColWidth);
		return sheet;
	}
	/***
	 * 表格标题样式
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle getHeaderTitleStytle() {
		// 生成一个样式
		HSSFCellStyle titleStyle = this.workbook.createCellStyle();
		// 设置这些样式
		titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font =this.getFont(this.fontName,this.titleFontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		titleStyle.setFont(font);
		return titleStyle;
	}

	/**
	 * 设置标题栏的样式
	 *
	 * @return 标题栏的样式
	 */
	public HSSFCellStyle getHeaderStyle() {
		// 生成一个样式
		HSSFCellStyle style = this.workbook.createCellStyle();
		HSSFPalette customPalette =this.workbook.getCustomPalette();
		customPalette.setColorAtIndex(HSSFColor.YELLOW.index,(byte)216, (byte)216, (byte)216);
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font =this.getFont(this.fontName,this.fontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	/***
	 *
	 * @param fontName
	 * @param fontSize
	 * @return
	 */
	public HSSFFont getFont(String fontName, Short fontSize){
		HSSFFont font = this.workbook.createFont();
		font.setFontName(fontName);
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeightInPoints(fontSize);
		return font;
	}

	/****
	 * 设置标题栏
	 *
	 * @param title
	 * @param headers
	 */
	public void setHeader(HSSFSheet sheet, String title, String[] headers,
							 Integer rowIndex) {

		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(rowIndex++);
		HSSFRow row = sheet.createRow(rowIndex++);
		for (int i = 0; i < headers.length; i++) {
			Cell titleCell = rowTitle.createCell(i);
			if (i == 0) {
				rowTitle.createCell(i).setCellValue(new HSSFRichTextString(title));
				titleCell.setCellValue(title);
			}
			// 设置标签样式
			titleCell.setCellStyle(this.getHeaderTitleStytle());
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			sheet.autoSizeColumn(i + 1);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,(headers.length - 1)));
	}



	/***
	 * 根据对象的字段转为转换数据
	 *
	 * @param t  对象
	 * @return Map<String,Field>
	 */
	public Map<String, Field> getFiled(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map;
	}



	/***
	 *
	 * @param value 单元格的值
	 * @param fieldName 字段名称
	 * @param dataFormats 格式化
	 * @return
	 */
	public String getCallValueForDate(Object value,String fieldName,Map<String,String> dataFormats){
		Date date = (Date) value;
		if(!CollectionUtils.isEmpty(dataFormats)&&dataFormats.containsKey(fieldName)){
			String pattern=dataFormats.get(fieldName);
			if(StringUtils.isNotEmpty(pattern)){
				return DateUtil.formatDate(date,pattern);
			}
		}
		return DateUtil.formatDate(date,this.dataPattern);
	}


	/***
	 * 获取数据样式
	 * @param value Object
	 * @param codeTypes Map类型转换
	 * @return
	 */
	public String getCellValueForBoolean(Object value,String fieldName,Map<String,Map<Object,Object>> codeTypes){
		Boolean tempVal=(Boolean) value;
		if(!CollectionUtils.isEmpty(codeTypes)&&codeTypes.containsKey(fieldName)){
			Map<Object,Object> types=codeTypes.get(fieldName);
			if(!CollectionUtils.isEmpty(types)){
				return (String) (types.containsKey(tempVal)?types.get(tempVal): StringUtils.EMPTY);
			}
		}
		return  (String) (DEFAULT_BOOLEAN_MAP.containsKey(tempVal)?DEFAULT_BOOLEAN_MAP.get(tempVal): StringUtils.EMPTY);
	}

	/***
	 * 根据byte数据组设置单元格的值的及样式
	 * @param sheet 工作样
	 * @param patriarch 画图的顶级管理器
	 * @param colIndex 列索引
	 * @param rowIndex 行索引
	 * @param row  行
	 * @param cell 单元格
	 * @param value  单元格的值
	 * @param cellStyle 单元格样式
	 */
	public void setCellForImage(HSSFSheet sheet, HSSFPatriarch patriarch, Integer colIndex, Integer rowIndex, HSSFRow row, HSSFCell cell, Object value, HSSFCellStyle cellStyle){
		// 有图片时，设置行高为60px;
		row.setHeightInPoints(60);
		// 设置图片所在列宽度为80px,注意这里单位的一个换算
		sheet.setColumnWidth(colIndex, (short) (35.7 * 80));
		byte[] bsValue = (byte[]) value;
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) 6, rowIndex, (short) 6, rowIndex);
		anchor.setAnchorType(2);
		patriarch.createPicture(anchor, this.workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
		cell.setCellStyle(cellStyle);
	}



	/***
	 * 字符类型：根据字符串设置单元格的值和样式。
	 * @param cell  单元格
	 * @param str   单元格值
	 * @param cellStyle 单元格样式
	 */
	public  void setCellForString(HSSFCell cell, String str, HSSFCellStyle cellStyle){
		HSSFRichTextString richString = new HSSFRichTextString(str);
		cell.setCellValue(richString);
		cell.setCellStyle(cellStyle);
	}

	/***
	 * 根据对象获取字段的值，获取Value
	 * @param obj 对象
	 * @return Object的Value
	 */
	public Object getProperty(T obj,Field field){
		Object value=null;
		try {
			value = PropertyUtils.getProperty(obj,field.getName());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(),e);
		}
		return value;
	}

	/***
	 * 根据对象获取字段的值，获取Value
	 * @param obj 对象
	 * @return Object的Value
	 */
	public Object getProperty(T obj,String fieldName){
		Object value=null;
		try {
			value = PropertyUtils.getProperty(obj,fieldName);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(),e);
		}
		return value;
	}


	/**
	 * @return 工作薄
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook
	 *            设置一个工作薄
	 */
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	/***
	 * 设置行的样式
	 *
	 * @return HSSFCellStyle 行的样式
	 */
	protected HSSFCellStyle getRowStyle() {
		// 生成并设置另一个样式
		HSSFCellStyle styleRow = this.workbook.createCellStyle();
		styleRow.setFillForegroundColor(HSSFColor.WHITE.index);
		styleRow.setFillBackgroundColor(HSSFColor.WHITE.index);
		styleRow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//styleRow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleRow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		// 把字体应用到当前的样式
		styleRow.setFont(this.getFont(this.fontName,this.fontSize));
		return styleRow;
	}

	/****
	 * 根据格式设置单元格格式
	 * @param pattern 格式;
	 * @return  HSSFCellStyle
	 */
	protected HSSFCellStyle getCallDataStyle(String pattern){
		HSSFDataFormat df= this.workbook.createDataFormat();
		HSSFCellStyle cellStyle=this.getRowStyle();
		cellStyle.setDataFormat(df.getFormat(pattern));
		return cellStyle;
	}

}
