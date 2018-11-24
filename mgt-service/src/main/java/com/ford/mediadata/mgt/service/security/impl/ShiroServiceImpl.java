package com.ford.mediadata.mgt.service.security.impl;


import com.ford.mediadata.mgt.entity.security.*;
import com.ford.mediadata.mgt.lisenter.LogUtil;
import com.ford.mediadata.mgt.service.security.PermissionService;
import com.ford.mediadata.mgt.service.security.ResourceService;
import com.ford.mediadata.mgt.service.security.UserRoleService;
import com.ford.mediadata.mgt.service.security.UserService;

import io.dabing.shiro.service.ShiroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by yangkui on 15/10/30.
 * 实现shiro所需要的接口
 */
@Component
public class ShiroServiceImpl implements ShiroService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 判断用户是否存在
     *
     * @param loginName 用户工号
     * @param password  未加密的密码
     * @return 是否存在
     */
    public boolean userExist(String loginName, String password) {
        BasUser user = userService.getUserByLoginNameAndPwd(loginName, userService.getEncodePassword(password));
        logger.info("登录时判断用户信息是否存在,登陆账户{},结果:{}", new Object[]{loginName, user});
        return user != null;
    }

    /**
     * 获取用户的角色名称集合
     *
     * @param userName 登录用户名
     * @return 用户角色列表
     */
    public Set<String> getUserRoleNames(String userName) {
        Set<String> set = new HashSet<String>();
        BasUser user = userService.getUserByLoginName(userName);
        List<BasUserRole> userRoleList = userRoleService.findBy(user.getId(), null);
        for (BasUserRole userRole : userRoleList) {
            String roleName = userRole.getRoleCode();//TODO 需要使用角色名称,现使用角色代码代替
            if (StringUtils.isEmpty (roleName)) {
                continue;
            }
            set.add(roleName);
        }
        return set;


    }

    /**
     * 获取用户的权限列表
     *
     * @param userName 登录用户名(工号)
     * @return 用户权限列表
     */
    public Set<String> getUserPermission(String userName) {
        Set<String> set = new HashSet<String>();
        BasUser user = userService.getUserByLoginName(userName);
        List<BasUserRole> userRoleList = userRoleService.findBy(user.getId(), null);
        for (BasUserRole userRole : userRoleList) {
            List<BasPermissionCacheVo> permissionCaches = permissionService.findByRoleCode(userRole.getRoleCode(), BasResource.ISMENU_NO, BasPermissionCacheVo.class);
            for (BasPermissionCacheVo permissionCache : permissionCaches) {
                Object permission = permissionCache.getPermissionStr();
                if (StringUtils.isEmpty (permission)) {
                    continue;
                }
                set.add(permission.toString());
            }
        }
        List<BasResource> resourceList = resourceService.findBy(BasResource.TYPE_GLOBAL);
        for (BasResource resource : resourceList) {
            String permission = resource.getPermission();
            if (StringUtils.isEmpty (permission)) {
                continue;
            }
            set.add(permission);
        }
        logger.info("当前用户:{},权限资源数量为:{}", userName, set.size());
        return set;

    }

    /**
     * 获取系统需要拦截的URL列表，以及对应的拦截策略。示例以及默认值：
     * map.put("/login.do","anon"); --这个不需要登录就可以访问
     * map.put("/logout.do","anon");
     * map.put("/api.do", "authc"); --这个需要登录才能访问
     *
     * @return
     */
    public Map<String, String> getDefinitionMap() {
        Map<String, String> maps = new HashMap<String, String>();
        return maps;
    }

    public Map<String, String> getUserAttr(String s) {
        return userService.findUserAttrs(s);
    }



    public boolean isPassed(String loginName, String password) {
        boolean flag = userService.isPassed(loginName, new Date());
        //密码已经过有效期,
        if(flag){
            LogUtil.writer (loginName,OperationType.USER_PASSWORD_EXPIRED,loginName);
        }else{
            LogUtil.writer (loginName,OperationType.USER_LOGIN,loginName);
        }
        return flag;
    }
}
