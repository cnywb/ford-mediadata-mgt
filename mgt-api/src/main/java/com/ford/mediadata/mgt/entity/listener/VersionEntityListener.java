package com.ford.mediadata.mgt.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ford.mediadata.mgt.entity.AuditEntity;
import com.ford.mediadata.mgt.entity.VersionEntity;

import java.util.Date;

/**
 * Created by wanglijun on 16/5/7.
 */
public class VersionEntityListener  extends  AbstractEntityListener{


    @PrePersist
    public void prePersist(AuditEntity entity) {
        this.setEntity (entity);
    }

    @PreUpdate
    public void preUpdate(VersionEntity entity) {
        Date updatedDate = new Date();
        entity.setUpdatedDate(updatedDate);
        //TODO
        entity.setCreatedById(1L);
        if(entity.getDeleted()){
            entity.setDeleteDate(updatedDate);
        }
    }
}
