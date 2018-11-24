/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * UserRole.java 15/11/2 下午2:27
 */
package com.ford.mediadata.mgt.entity.security;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.AuditEntity;

/**
 * 描述：用户角色关系模型
 *
 * @author Create by ziv.hung on 15/11/2.
 * @since 1.0
 */
@Entity
@Table(name = "MD_USER_ROLE")
public class BasUserRole extends AuditEntity {
    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_USER_ROLE")
    @SequenceGenerator(name = "SEQ_MD_USER_ROLE",allocationSize=1,sequenceName = "SEQ_MD_USER_ROLE")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private BasUser user;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    public BasUser getUser() {
        return user;
    }

    public void setUser(BasUser user) {
        this.user = user;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
