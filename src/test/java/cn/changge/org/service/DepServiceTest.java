package cn.changge.org.service;

import cn.changge.APP;
import cn.changge.auth.service.IPermissionScanService;
import cn.changge.base.utils.PageInfo;
import cn.changge.org.domain.Department;
import cn.changge.org.domain.ExcelEmployee;
import cn.changge.org.vo.DepartmentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.mapper
 * @Author: WangXi
 * @CreateTime: 2023-10-09  15:21
 * @Description: TODO
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class DepServiceTest {
    @Autowired
    private IDempartmentService service;
    @Autowired
   private IExcelService excelService;
    @Test
    public void test1(){
        service.queryAll().forEach(System.out::println);
    }
    @Test
    public void test2(){
        service.delete(43);
    }

    @Test
    public void pageList() {
        DepartmentVo query = new DepartmentVo();
        query.setCurrentPage(1);
        query.setPageSize(2);
        PageInfo<Department> pageList = service.queryPage(query);
        System.out.println("总条数:"+pageList.getTotal());
        System.out.println("当前页数据:");
        pageList.getData().forEach(System.out::println);
    }
    @Autowired
    private   IEmployeeService employeeService;
    @Test
    public void empFindAll(){
        employeeService.queryAll().forEach(System.out::println);
    }
    @Autowired
    private IPermissionScanService scanService;
    @Test
    public void test5(){
        System.out.println("---------------");
        scanService.scan();
        System.out.println("++++++++++++++");
    }
    @Test
    public void  test6(){
        excelService.queryAll().forEach(System.out::println);
    }
    @Test
    public void test7(){
        List<ExcelEmployee> excelEmployees = new ArrayList<>();



        ExcelEmployee excelEmployee2 = new ExcelEmployee("user1", "password1", "user1@example.com", "image1.jpg", 25, new Department(), "123456789");
        ExcelEmployee excelEmployee = new ExcelEmployee("user2", "password2", "user2@example.com", "image2.jpg", 30, new Department(), "987654321");
        ExcelEmployee excelEmployee1 = new ExcelEmployee("user3", "password3", "user3@example.com", "image3.jpg", 28, new Department(), "111222333");
        excelEmployees.add(excelEmployee);
        excelEmployees.add(excelEmployee2);
        excelEmployees.add(excelEmployee1);
        excelService.batchInsertEmployees(excelEmployees);
    }


    }


