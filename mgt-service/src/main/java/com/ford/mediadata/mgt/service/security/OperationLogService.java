/*
 * Copyright (c)  2016  newtouch.com
 * All rights reserved. 
 * OperationLog.java 2016-12-03 上午12:05
 */

package com.ford.mediadata.mgt.service.security;


import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

import java.util.Date;
import java.util.List;

import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.entity.security.OperationType;

/**
 * 描述：TODO
 *
 * @author wanglijun create on 2016-12-03
 * @since 1.0
 */
public interface OperationLogService {

    Page<OperationLog> pagingBy(GridPage page);

    void save(OperationLog object);

    void delete(List<OperationLog> objectList);

    void delete(OperationLog object);

    void delete(Long id);

    void update(OperationLog object);

    OperationLog get(Long id);

    /***
     *
     * @param page
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDateCover
     * @return
     */
    Page<OperationLog> pagingBy(GridPage page, String userName, OperationType operationType, Date startDate, Date endDateCover);
}
