package com.zwl.common.utils;

import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created  on 2019/6/21.
 *
 * @author 二师兄超级帅
 */
@Slf4j
public class SignUtil {
    public static void checkSign(String data, String key, String sign) {
        if (StringUtils.isBlank(data) ||
                StringUtils.isBlank(key) ||
                StringUtils.isBlank(sign)
        ) {
            throw new BizException(ErrorEnum.SIGN_ERROR);
        }
        String md5Sign = EncryptUtil.getInstance().MD5(data, key);
        log.info(md5Sign);
        boolean equalsResult = sign.equals(md5Sign);
        if (!equalsResult) {
            throw new BizException(ErrorEnum.SIGN_ERROR);
        }
    }

}
