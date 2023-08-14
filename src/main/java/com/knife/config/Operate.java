package com.knife.config;

import java.lang.annotation.*;

/**
 * @author geey
 * @date 2022/9/24 22:51
 */
@Target({ElementType.METHOD}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
@Inherited
public @interface Operate {
    String tag() default ""; // 操作模块
    OperateType type() default OperateType.添加;  // 操作类型
    String description() default "";  // 操作说明

    /**
     * 记录执行方法时的参数值
     */
    boolean logMethodArgs() default  false;

    /**
     * 记录方法执行后的结果
     */
    boolean logResultData() default  false;
}
