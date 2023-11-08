package cn.changge.base.utils;

import cn.changge.car.domain.vo.BucketVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.utils
 * @Author: WangXi
 * @CreateTime: 2023-10-10  18:46
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {
    //总条数
    private Long total=0l;
    //当前页数据
    private List<T> data=new ArrayList<T>();
    private Map<String,List<BucketVo>> aggs ;

    public PageInfo(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}
