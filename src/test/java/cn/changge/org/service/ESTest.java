package cn.changge.org.service;

import cn.changge.APP;
import cn.changge.car.esdoc.EmpDoc;
import cn.changge.car.repository.EmpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.org.service
 * @Author: WangXi
 * @CreateTime: 2023-11-02  19:08
 * @Description: TODO
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class ESTest {
    @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private EmpRepository empRepository;
    //先创建索引库，在自定义映射
    @Test
    public  void testPreHandle() throws Exception{
        System.out.println(elasticsearchTemplate);
        elasticsearchTemplate.createIndex(EmpDoc.class);//创建索引库
        elasticsearchTemplate.putMapping(EmpDoc.class);//做定义映射
        //crud
    }

    @Test
    public  void testAdd() throws Exception{
        EmpDoc empDoc = new EmpDoc(1L,"zs",18,"this is a shabi");
        System.out.println(empRepository.getClass());
        empRepository.save(empDoc);
    }
    @Test
    public  void testGetOne() throws Exception{

        Optional<EmpDoc> optional = empRepository.findById(1L);
        EmpDoc empDoc = optional.get();
        System.out.println(empDoc);

    }
    @Test
    public  void testUpdate() throws Exception{
        EmpDoc empDoc = new EmpDoc(1L,"ls",null,"this is a sb");
        System.out.println(empRepository.getClass());
        empRepository.save(empDoc);
    }
    @Test
    public  void testDel() throws Exception{
        empRepository.deleteById(1L);
    }
    @Test
    public  void testDsl() throws Exception{

    }
}

