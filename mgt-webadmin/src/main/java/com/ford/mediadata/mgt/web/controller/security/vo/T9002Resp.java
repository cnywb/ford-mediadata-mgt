/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9000Resp.java 15/10/19 下午3:53
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：管理人员 明细信息获取响应对象
 *
 * @author Create by ziv.hung on 15/10/19.
 * @since 1.0
 */
public class T9002Resp {
    private Long id;
    /** 后台登录用户名 */
    private String loginName;
    /** 后台登录密码 */
    private String password;
    /** 工号 */
    private String employeeNo;
    /** 照片 */
    private String imageUrl;
    /** 姓名 */
    private String name;
    /** 性别 */
    private String gender;
    /** 联系方式 */
    private String mobile;
    /** 在职模式 */
    private String jobType;
    /** 职务 */
    private String post;
    /** 出生日期 */
    private String birthDate;
    /** 身份证号码 */
    private String idCard;
    /** 民族 */
    private String nation;
    /** 籍贯 */
    private String birthplace;
    /** 毕业院校 */
    private String graduateSchool;
    /** 专业 */
    private String major;
    /** 学历 */
    private String education;
    /** 司龄 */
    private Integer joinYears;
    /** 家庭住址 */
    private String homeAddress;
    /** 现居住地址 */
    private String  currentAddress;
    /** 招聘途径 */
    private String joinWay;
    /** 合同编号 */
    private String contractNo;
    /** 合同期限 */
    private String contractLimits;
    /** 是否购买保险 */
    private Boolean isInsurance;
    /** 保险合同日期 */
    private String insuranceDate;
    /** 续保日期 */
    private String renewalDate;
    /** 相关证书 */
    private String certificate;
    /** 入职时间 */
    private String joinDate;
    /** 人员状态 */
    private String status;
    /** 离职时间 */
    private String quitDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getJoinYears() {
        return joinYears;
    }

    public void setJoinYears(Integer joinYears) {
        this.joinYears = joinYears;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(String joinWay) {
        this.joinWay = joinWay;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractLimits() {
        return contractLimits;
    }

    public void setContractLimits(String contractLimits) {
        this.contractLimits = contractLimits;
    }

    public Boolean getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(Boolean isInsurance) {
        this.isInsurance = isInsurance;
    }

    public String getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(String insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuitDate() {
        return quitDate;
    }

    public void setQuitDate(String quitDate) {
        this.quitDate = quitDate;
    }
}
