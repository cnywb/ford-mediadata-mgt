/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * PermissionService.java 15/11/2 下午2:10
 */
package com.ford.mediadata.mgt.service.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasPermission;

/**
 * Created by ziv.hung on 15/11/2.
 * 权限 服务
 *
 * @since 1.0
 */
public interface PermissionService extends Service {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasPermission> findByGridPage(Long roleId, GridPage page);

    /**
     * 根据租户 用户ID 角色ID获取权限列表
     *
     * @param roleId 角色ID  允许为空
     * @return
     */
    List<BasPermission> findBy(Long roleId, Long resourceId);

    /**
     * 获取指定角色的所有权限permission
     *
     * @param roleCode 角色代码
     * @return
     */
    List<BasPermission> findBy(String roleCode);

    Boolean existRoleResource(Long roleId, Long resourceId);

    List findBy(Long roleId, Long resourceId, Class clazz);


    /**
     * 根据角色代码获取角色授权的资源 权限控制使用,和左侧菜单数据来源使用
     *
     * @param roleCode 角色代码
     * @param isMenu   是否菜单
     * @return
     */

    List findByRoleCode(String roleCode, String isMenu, Class clazz);

    void save(BasPermission object);

    void delete(List<BasPermission> permissionList);

    /**
     * 重新授权,删除原有权限,添加新权限
     *
     * @param permissionList    删除原有权限数据集
     * @param permissionNewList 添加新权限数据集
     */
    void deleteAndInsert(List<BasPermission> permissionList, List<BasPermission> permissionNewList);

    void delete(Long roleId, Long resourceId);

    void update(BasPermission object);

    BasPermission get(Long id);
}