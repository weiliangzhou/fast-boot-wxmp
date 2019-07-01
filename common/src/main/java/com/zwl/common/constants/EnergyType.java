package com.zwl.common.constants;

/**
 * @author 二师兄超级帅
 */
public enum EnergyType {
    TYPE_1(1, 120, "注册赠送"),
    TYPE_2(2, 1, "每天分享app赠送1小时"),
    TYPE_3(3, 2, "每天登陆赠送2小时");

    private int index;
    private int value;
    private String desc;

    EnergyType(int index, int value, String desc) {
        this.index = index;
        this.value = value;
        this.desc = desc;
    }

    public static EnergyType getEnergyType(int index) {
        for (EnergyType energyType : EnergyType.values()) {
            if (energyType.getIndex() == index) {
                return energyType;
            }
        }
        return null;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
