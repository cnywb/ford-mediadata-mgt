/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * UserService.java 15/10/19 下午3:48
 */
package com.ford.mediadata.mgt.service.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ford.mediadata.mgt.entity.security.BasUser;

/**
 * Created by ziv.hung on 15/10/19.
 * 管理人员 服务
 *
 * @since 1.0
 */
public interface UserService extends Service {

    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    BasUser getCurrentUser();

    /**
     * 获取当前登录用户所属机构代码
     *
     * @return
     */
    String getCurrentUserOrgPath();

    /**
     * 获取当前登录用户所属机构ID
     *
     * @return
     */
    Long getCurrentUserOrgId();

    /**
     * 获取当前登录用户名
     *
     * @return
     */
    String getCurrentUserName();

    /**
     * 获取MD5加密后的密码值
     *
     * @param password 密码原文
     * @return 密码加密后的值
     */
    String getEncodePassword(String password);

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasUser> findByGridPage(GridPage page);

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasUser> findByGridPage(String batchNo,String loginName,String name,String mobile,GridPage page);

    /**
     * 根据GridPage对象的关键字按分页查找服务
     * @param keyWord 关键字模糊匹配 登录名，真实姓名，联系电话
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<BasUser> findByGridKeyWordPage(String batchNo, String keyWord, GridPage page);

    /**
     * 根据用户名和密码获取用户，不存在则返回null
     *
     * @param loginName 用户工号
     * @param password  加密后的密码
     * @return
     */
    BasUser getUserByLoginNameAndPwd(String loginName, String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param loginName 登陆用户名
     * @return
     */
    BasUser getUserByLoginName(String loginName);

    /**
     * 新增或修改用户时,用来验证是否在同一公司存在登录用户名和手机号相同的用户
     *
     * @param loginName 登录用户名
     * @param userId    登录用户ID
     * @return
     */
    Boolean findUserBy(String loginName, Long userId);

    void save(BasUser object);

    void delete(Long id);

    void update(BasUser object);

    BasUser get(Long id);

    /**
     * 根据当前登录用户名获取 loginName,name,orgId,orgPath,orgNamePath
     *
     * @param loginName 登录用户名
     * @return Map, key 有如下:loginName 登录用户名,name 登录人真实姓名,orgId 机构ID ,orgPath 机构ID Path, orgNamePath 机构名称path
     */
    Map<String, String> findUserAttrs(String loginName);

    /**
     *
     * @param page
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    Page<Object[]> pageByUserRoleResource(GridPage page, String userName, String roleName, String resourceName);

    /***
     * 判断密码是否已经过有效期
     * @param loginName
     * @param date
     * @return
     */
    boolean isPassed(String loginName, Date date);

    /**
     *
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    List<Object[]> findByUserRoleResource(String userName, String roleName, String resourceName);
}