package com.zwl.common.constants;

/**
 * @author 二师兄
 * @Title: 注册来源
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/6/1316:11
 */
public enum RegisterFrom {
    H5(1, "H5"),
    ANDROID(2, "Android"),
    IOS(3, "IOS"),
    XCX(4, "小程序");
    private int index;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    RegisterFrom(int index, String name) {
        this.index = index;
        this.name = name;
    }


}

