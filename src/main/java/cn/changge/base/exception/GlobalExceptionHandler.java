package cn.changge.base.exception;

import cn.changge.base.enums.ResponseCode;
import cn.changge.base.utils.*;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.AxiosResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice   // 在执行controller方法的前/后执行该代码
public class GlobalExceptionHandler {
    /**
     * 处理业务异常类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public AxiosResult businessExceptionHandler(BusinessException e){
        e.printStackTrace();
        return AxiosResult.me().setSuccess(false).setCode(e.getCode()).setMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage());
            sb.append("为空,");
        }
        String message = sb.substring(0,sb.length()-1);

        return AxiosResult.me().setSuccess(false).setCode(ResponseCode.RESPONSE_CODE_400.getCode())
                .setMessage(message);
    }
    /*
     @ExceptionHandler 拦截指定的异常
  */
    @ExceptionHandler(NullPointerException.class)
    public AxiosResult nullExcption(NullPointerException e){
        e.printStackTrace();
        return AxiosResult.me().setCode(ResponseCode.RESPONSE_CODE_500.getCode())
                .setMessage(ResponseCode.RESPONSE_CODE_500.getMessage()).setSuccess(false);
    }

    @ExceptionHandler(Exception.class)
    public AxiosResult exceptionHandler(Exception e){
        e.printStackTrace();
        return AxiosResult.me().setSuccess(false).setCode(ResponseCode.RESPONSE_CODE_500.getCode())
                .setMessage("网络异常!请稍后重试!");
    }
}
