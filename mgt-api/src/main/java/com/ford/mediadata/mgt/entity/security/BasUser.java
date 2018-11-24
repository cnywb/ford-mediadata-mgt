package com.ford.mediadata.mgt.entity.security;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.AuditEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理端用户
 *
 * @author li.yu
 * @since 1.0
 */
@Entity
@Table(name = "MD_USER")
public class BasUser extends AuditEntity {

    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_USER")
    @SequenceGenerator(name = "SEQ_MD_USER",allocationSize=1,sequenceName = "SEQ_MD_USER")
    protected Long id;
    /** 后台登录用户名 */
    @Column(name = "LOGIN_NAME")
    private String loginName;
    /** 后台登录密码 */
    @Column(name = "PASSWORD")
    private String password;
    /** 姓名 */
    @Column(name = "NAME")
    private String name;
    /** 联系电话 */
    @Column(name = "MOBILE")
    private String mobile;
    /** 邮件 */
    @Column(name = "EMAIL")
    private String email;
    /**密码有*/
    /** 机构代码 */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /** 机构ID */
    @Column(name = "ORG_ID")
    private Long orgId;
    /** 机构名称 */
    @Column(name = "ORG_NAME")
    private String orgName;
    /**密码有效期*/
    @Column(name="CREDENTIAL_EXPIRED_DATE")
    private Date credentialExpiredDate;

    /** 售前大区代码 */
    @Column(name = "PRE_SALES_BIG_AREA_CODE")
    private String preSalesBigAreaCode;
    /** 售前小区代码 */
    @Column(name = "PRE_SALES_SMALL_AREA_CODE")
    private String preSalesSmallAreaCode;
    /** 售后大区代码 */
    @Column(name = "AFTER_SALES_BIG_AREA_CODE")
    private String afterSalesBigAreaCode;
    /** 售后小区代码 */
    @Column(name = "AFTER_SALES_SMALL_AREA_CODE")
    private String afterSalesSmallAreaCode;
    /** 售前大区名称 */
    @Column(name = "PRE_SALES_BIG_AREA_NAME")
    private String preSalesBigAreaName;
    /** 售前小区名称 */
    @Column(name = "PRE_SALES_SMALL_AREA_NAME")
    private String preSalesSmallAreaName;
    /** 售后大区名称 */
    @Column(name = "AFTER_SALES_BIG_AREA_NAME")
    private String afterSalesBigAreaName;
    /** 售后小区名称 */
    @Column(name = "AFTER_SALES_SMALL_AREA_NAME")
    private String afterSalesSmallAreaName;
    /** 经销商代码 */
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    /** 批次号 */
    @Column(name = "BATCH_NO")
    private String batchNo;



    @OneToMany(mappedBy = "user")
    private List<BasUserRole> userRoleList = new ArrayList<BasUserRole>();

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BasUserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<BasUserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreSalesBigAreaCode() {
        return preSalesBigAreaCode;
    }

    public void setPreSalesBigAreaCode(String preSalesBigAreaCode) {
        this.preSalesBigAreaCode = preSalesBigAreaCode;
    }

    public String getPreSalesSmallAreaCode() {
        return preSalesSmallAreaCode;
    }

    public void setPreSalesSmallAreaCode(String preSalesSmallAreaCode) {
        this.preSalesSmallAreaCode = preSalesSmallAreaCode;
    }

    public String getAfterSalesBigAreaCode() {
        return afterSalesBigAreaCode;
    }

    public void setAfterSalesBigAreaCode(String afterSalesBigAreaCode) {
        this.afterSalesBigAreaCode = afterSalesBigAreaCode;
    }

    public String getAfterSalesSmallAreaCode() {
        return afterSalesSmallAreaCode;
    }

    public void setAfterSalesSmallAreaCode(String afterSalesSmallAreaCode) {
        this.afterSalesSmallAreaCode = afterSalesSmallAreaCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getAfterSalesSmallAreaName() {
        return afterSalesSmallAreaName;
    }

    public void setAfterSalesSmallAreaName(String afterSalesSmallAreaName) {
        this.afterSalesSmallAreaName = afterSalesSmallAreaName;
    }

    public String getPreSalesBigAreaName() {
        return preSalesBigAreaName;
    }

    public void setPreSalesBigAreaName(String preSalesBigAreaName) {
        this.preSalesBigAreaName = preSalesBigAreaName;
    }

    public String getPreSalesSmallAreaName() {
        return preSalesSmallAreaName;
    }

    public void setPreSalesSmallAreaName(String preSalesSmallAreaName) {
        this.preSalesSmallAreaName = preSalesSmallAreaName;
    }

    public String getAfterSalesBigAreaName() {
        return afterSalesBigAreaName;
    }

    public void setAfterSalesBigAreaName(String afterSalesBigAreaName) {
        this.afterSalesBigAreaName = afterSalesBigAreaName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }


    public Date getCredentialExpiredDate() {
        return credentialExpiredDate;
    }

    public void setCredentialExpiredDate(Date credentialExpiredDate) {
        this.credentialExpiredDate = credentialExpiredDate;
    }
}
