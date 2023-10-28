package cn.changge.org.domain;

import cn.changge.base.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-10-28
 */
public class Shop extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String tel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = ("GMT+8"))
    private Date registerTime;
    /**
     * 1=待审核 2=审核通过待激活 3=激活成功 4=驳回
     */
    private Integer state;
    private String address;
    private String logo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Shop{" +
        ", id=" + id +
        ", name=" + name +
        ", tel=" + tel +
        ", registerTime=" + registerTime +
        ", state=" + state +
        ", address=" + address +
        ", logo=" + logo +
        "}";
    }
}
