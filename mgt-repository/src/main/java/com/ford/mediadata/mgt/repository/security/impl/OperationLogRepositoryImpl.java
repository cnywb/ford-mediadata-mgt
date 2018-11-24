/*
 * Copyright (c)  2016, newtouch.com
 * All rights reserved.
 * OperationLogRepositoryImpl.java 2016-12-03 上午12:04
 */
package com.ford.mediadata.mgt.repository.security.impl;

import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.repository.security.OperationLogRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.common.util.DateUtils;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 描述：TODO
 *
 * @author wanglijun create on 2016-12-03
 * @since 1.0
 */
@Repository
public class OperationLogRepositoryImpl extends DefaultJpaRepository<OperationLog, Long> implements OperationLogRepository {

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里有关键字keyWord,供模糊匹配
     * @return 分页结果数据对象集合
     */
    public Page<OperationLog> pagingBy(GridPage page) {
        StringQuery query = StringQuery.newQuery ()
                .query ("from OperationLog where 1 = 1 ")
                .predicateHasText (page.getKeyWord ())
                .query (" and name like :name")
                .likeParam ("name", page.getKeyWord ())
                .predicate (Boolean.TRUE)
                .query (" order by id desc").build ();
        return find (query, PageRequest.newPage (page.getPageNumber (), page.getPageSize ()));
    }


    /***
     * @param page
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<OperationLog> pagingBy(GridPage page, String userName, OperationType operationType, Date startDate, Date endDate) {
        StringQuery query = StringQuery.newQuery()
                .query("from OperationLog a where 1 = 1  ")
                .predicateNotNull(startDate)
                .query(" and a.operationDate >=:startDate")
                .param("startDate", startDate)
                .predicateNotNull(endDate)
                .query(" and a.operationDate <=:endDate")
                .param("endDate", DateUtils.formatEndDate(endDate))
                .predicateHasText(userName)
                .query(" and a.userName =:userName")
                .param("userName", userName)
                .predicateNotNull (operationType)
                .query(" and a.operationType =:operationType")
                .param("operationType", operationType)
                .predicate(Boolean.TRUE)
                .query(" order by a.id desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }
}
