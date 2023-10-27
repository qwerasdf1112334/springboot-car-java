package cn.changge.org.controller;

import cn.changge.base.annotation.ChangGePermission;
import cn.changge.org.service.IOrgEmployeeService;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.query.OrgEmployeeQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orgEmployee")
@ChangGePermission(name = "员工管理")
public class OrgEmployeeController {
    @Autowired
    public IOrgEmployeeService orgEmployeeService;


    /**
     * 保存和修改公用的
     * @param orgEmployee  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    @ChangGePermission(name = "员工保存和新增")
    public AjaxResult addOrUpdate(@RequestBody OrgEmployee orgEmployee){
        try {
            if( orgEmployee.getId()!=null)
                orgEmployeeService.update(orgEmployee);
            else
                orgEmployeeService.insert(orgEmployee);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    @ChangGePermission(name = "员工删除通过id")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            orgEmployeeService.delete(id);
            return AjaxResult.success();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.error();
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    @ChangGePermission(name = "员工获取通过id")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            OrgEmployee orgEmployee = orgEmployeeService.queryById(id);
            return AjaxResult.success(orgEmployee);
        } catch (Exception e) {
            e.printStackTrace();
           return AjaxResult.error();
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    @ChangGePermission(name = "员工查找所有")
    public AjaxResult list(){

        try {
            List< OrgEmployee> list = orgEmployeeService.queryAll();
            return AjaxResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    @ChangGePermission(name = "员工分页查询")
    public AjaxResult pageList(@RequestBody OrgEmployeeQuery query)
    {
        try {
            PageInfo<OrgEmployee> pageList = orgEmployeeService.queryPage(query);
            return AjaxResult.success(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
   * 批量删除
   */
    @PatchMapping
    @ChangGePermission(name = "员工批量删除")
    public AjaxResult batchDelete(@RequestBody List<Long> ids)
    {
        try {
                orgEmployeeService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
