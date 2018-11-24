package com.ford.mediadata.mgt.lisenter;

import java.text.MessageFormat;
import java.util.Date;

import com.ford.mediadata.mgt.entity.security.OperationLog;
import com.ford.mediadata.mgt.entity.security.OperationType;

/**
 * 操作日志记录
 * Created by wanglijun on 16/12/3.
 */
public class LogUtil {

    /**
     * 敏感词替换数据
     */
    public static final String SENSITIVE_WORDS="**********";


    /***
     * 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(OperationType type,String ... params){
        writer (null,type,params);
    }

    /***
     *
     * @param userName 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(String userName,OperationType type,String  ... params){
        writer (userName,null,type,params);
    }


    /***
     *
     * @param userName 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(String userName,Long userId,OperationType type,String  ... params){
        OperationLog log=new OperationLog ();
        log.setUserName (userName);
        log.setUserId (userId);
        log.setOperationType (type);
        log.setOperationContent (MessageFormat.format (type.getDesc (),params));
        log.setOperationDate (new Date ());
        DataLogUtil.post (log);
    }




}
