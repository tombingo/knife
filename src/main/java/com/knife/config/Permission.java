package com.knife.config;

import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/14 19:43
 * @Version 1.0
 */
@Target({ElementType.METHOD}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
@Inherited
public @interface Permission {

    PermissionEnum needVerify() ;
}
