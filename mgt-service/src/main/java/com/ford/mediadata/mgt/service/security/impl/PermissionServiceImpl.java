/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * PermissionServiceImpl.java 15/11/2 下午2:11
 */
package com.ford.mediadata.mgt.service.security.impl;

import com.ford.mediadata.mgt.entity.security.BasPermission;
import com.ford.mediadata.mgt.repository.security.PermissionRepository;
import com.ford.mediadata.mgt.service.security.PermissionService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ziv.hung on 15/11/2.
 * 授权服务实现
 *
 * @since 1.0
 */
@Service
public class PermissionServiceImpl extends AbstractService implements PermissionService {
    @Autowired
    private PermissionRepository repository;

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasPermission> findByGridPage(Long roleId, GridPage page) {
        return repository.findByGridPage(roleId, page);
    }

    /**
     * 根据租户 用户ID 角色ID获取权限列表
     *
     * @param roleId 角色ID  允许为空
     * @return
     */
    @Cacheable(value = "permissionCache", key = "#roleId+'findBy'")
    public List<BasPermission> findBy(Long roleId, Long resourceId) {
        return repository.findBy(roleId, resourceId);
    }

    public List<BasPermission> findBy(String roleCode) {
        return repository.findBy(roleCode);
    }

    public List findByRoleCode(String roleCode, String isMenu, Class clazz) {
        return repository.findByRoleCode(roleCode, isMenu, clazz);
    }

    public Boolean existRoleResource(Long roleId, Long resourceId) {
        List<BasPermission> permissions = repository.findBy(roleId, resourceId);
        if (permissions != null && permissions.size() > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @CacheEvict(value = "permissionCache", key = "#permission.getRole().getId() + 'findBy'")
    public void save(BasPermission permission) {
        repository.save(permission);
    }

    @CacheEvict(value = "permissionCache", key = "#roleId + 'findBy'")
    public void delete(Long roleId, Long resourceId) {
        List<BasPermission> permissions = repository.findBy(roleId, resourceId);
        for (BasPermission permission : permissions) {
            repository.delete(permission);
        }
    }

    @CacheEvict(value = "permissionCache")
    public void delete(List<BasPermission> permissionList) {
        repository.delete(permissionList);
    }

    @CacheEvict(value = "permissionCache")
    public void deleteAndInsert(List<BasPermission> permissionRemoveList, List<BasPermission> permissionNewList) {
        repository.deleteList(permissionRemoveList);
        repository.saveList(permissionNewList);
    }


    @Cacheable(value = "permissionCache", key = "#roleId+'findBy'")
    public List findBy(Long roleId, Long resourceId, Class clazz) {
        return repository.findBy(roleId, resourceId, clazz);
    }

    @CacheEvict(value = "permissionCache", key = "#object.getRole().getId() + 'findBy'")
    public void update(BasPermission object) {
        repository.update(object);
    }

    public BasPermission get(Long id) {
        return repository.get(id);
    }
}