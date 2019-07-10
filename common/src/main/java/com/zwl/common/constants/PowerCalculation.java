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
    public static BigDecimal getResult(long second, int power) {
        OutputEfficiency outputEfficiency = OutputEfficiency.getOutputEfficiency(power);
        String efficiency = outputEfficiency.getEfficiency();
        return BigDecimalUtil.bigMul2(efficiency, "" + second, 10);

    }

    public static void main(String[] args) {
        NumberFormat nf = new DecimalFormat("##.##########");
        System.out.println("500GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 500)));
        System.out.println("500GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 500)));
//        System.out.println("500GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 500)));
//        System.out.println("500GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 500)));
//        System.out.println("500GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 500)));
//        System.out.println("500GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 500)));

        System.out.println("600GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 600)));
//        System.out.println("600GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 600)));
//        System.out.println("600GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 600)));
//        System.out.println("600GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 600)));
//        System.out.println("600GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 600)));
//        System.out.println("600GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 600)));

        System.out.println("700GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 700)));
//        System.out.println("700GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 700)));
//        System.out.println("700GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 700)));
//        System.out.println("700GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 700)));
//        System.out.println("700GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 700)));
//        System.out.println("700GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 700)));

        System.out.println("800GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 800)));
//        System.out.println("800GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 800)));
//        System.out.println("800GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 800)));
//        System.out.println("800GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 800)));
//        System.out.println("800GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 800)));
//        System.out.println("800GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 800)));

        System.out.println("900GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 900)));
//        System.out.println("900GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 900)));
//        System.out.println("900GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 900)));
//        System.out.println("900GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 900)));
//        System.out.println("900GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 900)));
//        System.out.println("900GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 900)));

        System.out.println("1000GH/S:每天产出" + nf.format(getResult(60 * 60 * 24, 1000)));
//        System.out.println("1000GH/S:10天产出" + nf.format(getResult(60 * 60 * 24 * 10, 1000)));
//        System.out.println("1000GH/S:20天产出" + nf.format(getResult(60 * 60 * 24 * 20, 1000)));
//        System.out.println("1000GH/S:45天产出" + nf.format(getResult(60 * 60 * 24 * 45, 1000)));
//        System.out.println("1000GH/S:180天产出" + nf.format(getResult(60 * 60 * 24 * 180, 1000)));
//        System.out.println("1000GH/S:365天产出" + nf.format(getResult(60 * 60 * 24 * 365, 1000)));
    }


}
