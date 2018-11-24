package com.ford.mediadata.mgt.web.controller.security;

import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.service.excel.ExcelService;
import com.ford.mediadata.mgt.service.security.OperationLogService;
import com.ford.mediadata.mgt.web.controller.security.vo.OperationLogPageReq;
import com.ford.mediadata.mgt.web.controller.security.vo.OperationLogPageResp;

import io.dabing.common.date.DateUtil;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by wanglijun on 2016-12-03.
 * 描述:TODO
 *
 * @since 1.0
 */
@Controller
public class OperationLogController {

    @Autowired
    OperationLogService service;


    @Autowired
    ExcelService excelService;




    /**
     * 按关键字分页查询对象 调用OperationLogPage
     *
     * @param req 请求入参对象(包含了keyWord关键字)
     * @return
     */
    @ApiService(transCode = "operationLogPage")
    public Page<OperationLogPageResp> OperationLogPage(OperationLogPageReq req) {
        Page<OperationLog> pages = service.pagingBy (req.getPage (),req.getUserName (),req.getOperationType (),req.getStartDate (),req.getEndDateCover ());
        Page<OperationLogPageResp> respS = pages.map (new Converter<OperationLog, OperationLogPageResp> () {
            public OperationLogPageResp convert(OperationLog source) {
                OperationLogPageResp resp = new OperationLogPageResp ();
                if (source != null) {
                    BeanUtils.copyProperties (source, resp);
                    resp.setCreatedDate (DateUtil.formatDate (source.getCreatedDate (), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
                    resp.setOperationTypeName (source.getOperationType ().getName ());
                }
                return resp;
            }
        });
        return respS;
    }

    /**
     * 操作日志信息导出
     */
    @RequestMapping(value = "/logExport.do")
    public void logExport(HttpServletRequest request, HttpServletResponse response) {
        excelService.exportData(ExcelList.B_CODE_LOG, request, response);
    }
}