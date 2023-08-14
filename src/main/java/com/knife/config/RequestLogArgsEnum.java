package com.knife.config;

/**
 * <p>
 * 操作日志明细
 * </p>
 */

public enum RequestLogArgsEnum {
    requestId,//请求Id
    success,//请求是否成功
    ip,//请求IP
    user,//用户身份
    className,//运行的class
    methodName,//运行的方法名
    url,//请求地址
    urlParams,//请求的url参数
    createTime,//创建时间
    /********************************************细化的操作和数据****************************************************/
    operateTag,//操作模块
    operateType,//操作类型
    operateDescription,//操作描述
    methodArgs,//方法的参数值
    resultData,//返回的结果
}
