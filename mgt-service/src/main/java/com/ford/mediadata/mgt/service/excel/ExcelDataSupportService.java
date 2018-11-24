/*
 * Copyright (c)  2016, 
 * All rights reserved.
 * ExcelDataSupportService.java 2016-05-18 上午11:10
 */

package com.ford.mediadata.mgt.service.excel;

import java.util.List;
import java.util.Map;

/**
 * 描述:Excel导出时获取数据源的接口,如果需要自定义查询数据的实现,可以实现该类,然后在ExcelList当中配置一下service的名称
 * 系统采用默认的实现方式.
 *
 * @author yangkui create on 2016-05-18.
 * @since 1.0
 */
public interface ExcelDataSupportService {

    /**
     * 查询数据
     *
     * @param queryFields 提供的查询字段,要求返回的结果数组必须与该字段下标匹配
     * @param from        查询那个类
     * @param where       where条件
     * @param orderBy     排序条件
     * @param whereValue  页面上传递的参数值.
     * @return
     */
    List<Object[]> getExportDataList(String queryFields, String from, String where, String orderBy, Map<String, String> whereValue);

}