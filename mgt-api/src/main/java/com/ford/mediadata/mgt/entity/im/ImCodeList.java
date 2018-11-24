package com.ford.mediadata.mgt.entity.im;

import javax.persistence.*;

import com.ford.mediadata.mgt.entity.VersionEntity;

/**
 * 字典小类
 * Created by ziv.hung on 15/10/13.
 * @since 1.0
 */
@Entity
@Table(name = "MD_IM_CODE_LIST")
public class ImCodeList extends VersionEntity {
    /** 物理主键 */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SEQ_MD_IM_CODE_LIST")
    @SequenceGenerator(name = "SEQ_MD_IM_CODE_LIST",allocationSize=1,sequenceName = "SEQ_MD_IM_CODE_LIST")
    protected Long id;
    /** 编码值 */
    @Column(name = "CODE")
    private String code;
    /** 字典大类代码 对应 字典大类的 CODE */
    @Column(name = "TYPE_CODE")
    private String typeCode;
    /** 编码中文名称 */
    @Column(name = "NAME_CN")
    private String nameCn;
    /** 编码英文名称 */
    @Column(name = "NAME_EN")
    private String nameEn;
    /** 编码排序 */
    @Column(name = "SORT_NO")
    private int sortNo;
    /** 默认选择 */
    @Column(name = "SELECTED")
    private Boolean selected;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
