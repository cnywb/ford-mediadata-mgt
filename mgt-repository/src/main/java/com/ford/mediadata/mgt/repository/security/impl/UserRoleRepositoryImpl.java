/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * UserRole.java 2015-11-02 14:37:12
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.BasUserRole;
import com.ford.mediadata.mgt.repository.security.UserRoleRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ziv.hung on 2015-11-02 14:37:12 .
 *
 * @since 1.0
 */
@Repository
public class UserRoleRepositoryImpl extends DefaultJpaRepository<BasUserRole, Long> implements UserRoleRepository {

    public List<BasUserRole> findBy(Long userId, String roleCode) {

        StringQuery query = StringQuery.newQuery()
                    .query("from BasUserRole a where 1 = 1 ")
                .predicateNotNull(userId)
                    .query(" and a.user.id = :userId")
                    .inParam("userId", userId)
                .predicateHasText(roleCode)
                    .query(" and a.roleCode = :roleCode")
                    .inParam("roleCode", roleCode)
                .predicate(Boolean.TRUE)
                    .query(" order by a.createdDate desc").build();
        List list = find(query);
        return list;
    }


    public Page<BasUserRole> findBy(Long userId, String roleCode, GridPage page){
        StringQuery query = StringQuery.newQuery()
                .query("from BasUserRole a where ( 1 = 1 ) ")
                .predicateNotNull(userId)
                    .query(" and a.user.id = :userId")
                    .inParam("userId", userId)
                .predicateHasText(roleCode)
                    .query(" and a.roleCode = :roleCode")
                    .inParam("roleCode", roleCode)
                .predicateHasText(page.getKeyWord())
                    .query(" and (a.user.name like :name or a.user.loginName like :loginName or a.user.mobile like :mobile)")
                    .likeParam("name", page.getKeyWord())
                    .likeParam("loginName", page.getKeyWord())
                    .likeParam("mobile", page.getKeyWord())
                .predicate(Boolean.TRUE)
                    .query(" order by  a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }
}