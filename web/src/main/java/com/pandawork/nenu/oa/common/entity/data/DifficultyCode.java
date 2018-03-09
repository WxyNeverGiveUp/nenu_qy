package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午9:54
 */
@Entity
@Table(name = "t_difficulty_code")
public class DifficultyCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "difficulty_id")
    private Integer difficultyId;

    @Column( name = "difficulty_mode" )
    private String difficultyMode;


    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getDifficultyMode() {
        return difficultyMode;
    }

    public void setDifficultyMode(String difficultyMode) {
        this.difficultyMode = difficultyMode;
    }

    public Integer getId() {
        return id;
    }
}
