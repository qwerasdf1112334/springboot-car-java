package cn.changge.auth.domain;

import cn.changge.base.domain.BaseDomain;
import lombok.Data;

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
public class Permission extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String url;
    private String descs;
    private String sn;
    /**
     * 父级权限的id
     */
    private Permission parent;
    private List<Permission> children;




    @Override
    public String toString() {
        return "Permission{" +
        ", id=" + id +
        ", name=" + name +
        ", url=" + url +
        ", descs=" + descs +
        ", sn=" + sn +
        ", parent=" + parent +
        "}";
    }
}
