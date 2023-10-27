package cn.changge.org.controller;

import cn.changge.base.utils.AjaxResult;
import cn.changge.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.controller
 * @Author: WangXi
 * @CreateTime: 2023-10-11  17:21
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping
    public AjaxResult findAll(){
        try {
            return AjaxResult.success(service.queryAll());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
