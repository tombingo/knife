package com.knife.config.interceptor;
import com.alibaba.fastjson2.JSONObject;
import com.knife.config.Access;
import com.knife.config.LoginUser;
import com.knife.config.RequestLogArgsEnum;
import com.knife.core.AccessContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用类请求日志记录
 * @Author geey
 * @Date 2023/8/2 17:04
 * @Version 1.0
 */
@Component
@Log4j2
public class RequestLogIntercept implements HandlerInterceptor {

    /**
     * 标记请求是否成功执行完成
     */
    public static final ThreadLocal<Map<RequestLogArgsEnum,Object>> requestLogContext =
            new ThreadLocal<>();

    /**
     * 注入对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<RequestLogArgsEnum,Object> map = new HashMap<>();
        map.put(RequestLogArgsEnum.success,false);
        requestLogContext.set(map);
        return true;
    }

    /**
     * 记录操作结果
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<RequestLogArgsEnum,Object> record = requestLogContext.get();
        record.put(RequestLogArgsEnum.success,true);
        requestLogContext.set(record);
    }

    /**
     * 完成操作日志的传递、记录
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Map<RequestLogArgsEnum,Object> record = requestLogContext.get();
        /**
         * 请求源
         */
        Access access = AccessContext.getAccessMeta();
        record.put(RequestLogArgsEnum.requestId,access.getRequestId());
        record.put(RequestLogArgsEnum.ip,access.getIp());
        LoginUser user = access.getUser();
        if(user != null){
            record.put(RequestLogArgsEnum.user,user.getUserId());
        }
        /**
         * 运行路径
         */
        if(handler instanceof HandlerMethod ) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method =  handlerMethod.getMethod();
            String className = method.getDeclaringClass().getName();
            record.put(RequestLogArgsEnum.className,className);
            String methodName = method.getName();
            record.put(RequestLogArgsEnum.methodName,methodName);
        }
        /**
         * 请求数据
         */
        record.put(RequestLogArgsEnum.url,request.getRequestURI());
        record.put(RequestLogArgsEnum.urlParams,request.getQueryString());
        log.info(JSONObject.toJSONString(record));
        requestLogContext.remove();

    }
}
