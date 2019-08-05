package com.zwl.common.constants;

/**
 * @author 二师兄超级帅
 */
public enum EnergyType {
    TYPE_1(1, "获得"),
    TYPE_2(-1, "挖矿扣除");

    private Integer type;
    private String desc;

    EnergyType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static EnergyType getTaskType(Integer type) {
        for (EnergyType taskType : EnergyType.values()) {
            if (taskType.getType().intValue() == type.intValue()) {
                return taskType;
            }
        }
        return null;
    }

    public static String getName(Integer type) {
        for (EnergyType taskType : EnergyType.values()) {
            if (taskType.getType().intValue() == type.intValue()) {
                return taskType.getDesc();
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
