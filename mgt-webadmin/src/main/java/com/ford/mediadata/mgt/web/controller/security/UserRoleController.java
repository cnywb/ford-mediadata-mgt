/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * UserRole.java 15/11/2 下午6:00
 */
package com.ford.mediadata.mgt.web.controller.security;

import com.ford.mediadata.mgt.entity.security.BasUser;
import com.ford.mediadata.mgt.entity.security.BasUserRole;
import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.lisenter.LogUtil;
import com.ford.mediadata.mgt.service.security.RoleService;
import com.ford.mediadata.mgt.service.security.UserRoleService;
import com.ford.mediadata.mgt.service.security.UserService;
import com.ford.mediadata.mgt.web.controller.TransCode;
import com.ford.mediadata.mgt.web.controller.security.vo.*;

import io.dabing.common.date.DateUtil;
import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziv.hung on 15/11/2.
 * 角色用户
 *
 * @since 1.0
 */
@Controller
public class UserRoleController {
    @Autowired
    private UserRoleService service;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    //按关键字分页查询对象
    @ApiService(transCode = TransCode.T_9110)
    public Page<T9110Resp> findPage(T9110Req req) {

        return searchPage(null, req.getPage());
    }

    private Page searchPage(String roleCode, GridPage page) {
        Page<BasUserRole> pages = service.findBy(null, roleCode, page);
        Page<T9110Resp> respS = pages.map(new Converter<BasUserRole, T9110Resp>() {
            public T9110Resp convert(BasUserRole source) {
                T9110Resp resp = new T9110Resp();
                BasUser user = source.getUser();
                BeanUtils.copyProperties(user, resp);
                resp.setFirstInsert(DateUtil.formatDate(user.getCreatedDate(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));
                return resp;
            }
        });
        return respS;
    }

    // 角色授权页面 有授权用户信息获取
    @ApiService(transCode = "viewPermissionUser")
    public Page<T9110Resp> viewPermissionUser(T9110Req req) {

        return searchPage(req.getRoleCode(), req.getPage());
    }

    //单一创建对象
    @ApiService(transCode = TransCode.T_9111)
    public List<T9111Resp> create(T9111Req req) {
        List<T9111Resp> respS = new ArrayList<T9111Resp>();
        String roleCode = req.getRoleCode();
        List<String> params=new ArrayList<> ();
        for (T9111ReqVo vo : req.getReqVoList()) {
            Long userId = vo.getId();
            Boolean exist = service.existUserRole(userId, roleCode);
            if (exist) {
                T9111Resp t9111Resp = new T9111Resp();
                t9111Resp.setUserName(vo.getName());
                respS.add(t9111Resp);
                continue;
            }
            BasUserRole userRole = new BasUserRole();
            BasUser user = userService.get(userId);
            userRole.setUser(user);
            params.add (user.getName ());
            userRole.setRoleCode(roleCode);
            this.service.save(userRole);
        }
        LogUtil.writer (OperationType.AUTH_ROLE_USER_ADD,req.getRoleCode (),params.toString ());
        return respS;
    }

    //批量删除对象
    @ApiService(transCode = TransCode.T_9113)
    public void delete(T9113Req req) {
        for (T9113ReqVo vo : req.getReqVos()) {
            this.service.delete(req.getRoleCode());
        }
        //添加日志
        LogUtil.writer (OperationType.ROLE_REMOVE,req.getRoleCode ());
    }
}