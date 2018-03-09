package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学院表
 * user: lishicao
 * date 15/4/15
 * time 上午9:53
 */
@Entity
@Table(name = "t_college_code")
public class CollegeCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "college_id")
    private int collegeId;

    @Column( name = "college")
    private String college;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
