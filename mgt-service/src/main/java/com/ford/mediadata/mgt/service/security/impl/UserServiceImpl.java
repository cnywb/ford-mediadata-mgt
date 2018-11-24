/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * UserServiceImpl.java 15/10/19 下午3:49
 */
package com.ford.mediadata.mgt.service.security.impl;

import com.ford.mediadata.mgt.entity.security.BasUser;
import com.ford.mediadata.mgt.repository.security.UserRepository;
import com.ford.mediadata.mgt.service.security.UserService;

import io.dabing.common.date.DateUtil;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import io.dabing.core.web.encrypt.encryptor.MD5Encryptor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ziv.hung on 15/10/19.
 * 管理人员 服务 实现
 *
 * @since 1.0
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {





    @Autowired
    private UserRepository repository;



    @Resource(name = "myMD5Encryptord5")
    private MD5Encryptor md5Encryptor;


    /**
     * @param page
     * @param userName
     * @param roleName
     * @param resourceName
     * @return
     */
    @Override
    public Page pageByUserRoleResource(GridPage page, String userName, String roleName, String resourceName) {
        return repository.pageByUserRoleResource(page, userName, roleName, resourceName);
    }

    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public BasUser getCurrentUser() {
        return this.getUserByLoginName(this.getCurrentUserName());
    }

    /**
     * 获取当前登录用户所属机构ID Path
     *
     * @return
     */
    public String getCurrentUserOrgPath() {
       return null;
    }

    public Long getCurrentUserOrgId() {
        return getCurrentUser().getOrgId();
    }

    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public String getCurrentUserName() {
        Subject subject=SecurityUtils.getSubject ();
        if(subject!=null&&subject.getPrincipal ()!= null){
            return subject.getPrincipal ().toString ();
        }
       return null;
    }

    /**
     * 获取MD5加密后的密码值
     *
     * @param password 密码原文
     * @return 密码加密后的值
     */
    public String getEncodePassword(String password) {
        return md5Encryptor.digest(password);
    }

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<BasUser> findByGridPage(GridPage page) {
        return repository.findByGridPage(page);
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
        return repository.findByGridPage(batchNo, loginName, name, mobile, page);
    }

    @Override
    public Page<BasUser> findByGridKeyWordPage(String batchNo, String keyWord, GridPage page){
        return repository.findByGridKeyWordPage(batchNo, keyWord, page);
    }

    /**
     * 根据用户名和密码获取用户，不存在则返回null
     * * @param tenantId   租户ID 允许为空
     *
     * @param employeeNo 用户工号
     * @param password   加密后的密码
     * @return
     */
    public BasUser getUserByLoginNameAndPwd(String employeeNo, String password) {
        List<BasUser> userList = repository.getUserByLoginNameAndPwd(employeeNo, password);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param loginName 登陆用户名
     * @return
     */
    public BasUser getUserByLoginName(String loginName) {
        List<BasUser> userList = repository.getUserByLoginName(loginName);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 新增或修改用户时,用来验证是否在同一公司存在登录用户名和手机号相同的用户
     *
     * @param loginName 登录用户名
     * @param userId    登录用户ID
     * @return
     */
    public Boolean findUserBy(String loginName, Long userId) {
        List<BasUser> userList = repository.findUserBy(loginName, userId);
        if (userList.isEmpty()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /***
     * 判断密码是否已经过有效期
     *
     * @param loginName
     * @param date
     * @return
     */
    @Override
    public boolean isPassed(String loginName, Date date) {
        List<BasUser> users = repository.getUserByLoginName(loginName);
        if (users.isEmpty()) {
            return true;
        }
        BasUser user = users.get(0);
        if (user.getCredentialExpiredDate () == null) {
            return true;
        } else if (date.compareTo(user.getCredentialExpiredDate ()) > -1) {
            return true;
        }
        return false;
    }

    public void save(BasUser object) {
        repository.save(object);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void update(BasUser object) {
        repository.update(object);
    }

    public BasUser get(Long id) {
        return repository.get(id);
    }

    public Map<String, String> findUserAttrs(String loginName) {
        BasUser user = this.getUserByLoginName(loginName);
        Map<String, String> attrMaps = new HashMap<String, String>();
        attrMaps.put("loginName", user.getLoginName());
        attrMaps.put("name", user.getName());
        long days = DateUtil.getBetweenDay(new Date(), user.getCredentialExpiredDate ());
        if (days <= 15) {
            attrMaps.put("willPassDays", String.valueOf(days + 1));
        }
        return attrMaps;
    }

    public List findByUserRoleResource(String userName, String roleName, String resourceName) {
        return repository.findByUserRoleResource(userName, roleName, resourceName);
    }
}