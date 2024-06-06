package com.magape.model.domain;


/**
 * NFT Attributes
 */
public class Attributes {

    /**
     * 属性名称
     */
    private String traitType;

    /**
     * 属性对应编码
     */
    private String code;

    /**
     * 属性值
     */
    private Object value;

    /**
     * 展示方式
     */
    private String displayType;

    public String getTraitType() {
        return traitType;
    }

    public void setTraitType(String traitType) {
        this.traitType = traitType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
}

