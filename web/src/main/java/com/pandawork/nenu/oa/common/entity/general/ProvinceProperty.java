package com.pandawork.nenu.oa.common.entity.general;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description:
 * 省份表实体类
 * user:qiulan
 * date:2016/7/27
 * time:16:28
 */
@Entity
@Table(name = "t_param_province")
public class ProvinceProperty extends AbstractEntity {
    //省份id
    @Id
    private Integer id;

    //省份代码
    @Column(name = "code")
    private String code;

    //省份名称
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
