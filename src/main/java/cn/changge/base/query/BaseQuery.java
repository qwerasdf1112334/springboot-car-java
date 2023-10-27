package cn.changge.base.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseQuery {
    private String keyword;
    private Integer currentPage;
    private Integer pageSize;

    public int getStart(){
        return (currentPage-1)*pageSize;
    }

}
