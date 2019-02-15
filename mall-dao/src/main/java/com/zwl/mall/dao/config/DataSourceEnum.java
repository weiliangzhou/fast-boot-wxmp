package com.zwl.mall.dao.config;

/**
 * @author 二师兄超级帅
 */
public enum DataSourceEnum {

    Write("write"), Read("read");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
