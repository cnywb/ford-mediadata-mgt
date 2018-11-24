/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * OrgGridPage.java 2016-04-05 下午6:06
 */

package io.dabing.common.grid;

import java.util.Date;

/**
 * 描述:带组织机构查询条件的gridPage对象
 *
 * @author yangkui create on 2016-04-05.
 * @since 1.0
 */
public class OrgGridPage extends GridPage {

    /**
     * 机构树path值
     */
    private String orgPath;
    /**
     * 机构所属年月份
     */
    private Date month;
    /**
     * 人员
     */
    private Long channelRoleId;


    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Long getChannelRoleId() {
        return channelRoleId;
    }

    public void setChannelRoleId(Long channelRoleId) {
        this.channelRoleId = channelRoleId;
    }
}