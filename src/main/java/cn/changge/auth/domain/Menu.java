package cn.changge.auth.domain;

import cn.changge.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;

    private List<Menu> children;
}
