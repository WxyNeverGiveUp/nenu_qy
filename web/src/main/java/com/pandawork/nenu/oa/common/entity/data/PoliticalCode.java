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
@Table(name = "t_political_code")
public class PoliticalCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "political_stand_id" )
    private String politicalStandId;

    @Column( name = "political_stand" )
    private String politicalStand;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getPoliticalStandId() {
        return politicalStandId;
    }

    public void setPoliticalStandId(String politicalStandId) {
        this.politicalStandId = politicalStandId;
    }

    public String getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(String politicalStand) {
        this.politicalStand = politicalStand;
    }
}
