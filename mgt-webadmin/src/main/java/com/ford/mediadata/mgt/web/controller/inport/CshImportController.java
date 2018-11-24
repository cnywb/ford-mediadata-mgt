package com.ford.mediadata.mgt.web.controller.inport;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.service.excel.ExcelService;
import com.ford.mediadata.mgt.service.order.BatchSaveService;
import com.ford.mediadata.mgt.service.order.CshCallService;
import com.ford.mediadata.mgt.service.order.CshOrderService;
import com.ford.mediadata.mgt.service.order.SequenceService;
import com.ford.mediadata.mgt.web.controller.inport.vo.CshCallImportVO;
import com.ford.mediadata.mgt.web.controller.inport.vo.CshOrderImportVO;
import com.ford.mediadata.mgt.web.controller.inport.vo.FileUploadResponse;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/import/csh")
public class CshImportController {

	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	@Resource
	private ExcelService excelService;

	@Resource
	private CshOrderService cshOrderService;

	@Resource
	private CshCallService cshCallService;

	@Resource
	private SequenceService sequenceService;

	/**
	 * 车商汇订单导入
	 */
	@ResponseBody
	@RequestMapping(value = "/order")
	public FileUploadResponse commonOrderInport(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				throw new IllegalArgumentException("没有权限");
			}
			final String sn = "" + System.currentTimeMillis();
			final Date uploadTime = new Date();
			log.info("[{}]开始导入订单", sn);
			@Cleanup
			InputStream stream = getFileInputStream(request);
			List<CshOrderImportVO> result = excelService.importData(stream, CshOrderImportVO.class);
			IOUtils.closeQuietly(stream);
			log.info("[{}]总记录数:{}", sn, result.size());
			List<List<CshOrderImportVO>> batchList = splitList(result, 10000);
			int i = 0;
			for (List<CshOrderImportVO> batch : batchList) {
				i++;
				final String threadSN = sn + "_" + i;
				BatchSaveThread<CshOrderImportVO, CshOrderEntity> thread = new BatchSaveThread<CshOrderImportVO, CshOrderEntity>(
						threadSN, cshOrderService, batch, new Converter<CshOrderImportVO, CshOrderEntity>() {
							@Override
							public CshOrderEntity convert(CshOrderImportVO source) {
								CshOrderEntity target = new CshOrderEntity();
								BeanUtils.copyProperties(source, target);
								target.setUploadTime(uploadTime);
								return target;
							}
						}, 1000);
				log.info("[{}]创建导入线程", threadSN);
				threadPool.execute(thread);
			}
			return new FileUploadResponse(Boolean.TRUE, result.size(), "上传成功，数据正在后台处理中，请勿重复上传!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new FileUploadResponse(Boolean.FALSE, 0, "上传失败，请检查EXCEL数据格式是否正确！错误信息：" + e.getMessage());
		}
	}

	/**
	 * 车商汇话单导入
	 */
	@ResponseBody
	@RequestMapping(value = "/call")
	public FileUploadResponse callOrderInport(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!SecurityUtils.getSubject().isAuthenticated()) {
				throw new IllegalArgumentException("没有权限");
			}
			final String sn = "" + System.currentTimeMillis();
			final Date uploadTime = new Date();
			log.info("[{}]开始导入话单", sn);
			@Cleanup
			InputStream stream = getFileInputStream(request);
			List<CshCallImportVO> result = excelService.importData(stream, CshCallImportVO.class);
			IOUtils.closeQuietly(stream);
			log.info("[{}]总记录数:{}", sn, result.size());
			List<List<CshCallImportVO>> batchList = splitList(result, 10000);
			int i = 0;
			for (List<CshCallImportVO> batch : batchList) {
				i++;
				final String threadSN = sn + "_" + i;
				BatchSaveThread<CshCallImportVO, CshCallEntity> thread = new BatchSaveThread<CshCallImportVO, CshCallEntity>(
						threadSN, cshCallService, batch, new Converter<CshCallImportVO, CshCallEntity>() {
							@Override
							public CshCallEntity convert(CshCallImportVO source) {
								CshCallEntity target = new CshCallEntity();
								BeanUtils.copyProperties(source, target);
								target.setUploadTime(uploadTime);
								return target;
							}
						}, 1000);
				log.info("[{}]创建导入线程", threadSN);
				threadPool.execute(thread);
			}
			return new FileUploadResponse(Boolean.TRUE, result.size(), "上传成功，数据正在后台处理中，请勿重复上传!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new FileUploadResponse(Boolean.FALSE, 0, "上传失败，请检查EXCEL数据格式是否正确！错误信息：" + e.getMessage());
		}
	}

	private InputStream getFileInputStream(HttpServletRequest request) throws Exception {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		FileItem fileItem = items.get(0);
		return fileItem.getInputStream();
	}

	@Getter
	@AllArgsConstructor
	private class BatchSaveThread<S, T> implements Runnable {

		private String sn;

		private BatchSaveService<T> service;

		private List<S> list;

		private Converter<S, T> converter;

		private Integer maxCountPerTrans;

		@Override
		public void run() {
			try {
				if (CollectionUtils.isEmpty(list)) {
					return;
				}
				List<T> entityList = Lists.newArrayList();
				for (S s : list) {
					T t = converter.convert(s);
					entityList.add(t);
				}
				List<List<T>> splitEntityList = splitList(entityList, maxCountPerTrans);
				for (List<T> splitEntityBatch : splitEntityList) {
					service.batchSave(splitEntityBatch);
				}
				log.info("[{}]导入批次保存成功，总记录数:{}", sn, list.size());
			} catch (Exception e) {
				log.error("[{}]导入批次异常:{}", e.getMessage(), e);
			}
		}

	}

	private <T> List<List<T>> splitList(List<T> sourceList, Integer maxCountPerList) {
		List<List<T>> resultList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(sourceList)) {
			return resultList;
		}
		List<T> tempList = Lists.newArrayList();
		for (T t : sourceList) {
			if (tempList.size() >= maxCountPerList) {
				resultList.add(tempList);
				tempList = Lists.newArrayList();
			}
			tempList.add(t);
		}
		if (CollectionUtils.isNotEmpty(tempList)) {
			resultList.add(tempList);
		}
		return resultList;
	}

}
