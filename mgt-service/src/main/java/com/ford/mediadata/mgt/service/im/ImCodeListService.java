/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * ImCodeList.java 15/10/13 下午4:25
 */
package com.ford.mediadata.mgt.service.im;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.List;

import com.ford.mediadata.mgt.entity.im.ImCodeList;

/**
 * 描述：字典类服务
 *
 * @author ziv.hung
 * @since 1.0
 */
public interface ImCodeListService extends Service {

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

    void save(ImCodeList imCodeList);

    void update(ImCodeList imCodeList);

    ImCodeList get(Long id);

    void delete(Long id);

    /***
     * 根据字典类型,小类字典代码查询小类字典名称
     * @param typeCode 类型
     * @param code 代码
     * @return 返回名称
     */
    public String getNameByTypeAndCode(String typeCode, String code);
}