package core.app.zh.com.core.utils;

/**
 * author : dayezi
 * data :2019/4/11
 * description:自定义工具类
 */
public class OtherUtil {

    /**
     * 特定字符补齐字符串缺少情况
     * @param value 需补齐字符串
     * @param length 字符串完整长度
     * @param obj  缺少位置添加的字符串
     * @return
     */
    public static String makeUp(String value, int length, char obj) {
        StringBuilder builder = new StringBuilder(value);
        if (value.length() < length) builder.insert(0, obj);
        return builder.toString().length() < length ? makeUp(builder.toString(), length, obj) : builder.toString();
    }

}