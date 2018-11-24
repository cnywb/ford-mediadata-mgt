/*
 * Copyright (c)  2016, newtouch.com
 * All rights reserved.
 * OperationLogServiceImpl.java 2016-12-03 上午12:05
 */
package com.ford.mediadata.mgt.service.security.impl;

import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.repository.security.OperationLogRepository;
import com.ford.mediadata.mgt.service.security.OperationLogService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述：TODO
 *
 * @author wanglijun create on 2016-12-03
 * @since 1.0
 */
@Service
public class OperationLogServiceImpl extends AbstractService implements OperationLogService {
    @Autowired
    OperationLogRepository repository;


    /***
     * @param page
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDateCover
     * @return
     */
    @Override
    public Page<OperationLog> pagingBy(GridPage page, String userName, OperationType operationType, Date startDate, Date endDateCover) {
        return this.repository.pagingBy (page, userName, operationType, startDate, endDateCover);
    }

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里有关键字keyWord,供模糊匹配
     * @return 分页结果数据对象集合
     */
    public Page<OperationLog> pagingBy(GridPage page) {
        return repository.pagingBy (page);
    }

    public void save(OperationLog object) {
        repository.save (object);
    }

    public void delete(List<OperationLog> objectList) {
        repository.delete (objectList);
    }

    public void delete(OperationLog object) {
        repository.delete (object);
    }

    public void delete(Long id) {
        repository.delete (id);
    }

    public void update(OperationLog object) {
        repository.update (object);
    }

    public OperationLog get(Long id) {
        return repository.get (id);
    }
}
