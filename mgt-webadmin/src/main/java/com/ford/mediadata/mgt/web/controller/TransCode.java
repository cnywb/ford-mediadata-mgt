package com.ford.mediadata.mgt.web.controller;

/**
 * @author kui.yang
 * @since 1.0
 * create date: 15/8/25 下午3:11
 * 定义所有交易号，便于统计与管理
 * 交易代码约定规则
 * 前2位：大模块
 * 后2位：小模块
 * 示例：1001 10：市场大模块 01:活动申请
 */
public class TransCode {

   /* 数据字典模块 */
    /** 数据字典模块-根据类别代码查询所有大类 */
    public static final String T_1002 = "1002";
   /* 系统模块 */
    /**权限管理/字典管理/数据字典大类/分页*/
    public static final String T_1010 = "1010";
    /**权限管理/字典管理/数据字典大类/创建、变更*/
    public static final String T_1011 = "1011";
    /**权限管理/字典管理/数据字典大类/数据详情*/
    public static final String T_1012 = "1012";
    /**权限管理/字典管理/数据字典大类/删除*/
    public static final String T_1013 = "1013";

    /*权限管理/字典管理/数据字典大类/下拉列表*/
    public static final String T_1016 = "1016";

    /**权限管理/字典管理/数据字典项/分页*/
    public static final String T_1020 = "1020";
    /**权限管理/字典管理/数据字典项/创建、变更*/
    public static final String T_1021 = "1021";
    /**权限管理/字典管理/数据字典项/数据详情*/
    public static final String T_1022 = "1022";
    /**权限管理/字典管理/数据字典项/删除*/
    public static final String T_1023 = "1023";

    /*权限管理/字典管理/数据字典项/下拉列表*/
    public static final String T_1026 = "1026";

    /**权限管理/职员管理/分页*/
    public static final String T_9000 = "9000";
    /**权限管理/职员管理/创建、变更*/
    public static final String T_9001 = "9001";
    /**权限管理/职员管理/详情*/
    public static final String T_9002 = "9002";
    /**权限管理/职员管理/删除*/
    public static final String T_9003 = "9003";

    /**权限管理/角色管理/分页*/
    public static final String T_9100 = "9100";
    /**权限管理/角色管理/创建/变更*/
    public static final String T_9101 = "9101";
    /**权限管理/角色管理/删除*/
    public static final String T_9103 = "9103";
    /**权限管理/角色管理/授权资源*/
    public static final String T_9104 = "9104";

    /**权限管理/角色管理/角色用户/分页*/
    public static final String T_9110 = "9110";
    /**权限管理/角色管理/角色用户/创建、变更*/
    public static final String T_9111 = "9111";
    /**权限管理/角色管理/角色用户/删除*/
    public static final String T_9113 = "9113";

    /**权限管理/角色管理/角色资源/分页*/
    public static final String T_9120 = "9120";
    /**权限管理/角色管理/角色资源/创建、变更*/
    public static final String T_9121 = "9121";
    /**权限管理/角色管理/角色资源/删除*/
    public static final String T_9123 = "9123";
    /*(*)系统资源/登录用户资源*/
    public static final String T_9124 = "9124";

    /**权限管理/资源管理/分页*/
    public static final String T_9200 = "9200";
    /**权限管理/资源管理/创建、变更*/
    public static final String T_9201 = "9201";
    /**权限管理/资源管理/详情*/
    public static final String T_9202 = "9202";
    /**权限管理/资源管理/删除*/
    public static final String T_9203 = "9203";
    /**权限管理/资源管理/下拉列表*/
    public static final String T_9204 = "9204";
    
}
