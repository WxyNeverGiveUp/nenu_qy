package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: chenyuting
 * @DateTime: 2017/5/31  18:58
 */
@Entity
@Table(name = "t_whereaboutgo_intend_code")
public class WhereAboutGoIntendCode extends AbstractEntity{

    //唯一表示
    @Id
    private Integer id ;

    // 拟就业去向代码
    @Column( name = "whereaboutgo_id")
    private Integer whereAboutGoId;

    // 拟就业去向
    @Column( name = "whereaboutgo")
    private String whereAboutGo;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getWhereAboutGoId() {
        return whereAboutGoId;
    }

    public void setWhereAboutGoId(int whereAboutGoId) {
        this.whereAboutGoId = whereAboutGoId;
    }

    public String getWhereAboutGo() {
        return whereAboutGo;
    }

    public void setWhereAboutGo(String whereAboutGo) {
        this.whereAboutGo = whereAboutGo;
    }
}