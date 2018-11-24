package com.ford.mediadata.mgt.entity.listener;

import javax.annotation.Resource;
import javax.persistence.PrePersist;

import com.ford.mediadata.mgt.entity.AuditEntity;

import java.util.Date;

/**
 * Created by wanglijun on 16/5/7.
 */
public abstract class AbstractEntityListener {

    @Resource
    private UserInfoListener  userInfoListener;

    @PrePersist
    public void prePersist(AuditEntity entity) {
        this.setEntity (entity);
    }

    protected void setEntity(AuditEntity entity){
         if(entity instanceof  AuditEntity){
             Date createdDate = new Date();
             entity.setCreatedById(1L);
             entity.setCreatedDate(createdDate);
             entity.setUpdatedDate(createdDate);
             entity.setUpdatedById(1L);
         }
    }
}
