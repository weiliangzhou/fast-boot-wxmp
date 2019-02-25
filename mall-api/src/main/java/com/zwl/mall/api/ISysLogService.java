
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.SysLog;
import org.aspectj.lang.JoinPoint;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-02-25
 */
public interface ISysLogService extends IService<SysLog> {
    public void put(JoinPoint joinPoint, String methodName, String module, String description);


}
