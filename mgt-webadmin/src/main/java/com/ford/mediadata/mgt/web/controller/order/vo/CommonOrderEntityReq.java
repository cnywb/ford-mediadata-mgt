package com.ford.mediadata.mgt.web.controller.order.vo;

import com.ford.mediadata.mgt.web.controller.CommonGetReq;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class CommonOrderEntityReq extends CommonGetReq {

    /**
     * 物理主键
     */
    private Long id;
    /**
     * 状态 1 待处理 2 处理中 3 已处理
     */
    private Integer status;

    /**
     * 订单类型名称
     */
    private String orderTypeName;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市名城
     */
    private String cityName;
    /**
     * 订购者名称
     */
    private String userName;

    /**
     * 销售顾问
     */
    private String accountName;

    /**
     * 订单类商机ID
     */
    private Long orderId;

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
