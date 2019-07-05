package com.zwl.common.constants;

/**
 * @Date: 2019/7/5 14:00
 * @Author: 二师兄超级帅
 * @Description: 产出率
 */

public enum OutputEfficiency {
    TYPE_500(500, "0.0000000011"),
    TYPE_600(600, "0.0000000013"),
    TYPE_700(700, "0.0000000016"),
    TYPE_800(800, "0.0000000018"),
    TYPE_900(900, "0.0000000020"),
    TYPE_1000(1000, "0.0000000022");

    private int power;
    private String efficiency;

    OutputEfficiency(int power, String efficiency) {
        this.power = power;
        this.efficiency = efficiency;
    }

    public static OutputEfficiency getOutputEfficiency(int power) {
        for (OutputEfficiency outputEfficiency : OutputEfficiency.values()) {
            if (outputEfficiency.getPower() == power) {
                return outputEfficiency;
            }
        }
        return null;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
}
