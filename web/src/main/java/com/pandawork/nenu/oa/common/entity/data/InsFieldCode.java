package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午10:02
 */
@Entity
@Table(name = "t_insfield_code")
public class InsFieldCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "ins_field_id")
    private int insFieldId;

    @Column( name = "ins_field" )
    private String insField;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getInsFieldId() {
        return insFieldId;
    }

    public void setInsFieldId(int insFieldId) {
        this.insFieldId = insFieldId;
    }

    public String getInsField() {
        return insField;
    }

    public void setInsField(String insField) {
        this.insField = insField;
    }
}
