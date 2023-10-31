package cn.changge.base.utils;

import cn.changge.base.enums.ResponseCode;

public class AxiosResult {

    private Integer code = ResponseCode.RESPONSE_CODE_200.getCode();     // 默认200，表示操作成功
    private Boolean success = true;    // 表示当前操作是成功还是失败 true:成功 false:失败
    private String message = ResponseCode.RESPONSE_CODE_200.getMessage();     // 失败的原因
    private Object data;   // 返回给前端的查询结果对象,是一定不能把类型写死的,因为这个AxiosResult类是公共的,每个controller都是返回AxiosResult类的

    /**
     * 公共的静态方法,用来获取AxiosResult对象的
     * @return
     */
    public static AxiosResult me(){
        return new AxiosResult();
    }

    public Integer getCode() {
        return code;
    }

    public AxiosResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public AxiosResult setSuccess(Boolean success) {
        this.success = success;
        return this;    // this它的意思是:谁调用我我就是谁
    }

    public String getMessage() {
        return message;
    }

    public AxiosResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AxiosResult setData(Object data) {
        this.data = data;
        return this;
    }
}