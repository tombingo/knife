package com.knife.config.aop;

import com.alibaba.fastjson2.JSONObject;
import com.knife.config.Operate;
import com.knife.config.RequestLogArgsEnum;
import com.knife.config.interceptor.RequestLogIntercept;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geey
 * @date 2022/9/24 22:52
 */
@Aspect
@Component
@Log4j2
public class OperateAspect {

    /**
     * 操作日志切入点
     */
    @Pointcut("@annotation(com.knife.config.Operate)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void myBefore(JoinPoint joinPoint) {
        Map<RequestLogArgsEnum,Object> requestLog = RequestLogIntercept.requestLogContext.get();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Operate operate = methodSignature.getMethod().getAnnotation(Operate.class);
        requestLog.put(RequestLogArgsEnum.operateTag,operate.tag());
        requestLog.put(RequestLogArgsEnum.operateType,operate.type());
        requestLog.put(RequestLogArgsEnum.operateDescription,operate.description());
        if (operate.logMethodArgs()) {
            String[] argNames = methodSignature.getParameterNames();
            Object[] args = joinPoint.getArgs();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < argNames.length; i++) {
                map.put(argNames[i], args[i]);
            }
            requestLog.put(RequestLogArgsEnum.methodArgs,JSONObject.toJSONString(map));
            RequestLogIntercept.requestLogContext.set(requestLog);
        }
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void myAfterReturning(JoinPoint joinPoint, Object result) {
        Map<RequestLogArgsEnum,Object> requestLog = RequestLogIntercept.requestLogContext.get();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Operate operate = methodSignature.getMethod().getAnnotation(Operate.class);
        if (operate.logResultData()) {
            requestLog.put(RequestLogArgsEnum.resultData,JSONObject.toJSONString(result));
            RequestLogIntercept.requestLogContext.set(requestLog);
        }
    }
}
