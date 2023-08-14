package com.knife.core;

/**
 * 存储当前http请求信息
 * @author 假老练
 * @date 2021.9.30 11:38
 */
public class AccessContext {

    /**
     * 线程变量，存放user实体类信息，即使是静态的与其他线程也是隔离的
     *
     */
    private static final ThreadLocal<AccessMeta> accessContext =
            new ThreadLocal<>();

    /**
     * 设置线程变量
     */
    public  static  void setAccessMeta(AccessMeta accessMeta)  {
         accessContext.set(accessMeta);
    }

    /**
     * 获取线程变量
     */
    public static  <A extends AccessMeta> A  getAccessMeta()  {
        return (A) accessContext.get();
    }

    //清除线程变量
    public static void remove() {
        accessContext.remove();
    }
}
