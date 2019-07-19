package com.zwl.mall.api;

import java.math.BigDecimal;

/**
 * 异步发送数据到omex
 *
 * @author 二师兄
 */
public interface AsyncDataToOmexService {
    /**
     * 异步发送数据到omex
     *
     * @param todayBTCInfo
     * @param outOpenId
     */
    void sendOMEX(BigDecimal todayBTCInfo, String outOpenId);

}
