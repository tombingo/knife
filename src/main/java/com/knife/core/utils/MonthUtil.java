package com.knife.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 自动获取月份的开始日和结束日
 *
 * @author geey
 * @date 2022/11/9 14:53
 */
@Data
public class MonthUtil implements Serializable {

    private String separator;
    /**
     * 格式 2022-10
     */
    private String month;
    /**
     * 开始日
     */
    private Date start;
    /**
     * 结束日
     */
    private Date end;


    public MonthUtil() {
        this(null);
    }

    public MonthUtil(String month) {
        this(month, null);
    }

    public MonthUtil(String month, String separator) {
        this.separator = separator == null ?  "-" : separator;
        Date monthDate;
        if (StrUtil.isBlank(month)) {
            monthDate = DateUtil.date();
        } else {
            /**
             * 转换成统一格式
             */
            String temp = "";
            for (char c : month.toCharArray()) {
                if (c < 0 || c > 9) {
                    temp = temp + this.separator;
                } else {
                    temp = temp + c;
                }
            }
            monthDate = DateUtil.parse(temp + this.separator + "01");
        }
        this.month = DateUtil.year(monthDate) + this.separator + (DateUtil.month(monthDate) + 1);
        this.start = DateUtil.beginOfMonth(monthDate);
        this.end = DateUtil.endOfMonth(monthDate);
    }
}
