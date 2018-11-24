package com.ford.mediadata.mgt.entity.im;


import javax.persistence.*;

import com.ford.mediadata.mgt.entity.VersionEntity;

/**
 * 字典大类
 * Created by ziv.hung on 15/10/13.
 *
 * @since 1.0
 */
@Entity
@Table(name = "MD_IM_CODE_TYPE")
public class ImCodeType extends VersionEntity {
    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_IM_CODE_TYPE")
    @SequenceGenerator(name = "SEQ_MD_IM_CODE_TYPE",allocationSize=1,sequenceName = "SEQ_MD_IM_CODE_TYPE")
    protected Long id;
    /**
     * 字典大类代码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 类型代码，用来区分各种字典分类
     */
    @Column(name = "TYPE_CODE")
    private String typeCode;
    /**
     * 中文名称
     */
    @Column(name = "NAME_CN")
    private String nameCn;
    /**
     * 英文名称
     */
    @Column(name = "NAME_EN")
    private String nameEn;
    /**
     * 描述
     */
    @Column(name = "COMMENT_")
    private String comment;
    /**
     * 是允许客户修改 1 可以 0 不可以
     */
    @Column(name = "ENABLE_")
    private boolean enable = true;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
