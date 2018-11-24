/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ResourceDeleteReq.java 2016-02-23 16:43
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.List;

/**
 * 描述：Resource 逻辑删除请求对象
 *
 * @author ziv.hung create on 2016-02-23 16:43
 * @since 1.0
 */
public class ResourceDeleteReq {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
