/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * PermissionRepository.java 15/11/2 下午2:03
 */
package com.ford.mediadata.mgt.repository.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasPermission;

/**
 * Created by ziv.hung on 15/11/2.
 * 用户、角色、资源 权限仓储
 *
 * @since 1.0
 */
public interface PermissionRepository extends JpaRepository<BasPermission, Long> {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param roleId 角色ID 允许为空
     * @param page   分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasPermission> findByGridPage(Long roleId, GridPage page);

    /**
     * 根据租户 用户ID 角色ID获取权限列表
     *
     * @param roleId     角色ID  允许为空
     * @param resourceId 资源ID  允许为空
     * @return
     */
    List<BasPermission> findBy(Long roleId, Long resourceId);


    /**
     * 根据角色代码获取角色授权的资源 权限控制使用
     *
     * @param roleCode 角色代码
     * @param isMenu   是否菜单
     * @return
     */

    List findByRoleCode(String roleCode, String isMenu, Class clazz);

    /**
     * 获取指定角色的所有权限permission
     *
     * @param roleCode 角色代码  允许为空
     * @return
     */
    List<BasPermission> findBy(String roleCode);

    List findBy(Long roleId, Long resourceId, Class clazz);

    void deleteList(List<BasPermission> list);

    void saveList(List<BasPermission> list);
}