/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * UserRole.java 2015-11-02 14:37:12
 */
package com.ford.mediadata.mgt.service.security;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.List;

import com.ford.mediadata.mgt.entity.security.BasUserRole;

/**
 * Created by ziv.hung on 2015-11-02 14:37:12 .
 *
 * @since 1.0
 */
public interface UserRoleService extends Service {

    List<BasUserRole> findBy(Long userId, String roleCode);

    Page<BasUserRole> findBy(Long userId, String roleCode, GridPage page);

    Boolean existUserRole(Long userId, String roleCode);

    void save(BasUserRole object);

    void delete(List<BasUserRole> userRoleList);

    void delete(String roleCode);

    void update(BasUserRole object);

    BasUserRole get(Long id);
}