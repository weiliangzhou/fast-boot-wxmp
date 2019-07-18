package com.zwl.common.constants;

/**
 * @author 二师兄超级帅
 */
public enum TaskType {
    TYPE_1(1, "一次性任务"),
    TYPE_2(2, "每日"),
    TYPE_3(3, "仅展示");

    private Integer type;
    private String desc;

    TaskType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static TaskType getTaskType(Integer type) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getType().intValue() == type.intValue()) {
                return taskType;
            }
        }
        return null;
    }

    public static String getName(Integer type) {
        for (TaskType taskType : TaskType.values()) {
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
