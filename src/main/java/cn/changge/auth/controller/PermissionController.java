package cn.changge.auth.controller;

import cn.changge.auth.service.IPermissionService;
import cn.changge.auth.domain.Permission;
import cn.changge.auth.query.PermissionQuery;
import cn.changge.base.annotation.ChangGePermission;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")

public class PermissionController {
    @Autowired
    public IPermissionService permissionService;


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AjaxResult json(@RequestBody PermissionQuery query)
    {
        try {
            PageInfo<Permission> pageList = permissionService.queryPage(query);
            return AjaxResult.success(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
     * 查询权限树
     *

     */
    @GetMapping
    public AjaxResult queryAll()
    {
        try {
            List<Permission> permissions = permissionService.queryAll();
            return AjaxResult.success(permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }



}
