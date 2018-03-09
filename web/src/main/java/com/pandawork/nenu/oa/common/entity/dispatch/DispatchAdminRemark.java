package com.pandawork.nenu.oa.common.entity.dispatch;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DispatchAdminRemark
 * 管理员派遣备注
 * Created by ZhangBiLai on 2017/7/17.
 */

@Entity
@Table(name = "t_dispatch_admin_remark")
public class DispatchAdminRemark extends AbstractEntity {

    //主键唯一标识
    @Id
    private Integer id;

    //派遣学生ID
    @Column(name = "dispatch_id")
    private Integer dispatchId;

    //学生派遣备注
    @Column(name = "remark")
    private String remark;

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

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
