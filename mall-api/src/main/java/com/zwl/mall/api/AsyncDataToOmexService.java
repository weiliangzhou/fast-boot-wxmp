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
     * @param flowId
     * @param outOpenId
     * @param todayBTCInfo
     */
    void sendOMEX(Long flowId, BigDecimal todayBTCInfo, String outOpenId);

}
