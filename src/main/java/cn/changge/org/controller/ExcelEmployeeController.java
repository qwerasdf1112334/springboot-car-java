package cn.changge.org.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.ExcelUtil;
import cn.changge.org.domain.ExcelEmployee;
import cn.changge.org.service.IExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.controller
 * @Author: WangXi
 * @CreateTime: 2023-10-21  10:08
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/excel")
public class ExcelEmployeeController {
    @Autowired
    private IExcelService service;
    @PostMapping()
    public AjaxResult importEmployees(@RequestParam("file") MultipartFile file)  {
        ImportParams params = new ImportParams();
        // 删掉表格的表头行（如果导入有表头的话）
        params.setHeadRows(1);

        //表格标题行数,默认0
        params.setTitleRows(1);


        try {
            List<ExcelEmployee> list = ExcelImportUtil.importExcel(file.getInputStream(), ExcelEmployee.class, params);


            service.batchInsertEmployees(list);

            return AjaxResult.success(list);

        } catch (Exception e) {

            return AjaxResult.error("导入失败！"+e.getMessage());
        }
    }




    @GetMapping()
    public void exportEmployees(HttpServletResponse response) throws Exception {


        try {
            // 从数据库中查询员工数据
            List<ExcelEmployee> employees = service.queryAll();
            // 导出Excel
            // 设置响应头
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"t_org_employee.xls\"");
            ExcelUtil.exportExcel(employees, "员工列表", "员工信息", ExcelEmployee.class, "t_org_employee", response);

        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
            response.setContentType("text/plain");
            response.getWriter().write("导出失败");
        }

    }
}
