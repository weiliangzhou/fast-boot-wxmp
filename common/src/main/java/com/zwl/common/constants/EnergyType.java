package com.zwl.common.constants;

/**
 * @author 二师兄超级帅
 */
public enum EnergyType {
    TYPE_0(0, 120, "新用户奖励"),
    CONSUME_1(-1, 1, "挖矿扣除");

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

    public static String getName(int index) {
        for (EnergyType energyType : EnergyType.values()) {
            if (energyType.getIndex() == index) {
                return energyType.getDesc();
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
