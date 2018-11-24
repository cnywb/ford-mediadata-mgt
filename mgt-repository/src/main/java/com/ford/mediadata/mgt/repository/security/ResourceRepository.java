/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-10-31 04:39:39
 */
package com.ford.mediadata.mgt.repository.security;

import io.dabing.core.repository.JpaRepository;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasResource;

/**
 * Created by yangkui on 2015-10-31 04:39:39.
 *
 * @since 1.0
 */
public interface ResourceRepository extends JpaRepository<BasResource, Long> {

    List<BasResource> findBy(String type);

    /**
     * 根据角色代码获取角色授权的资源(角色授权中树形结构数据)
     *
     * @param roleCode 角色代码
     * @return
     */

    List findTreeByRoleCode(String roleCode, String isMenu, Class clazz);
}