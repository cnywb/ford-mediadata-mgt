/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * Role.java 15/10/30 下午4:39
 */
package com.ford.mediadata.mgt.repository.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasRole;


/**
 * Created by yangkui on 15/10/30.
 * 管理员角色
 *
 * @since 1.0
 */
public interface RoleRepository extends JpaRepository<BasRole, Long> {

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasRole> findByGridPage(GridPage page);

    List<BasRole> findForExport(String keyWord);

    List<BasRole> findBy(String code, Long id);

    /***
     * 根据角色代码查询
     * @param code 角色代码
     * @return Role
     */
    BasRole findByCode(String code);
}
