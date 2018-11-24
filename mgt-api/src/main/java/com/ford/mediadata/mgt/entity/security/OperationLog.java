package com.ford.mediadata.mgt.entity.security;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.AuditEntity;

import java.util.Date;

/**
 * 描述:LogSecurity
 * 操作权限 记录日志
 *
 * @author ziv.hung create on 16/8/25
 * @since 1.0
 */
@Entity
@Table(name = "MD_OPERATION_LOG")
public class OperationLog extends AuditEntity{



    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_OPERATION_LOG")
    @SequenceGenerator(name = "SEQ_MD_OPERATION_LOG", allocationSize = 1, sequenceName = "SEQ_MD_OPERATION_LOG")
    protected Long id;
    /**用户名*/
    @Column(name = "USER_NAME", nullable = false)
    protected String userName;
    /**
     * 用户ID
     * */
    @Column(name="USER_ID")
    private Long userId;

    /**操作类型*/
    @Column(name = "OPERATION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    protected OperationType operationType;

    /**操作内容*/
    @Column(name = "OPERATION_CONTENT", nullable = false)
    protected String operationContent;

    /**操作日期*/
    @Column(name = "OPERATION_DATE", nullable = false)
    @Temporal (TemporalType.TIMESTAMP)
    protected Date operationDate;


    public OperationLog() {
        super();
    }

    public static OperationLog instance(OperationType operationType) {
        OperationLog logSecurity = new OperationLog ();
        logSecurity.setOperationDate(new Date());
        logSecurity.setOperationType (operationType);
        return logSecurity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }


    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", operationType=" + operationType +
                ", operationContent='" + operationContent + '\'' +
                ", operationDate=" + operationDate +
                '}';
    }
}
