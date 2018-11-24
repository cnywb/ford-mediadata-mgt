package com.ford.mediadata.mgt.entity;

/**
 * Created by wanglijun on 16/10/12.
 */
public class StatisticsEntity {

    /**统计中1*/
    private Long num;


    public StatisticsEntity() {
        super();
    }

    public StatisticsEntity(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

}
