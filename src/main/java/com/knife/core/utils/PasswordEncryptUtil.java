package com.knife.core.utils;
import cn.hutool.crypto.SecureUtil;

/**
 * 密码加密工具
 * 单向
 * 多角度触发一对多，增加密文的破解难度
 * @author geey
 * @date 2022/9/24 11:38
 */
public class PasswordEncryptUtil {
    /**
     * 字典
     */
    private final  static String[] DIC = {"a","b","s", "d","L", "e", "f", "g", "h", "i", "j","k","l","m","n",
            "o","p","q","r","7","@","s","t","c","v","w","x","y","z","u","A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J","K","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","?","S",
            "5","9","8","6"};

    /**
     * 加密
     * @param plaintext
     * @param l
     * @return
     */
    public static String encrypt(String plaintext, long l) {
        return encrypt(plaintext, l,DIC);
    }

    /**
     * 加密
     * @param plaintext
     * @param l
     * @param dic
     * @return
     */
    public static String encrypt(String plaintext, long l,String[] dic) {
        /**
         * 明文转换成md5，实现第一次一对多
         */
        String md5 = SecureUtil.md5(plaintext);
        char[] chars = md5.toCharArray();
        String result = "";
        for (char c : chars) {
            //c的ascii码
            int ascii = c;
            //余数
            int remainder = (int) (l % ascii);
            remainder = remainder % dic.length;
            result = result + dic[remainder];
        }
        return result;
    }

}
