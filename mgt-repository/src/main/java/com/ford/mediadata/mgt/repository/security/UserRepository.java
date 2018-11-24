/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * User.java 15/10/19 下午3:43
 */
package com.ford.mediadata.mgt.repository.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasUser;

/**
 * Created by ziv.hung on 15/10/19.
 * 管理人员 仓储
 *
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<BasUser, Long> {
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
     * @param employeeNo 用户工号
     * @param password   加密后的密码
     * @return
     */
    List<BasUser> getUserByLoginNameAndPwd(String employeeNo, String password);


    /**
     * 根据用户名获取用户信息
     *
     * @param loginName 登陆用户名
     * @return
     */
    List<BasUser> getUserByLoginName(String loginName);

    /**
     * 新增或修改用户时,用来验证是否在同一公司存在登录用户名和手机号相同的用户
     *
     * @param loginName 登录用户名
     * @param userId    登录用户ID
     * @return
     */
    List<BasUser> findUserBy(String loginName, Long userId);

    /**
     * 根据当前登录用户名 获取部分属性
     * @param loginName 登录用户名
     * @return
     */
    List<Object[]> findUserAttrs(String loginName);

    /***
     *
     * @param page
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    Page pageByUserRoleResource(GridPage page, String userName, String roleName, String resourceName);

    /***
     *
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    List findByUserRoleResource(String userName, String roleName, String resourceName);
}