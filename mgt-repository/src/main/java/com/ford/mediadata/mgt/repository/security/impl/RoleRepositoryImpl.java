/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * Role.java 15/10/30 下午4:39
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.BasRole;
import com.ford.mediadata.mgt.repository.security.RoleRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by yangkui on 15/10/30.
 * 管理员角色
 *
 * @since 1.0
 */
@Repository
public class RoleRepositoryImpl extends DefaultJpaRepository<BasRole, Long> implements RoleRepository {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasRole> findByGridPage(GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasRole a where (1=1) ")
                .predicateHasText(page.getKeyWord())
                    .query(" and a.name like :name")
                    .likeParam("name", page.getKeyWord())
                .predicate(Boolean.TRUE)
                    .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    public List<BasRole> findForExport(String keyWord) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasRole a where (1=1) ")
                .predicateHasText(keyWord)
                    .query(" and a.name like :name")
                    .likeParam("name", keyWord)
                .predicate(Boolean.TRUE)
                    .query(" order by a.createdDate desc").build();
        return find(query);
    }

    public List<BasRole> findBy(String code, Long id) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasRole a where a.code = :code ")
                    .inParam("code", code)
                .predicateNotNull(id)
                    .query(" and a.id <> :id")
                    .inParam("id", id).build();
        return find(query);
    }

    /***
     * 根据角色代码查询
     *
     * @param code 角色代码
     * @return Role
     */
    public BasRole findByCode(String code) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasRole a where a.code = :code ")
                .inParam("code", code)
                .build();
        List<BasRole> roles=find(query);
        if(CollectionUtils.isEmpty(roles)){
            return null;
        }
        return roles.get(0);
    }
}