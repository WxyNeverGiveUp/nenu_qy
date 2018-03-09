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
@Table(name = "t_job_code")
public class JobCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id ;

    @Column( name = "job_id")
    private int jobId;

    @Column( name = "job")
    private String job;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
