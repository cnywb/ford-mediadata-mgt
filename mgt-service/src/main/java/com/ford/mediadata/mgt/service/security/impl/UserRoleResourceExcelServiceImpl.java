/**
 * Copyright (c)  2016, xiaosheep.com
 * All rights reserved.
 * UrrExcelServiceImpl.java 16/8/25
 */
package com.ford.mediadata.mgt.service.security.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.mediadata.mgt.service.security.UserRoleResourceExcelService;
import com.ford.mediadata.mgt.service.security.UserService;

import java.util.List;
import java.util.Map;

/**
 * 描述:UrrExcelServiceImpl
 * TODO
 *
 * @author ziv.hung create on 16/8/25
 * @since 1.0
 */
@Service(value = "userRoleResourceExcelService")
public class UserRoleResourceExcelServiceImpl implements UserRoleResourceExcelService {

    @Autowired
    UserService userService;

    /**
     * 查询数据
     *
     * @param queryFields 提供的查询字段,要求返回的结果数组必须与该字段下标匹配
     * @param from        查询那个类
     * @param where       where条件
     * @param orderBy     排序条件
     * @param whereValue  页面上传递的参数值.
     * @return
     */
    public List<Object[]> getExportDataList(String queryFields, String from, String where, String orderBy, Map<String, String> whereValue) {

        String userName = whereValue.get("userName");
        String roleName = whereValue.get("roleName");
        String resourceName = whereValue.get("resourceName");
        List<Object[]> objects = userService.findByUserRoleResource(userName, roleName, resourceName);
        for (Object[] object : objects) {
            object[4] = "business".equalsIgnoreCase(object[4].toString()) ? "业务资源" : "全局资源";
        }
        return objects;
    }
}
