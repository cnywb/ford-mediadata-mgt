/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelDetail.java 2016-05-12 15:03:01
 */
package com.ford.mediadata.mgt.repository.excel.impl;

import com.ford.mediadata.mgt.entity.excel.ExcelDetail;
import com.ford.mediadata.mgt.repository.excel.ExcelDetailRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ziv.hung on 2016-05-12 15:03:01 .
 *
 * @since 1.0
 */
@Repository
public class ExcelDetailRepositoryImpl extends DefaultJpaRepository<ExcelDetail, Long> implements ExcelDetailRepository {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ExcelDetail> findByGridPage(GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelDetail a where (1=1) ")
                .predicateHasText(page.getKeyWord())
                    .query(" and a.name like :name")
                    .likeParam("name", page.getKeyWord())
                .predicate(true)
                    .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    public List<ExcelDetail> findBy(Long excelListId) {
        StringQuery query = StringQuery.newQuery()
                .query("from ExcelDetail a where (1=1) ")
                .predicateNotNull(excelListId)
                    .query(" and a.excelList.id = :excelListId")
                    .param("excelListId", excelListId)
                .predicate(true)
                    .query(" order by a.excelCellNum asc").build();
        return find(query);
    }
}