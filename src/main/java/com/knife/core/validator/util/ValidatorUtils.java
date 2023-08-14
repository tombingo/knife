package com.knife.core.validator.util;

/**
 * @author 86151
 */
public class ValidatorUtils
{
    // 根据Unicode编码完美的判断中文汉字和符号
   public static  boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
       return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
               || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
               || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
               || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
               || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
               || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
               || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;

    }
}
