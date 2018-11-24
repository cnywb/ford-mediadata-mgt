/*
 * Copyright (c)  2016, 
 * All rights reserved.
 * DefaultExcelDataSupportServiceImpl.java 2016-05-18 上午11:14
 */

package com.ford.mediadata.mgt.service.excel.impl;

import com.ford.mediadata.mgt.repository.excel.ExcelListRepository;
import com.ford.mediadata.mgt.service.excel.ExcelDataSupportService;

import io.dabing.core.repository.domain.NamedParams;
import io.dabing.core.repository.domain.StringQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 描述:默认采用HQL语句查询
 *
 * @author yangkui create on 2016-05-18.
 * @since 1.0
 */
@Service(value = "defaultExcelDataSupportService")
public class DefaultExcelDataSupportServiceImpl implements ExcelDataSupportService {

    @Autowired
    private ExcelListRepository excelListRepository;

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
    public List<Object[]> getExportDataList(String queryFields, String from, String where, String orderBy, Map<String, String> whereValue) {
        NamedParams namedParams =  NamedParams.newParams();
        StringBuffer sb = new StringBuffer();

        if(!StringUtils.isEmpty(where)){
            String[] ws = where.split(",");
            for(String w :ws){
                String[] o = w.split("\\|\\|");
                String p = o[0];
                String operator = o[1];
                String param = o[2];
                String value = (String)whereValue.get(param);
                if(!StringUtils.isEmpty(value)){
                   sb.append(" and "+p+operator+":"+param);
                    if("like".equalsIgnoreCase(operator)){
                        namedParams.likeParam(param,value);
                    }else{
                        namedParams.param(param,value);
                    }
                }
            }
        }
        StringQuery query = StringQuery.newQuery().query("select ").query(queryFields).query(" from "+from+" where 1=1 ")
                .predicateHasText(where)
                .query(sb.toString())
                .predicateHasText(orderBy)
                .query(" order by " +orderBy).build();
        return excelListRepository.findBy(query.getQuery(),namedParams);
    }
}