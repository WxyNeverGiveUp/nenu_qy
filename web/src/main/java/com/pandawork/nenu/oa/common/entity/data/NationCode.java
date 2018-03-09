package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午9:55
 */
@Entity
@Table(name = "t_nation_code")
public class NationCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "nation_id")
    private String nationId;

    @Column( name = "nation" )
    private String nation;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NationCode that = (NationCode) o;

        return nationId != null ? nationId.equals(that.nationId) : that.nationId == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nationId != null ? nationId.hashCode() : 0);
        return result;
    }
}
