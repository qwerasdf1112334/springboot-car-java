package cn.changge.base.utils;

import lombok.Data;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.utils
 * @Author: WangXi
 * @CreateTime: 2023-10-09  16:47
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class AjaxResult {
    private Boolean success;
    private Object data;
    private String msg;

    /**
     * @description:
     * @author: WangXi
     * @date: 2023/10/9 16:53
     * @param: []
     * @return: cn.changge.base.utils.AjaxResult
     **/
    public static AjaxResult success(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success=true;
        ajaxResult.msg="操作成功";
        return ajaxResult;
    }
    public static AjaxResult success(String msg){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success=true;
        ajaxResult.msg=msg;
        return ajaxResult;
    }
    public static AjaxResult success(String msg,Object data){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success=true;
        ajaxResult.msg=msg;
        ajaxResult.data=data;
        return ajaxResult;
    }
    public static AjaxResult success(Object data){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.data=data;
        ajaxResult.success=true;
        ajaxResult.msg="操作成功";
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success=false;
        ajaxResult.msg="操作失败";
        return ajaxResult;
    }
    public static AjaxResult error(String msg){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success=false;
        ajaxResult.msg=msg;
        return ajaxResult;
    }

}
