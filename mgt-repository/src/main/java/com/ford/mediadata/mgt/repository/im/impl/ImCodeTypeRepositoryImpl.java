/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * ImCodeType.java 15/10/15 上午11:45
 */
package com.ford.mediadata.mgt.repository.im.impl;

import com.ford.mediadata.mgt.entity.im.ImCodeType;
import com.ford.mediadata.mgt.repository.im.ImCodeTypeRepository;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.DefaultJpaRepository;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.repository.domain.PageRequest;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by yangkui on 15/10/15.
 * 字典大类
 *
 * @since 1.0
 */
@Repository
public class ImCodeTypeRepositoryImpl extends DefaultJpaRepository<ImCodeType, Long> implements ImCodeTypeRepository {


    /**
     * 根据GridPage对象按分页查找
     *
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ImCodeType> findByGridPage(GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from ImCodeType a where deleted = false  ")
                .predicateHasText(page.getKeyWord())
                    .query(" and ( a.nameCn like :nameCn or a.nameEn like:nameEn )")
                    .likeParam("nameCn", page.getKeyWord())
                    .likeParam("nameEn", page.getKeyWord())
                .predicate(Boolean.TRUE)
                    .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    /**
     * 根据字典分类代码码查询所有字典
     *
     * @param typeCode     字典分类代码
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ImCodeType> findByCodeGridPage(String typeCode, GridPage page) {
        StringQuery query = StringQuery.newQuery()
                .query("from ImCodeType a where deleted = false  ")
                .predicateNotNull(typeCode)
                    .query(" and a.typeCode = :typeCode")
                    .inParam("typeCode", typeCode)
                .predicateHasText(page.getKeyWord())
                    .query(" and ( a.nameCn like :nameCn or a.nameEn like:nameEn )")
                    .likeParam("nameCn", page.getKeyWord())
                    .likeParam("nameEn", page.getKeyWord())
                .query(" order by a.createdDate desc").build();
        return find(query, PageRequest.newPage(page.getPageNumber(), page.getPageSize()));
    }

    public List<ImCodeType> findAll(String typeCode) {
        StringQuery query = StringQuery.newQuery()
                    .query("from ImCodeType a where deleted = false ")
                .predicateNotNull(typeCode)
                    .query(" and a.typeCode = :typeCode")
                    .inParam("typeCode", typeCode)
                .query(" order by a.id desc").build();
        return find(query);
    }
}
