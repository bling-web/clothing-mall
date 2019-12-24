package com.tim.mall.admin.model;

import org.apache.ibatis.annotations.Param;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Menu {

    private Integer id;

    private String name;

    private String url;

    private String icon;

    private String menutype;

    private Integer display;

    private Integer parentid;

    private Integer creator;

    private Date createtime;

    private Integer updateuser;

    private Date updatetime;

    private String flag;

    //多表查询时,需要加上的字段
    @Transient
    private int roleid;

    //这个注解的作用是在对于数据库查询,修改时不匹配该字段,而有需要使用到该字段
    @Transient
    private String type;

    //建立层级关系时需要用到的属性
    @Transient
    private List<Menu> children;

    //标记登录用户是否拥有某个菜单,这里不知道为啥additionalParameters这个名称换成其他的就不可以了
    @Transient
    private HashMap<String,Object> additionalParameters;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public HashMap<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(HashMap<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {

        this.menutype = menutype == null ? null : menutype.trim();
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}