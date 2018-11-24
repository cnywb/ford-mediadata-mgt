/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelList.java 2016-05-12 14:55:25
 */
package com.ford.mediadata.mgt.repository.excel.impl;


import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.repository.excel.ExcelListRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.NamedParams;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ziv.hung on 2016-05-12 14:55:25 .
 *
 * @since 1.0
 */
@Repository
public class ExcelListRepositoryImpl extends DefaultJpaRepository<ExcelList, Long> implements ExcelListRepository {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ExcelList> findByGridPage(GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelList a where (1=1) ")
                .predicateHasText(page.getKeyWord())
                    .query(" and a.name like :name")
                    .likeParam("name", page.getKeyWord())
                .predicate(true)
                    .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    public List<ExcelList> findAll() {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelList a where (1=1) ")
                .query(" order by a.createdDate desc").build();
        return find(query);
    }

    public List<ExcelList> findBy(String businessCode, String businessType) {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelList a where (1=1) ")
                .predicateHasText(businessCode)
                    .query(" and a.businessCode = :businessCode")
                    .param("businessCode",businessCode)
                .predicateHasText(businessType)
                    .query(" and a.businessType = :businessType")
                    .param("businessType",businessType)
                .predicate(true)
                    .query(" order by a.createdDate desc").build();
        return find(query);
    }

    /**
     * 根据fileName 和 businessCode 获取ExcelList对象
     *
     * @param fileName     文件名称
     * @param businessType 模块代码对应的操作类型
     * @return
     */
    @Override
    public List<ExcelList> findByFile(String fileName, String businessType) {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelList a where (1=1) ")
                .predicateHasText(fileName)
                .query(" and a.fileName = :fileName")
                .param("fileName",fileName)
                .predicateHasText(businessType)
                .query(" and a.businessType = :businessType")
                .param("businessType",businessType)
                .predicate(true)
                .query(" order by a.createdDate desc").build();
        return find(query);
    }

    public List findBy(String query, NamedParams namedParams) {
        return find(query, namedParams);
    }
}