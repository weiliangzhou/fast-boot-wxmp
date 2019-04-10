package com.zwl.common.utils.excel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 二师兄超级帅
 * @Title: 微信过滤表情
 * @Description: TODO
 * @date 2018/11/318:50
 */
public class EmojiFilter {
    private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("");
            return source;
        }
        return source;
    }
}
