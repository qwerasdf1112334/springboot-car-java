package cn.changge.base.enums;

public enum  ResponseCode {
    RESPONSE_CODE_200(200,"操作成功!"),
    RESPONSE_CODE_400(400,"参数错误!"),
    RESPONSE_CODE_400001(400001,"两次密码不一致,请检查后重新提交!"),
    RESPONSE_CODE_400002(400002,"店铺已存在,请检查后重新提交!"),
    RESPONSE_CODE_500(500,"网络异常!请稍后重试!");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}