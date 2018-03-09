package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description:
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:53
 */
@Entity
@Table(name = "t_report_code")
public class ReportCode extends AbstractEntity {

    //报到证类别表Id
    @Id
    private Integer id;

    //报到证类别代码
    @Column(name = "report_mode_id")
    private Integer reportModeId;

    //报到证类别
    @Column(name = "report_mode")
    private String reportMode;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportModeId() {
        return reportModeId;
    }

    public void setReportModeId(Integer reportModeId) {
        this.reportModeId = reportModeId;
    }

    public String getReportMode() {
        return reportMode;
    }

    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
