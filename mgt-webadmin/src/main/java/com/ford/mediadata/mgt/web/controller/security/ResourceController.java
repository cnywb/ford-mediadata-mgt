/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 12:06:34
 */
package com.ford.mediadata.mgt.web.controller.security;

import com.ford.mediadata.mgt.entity.security.BasPermission;
import com.ford.mediadata.mgt.entity.security.BasResource;
import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.lisenter.LogUtil;
import com.ford.mediadata.mgt.service.security.PermissionService;
import com.ford.mediadata.mgt.service.security.ResourceService;
import com.ford.mediadata.mgt.web.controller.security.vo.*;

import io.dabing.core.web.annotation.ApiService;
import io.dabing.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangkui on 2015-11-01 12:06:34.
 *
 * @since 1.0
 */
@Controller
public class ResourceController {
    @Autowired
    private ResourceService service;

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据资源分类 获取资源数据信息
     *
     * @return
     */
    @ApiService(transCode = "resourceSelectList")
    public List<ResourceListResp> resourceSelectList(ResourceListReq req) {
        List<BasResource> ResourceList = service.findBy(BasResource.TYPE_BUSINESS);
        List<ResourceListResp> respList = new ArrayList<ResourceListResp>();
        for (BasResource resource : ResourceList) {
            ResourceListResp resp = new ResourceListResp();
            BeanUtils.copyProperties(resource, resp);
            respList.add(resp);
        }
        return respList;
    }

    /**
     * 根据资源分类 获取资源数据信息
     *
     * @return
     */
    @ApiService(transCode = "resourceList")
    public List<ResourceListResp> resourceList(ResourceListReq req) {
        List<BasResource> resourceList = service.findBy(null);
        List<ResourceListResp> respList = new ArrayList<ResourceListResp>();
        for (BasResource resource : resourceList) {
            ResourceListResp resp = new ResourceListResp();
            BeanUtils.copyProperties(resource, resp);
            respList.add(resp);
        }
        return respList;
    }

    //单一创建对象
    @ApiService(transCode = "resourceHandle")
    public void resourceHandle(ResourceHandleReq req) {
        BasResource object = new BasResource();
        BeanUtils.copyProperties(req, object);
        this.service.save(object);
        LogUtil.writer (OperationType.RESOURCE_CREATE,req.getName (),object.getCode ());
}

    //单一修改对象
    @ApiService(transCode = "resourceModify")
    public void resourceModify(ResourceHandleReq req) {
        BasResource object = service.get(req.getId());
        String oldCode=object.getCode ();
        BeanUtils.copyProperties(req, object, new String[]{"id", "code"});
        this.service.update(object);
        LogUtil.writer (OperationType.RESOURCE_MODIFY,req.getName (),oldCode,object.getName (),object.getCode ());
    }

    //根据ID获取对象
    @ApiService(transCode = "resourceGet")
    public ResourceGetResp resourceGet(ResourceGetReq req) {
        BasResource object = this.service.get(req.getId());
        if (object == null) {
            throw new BusinessException("对象不存在!");
        }
        ResourceGetResp resp = new ResourceGetResp();
        BeanUtils.copyProperties(object, resp);
        return resp;
    }

    //批量删除对象
    @ApiService(transCode = "resourceDelete")
    public void resourceDelete(ResourceDeleteReq req) {
        for (Long id : req.getIds()) {
            List<BasPermission> permissionList = permissionService.findBy(null, id);
            permissionService.delete(permissionList);
            this.service.delete(id);
        }
    }
}