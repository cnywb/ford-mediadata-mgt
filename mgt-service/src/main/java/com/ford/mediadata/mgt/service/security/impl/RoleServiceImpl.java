/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * Role.java 15/10/30 下午4:42
 */
package com.ford.mediadata.mgt.service.security.impl;

import com.ford.mediadata.mgt.entity.security.BasPermission;
import com.ford.mediadata.mgt.entity.security.BasRole;
import com.ford.mediadata.mgt.entity.security.BasUserRole;
import com.ford.mediadata.mgt.repository.security.PermissionRepository;
import com.ford.mediadata.mgt.repository.security.RoleRepository;
import com.ford.mediadata.mgt.repository.security.UserRoleRepository;
import com.ford.mediadata.mgt.service.security.RoleService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangkui on 15/10/30.
 * 角色
 *
 * @since 1.0
 */
@Service
public class RoleServiceImpl extends AbstractService implements RoleService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasRole> findByGridPage(GridPage page) {
        return repository.findByGridPage(page);
    }

    public List<BasRole> findForExport(String keyWord) {
        return repository.findForExport(keyWord);
    }

    /**
     * 是否存在角色代码
     *
     * @param code 角色代码
     * @param id   角色ID
     * @return true 存在, false 不存在
     */
    public boolean existCode(String code, Long id) {
        List<BasRole> roleList = repository.findBy(code, id);
        if (roleList.isEmpty()) {
            return false;
        }
        return true;
    }

    public void save(BasRole object) {
        repository.save(object);
    }

    public void delete(String roleCode) {
        List<BasPermission> permissionList = permissionRepository.findBy(roleCode);
        permissionRepository.delete(permissionList);
        List<BasUserRole> userRoleList = userRoleRepository.findBy(null, roleCode);
        userRoleRepository.delete(userRoleList);
        List<BasRole> roleList = repository.findBy(roleCode, null);
        repository.delete(roleList);
    }

    public void update(BasRole object) {
        repository.update(object);
    }

    public BasRole get(Long id) {
        return repository.get(id);
    }


    /***
     * 根据角色代码获取角色ID
     *
     * @param code
     * @return Role
     */
    public BasRole getByCode(String code) {
        return this.repository.findByCode(code);
    }
}