package com.knife.core.utils;

/**
 * @author geey
 * @date 2022/9/28 14:56
 */
public class FormatUtil {

    /**
     * 秒数转化为文本秒数方式
     * @param second 秒数
     * @return xx时xx分xx秒
     */
    public static String formatHMS(long second){
        /*
          计算小时
         */
        long hour = second/3600;

        long remainder = second%3600;
        /*
          计算分钟
         */
        long minute = remainder/60;

        /*
          余数就是秒
         */
        long s = remainder % 60;
        /*
          连接字符串
         */
        return hour + "时"+minute+"分"+s+"秒";
    }
}
