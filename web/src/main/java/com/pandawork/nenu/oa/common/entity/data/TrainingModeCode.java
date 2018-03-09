package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午9:58
 */
@Entity
@Table(name = "t_training_mode_code")
public class TrainingModeCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "training_mode_id")
    private int trainingModeId;

    @Column( name = "training_mode")
    private String trainingMode;


    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getTrainingModeId() {
        return trainingModeId;
    }

    public void setTrainingModeId(int trainingModeId) {
        this.trainingModeId = trainingModeId;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }
}
