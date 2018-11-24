/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * ImCodeType.java 15/10/15 上午11:43
 */
package com.ford.mediadata.mgt.repository.im;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.im.ImCodeType;

/**
 * Created by yangkui on 15/10/15.
 * 数据字典大类
 *
 * @since 1.0
 */
public interface ImCodeTypeRepository extends JpaRepository<ImCodeType, Long> {

    /**
     * 根据GridPage对象按分页查找
     *
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ImCodeType> findByGridPage(GridPage page);

    /**
     * 根据字典分类代码代码查询所有字典
     *
     * @param typeCode 字典分类代码 不允许为空
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ImCodeType> findByCodeGridPage(String typeCode, GridPage page);

    /**
     * 根据租户ID和字典分类代码获取所有字典大类
     * @param typeCode 字典分类代码 允许为空
     * @return
     */
    List<ImCodeType> findAll(String typeCode);
}
