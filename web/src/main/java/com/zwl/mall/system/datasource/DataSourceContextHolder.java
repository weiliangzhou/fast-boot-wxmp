package com.zwl.mall.system.datasource;

/**
 * 数据源处理器
 *
 * @author 二师兄超级帅
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new InheritableThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param db
     */
    public static void setDataSource(String db) {
        CONTEXT_HOLDER.set(db);
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
