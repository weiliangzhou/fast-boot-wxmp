package com.zwl.common.utils;

import java.io.*;

/**
 * @author 二师兄超级帅
 * @Title: Txt
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/4/914:29
 */
public class TxtUtil {
    public static String readTxt(InputStream is) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
