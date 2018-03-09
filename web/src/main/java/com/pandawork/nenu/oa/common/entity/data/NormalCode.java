package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午9:56
 */
@Entity
@Table(name = "t_normal_code")
public class NormalCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "normal_stu_id")
    private int normalStuId;

    @Column( name = "normal_stu")
    private String normalStu;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getNormalStuId() {
        return normalStuId;
    }

    public void setNormalStuId(int normalStuId) {
        this.normalStuId = normalStuId;
    }

    public String getNormalStu() {
        return normalStu;
    }

    public void setNormalStu(String normalStu) {
        this.normalStu = normalStu;
    }
}
