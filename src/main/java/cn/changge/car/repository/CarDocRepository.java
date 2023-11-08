package cn.changge.car.repository;

import cn.changge.car.esdoc.CarDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDocRepository extends ElasticsearchRepository<CarDoc,Long> {
}
