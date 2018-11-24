/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * ImCodeList.java 15/10/13 下午4:40
 */
package com.ford.mediadata.mgt.web.controller.im;

import com.ford.mediadata.mgt.entity.im.ImCodeList;
import com.ford.mediadata.mgt.service.im.ImCodeListService;
import com.ford.mediadata.mgt.web.controller.TransCode;
import com.ford.mediadata.mgt.web.controller.im.vo.*;

import io.dabing.common.date.DateUtil;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;
import io.dabing.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述：字典项业务层
 *
 * @author ziv.hung
 * @since 1.0
 */
@Controller
public class ImCodeListController {

    @Autowired
    private ImCodeListService service;

    @ApiService(transCode = TransCode.T_1020)
    public Page<T1020Resp> findPage(T1020Req req) {
        Page<ImCodeList> pages = service.findByGridPage(req.getTypeCode(), req.getPage());
        Page<T1020Resp> resp = pages.map(new Converter<ImCodeList, T1020Resp>() {
            public T1020Resp convert(ImCodeList source) {
                T1020Resp a = new T1020Resp();
                BeanUtils.copyProperties(source, a);

                a.setFirstInsert(DateUtil.formatDate(source.getCreatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
                return a;
            }
        });
        return resp;
    }

    @ApiService(transCode = TransCode.T_1026)
    public List<T1026Resp> findPage(T1026Req req) {
        List<ImCodeList> imCodeLists = service.findByTypeCode(req.getTypeCode());
        List<T1026Resp> resp = new ArrayList<T1026Resp>();
        for (ImCodeList imCodeList : imCodeLists) {
            T1026Resp t1026Resp = new T1026Resp();
            BeanUtils.copyProperties(imCodeList, t1026Resp);
            resp.add(t1026Resp);
        }
        return resp;
    }

    @ApiService(transCode = TransCode.T_1021)
    public void create(T1021Req req) {
        ImCodeList ImCodeList = new ImCodeList();
        BeanUtils.copyProperties(req, ImCodeList);
        this.service.save(ImCodeList);
    }

    @ApiService(transCode = "imCodeListModify")
    public void imCodeListModify(T1021Req req) {
        ImCodeList ImCodeList = service.get(req.getId());
        BeanUtils.copyProperties(req, ImCodeList);
        ImCodeList.setUpdatedDate(new Date());
        this.service.update(ImCodeList);
    }

    @ApiService(transCode = TransCode.T_1022)
    public T1022Resp get(T1022Req req) {
        ImCodeList ImCodeList = this.service.get(req.getId());
        if (ImCodeList == null) {
            throw new BusinessException("对象不存在!");
        }
        T1022Resp resp = new T1022Resp();
        BeanUtils.copyProperties(ImCodeList, resp);
        return resp;
    }

    @ApiService(transCode = TransCode.T_1023)
    public void delete(T1023Req req) {
        for (T1023ReqVo reqVo : req.getReqs()) {
            this.service.delete(reqVo.getId());
        }
    }
}