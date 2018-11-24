package com.ford.mediadata.mgt.lisenter;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.ford.mediadata.mgt.entity.listener.UserInfo;
import com.ford.mediadata.mgt.entity.listener.UserInfoListener;


/**
 * Created by wanglijun on 16/8/24.
 */
@Component
public class UserInfoListenerImpl implements UserInfoListener {
    /***
     * 获取用户信息 (用户ID,用户名)
     *
     * @return
     */
    @Override
    public UserInfo getUserInfo() {
        //TODO
        SecurityUtils.getSubject ().getPrincipal ();
        UserInfo userInfo=new UserInfo ();
        userInfo.setUserId (1L);
        userInfo.setUserName ("admin");
        return userInfo;
    }
}
