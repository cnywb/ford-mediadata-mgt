package com.ford.mediadata.mgt.web.controller.order.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CallOrderEntiyReq extends CommonGetReq {

    /**
     * 物理主键
     */
    private Long id;
    /**
     * 状态 1 未跟进 2 已跟进 3 无效
     */
    private Integer dealerFollowStatus;

    /**
     * 坐席
     */
    private String agentName;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 通话类商机Id
     */
    private Long callBusinessOpportunityId;


    /**
     * 通话类商机创建时间(开始)
     */
    private Date createTimeStartDate;
    /**
     * 通话类商机创建时间（结束）
     */
    private Date createTimeEndDate;



    /**
     * 数据创建时间
     */
    private Date createdDateStartDate;
    /**
     * 数据创建时间
     */
    private Date createdDateEndDate;


    /**
     * 数据跟新时间
     */
    private Date updatedDateStartDate;
    /**
     * 数据跟新时间
     */
    private Date updatedDateEndDate;
}
