package com.knife.config;

import com.knife.core.AccessContext;
import com.knife.core.BusinessException;
import com.knife.core.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author 假老练
 */
@ControllerAdvice
@Log4j2
public class ExceptionHandle {
    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     *
     * @param e
     * @return
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        //e.printStackTrace();
        Access access = AccessContext.getAccessMeta();
        String n  = "异常编号："+ access.getRequestId();
        log.error(n+e.getMessage());
        /**
         * 业务异常
         */
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            return R.error(businessException.getCode(), businessException.getMsg());

        }
        /**
         * 数据校验异常
         */
        else if(e instanceof BindException){
            BindException ex = (BindException) e;
            // 拼接错误
            StringBuilder detailMessage = new StringBuilder();
            for (ObjectError objectError : ex.getAllErrors()) {
                // 使用 ; 分隔多个错误
                if (detailMessage.length() > 0) {
                    detailMessage.append(";");
                }
                // 拼接内容到其中
                detailMessage.append(objectError.getDefaultMessage());
            }
            return R.error(detailMessage.toString());
        }
        /**
         * 主键重复
         */
        else if( e instanceof DuplicateKeyException){
            return R.error("主键重复");
        }
        /**
         * 未知异常
         */
        else{
            return R.error(-1,n);
        }
    }
}