/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * UserRole.java 2015-11-02 14:37:12
 */
package com.ford.mediadata.mgt.repository.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasUserRole;

/**
 * Created by ziv.hung on 2015-11-02 14:37:12.
 * @since 1.0
 */
public interface UserRoleRepository extends JpaRepository<BasUserRole, Long> {

    List<BasUserRole> findBy(Long userId, String roleCode);

    Page<BasUserRole> findBy(Long userId, String roleCode, GridPage page);
}