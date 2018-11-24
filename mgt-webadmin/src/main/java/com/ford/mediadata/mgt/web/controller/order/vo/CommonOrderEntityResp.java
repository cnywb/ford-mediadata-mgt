package com.ford.mediadata.mgt.web.controller.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/27.
 */
@Getter
@Setter
public class CommonOrderEntityResp {
	
	private Long id;
	
    /**
     * 订单类商机ID
     */
    private Long orderId;

    /**
     * 订单类型<br/>
     * 1新车，2试驾，6置换
     */
    private Integer orderTypeId;

    /**
     * 订单类型名称<br/>
     * 新车，试驾，置换
     */
    private String orderTypeName;

    /**
     * 订购者名称
     */
    private String userName;

    /**
     * 订购者电话
     */
    private String userMobile;

    /**
     * 订购者性别<br/>
     * 0女，1男，-1未知
     */
    private Integer userGender;

    /**
     * 订购者邮件
     */
    private String userMail;

    /**
     * 车型颜色
     */
    private String carColor;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 车系ID
     */
    private Long csId;

    /**
     * 车型名称
     */
    private String csName;

    /**
     * 销售顾问
     */
    private String accountName;

    /**
     * 线索在易车的处理状态
     */
    private Integer status;

    /**
     * 首次处理时间
     */
    private Date fistFollowTime;

    /**
     * 车款Id
     */
    private String carId;

    /**
     * 车款Name
     */
    private String carTypeName;

    /**
     * 车名称
     */
    private String carName;

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     * 省份ID
     */
    private Long provinceId;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 订单类商机创建时间
     */
    private Date orderBusinessOpportunityCreateTime;

    /**
     * 经销商名称
     */
    private String dealerName;

    /**
     * 经销商ID
     */
    private Long dealerId;

    /**
     * 订单备注信息
     */
    private String orderRemark;

    /**
     * 订购价格
     */
    private BigDecimal yeferPrice;

    /**
     * 上市年份
     */
    private Integer yearType;

    /**
     * 数据创建时间
     */
    private Date createdDate;
    /**
     * 数据跟新时间
     */
    private Date updatedDate;
    
}