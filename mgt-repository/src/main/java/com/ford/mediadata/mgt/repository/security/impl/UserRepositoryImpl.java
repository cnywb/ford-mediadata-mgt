/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * User.java 15/10/19 下午3:44
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.BasUser;
import com.ford.mediadata.mgt.repository.security.UserRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by ziv.hung on 15/10/19.
 * 管理人员 仓储实现
 *
 * @since 1.0
 */
@Repository
public class UserRepositoryImpl extends DefaultJpaRepository<BasUser, Long> implements UserRepository {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasUser> findByGridPage(GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1 ")
                .predicateHasText(page.getKeyWord())
                    .query(" and (a.name like :name or a.loginName like :loginName or a.mobile like :mobile)")
                    .likeParam("name", page.getKeyWord())
                    .likeParam("loginName", page.getKeyWord())
                    .likeParam("mobile", page.getKeyWord())
                .predicate(Boolean.TRUE)
                    .query(" order by a.ID desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    /***
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    @Override
    public List findByUserRoleResource(String userName, String roleName, String resourceName) {
        StringQuery query = StringQuery.newQuery()
                .query("SELECT ")
                .query("us.LOGIN_NAME, ")
                .query("us.NAME as uName, ")
                .query("r.ROLE_NAME, ")
                .query("re.NAME as rName, ")
                .query("re.TYPE, ")
                .query("re.IS_MENU, ")
                .query("re.PERMISSION ")
                .query("FROM ")
                .query("MD_USER us, ")
                .query("MD_USER_ROLE ur, ")
                .query("MD_ROLE r, ")
                .query("MD_PERMISSION pe, ")
                .query("MD_RESOURCE re ")
                .query("WHERE ")
                .query("us.ID = ur.USER_ID ")
                .query("AND ur.ROLE_CODE = r.ROLE_CODE ")
                .query("AND r.ROLE_CODE = pe.ROLE_CODE ")
                .query("AND pe.RESOURCE_CODE = re.CODE ")
                .predicateHasText(userName)
                .query("and us.NAME = :userName ")
                .param("userName",userName)
                .predicateHasText(roleName)
                .query("and r.ROLE_NAME = :roleName ")
                .param("roleName",roleName)
                .predicateHasText(resourceName)
                .query("and re.NAME = :resourceName")
                .param("resourceName",resourceName).build();
        return findBySql(query);
    }

    /***
     * @param page
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    @Override
    public Page pageByUserRoleResource(GridPage page, String userName, String roleName, String resourceName) {
        StringQuery query = StringQuery.newQuery()
                .query("SELECT ")
                .query("us.LOGIN_NAME, ")
                .query("us.NAME as uName, ")
                .query("r.ROLE_CODE, ")
                .query("r.ROLE_NAME, ")
                .query("r.REMARK, ")
                .query("re.CODE, ")
                .query("re.NAME as rName, ")
                .query("re.TYPE, ")
                .query("re.IS_MENU, ")
                .query("re.PERMISSION ")
                .query("FROM ")
                .query("MD_USER us, ")
                .query("MD_USER_ROLE ur, ")
                .query("MD_ROLE r, ")
                .query("MD_PERMISSION pe, ")
                .query("MD_RESOURCE re ")
                .query("WHERE ")
                .query("us.ID = ur.USER_ID ")
                .query("AND ur.ROLE_CODE = r.ROLE_CODE ")
                .query("AND r.ROLE_CODE = pe.ROLE_CODE ")
                .query("AND pe.RESOURCE_CODE = re.CODE ")
                .predicateHasText(userName)
                .query("and us.NAME = :userName ")
                .param("userName",userName)
                .predicateHasText(roleName)
                .query("and r.ROLE_NAME = :roleName ")
                .param("roleName",roleName)
                .predicateHasText(resourceName)
                .query("and re.NAME = :resourceName")
                .param("resourceName",resourceName).build();
        return findBySql(query,PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param batchNo
     * @param loginName
     * @param name
     * @param mobile
     * @param page      分页对象，里面有关键字进行模糊匹配  @return
     */
    @Override
    public Page<BasUser> findByGridPage(String batchNo, String loginName, String name, String mobile, GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1 ")

                .predicateHasText(batchNo)
                .query(" and a.batchNo like :batchNo")
                .likeParam("batchNo", batchNo)

                .predicateHasText(name)
                .query(" and a.name like :name")
                .likeParam("name", name)

                .predicateHasText(loginName)
                .query(" and a.loginName like :loginName")
                .likeParam("loginName", loginName)

                .predicateHasText(mobile)
                .query(" and a.mobile like :mobile")
                .likeParam("mobile", mobile)

                .build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }


    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param batchNo
     * @param keyWord
     * @param page      分页对象，里面有关键字进行模糊匹配  @return
     */
    @Override
    public Page<BasUser> findByGridKeyWordPage(String batchNo, String keyWord, GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1 ")

                .predicateHasText(batchNo)
                .query(" and a.batchNo like :batchNo")
                .likeParam("batchNo", batchNo)

                .predicateHasText(keyWord)
                .query(" and ( a.name like :keyWord")
                .query(" or a.loginName like :keyWord")
                .query(" or a.mobile like :keyWord)")
                .likeParam("keyWord", keyWord)


                .build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    /**
     * 根据用户名和密码获取用户，不存在则返回null
     *
     * @param loginName 用户工号
     * @param password 加密后的密码
     * @return
     */
    public List<BasUser> getUserByLoginNameAndPwd(String loginName, String password) {
        Assert.notNull(loginName, "loginName不能为空");
        Assert.notNull(password, "password不能为空");
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1  ")
                .predicateHasText(loginName)
                    .query(" and a.loginName = :loginName")
                    .inParam("loginName", loginName)
                .predicateHasText(password)
                    .query(" and a.password = :password")
                    .inParam("password", password)
              .build();
        return find(query);
    }
    /**
     * 根据用户名获取用户信息
     *
     * @param loginName 登录用户名
     * @return
     */
    public List<BasUser> getUserByLoginName(String loginName) {
        Assert.notNull(loginName, "loginName不能为空");
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1  ")
                .predicateHasText(loginName)
                    .query(" and a.loginName = :loginName")
                    .inParam("loginName", loginName)
              .build();
        return find(query);
    }

    public List<BasUser> findUserBy(String loginName, Long userId) {
        Assert.notNull(loginName, "loginName不能为空");
        StringQuery query = StringQuery.newQuery()
                .query("from BasUser a where 1 = 1  ")
                .predicateHasText(loginName)
                    .query(" and a.loginName = :loginName")
                    .inParam("loginName", loginName)
                .predicateNotNull(userId)
                    .query(" and a.id <> :userId")
                    .inParam("userId", userId)
                .build();
        return find(query);
    }

    public List findUserAttrs(String loginName) {
        Assert.notNull(loginName, "loginName 不能为空");
       /* StringQuery query = StringQuery.newQuery()
                .query("SELECT ")
                .query("u.LOGIN_NAME, ")
                .query("u.`NAME`, ")
                .query("u.ORG_ID, ")
                .query("p.ORG_PATH, ")
                .query("p.ORG_NAME_PATH ")
                .query("FROM MALL_USER u,  MALL_ORGANIZATION_PATH p ")
                .query("WHERE ")
                .query("u.ORG_ID = p.ORG_ID ")
                    .query("AND u.LOGIN_NAME = :loginName")
                    .inParam("loginName",loginName).build();
        return findBySql(query);*/
        return null;
    }
}