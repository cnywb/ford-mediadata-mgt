package com.ford.mediadata.mgt.entity.security;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.VersionEntity;

/**
 * Created by kui.yang on 2014/6/8.
 * 资源表
 */
@Entity
@Table(name = "MD_RESOURCE")
public class BasResource extends VersionEntity {

    public static final String TYPE_GLOBAL = "global";

    public static final String TYPE_BUSINESS = "business";

    public static final String ISMENU_YES = "是";

    public static final String ISMENU_NO = "否";

    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_RESOURCE")
    @SequenceGenerator(name = "SEQ_MD_RESOURCE",allocationSize=1,sequenceName = "SEQ_MD_RESOURCE")
    protected Long id;

    /* 资源代码 系统自动生成 */
    @Column(name = "CODE")
    protected String code;

    /* 资源名称 */
    @Column(name = "NAME")
    protected String name;

    /* 资源类型 */
    @Column(name = "TYPE")
    protected String type;

    /* 资源是否为菜单 是 或 否 */
    @Column(name = "IS_MENU")
    protected String isMenu;

    /* 上级资源 资源代码 */
    @Column(name = "PARENT_CODE")
    protected String parentCode;

    /* 上级资源 资源名称 */
    @Column(name = "PARENT_NAME")
    protected String parentName;

    /* 资源 URL或权限标识 */
    @Column(name = "PERMISSION")
    protected String permission;

    /* 资源 排序 */
    @Column(name = "SORT_NO")
    protected Integer sortNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
