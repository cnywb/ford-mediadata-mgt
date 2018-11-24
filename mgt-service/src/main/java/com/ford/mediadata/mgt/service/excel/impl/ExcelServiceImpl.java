/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelServiceImpl.java 2016-05-12 15:22
 */

package com.ford.mediadata.mgt.service.excel.impl;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ford.mediadata.mgt.entity.excel.ExcelDetail;
import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.service.excel.ExcelDataSupportService;
import com.ford.mediadata.mgt.service.excel.ExcelDetailService;
import com.ford.mediadata.mgt.service.excel.ExcelListService;
import com.ford.mediadata.mgt.service.excel.ExcelService;
import com.ford.mediadata.mgt.service.excel.ExcelUtil;
import com.ford.mediadata.mgt.service.excel.impl.ExcelField.ColType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.monitorjbl.xlsx.StreamingReader;

import ch.qos.logback.ext.spring.ApplicationContextHolder;
import io.dabing.common.Assert;
import io.dabing.common.date.DateUtil;
import io.dabing.common.excel.CellAlign;
import io.dabing.common.excel.CellDataType;
import io.dabing.common.excel.ExcelConfig;
import io.dabing.common.number.NumberUtils;
import io.dabing.common.util.NumberUtil;
import io.dabing.exception.BusinessException;
import io.dabing.exception.ErrorCode;
import io.dabing.exception.ExcelException;
import lombok.Cleanup;

