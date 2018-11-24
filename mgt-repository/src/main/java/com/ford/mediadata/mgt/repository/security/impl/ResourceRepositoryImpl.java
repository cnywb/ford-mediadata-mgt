/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 13:06:41
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.BasResource;
import com.ford.mediadata.mgt.repository.security.ResourceRepository;

import io.dabing.common.Assert;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangkui on 2015-11-01 13:06:41 .
 *
 * @since 1.0
 */
@Repository
public class ResourceRepositoryImpl extends DefaultJpaRepository<BasResource, Long> implements ResourceRepository {

    /**
     * 根据 资源类型获取对应资源类型的资源数据集合
     *
     * @param type 资源类型 @see io.dabing.chuangdao.entity.security.Resource#TYPE_GLOBAL,TYPE_BUSINESS
     * @return
     */
    public List<BasResource> findBy(String type) {
        StringQuery query = StringQuery.newQuery()
                    .query("from BasResource a where a.deleted = :deleted ")
                    .param("deleted", false)
                .predicateHasText(type)
                    .query(" and a.type = :type ")
                    .param("type", type)
                .predicate(true)
                    .query(" order by a.sortNo asc").build();
        return find(query);
    }

    public List findTreeByRoleCode(String roleCode, String isMenu, Class clazz) {

        Assert.notNull(roleCode, "角色不得为空!");

        StringQuery query = StringQuery.newQuery()
                .query(" SELECT r.ID AS resourceId, ")
                .query(" r.CODE AS resourceCode, ")
                .query(" r.NAME AS resourceName, ")
                .query(" r.PARENT_CODE AS parentCode, ")
                .query(" r.IS_MENU AS isMenu, ")
                .query(" p.RESOURCE_CODE PRESOURCECODE")
                .query(" FROM MD_RESOURCE r LEFT JOIN MD_PERMISSION p ON r.CODE = p.RESOURCE_CODE ")
                .query(" AND p.ROLE_CODE = :roleCode")
                    .param("roleCode", roleCode)
                .predicateHasText(isMenu)
                .query(" WHERE r.IS_MENU = :isMenu")
                    .param("isMenu", isMenu)
                .query(" order by r.SORT_NO asc").build();
        return findBySql(query, clazz);
    }
}