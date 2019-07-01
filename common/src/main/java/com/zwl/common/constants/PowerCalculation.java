package com.zwl.common.constants;

import com.zwl.common.utils.BigDecimalUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author 二师兄
 * @desc 产出效率
 * @date 2019/7/1.
 */

public class PowerCalculation {
    /**
     * 每秒产出btc
     */
    public static final String secondPower = "0.0000000011";

    public static BigDecimal getResult(long second) {
        return BigDecimalUtil.bigMul2(secondPower, "" + second, 10);

    }

    public static void main(String[] args) {
        NumberFormat nf = new DecimalFormat("##.##########");
        System.out.println(nf.format(getResult(1)));
    }


}