/**
 * 描述:TODO
 *
 * @author ziv.hung create on 2016-05-12.
 * @since 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

	private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	protected HSSFCellStyle cellStyle;
	/** 默认 */
	public final static String DEFAULT_FONTNAME = "微软雅黑";
	/** 工作薄 */
	public HSSFWorkbook workbook;
	/** 单元格样式 */
	public HSSFCellStyle styleRow;
	/** 字体 默认为：微软雅黑 **/
	public String fontName = DEFAULT_FONTNAME;
	/** 标题栏字体大小 */
	public Short titleFontSize = 14;
	/** 字体大小 */
	public Short fontSize = 10;
	/** 小数格式:默认保留两位小数 */
	public String decimalFmt = "#,##0.00";
	/** 全局日期格式:默认格式为：yyyy-MM-dd HH:mm:ss */
	public String dataPattern = DateUtil.FORMAT_DATETIME_DEFAULT;
	/** 默认Boolean转换 */
	public static Map<Object, Object> DEFAULT_BOOLEAN_MAP;
	/** 默认宽度 15 */
	public Integer defaultColWidth = 15;
	/** excel 全局配置 */
	public ExcelConfig excelConfig;

	@Autowired
	ExcelListService excelListService;

	@Autowired
	ExcelDetailService excelDetailService;

	@Resource(name = "defaultExcelDataSupportService")
	ExcelDataSupportService defaultExcelDataSupportService;

	public <T extends ExcelImportBase> ExcelImportResult<T> importData(String businessCode, InputStream stream,
			Class<T> clazz) {
		ExcelImportResult<T> result = new ExcelImportResult<T>();
		// 校验数据完整性
		ExcelList excelList = excelListService.findBy(businessCode, ExcelList.TYPE_IMPORT);
		Assert.notNull(excelList, "【校验不通过】原因:未找到本模块的导入配置信息");
		// 根据业务模块代码 获取对应excel到model的配置信息
		List<ExcelDetail> excelDetailList = excelDetailService.findBy(excelList.getId());
		if (excelDetailList != null && excelDetailList.size() == 0) {
			logger.error("【校验不通过】原因 未找到表头映射配置,模块代码{},描述{},ID{}",
					new Object[] { excelList.getBusinessCode(), excelList.getBusinessDesc(), excelList.getId() });
			throw new BusinessException("【校验不通过】原因:未找到本模块的导入excel表头映射配置信息");
		}
		List<T> retval = Lists.newArrayList();
		try {
			// 解析excel文件
			BufferedInputStream in = new BufferedInputStream(stream);

			// 打开HSSFWorkbook
			Workbook wb = null;
			try {
				wb = WorkbookFactory.create(in);
			} catch (FileNotFoundException e) {
				logger.error("Excel导出失败! message:{}", e);
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				logger.error("Excel导出失败! message:{}", e);
				e.printStackTrace();
			}
			// 获取指定名称的sheet
			Sheet sheet = wb.getSheetAt(0);
			Assert.notNull(sheet, "【Excel解析失败】原因:导入的excel中sheet与配置不匹配");
			int rowSize = 0;// 导入 有效的总条数

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				// 无记录 跳过
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				if (row.getCell(0) == null) {
					continue;
				}
				rowSize++;// 记录存在总条数 ++;
				JSONObject json = new JSONObject();
				for (ExcelDetail excelDetail : excelDetailList) {
					Cell cell = row.getCell(excelDetail.getExcelCellNum().intValue());
					Boolean notNull = excelDetail.getNotNull();
					if (notNull && cell == null) {
						json.put("status", "fail");
						json.put("errorMsg", excelDetail.getExcelCell() + " is null!");
						break;
					} else {
						String key = excelDetail.getTableColumn();
						String dt = excelDetail.getDataType();
						System.out.println("key :" + key + "   dt:" + dt);
						if (cell == null) {
							json.put(key, null);
						} else if (ExcelDetail.DATA_TYPE_STRING.equalsIgnoreCase(dt)) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							json.put(key, cell.getStringCellValue());
						} else if (ExcelDetail.DATA_TYPE_INTEGER.equalsIgnoreCase(dt)
								|| ExcelDetail.DATA_TYPE_DOUBLE.equalsIgnoreCase(dt)
								|| ExcelDetail.DATA_TYPE_LONG.equalsIgnoreCase(dt)) {
							json.put(key, cell.getNumericCellValue());
						} else if (ExcelDetail.DATA_TYPE_DATE.equalsIgnoreCase(dt)) {
							json.put(key, cell.getDateCellValue());
						}
					}
				}
				T object = JSON.toJavaObject(json, clazz);
				retval.add(object);
				result.setCount(rowSize);
			}
			result.setResult(retval);

		} catch (IOException e) {
			logger.error("excel 解析 IO异常!", e);
		}
		return result;
	}

	public void exportData(String businessCode, HttpServletRequest request, HttpServletResponse httpServletResponse) {

		// 校验数据完整性
		ExcelList excelList = excelListService.findBy(businessCode, ExcelList.TYPE_EXPORT);
		Assert.notNull(excelList, "【校验不通过】原因:未找到本模块的导入配置信息");
		// 根据业务模块代码 获取对应excel到model的配置信息
		List<ExcelDetail> excelDetailList = excelDetailService.findBy(excelList.getId());
		if (excelDetailList != null && excelDetailList.size() == 0) {
			logger.error("【校验不通过】原因 未找到表头映射配置,模块代码{},描述{},ID{}",
					new Object[] { excelList.getBusinessCode(), excelList.getBusinessDesc(), excelList.getId() });
			throw new BusinessException("【校验不通过】原因:未找到本模块的导入excel表头映射配置信息");
		}

		StringBuffer sb = new StringBuffer();
		String[] fieldName = new String[excelDetailList.size()];
		String[] fieldType = new String[excelDetailList.size()];

		for (int i = 0; i < excelDetailList.size(); i++) {
			ExcelDetail d = excelDetailList.get(i);
			fieldName[i] = d.getExcelCell();
			fieldType[i] = d.getDataType();
			sb.append(d.getTableColumn());
			if (i != excelDetailList.size() - 1) {
				sb.append(",");
			}
		}

		List<Object[]> retval = null;
		String serviceName = excelList.getServiceName();

		Map reqParams = request.getParameterMap();
		Map<String, String> paramsMap = new HashMap<String, String>();
		for (String key : (Set<String>) reqParams.keySet()) {
			paramsMap.put(key, request.getParameter(key));
		}

		if (StringUtils.isEmpty(serviceName)) {
			retval = defaultExcelDataSupportService.getExportDataList(sb.toString(), excelList.getFrom(),
					excelList.getWhere(), excelList.getOrderBy(), paramsMap);
		} else {
			ExcelDataSupportService service = (ExcelDataSupportService) ApplicationContextHolder.getApplicationContext()
					.getBean(serviceName);
			if (service == null) {
				logger.error("【校验不通过】原因 未找到表头映射配置,模块代码{},描述{},ID{}",
						new Object[] { excelList.getBusinessCode(), excelList.getBusinessDesc(), excelList.getId() });
				throw new BusinessException("【校验不通过】原因:未找到本模块的导入excel表头映射service类");
			}
			retval = service.getExportDataList(sb.toString(), excelList.getFrom(), excelList.getWhere(),
					excelList.getOrderBy(), paramsMap);
		}

		if (retval == null) {
			logger.info("businessCode :{} retval is empty!", businessCode);
			return;
		}

		for (int i = 0; i < retval.size(); i++) {
			Object[] objs = retval.get(i);
			for (int j = 0; j < objs.length; j++) {
				String type = fieldType[j];
				if ("date".equalsIgnoreCase(type)) {
					Object value = DateUtil.formatDefaultDate((Date) objs[j]);
					objs[j] = value;
				} else if ("time".equalsIgnoreCase(type)) {
					Object value = DateUtil.formatDate((Date) objs[j], DateUtil.FORMAT_DATETIME_DEFAULT);
					objs[j] = value;
				}
			}
		}

		try {
			httpServletResponse.reset();
			httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition",
					"attachment;filename=" + new String((excelList.getFileName() + ".xlsx").getBytes(), "iso-8859-1"));
			@Cleanup
			OutputStream outputStream = httpServletResponse.getOutputStream();
			ExcelUtil.createWorkBook(retval, excelList.getSheetName(), fieldName).write(outputStream);
			outputStream.flush();
		} catch (IOException ex) {
			logger.error("Excel导出失败! message:{}", ex);
			Assert.isTrue(true, "Excel导出失败!");
		}
	}

	/**
	 * 导出excel文件,直接返回给浏览器(lion)
	 *
	 * @param businessCode
	 *            导入业务代码
	 */
	public void lionExportData(List<?> data, String businessCode, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		// 校验数据完整性
		ExcelList excelList = excelListService.findBy(businessCode, ExcelList.TYPE_EXPORT);
		Assert.notNull(excelList, "【校验不通过】原因:未找到本模块的导入配置信息");
		// 根据业务模块代码 获取对应excel到model的配置信息
		List<ExcelDetail> excelDetailList = excelDetailService.findBy(excelList.getId());
		if (excelDetailList != null && excelDetailList.size() == 0) {
			logger.error("【校验不通过】原因 未找到表头映射配置,模块代码{},描述{},ID{}",
					new Object[] { excelList.getBusinessCode(), excelList.getBusinessDesc(), excelList.getId() });
			throw new BusinessException("【校验不通过】原因:未找到本模块的导入excel表头映射配置信息");
		}

		// 声明一个工作薄
		this.createWorkBook();
		// 生成一个表格
		HSSFSheet sheet = this.workbook.createSheet(excelList.getSheetName());
		// 设置行的样式
		HSSFCellStyle rowStyle = this.getRowStyle();
		// **画图的顶级管理器***
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		Integer index = 0;
		// 设置标题和标题栏的样式
		HSSFRow row;

		this.setHeader(sheet, excelList.getFileName(), excelDetailList, index);
		index++;
		// 遍历集合数据，产生数据行
		Iterator<?> it = data.iterator();
		HSSFCellStyle temp = this.workbook.createCellStyle();
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object obj = (Object) it.next();
			for (int i = 0; i < excelDetailList.size(); i++) {
				HSSFCell cell = row.createCell(i);
				ExcelDetail excelDetail = excelDetailList.get(i);
				String fieldName = excelDetail.getTableColumn();
				Object value = this.getProperty(obj, fieldName);
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				/** 默认为字符串 */
				CellDataType dataType = CellDataType.STRING;

				if (value instanceof Boolean) {
					textValue = this.getCellValueForBoolean(value, fieldName, null);
					dataType = CellDataType.BOOLEAN;
				} else if (value instanceof Date) {
					textValue = this.getCallValueForDate(value, fieldName, null);
					dataType = CellDataType.DATE;
				} else if (value instanceof byte[]) {
					dataType = CellDataType.BYTES;
					this.setCellForImage(sheet, patriarch, i, index, row, cell, value, rowStyle);
				} else {
					// 其它数据类型都当作字符串简单处理
					textValue = String.valueOf(value == null ? StringUtils.EMPTY : value);
				}

				if (value instanceof String && org.apache.commons.lang.StringUtils.isNotEmpty(textValue)) {
					this.setCellForString(temp, cell, textValue, rowStyle, dataType, "center");
				} else if (org.apache.commons.lang.StringUtils.isNotEmpty(textValue)
						&& NumberUtils.isNumeric(textValue)) {
					dataType = CellDataType.NUMBER;
					this.setCellForNumber(temp, cell, textValue, rowStyle, dataType, "center");
				} else {
					textValue = (org.apache.commons.lang.StringUtils.isEmpty(textValue)
							? org.apache.commons.lang.StringUtils.EMPTY : textValue);
					this.setCellForString(temp, cell, textValue, rowStyle, dataType, "center");
				}
			}
		}
		try {

			httpServletResponse.reset();
			httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition",
					"attachment;filename=" + new String((excelList.getFileName() + ".xls").getBytes(), "utf-8"));
			OutputStream outputStream = httpServletResponse.getOutputStream();
			this.workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ExcelException(ErrorCode.EXCEL_EXPORT_WRITE.code(), e.getMessage(), e);
		}
	}

	@Override
	public void lionExportData(List<?> data, String fileName, String businessCode, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {
		// 校验数据完整性
		ExcelList excelList = excelListService.findBy(businessCode, ExcelList.TYPE_EXPORT);
		Assert.notNull(excelList, "【校验不通过】原因:未找到本模块的导入配置信息");
		// 根据业务模块代码 获取对应excel到model的配置信息
		List<ExcelDetail> excelDetailList = excelDetailService.findBy(excelList.getId());
		if (excelDetailList != null && excelDetailList.size() == 0) {
			logger.error("【校验不通过】原因 未找到表头映射配置,模块代码{},描述{},ID{}",
					new Object[] { excelList.getBusinessCode(), excelList.getBusinessDesc(), excelList.getId() });
			throw new BusinessException("【校验不通过】原因:未找到本模块的导入excel表头映射配置信息");
		}

		// 声明一个工作薄
		this.createWorkBook();
		// 生成一个表格
		HSSFSheet sheet = this.workbook.createSheet(excelList.getSheetName());
		// 设置行的样式
		HSSFCellStyle rowStyle = this.getRowStyle();
		// **画图的顶级管理器***
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		Integer index = 0;
		// 设置标题和标题栏的样式
		HSSFRow row;

		this.setHeader(sheet, fileName, excelDetailList, index);
		index++;
		// 遍历集合数据，产生数据行
		Iterator<?> it = data.iterator();
		HSSFCellStyle temp = this.workbook.createCellStyle();
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object obj = (Object) it.next();
			for (int i = 0; i < excelDetailList.size(); i++) {
				HSSFCell cell = row.createCell(i);
				ExcelDetail excelDetail = excelDetailList.get(i);
				String fieldName = excelDetail.getTableColumn();
				Object value = this.getProperty(obj, fieldName);
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				/** 默认为字符串 */
				CellDataType dataType = CellDataType.STRING;

				if (value instanceof Boolean) {
					textValue = this.getCellValueForBoolean(value, fieldName, null);
					dataType = CellDataType.BOOLEAN;
				} else if (value instanceof Date) {
					textValue = this.getCallValueForDate(value, fieldName, null);
					dataType = CellDataType.DATE;
				} else if (value instanceof byte[]) {
					dataType = CellDataType.BYTES;
					this.setCellForImage(sheet, patriarch, i, index, row, cell, value, rowStyle);
				} else {
					// 其它数据类型都当作字符串简单处理
					textValue = String.valueOf(value == null ? org.apache.commons.lang.StringUtils.EMPTY : value);
				}

				if (value instanceof String && org.apache.commons.lang.StringUtils.isNotEmpty(textValue)) {
					this.setCellForString(temp, cell, textValue, rowStyle, dataType, "center");
				} else if (org.apache.commons.lang.StringUtils.isNotEmpty(textValue)
						&& NumberUtils.isNumeric(textValue)) {
					dataType = CellDataType.NUMBER;
					this.setCellForNumber(temp, cell, textValue, rowStyle, dataType, "center");
				} else {
					textValue = (org.apache.commons.lang.StringUtils.isEmpty(textValue)
							? org.apache.commons.lang.StringUtils.EMPTY : textValue);
					this.setCellForString(temp, cell, textValue, rowStyle, dataType, "center");
				}
			}
		}
		try {

			httpServletResponse.reset();
			httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			OutputStream outputStream = httpServletResponse.getOutputStream();
			this.workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ExcelException(ErrorCode.EXCEL_EXPORT_WRITE.code(), e.getMessage(), e);
		}
	}

	/**
	 * 导出excel文件,直接返回给浏览器(lion)
	 *
	 * @param map
	 * @param fileName
	 *            文件名称
	 * @param request
	 * @param httpServletResponse
	 */
	@Override
	public void lionExportDataByFile(Map<String, List<?>> map, String fileName, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		List<ExcelList> list = this.excelListService.findByFile(fileName, ExcelList.TYPE_EXPORT);

		// 声明一个工作薄
		this.createWorkBook();
		for (ExcelList excelList : list) {
			// 根据业务模块代码 获取对应excel到model的配置信息
			List<ExcelDetail> excelDetailList = excelDetailService.findBy(excelList.getId());
			// 生成一个表格
			HSSFSheet sheet = this.workbook.createSheet(excelList.getSheetName());
			// 设置行的样式
			HSSFCellStyle rowStyle = this.getRowStyle();
			// **画图的顶级管理器***
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			Integer index = 0;
			// 设置标题和标题栏的样式
			HSSFRow row;

			this.setHeader(sheet, excelList.getSheetName(), excelDetailList, index);
			index++;
			// 遍历集合数据，产生数据行
			List<?> data = map.get(excelList.getBusinessCode());
			if (!(data != null && !data.isEmpty())) {
				continue;
			}
			Iterator<?> it = data.iterator();
			if (this.cellStyle == null) {
				cellStyle = this.workbook.createCellStyle();
			}
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Object obj = (Object) it.next();
				if (obj == null) {
					continue;
				}
				for (int i = 0; i < excelDetailList.size(); i++) {
					HSSFCell cell = row.createCell(i);
					ExcelDetail excelDetail = excelDetailList.get(i);
					String fieldName = excelDetail.getTableColumn();
					Object value = this.getProperty(obj, fieldName);
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					/** 默认为字符串 */
					CellDataType dataType = CellDataType.STRING;

					if (value instanceof Boolean) {
						textValue = this.getCellValueForBoolean(value, fieldName, null);
						dataType = CellDataType.BOOLEAN;
					} else if (value instanceof Date) {
						textValue = this.getCallValueForDate(value, fieldName, null);
						dataType = CellDataType.DATE;
					} else if (value instanceof byte[]) {
						dataType = CellDataType.BYTES;
						this.setCellForImage(sheet, patriarch, i, index, row, cell, value, rowStyle);
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = String.valueOf(value == null ? org.apache.commons.lang.StringUtils.EMPTY : value);
					}

					if (value instanceof String && org.apache.commons.lang.StringUtils.isNotEmpty(textValue)) {
						this.setCellForString(cellStyle, cell, textValue, rowStyle, dataType, "center");
					} else if (org.apache.commons.lang.StringUtils.isNotEmpty(textValue)
							&& NumberUtils.isNumeric(textValue)) {
						dataType = CellDataType.NUMBER;
						this.setCellForNumber(cellStyle, cell, textValue, rowStyle, dataType, "center");
					} else {
						textValue = (org.apache.commons.lang.StringUtils.isEmpty(textValue)
								? org.apache.commons.lang.StringUtils.EMPTY : textValue);
						this.setCellForString(cellStyle, cell, textValue, rowStyle, dataType, "center");
					}
				}
			}
		}
		try {
			httpServletResponse.reset();
			httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			OutputStream outputStream = httpServletResponse.getOutputStream();
			this.workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ExcelException(ErrorCode.EXCEL_EXPORT_WRITE.code(), e.getMessage(), e);
		}
	}

	/****
	 * 设置标题栏
	 *
	 * @param title
	 * @param excelDetailList
	 */
	public void setHeader(HSSFSheet sheet, String title, List<ExcelDetail> excelDetailList, Integer rowIndex) {

		// 设置标题栏样式
		HSSFCellStyle headerStyle = this.getHeaderStyle();
		// 产生表格标题栏
		Row rowTitle = sheet.createRow(rowIndex++);
		HSSFRow row = sheet.createRow(rowIndex++);
		for (int i = 0; i < excelDetailList.size(); i++) {
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
			HSSFRichTextString text = new HSSFRichTextString(excelDetailList.get(i).getExcelCell());
			cell.setCellValue(text);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (excelDetailList.size() - 1)));
	}

	/***
	 *
	 * @param cell
	 *            单元格
	 * @param str
	 *            数据
	 * @param cellStyle
	 *            单元格样式
	 * @param dataType
	 *            数据类型
	 * @param align
	 *            对齐方式
	 */
	public void setCellForString(HSSFCellStyle temp, HSSFCell cell, String str, HSSFCellStyle cellStyle,
			CellDataType dataType, String align) {
		HSSFRichTextString richString = new HSSFRichTextString(str);
		cell.setCellValue(richString);
		cell.setCellStyle(cellStyle);
		temp.cloneStyleFrom(cell.getCellStyle());
		temp.setAlignment(this.getCellForAlign(dataType, align));
		cell.setCellStyle(temp);

	}

	/***
	 * 处理字格式
	 * 
	 * @param cell
	 *            单元格
	 * @param str
	 *            数据
	 * @param cellStyle
	 *            单元格样式
	 * @param dataType
	 *            数据类型
	 * @param align
	 *            对齐方式
	 */
	public void setCellForNumber(HSSFCellStyle temp, HSSFCell cell, String str, HSSFCellStyle cellStyle,
			CellDataType dataType, String align) {
		// 是数字当作double处理
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Double.parseDouble(str));
		if (NumberUtils.isDecimal(str)) {
			cellStyle = this.getCallDataStyle(this.decimalFmt);
		}
		cell.setCellStyle(cellStyle);
		temp.cloneStyleFrom(cell.getCellStyle());
		temp.setAlignment(this.getCellForAlign(dataType, align));
		cell.setCellStyle(temp);
	}

	/***
	 * Excel单元格对齐方式处理 ,如DataGrid中对齐方式与定义不匹配的情况的，使用Cell单元格默认对齐方式
	 * 
	 * @param dataType
	 *            单元格数据类型
	 * @param align
	 *            HTML单元格对齐方式;
	 * @return Excel中单元格对齐方式
	 */
	public short getCellForAlign(CellDataType dataType, String align) {

		if (CellAlign.CENTER.code().equalsIgnoreCase(align)) {
			return CellAlign.CENTER.cellCode();
		} else if (CellAlign.LEFT.code().equalsIgnoreCase(align)) {
			return CellAlign.LEFT.cellCode();
		} else if (CellAlign.RIGHT.code().equalsIgnoreCase(align)) {
			return CellAlign.RIGHT.cellCode();
		} else {
			return this.cellDefaultAlign(dataType);
		}
	}

	/***
	 * 根据数据类型设置默认对齐方式 数据默认右对齐，字符串左对齐、布尔和日期及字节数组居中对齐
	 * 
	 * @param dataType
	 * @return
	 */
	public short cellDefaultAlign(CellDataType dataType) {
		short cellAlign = HSSFCellStyle.ALIGN_LEFT;
		switch (dataType.code()) {
		case 0:
			cellAlign = HSSFCellStyle.ALIGN_LEFT;
			break;
		case 1:
			cellAlign = HSSFCellStyle.ALIGN_RIGHT;
			break;
		case 2:
		case 3:
		case 4:
			cellAlign = HSSFCellStyle.ALIGN_CENTER;
			break;
		}
		return cellAlign;
	}

	/***
	 *
	 * @param value
	 *            单元格的值
	 * @param fieldName
	 *            字段名称
	 * @param dataFormats
	 *            格式化
	 * @return
	 */
	public String getCallValueForDate(Object value, String fieldName, Map<String, String> dataFormats) {
		Date date = (Date) value;
		if (!CollectionUtils.isEmpty(dataFormats) && dataFormats.containsKey(fieldName)) {
			String pattern = dataFormats.get(fieldName);
			if (StringUtils.isNotEmpty(pattern)) {
				return DateUtil.formatDate(date, pattern);
			}
		}
		return DateUtil.formatDate(date, this.dataPattern);
	}

	/***
	 * 获取数据样式
	 * 
	 * @param value
	 *            Object
	 * @param codeTypes
	 *            Map类型转换
	 * @return
	 */
	public String getCellValueForBoolean(Object value, String fieldName, Map<String, Map<Object, Object>> codeTypes) {
		Boolean tempVal = (Boolean) value;
		if (!CollectionUtils.isEmpty(codeTypes) && codeTypes.containsKey(fieldName)) {
			Map<Object, Object> types = codeTypes.get(fieldName);
			if (!CollectionUtils.isEmpty(types)) {
				return (String) (types.containsKey(tempVal) ? types.get(tempVal) : StringUtils.EMPTY);
			}
		}
		return (String) (DEFAULT_BOOLEAN_MAP.containsKey(tempVal) ? DEFAULT_BOOLEAN_MAP.get(tempVal)
				: StringUtils.EMPTY);
	}

	/***
	 * 根据byte数据组设置单元格的值的及样式
	 * 
	 * @param sheet
	 *            工作样
	 * @param patriarch
	 *            画图的顶级管理器
	 * @param colIndex
	 *            列索引
	 * @param rowIndex
	 *            行索引
	 * @param row
	 *            行
	 * @param cell
	 *            单元格
	 * @param value
	 *            单元格的值
	 * @param cellStyle
	 *            单元格样式
	 */
	public void setCellForImage(HSSFSheet sheet, HSSFPatriarch patriarch, Integer colIndex, Integer rowIndex,
			HSSFRow row, HSSFCell cell, Object value, HSSFCellStyle cellStyle) {
		// 有图片时，设置行高为60px;
		row.setHeightInPoints(60);
		// 设置图片所在列宽度为80px,注意这里单位的一个换算
		sheet.setColumnWidth(colIndex, (short) (35.7 * 80));
		byte[] bsValue = (byte[]) value;
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, rowIndex, (short) 6, rowIndex);
		anchor.setAnchorType(2);
		patriarch.createPicture(anchor, this.workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
		cell.setCellStyle(cellStyle);
	}

	/***
	 * 数字类型：根据字符串设置单元格的值和样式；
	 * 
	 * @param cell
	 *            单元格
	 * @param str
	 *            单元格值
	 * @param cellStyle
	 *            单元格样式
	 */
	public void setCellForNumber(HSSFCell cell, String str, HSSFCellStyle cellStyle) {
		// 是数字当作double处理
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Double.parseDouble(str));
		if (NumberUtil.isDecimal(str)) {
			cell.setCellStyle(this.getCallDataStyle(this.decimalFmt));
		} else {
			cell.setCellStyle(cellStyle);
		}
	}

	/***
	 * 字符类型：根据字符串设置单元格的值和样式。
	 * 
	 * @param cell
	 *            单元格
	 * @param str
	 *            单元格值
	 * @param cellStyle
	 *            单元格样式
	 */
	public void setCellForString(HSSFCell cell, String str, HSSFCellStyle cellStyle) {
		HSSFRichTextString richString = new HSSFRichTextString(str);
		cell.setCellValue(richString);
		cell.setCellStyle(cellStyle);
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
	public HSSFCellStyle getRowStyle() {
		// 生成并设置另一个样式
		HSSFCellStyle styleRow = this.workbook.createCellStyle();
		styleRow.setFillForegroundColor(HSSFColor.WHITE.index);
		styleRow.setFillBackgroundColor(HSSFColor.WHITE.index);
		styleRow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// styleRow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleRow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		// 把字体应用到当前的样式
		styleRow.setFont(this.getFont(this.fontName, this.fontSize));
		return styleRow;
	}

	/****
	 * 根据格式设置单元格格式
	 * 
	 * @param pattern
	 *            格式;
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle getCallDataStyle(String pattern) {
		HSSFDataFormat df = this.workbook.createDataFormat();
		HSSFCellStyle cellStyle = this.getRowStyle();
		cellStyle.setDataFormat(df.getFormat(pattern));
		return cellStyle;
	}

	/***
	 * 创建工作薄
	 */
	public void createWorkBook() {
		this.workbook = new HSSFWorkbook();
	}

	/***
	 * 根据对象获取字段的值，获取Value
	 * 
	 * @param obj
	 *            对象
	 * @return Object的Value
	 */
	public Object getProperty(Object obj, Field field) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(obj, field.getName());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		return value;
	}

	/***
	 * 根据对象获取字段的值，获取Value
	 * 
	 * @param obj
	 *            对象
	 * @return Object的Value
	 */
	public Object getProperty(Object obj, String fieldName) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(obj, fieldName);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		return value;
	}

	/**
	 * 设置标题栏的样式
	 *
	 * @return 标题栏的样式
	 */
	public HSSFCellStyle getHeaderStyle() {
		// 生成一个样式
		HSSFCellStyle style = this.workbook.createCellStyle();
		HSSFPalette customPalette = this.workbook.getCustomPalette();
		customPalette.setColorAtIndex(HSSFColor.YELLOW.index, (byte) 216, (byte) 216, (byte) 216);
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
		HSSFFont font = this.getFont(this.fontName, this.fontSize);
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
	public HSSFFont getFont(String fontName, Short fontSize) {
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
	public void setHeader(HSSFSheet sheet, String title, String[] headers, Integer rowIndex) {

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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (headers.length - 1)));
	}

	/***
	 * 表格标题样式
	 * 
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
		HSSFFont font = this.getFont(this.fontName, this.titleFontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		titleStyle.setFont(font);
		return titleStyle;
	}

	@Override
	public <T> List<T> importData(InputStream stream, Class<T> clazz) throws Exception {
		Workbook wb = StreamingReader.builder().rowCacheSize(1000).bufferSize(10000).open(stream);
		Map<String, SimpleDateFormat> dateFormatMap = Maps.newHashMap();
		List<T> result = Lists.newArrayList();
		Sheet sheet = wb.getSheetAt(0);
		Long count = 0L;
		boolean skipTitle = false;
		for (Row row : sheet) {
			if (!skipTitle) {
				skipTitle = true;
				continue;
			}
			if (row == null) {
				continue;
			}
			if (row.getCell(0) == null) {
				continue;
			}
			T object = clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				ExcelField fieldAnnotation = field.getAnnotation(ExcelField.class);
				if (fieldAnnotation == null) {
					continue;
				}
				Cell cell = row.getCell(fieldAnnotation.col());
				if (cell == null) {
					continue;
				}
				String string = cell.getStringCellValue();
				if (fieldAnnotation.colType() == ColType.STRING) {
					field.setAccessible(true);
					field.set(object, string);
					// json.put(field.getName(), string);
				} else if (fieldAnnotation.colType() == ColType.DATE
						&& org.apache.commons.lang3.StringUtils.isNotEmpty(fieldAnnotation.dateFormat())
						&& org.apache.commons.lang3.StringUtils.isNotEmpty(string)) {
					SimpleDateFormat sdf = null;
					if (dateFormatMap.containsKey(fieldAnnotation.dateFormat())) {
						sdf = dateFormatMap.get(fieldAnnotation.dateFormat());
					} else {
						dateFormatMap.put(fieldAnnotation.dateFormat(),
								new SimpleDateFormat(fieldAnnotation.dateFormat(), Locale.ENGLISH));
						sdf = dateFormatMap.get(fieldAnnotation.dateFormat());
					}
					Date date = sdf.parse(string);
					field.setAccessible(true);
					field.set(object, date);
				}
			}
			result.add(object);
			count++;
		}
		return result;
	}

	public void exportData(List<Object[]> listData, String fileName, String[] columnNames,
			HttpServletResponse httpServletResponse) {
		try {
			httpServletResponse.reset();
			httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
			@Cleanup
			OutputStream outputStream = httpServletResponse.getOutputStream();
			ExcelUtil.createWorkBook(listData, fileName, columnNames).write(outputStream);
			outputStream.flush();
		} catch (IOException ex) {
			logger.error("Excel导出失败! message:{}", ex);
			Assert.isTrue(true, "Excel导出失败!");
		}
	}

}