package com.ford.mediadata.mgt.entity.listener;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ford.mediadata.mgt.entity.AuditEntity;

import java.util.Date;

/**
 * @author li.yu
 * @since 1.0
 */
public class AuditEntityListener extends  AbstractEntityListener {


    @PrePersist
    public void prePersist(AuditEntity entity) {
        Date createdDate = new Date();
        entity.setCreatedById(1L);
        entity.setCreatedDate(createdDate);
        entity.setUpdatedDate(createdDate);
        entity.setUpdatedById(1L);
    }

    @PreUpdate
    public void preUpdate(AuditEntity entity) {
        Date updatedDate = new Date();
        entity.setUpdatedDate(updatedDate);
        //TODO
        entity.setCreatedById(1L);
    }

}