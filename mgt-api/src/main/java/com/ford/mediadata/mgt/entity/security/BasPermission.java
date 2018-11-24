/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * RoleUserResource.java 15/11/2 上午10:26
 */
package com.ford.mediadata.mgt.entity.security;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.AuditEntity;

/**
 * 描述：角色 用户 资源 关系模型
 *
 * @author Create by ziv.hung on 15/11/2.
 * @since 1.0
 */
@Entity
@Table(name = "MD_PERMISSION")
public class BasPermission extends AuditEntity {


    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_PERMISSION")
    @SequenceGenerator(name = "SEQ_MD_PERMISSION",allocationSize=1,sequenceName = "SEQ_MD_PERMISSION")
    protected Long id;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "RESOURCE_CODE")
    private String resourceCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
