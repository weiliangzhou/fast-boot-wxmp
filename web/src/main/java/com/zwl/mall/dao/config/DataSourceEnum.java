package com.zwl.mall.dao.config;

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
