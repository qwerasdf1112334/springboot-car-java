package cn.changge.base.exception;

import cn.changge.base.enums.ResponseCode;

/**
 * 业务类的异常，系统已知的异常
 */
public class BusinessException extends RuntimeException{

    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ResponseCode code){
        super(code.getMessage());
        this.code = code.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
