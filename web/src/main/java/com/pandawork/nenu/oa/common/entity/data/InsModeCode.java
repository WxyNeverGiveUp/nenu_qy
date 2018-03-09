package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午10:03
 */
@Entity
@Table(name = "t_insmode_code")
public class InsModeCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "ins_mode_id" )
    private int insModeId;

    @Column( name = "ins_mode")
    private String insMode;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getInsModeId() {
        return insModeId;
    }

    public void setInsModeId(int insModeId) {
        this.insModeId = insModeId;
    }

    public String getInsMode() {
        return insMode;
    }

    public void setInsMode(String insMode) {
        this.insMode = insMode;
    }
}
