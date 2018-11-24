/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * Role.java 15/10/30 下午4:41
 */
package com.ford.mediadata.mgt.service.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasRole;

/**
 * Created by yangkui on 15/10/30.
 * 角色
 *
 * @since 1.0
 */
public interface RoleService extends Service {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasRole> findByGridPage(GridPage page);

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param keyWord 关键字进行模糊匹配
     * @return
     */
    List<BasRole> findForExport(String keyWord);

    /**
     * 是否存在角色代码
     * @param code 角色代码
     * @param id 角色ID
     * @return true 存在, false 不存在
     */
    boolean existCode(String code, Long id);

    void save(BasRole object);

    void delete(String roleCode);

    void update(BasRole object);

    BasRole get(Long id);

    /***
     * 根据角色代码获取角色ID
     * @param code
     * @return  Role
     */
    BasRole getByCode(String code);
}