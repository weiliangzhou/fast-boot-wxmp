package com.zwl.common.utils.excel; /**
 * @author 二师兄超级帅
 * @Title: 判断对象是否为空或null
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/4/814:51
 */

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EmptyUtils {
    /**
     * 判断list不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotNull(List list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        else if (obj instanceof Map) return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }


}
