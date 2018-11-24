/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * PermissionRepositoryImpl.java 15/11/2 下午2:03
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.BasPermission;
import com.ford.mediadata.mgt.repository.security.PermissionRepository;

import io.dabing.common.Assert;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ziv.hung on 15/11/2.
 * 授权仓储实现
 *
 * @since 1.0
 */
@Repository
public class PermissionRepositoryImpl extends DefaultJpaRepository<BasPermission, Long> implements PermissionRepository {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param roleId   角色ID 允许为空
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasPermission> findByGridPage(Long roleId, GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasPermission a where 1 = 1 ")
                .predicateNotNull(roleId)
                    .query(" and a.role.id = :roleId")
                    .inParam("roleId", roleId)
                .predicateHasText(page.getKeyWord())
                    .query(" and (a.resource.name like :name or a.resource.code like :code or a.resource.permission like :permission)")
                    .likeParam("name", page.getKeyWord())
                    .likeParam("code", page.getKeyWord())
                    .likeParam("permission", page.getKeyWord())
                .predicate(Boolean.TRUE)
                    .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    public List<BasPermission> findBy(Long roleId, Long resourceId) {

        StringQuery query = StringQuery.newQuery()
                    .query("from BasPermission a where 1 = 1 ")
                .predicateNotNull(roleId)
                    .query(" and a.role.id = :roleId")
                    .inParam("roleId", roleId)
                .predicateNotNull(resourceId)
                    .query(" and a.resource.id = :resourceId")
                    .inParam("resourceId", resourceId).build();
        List list = find(query);
        return list;
    }

    public List findByRoleCode(String roleCode, String isMenu, Class clazz) {

        Assert.notNull(roleCode, "角色不得为空!");

        StringQuery query = StringQuery.newQuery()
                .query("select r.id as resourceId ,r.code as resourceCode,r.name as resourceName,r.parentCode as parentCode,r.permission as permissionStr")
                .query(" from BasResource r,BasPermission p where r.code = p.resourceCode and p.roleCode = :roleCode")
                .inParam("roleCode", roleCode)
                .predicateHasText(isMenu)
                    .query(" and r.isMenu = :isMenu ")
                    .inParam("isMenu", isMenu)
                    .query(" order by r.sortNo asc").build();
        return find(query, clazz);
    }

    public List<BasPermission> findBy(String roleCode) {
        StringQuery query = StringQuery.newQuery()
                    .query("from BasPermission a where 1 = 1 ")
                .predicateHasText(roleCode)
                    .query(" and a.roleCode = :roleCode")
                    .inParam("roleCode", roleCode).build();
        List list = find(query);
        return list;
    }

    public List findBy( Long roleId, Long resourceId, Class clazz) {

        StringQuery query = StringQuery.newQuery()
                    .query("select a.resource.permission as permission ,a.resource.url as url, a.role.id as roleId, " +
                            "a.resource.id as resourceId, a.resource.code as code,  a.resource.name as name," +
                            "  a.resource.createdDate as createdDate from BasPermission a where 1 = 1 ")
                .predicateNotNull(roleId)
                    .query(" and a.role.id = :roleId")
                    .inParam("roleId", roleId)
                .predicateNotNull(resourceId)
                    .query(" and a.resource.id = :resourceId")
                    .inParam("resourceId", resourceId).build();
        return find(query, clazz);
    }

    @Override
    public void deleteList(List<BasPermission> list) {
        for (BasPermission deletePermission : list) {
            this.delete(deletePermission);
        }
    }

    @Override
    public void saveList(List<BasPermission> list) {
        for (BasPermission savePermission : list) {
            this.save(savePermission);
        }
    }
}