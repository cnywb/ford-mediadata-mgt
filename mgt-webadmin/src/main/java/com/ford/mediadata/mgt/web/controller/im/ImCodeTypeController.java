/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * ImCodeType.java 15/10/13 下午4:40
 */
package com.ford.mediadata.mgt.web.controller.im;

import com.ford.mediadata.mgt.entity.im.ImCodeType;
import com.ford.mediadata.mgt.service.im.ImCodeTypeService;
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
 * 描述：字典大类项业务层
 *
 * @author ziv.hung
 * @since 1.0
 */
@Controller
public class ImCodeTypeController {

    @Autowired
    private ImCodeTypeService service;

    @ApiService(transCode = TransCode.T_1010)
    public Page<T1010Resp> findPage(T1010Req req) {
        Page<ImCodeType> pages = service.findByGridPage(req.getPage());
        Page<T1010Resp> resp = pages.map(new Converter<ImCodeType, T1010Resp>() {
            public T1010Resp convert(ImCodeType source) {
                T1010Resp a = new T1010Resp();
                BeanUtils.copyProperties(source, a);
                a.setFirstInsert(DateUtil.formatDate(source.getCreatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
                return a;
            }
        });
        return resp;
    }

    @ApiService(transCode = TransCode.T_1016)
    public List<T1016Resp> findPage(T1016Req req) {
        List<ImCodeType> imCodeTypes = service.findAll(req.getType());
        List<T1016Resp> resp = new ArrayList<T1016Resp>();
        for (ImCodeType imCodeType : imCodeTypes) {
            T1016Resp t1016Resp = new T1016Resp();
            BeanUtils.copyProperties(imCodeType, t1016Resp);
            resp.add(t1016Resp);
        }
        return resp;
    }

    @ApiService(transCode = TransCode.T_1011)
    public void create(T1011Req req) {
        ImCodeType imCodeType = new ImCodeType();
        BeanUtils.copyProperties(req, imCodeType);
        imCodeType.setEnable(true);
        this.service.save(imCodeType);
    }

    @ApiService(transCode = "imCodeTypeModify")
    public void imCodeTypeModify(T1011Req req) {
        ImCodeType imCodeType = service.get(req.getId());
        BeanUtils.copyProperties(req, imCodeType);
        imCodeType.setUpdatedDate(new Date());
        this.service.update(imCodeType);
    }

    @ApiService(transCode = TransCode.T_1012)
    public T1012Resp get(T1012Req req) {
        ImCodeType imCodeType = this.service.get(req.getId());
        if (imCodeType == null) {
            throw new BusinessException("对象不存在!");
        }
        T1012Resp resp = new T1012Resp();
        BeanUtils.copyProperties(imCodeType, resp);
        return resp;
    }

    @ApiService(transCode = TransCode.T_1013)
    public void delete(T1013Req req) {
        for (T1013ReqVo reqVo : req.getReqs()) {
            this.service.delete(reqVo.getId());
        }
    }

    //根据类别代码查询所有大类
    @ApiService(transCode = TransCode.T_1002)
    public List<T1000Resp> findByTypeCode(T1002Req req) {
        List<ImCodeType> list = service.findAll(req.getTypeCode());
        List<T1000Resp> resps = new ArrayList<T1000Resp>();
        for (ImCodeType type : list) {
            T1000Resp a = new T1000Resp();
            BeanUtils.copyProperties(type, a);
            resps.add(a);
        }
        return resps;
    }

}