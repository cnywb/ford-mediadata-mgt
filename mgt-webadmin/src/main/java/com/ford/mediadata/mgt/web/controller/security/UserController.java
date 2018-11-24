/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * User.java 15/10/19 下午3:53
 */
package com.ford.mediadata.mgt.web.controller.security;

import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.entity.security.BasUser;
import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.lisenter.LogUtil;
import com.ford.mediadata.mgt.service.excel.ExcelService;
import com.ford.mediadata.mgt.service.security.UserRoleService;
import com.ford.mediadata.mgt.service.security.UserService;
import com.ford.mediadata.mgt.web.controller.TransCode;
import com.ford.mediadata.mgt.web.controller.security.vo.*;

import io.dabing.common.date.DateUtil;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ziv.hung on 15/10/19.
 * 管理人员 业务逻辑
 *
 * @since 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private ExcelService excelService;


    //按关键字分页查询对象
    @ApiService(transCode = TransCode.T_9000)
    public Page<T9000Resp> findPage(UserGetReq req) {
        return searchUsers(req);
    }

    /**
     * 角色授权用户界面使用 可供授权用户分页数据
     */
    @ApiService(transCode = "preAuthorizedUsers")
    public Page<T9000Resp> preAuthorizedUsers(UserGetReq req) {
        return searchUsers(req);
    }

    private Page<T9000Resp> searchUsers(UserGetReq req) {
        Page<BasUser> pages = userService.findByGridKeyWordPage(req.getBatchNo(),req.getKeyWord(),req.getPage());
        Page<T9000Resp> respS = pages.map(new Converter<BasUser, T9000Resp>() {
            public T9000Resp convert(BasUser source) {
                T9000Resp resp = new T9000Resp();
                BeanUtils.copyProperties(source, resp);
                resp.setFirstInsert(DateUtil.formatDate(source.getCreatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
                return resp;
            }
        });
        return respS;
    }

    //单一创建对象
    @ApiService(transCode = TransCode.T_9001)
    public String create(T9001Req req) {
        BasUser user = new BasUser();
        Boolean userCheck = userService.findUserBy(req.getLoginName(), null);
        if (userCheck) {
            return "登录用户已存在!";
        }
        BeanUtils.copyProperties(req, user);
        //密码采用加密后的密码
        if (req.getPassword() != null) {
            user.setPassword(userService.getEncodePassword(req.getPassword()));
        }
        user.setCredentialExpiredDate (DateUtil.getNextDateByMonth (new Date (),3));
        this.userService.save(user);
        LogUtil.writer (OperationType.USER_CREATE,req.getLoginName ());
        return "";
    }

    //登录用户自改密码
    @ApiService(transCode = "modifyPwd")
    public void modifyPwd(T9001Req req) {
        BasUser user = userService.getCurrentUser();
        //密码采用加密后的密码
        if (req.getPassword() != null) {
            user.setPassword(userService.getEncodePassword(req.getPassword()));
        }
        user.setCredentialExpiredDate (DateUtil.getNextDateByMonth(new Date (), 3));
        this.userService.update(user);
        LogUtil.writer (OperationType.USER_PASSWORD_MODIFY,user.getLoginName (),req.getPassword ().replace (req.getPassword (),LogUtil.SENSITIVE_WORDS));
    }

    //单一创建对象
    @ApiService(transCode = "userModify")
    public String userModify(T9001Req req) {
        BasUser user = userService.get(req.getId());
        Boolean userCheck = userService.findUserBy(req.getLoginName(), user.getId());
        if (userCheck) {
            return "登录用户已存在!";
        }
        BeanUtils.copyProperties(req, user, new String[]{"password",});
        this.userService.update(user);
        LogUtil.writer (OperationType.USER_MODIFY,user.getLoginName (),req.getLoginName ());
        return "";
    }

    //单一创建对象
    @ApiService(transCode = "modifyPassword")
    public void modifyPassword(T9001Req req) {
        BasUser user = userService.get(req.getId());
        //密码采用加密后的密码
        if (req.getPassword() != null) {
            user.setPassword(userService.getEncodePassword(req.getPassword()));
        }
        //密码有效期为3个月
        user.setCredentialExpiredDate (DateUtil.getNextDateByMonth (new Date (),3));
        LogUtil.writer (OperationType.USER_PASSWORD_RESET,user.getLoginName ());
        this.userService.update(user);
    }

    @ApiService(transCode = "userRoleResourcePage")
    public Page<UserRoleResourceVo> pageUserRoleResource(UserRoleResourceVoReq req) {
        Page<Object[]> pages = userService.pageByUserRoleResource(req.getPage(), req.getUserName(), req.getRoleName(), req.getResourceName());
        Page<UserRoleResourceVo> respS = pages.map(new Converter<Object[], UserRoleResourceVo>() {
            public UserRoleResourceVo convert(Object[] source) {
                UserRoleResourceVo resp = new UserRoleResourceVo();
                resp.setUserLoginName(source[0]);
                resp.setUserName(source[1]);
                resp.setRoleCode(source[2]);
                resp.setRoleName(source[3]);
                resp.setRoleRemark(source[4]);
                resp.setResourceCode(source[5]);
                resp.setResourceName(source[6]);
                resp.setResourceType(source[7]);
                resp.setResourceIsMenu(source[8]);
                resp.setResourcePermission(source[9]);
                return resp;
            }
        });
        return respS;
    }

    //批量删除对象
    @ApiService(transCode = TransCode.T_9003)
    public void delete(T9003Req req) {
        List<String> params=new ArrayList<> ();
        for (T9003ReqVo vo : req.getReqs()) {
            BasUser user = userService.get(vo.getId());
            this.userRoleService.delete(user.getUserRoleList());
            this.userService.delete(vo.getId());
            params.add (user.getLoginName ());

        }
        LogUtil.writer (OperationType.USER_REMOVE,params.toString ());
    }

    /**
     * 用户权限信息导出
     */
    @RequestMapping(value = "/userRoleResourceExport.do")
    public void userRoleResourceExport(HttpServletRequest request, HttpServletResponse response) {
        excelService.exportData(ExcelList.B_CODE_USER_ROLE_RESOURCE, request, response);
    }
}