/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-10-31 04:39:39
 */
package com.ford.mediadata.mgt.service.security.impl;

import com.ford.mediadata.mgt.entity.security.BasResource;
import com.ford.mediadata.mgt.repository.security.ResourceRepository;
import com.ford.mediadata.mgt.service.security.ResourceService;

import io.dabing.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangkui on 2015-10-31 04:39:39.
 *
 * @since 1.0
 */
@Service
public class ResourceServiceImpl extends AbstractService implements ResourceService {
    @Autowired
    private ResourceRepository repository;

    /**
     * 根据资源类型查找资源
     *
     * @param type 资源类型，@see io.dabing.chuangdao.entity.security.Resource
     * @return
     */
    public List<BasResource> findBy(String type) {
        return repository.findBy(type);
    }

    public List findTreeByRoleCode(String roleCode, String isMenu, Class clazz) {
        return repository.findTreeByRoleCode(roleCode, isMenu, clazz);
    }

    public BasResource save(BasResource object) {
        repository.save(object);
        String code = this.generateResourceCode(object.getId());
        object.setCode(code);
        repository.update(object);
        return object;
    }

    /**
     * 逻辑删除 deleted true
     */
    public void deleteByLogic(List<BasResource> objectList) {
        for (BasResource object : objectList) {
            deleteByLogic(object.getId());
        }
    }

    /**
     * 逻辑删除 deleted true
     */
    public void deleteByLogic(BasResource object) {
        deleteByLogic(object.getId());
    }

    /**
     * 逻辑删除 deleted true
     */
    public void deleteByLogic(Long id) {
        BasResource objectDb = this.get(id);
        if (objectDb == null) {
            return;
        }
        objectDb.setDeleted(true);
        this.repository.update(objectDb);
    }

    /**
     * 物理删除
     */
    public void delete(BasResource object) {
        this.repository.delete(object);
    }

    /**
     * 物理删除
     */
    public void delete(Long id) {
        this.repository.delete(id);
    }

    public BasResource update(BasResource object) {
        repository.update(object);
        return object;
    }

    public BasResource get(Long id) {
        return repository.get(id);
    }

    /*
     * 订单号生成规则：RS + ID 补全8位
     *
     * @param id
     * @return
     */
    private String generateResourceCode(Long id) {
        String randomNoStr = String.format("%06d", id);
        String resourceCode = "RS" + randomNoStr;
        return resourceCode;
    }

}