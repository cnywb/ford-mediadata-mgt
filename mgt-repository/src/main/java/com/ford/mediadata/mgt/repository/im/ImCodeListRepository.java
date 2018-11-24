/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * ImCodeList.java 15/10/15 上午11:44
 */
package com.ford.mediadata.mgt.repository.im;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.JpaRepository;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.im.ImCodeList;


/**
 * Created by yangkui on 15/10/15.
 * updated by ziv.hung on 15/10/15.
 * 字典项仓储
 *
 * @since 1.0
 */
public interface ImCodeListRepository extends JpaRepository<ImCodeList, Long> {

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param typeCode 字典大类代码 允许为空
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ImCodeList> findByGridPage(String typeCode, GridPage page);

    /**
     * 根据租户ID和字典大类代码 查询所属字典项
     * @param typeCode 字典大类代码 允许为空
     * @return
     */
    List<ImCodeList> findByTypeCode(String typeCode);
}
