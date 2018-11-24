package com.ford.mediadata.mgt.entity;

import com.ford.mediadata.mgt.entity.listener.VersionEntityListener;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.Date;

/**
 * 逻辑删除,乐观
 * Created by wanglijun on 16/5/7.
 */
@MappedSuperclass
@EntityListeners(VersionEntityListener.class)
public abstract class VersionEntity extends  AuditEntity {
    /**
     *  操作Version (乐观锁)
    */
    @XStreamOmitField
    @Version
    @Column(name="OPT_COUNTER")
    private Long optCounter;

    @XStreamOmitField
    /** 是否删除 */
    @Column(name="MARK_FOR_DELETE")
    private Boolean deleted =Boolean.FALSE;

    /** 删除日期 */
    @XStreamOmitField
    @Column(name="DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getOptCounter() {
        return optCounter;
    }

    public void setOptCounter(Long optCounter) {
        this.optCounter = optCounter;
    }
}
