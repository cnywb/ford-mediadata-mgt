/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * PermissionController.java 15/11/2 22:27
 */
package com.ford.mediadata.mgt.web.controller.security;

import com.ford.mediadata.mgt.entity.security.*;
import com.ford.mediadata.mgt.service.security.*;
import com.ford.mediadata.mgt.web.controller.TransCode;
import com.ford.mediadata.mgt.web.controller.security.vo.*;

import io.dabing.core.web.annotation.ApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziv.hung on 15/11/2.
 *
 * @since 1.0
 */
@Controller
public class PermissionController {
    @Autowired
    private PermissionService service;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    //查询当前登录用户的菜单权限
    @ApiService(transCode = TransCode.T_9124)
    public List<T9120Resp> findByCurrentUser(String no) {
        List<T9120Resp> resps = new ArrayList<T9120Resp>();
        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal().toString();
        BasUser user = userService.getUserByLoginName(userName);
        List<BasUserRole> userRoleList = userRoleService.findBy(user.getId(), null);
        for (BasUserRole userRole : userRoleList) {
            List<BasPermissionCacheVo> permissionList = service.findByRoleCode(userRole.getRoleCode(), BasResource.ISMENU_YES, BasPermissionCacheVo.class);
            for (BasPermissionCacheVo permissionCacheVo : permissionList) {
                Object permission = permissionCacheVo.getPermissionStr();
                if (permission != null && !"".equals(permission)) {
                    T9120Resp resp = new T9120Resp();
                    BeanUtils.copyProperties(permissionCacheVo, resp);
                    resp.setRoleCode(userRole.getRoleCode());
                    resps.add(resp);
                }
            }
        }
        return resps;
    }

    //单一创建对象
    @ApiService(transCode = TransCode.T_9121)
    public List<T9121Resp> create(T9121Req req) {
        List<T9121Resp> respS = new ArrayList<T9121Resp>();
        Long roleId = req.getRoleId();
        for (T9121ReqVo vo : req.getReqVoList()) {
            Long resourceId = vo.getId();
            Boolean exist = service.existRoleResource(roleId, resourceId);
            if (exist) {
                T9121Resp t9121Resp = new T9121Resp();
                t9121Resp.setResourceCode(vo.getResourceCode());
                t9121Resp.setResourceName(vo.getResourceName());
                respS.add(t9121Resp);
                continue;
            }
            BasPermission permission = new BasPermission();
            this.service.save(permission);
        }
        return respS;
    }

    //批量删除对象
    @ApiService(transCode = TransCode.T_9123)
    public void delete(T9123Req req) {
        for (T9123ReqVo vo : req.getReqVos()) {
            this.service.delete(req.getRoleId(), vo.getId());
        }
    }
}
