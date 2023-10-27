package cn.changge.org.mapper;

import cn.changge.APP;
import cn.changge.org.domain.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class DepTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    public void test1(){
        departmentMapper.queryAll().forEach(System.out::println);
    }
    @Test
    public void test2(){
        Department department = departmentMapper.queryById(7);

    }

}
