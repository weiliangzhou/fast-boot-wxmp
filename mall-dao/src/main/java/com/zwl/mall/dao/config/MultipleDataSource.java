package com.zwl.mall.dao.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 二师兄超级帅
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    public static final Map<DataSourceEnum, List<String>> METHOD_TYPE_MAP = new HashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

    void setMethodType(DataSourceEnum type, String content) {
        List<String> list = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(type, list);
    }
}
