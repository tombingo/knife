package com.knife.config;
import com.knife.core.AccessContext;
import com.knife.core.BusinessException;
import com.knife.core.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * qps异常
     */
    @ExceptionHandler(value = QpsException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(QpsException e){
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(e.getMessage());
    }
    /**
     * 业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public R handleException(BusinessException e){
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 数据校验异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public R handleException(BindException e){
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : e.getAllErrors()) {
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
     * 数据库执行异常
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public R handleException(DuplicateKeyException e){
        String n  = log(e);
        return R.error("主键重复,"+n);
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handleException(Exception e){
        String n  = log(e);
        return R.error(-1,n);
    }

    private String  log(Exception e){
        Access access = AccessContext.getAccessMeta();
        String n  = "异常编号--"+ access.getRequestId()+"--";
        log.error(n+e.getMessage());
        return n;
    }
}