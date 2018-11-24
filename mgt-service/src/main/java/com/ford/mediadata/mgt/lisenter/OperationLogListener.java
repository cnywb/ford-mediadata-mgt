package com.ford.mediadata.mgt.lisenter;

import com.ford.mediadata.mgt.entity.security.BasUser;
import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.service.security.OperationLogService;
import com.ford.mediadata.mgt.service.security.UserService;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wanglijun on 16/12/3.
 */
@Component
public class OperationLogListener {
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (OperationLogListener.class);

    @Resource
    private OperationLogService operationLogService;

    @Autowired
    UserService userService;

    /**
     * 写入日志
     * @param operationLog
     */
    @Subscribe
    public void saveOperationLog(OperationLog operationLog){
        logger.debug (operationLog.toString ());
        if(operationLog!=null&& StringUtils.isNotEmpty (userService.getCurrentUserName ())){
            BasUser basUser=userService.getCurrentUser ();
            if(basUser!=null) {
                operationLog.setUserId (basUser.getId ());
                operationLog.setUserName (basUser.getLoginName ());
            }
        }
        operationLogService.save (operationLog);
    }
}
