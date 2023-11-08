package cn.changge.car.esdoc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "test",type = "emp")
public class EmpDoc{
    @Id //文档id字段
    private Long id;

    @Field(type = FieldType.Keyword) //不分词
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text,analyzer = "ik_smart"
            ,searchAnalyzer = "ik_smart")
    private String intro;
}