package com.ford.mediadata.mgt.entity;

import com.ford.mediadata.mgt.entity.listener.AuditEntityListener;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.Date;

/**
 * 日志审计
 * Created by wanglijun on 16/5/7.
 */
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AuditEntity extends BaseEntity{

    /***
     * @Fields createById: is create UserId
     */
    @XStreamOmitField
    @Column(name="CREATED_BY_ID")
    private Long createdById;
    /***
     * @Fields createdDate: is create Date
     */
    @XStreamOmitField
    @Column(name="CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    /***
     * @Fields updatedById: is update UserId
     */
    @XStreamOmitField
    @Column(name="UPDATED_BY_ID")
    private Long updatedById;
    /***
     * @Fields updatedById: is update Date
     */
    @XStreamOmitField
    @Column(name="UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
