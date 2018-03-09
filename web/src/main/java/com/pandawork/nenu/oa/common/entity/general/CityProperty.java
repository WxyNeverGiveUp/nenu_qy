package com.pandawork.nenu.oa.common.entity.general;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:市实体
 * Author:chenwy
 * Date:2017/5/15
 * Time:15:44
 */
@Entity
@Table(name = "t_param_city")
public class CityProperty extends AbstractEntity{

    //市id
    @Id
    private Integer id;

    //对应省份id
    @Column(name = "province_id")
    private String provinceId;

    //市名称
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
