package com.ford.mediadata.mgt.entity.listener;

/**
 * 用户登录信息
 * Created by wanglijun on 16/8/24.
 */
public class UserInfo {
    /**用户ID*/
    private Long userId;
    /**用户名*/
    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
