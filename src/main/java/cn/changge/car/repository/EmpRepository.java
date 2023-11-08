package cn.changge.car.repository;

import cn.changge.car.esdoc.EmpDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.car.repository
 * @Author: WangXi
 * @CreateTime: 2023-11-02  19:04
 * @Description: TODO
 * @Version: 1.0
 */
public interface EmpRepository extends ElasticsearchRepository<EmpDoc,Long> {
}
