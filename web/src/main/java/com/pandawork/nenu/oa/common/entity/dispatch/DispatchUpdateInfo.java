package com.pandawork.nenu.oa.common.entity.dispatch;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DispatchUpdateInfo
 * 派遣信息修改表
 * Created by ZhangBiLai on 2017/9/25.
 */

@Entity
@Table(name = "t_dispatch_update_info")
public class DispatchUpdateInfo extends AbstractEntity {

    //派遣修改信息id
    @Id
    private Integer id;

    //派遣id
    @Column(name = "dispatch_info_id")
    private Integer dispatchInfoId;

    //修改前信息
    @Column(name = "before_update")
    private String beforeUpdate;

    //修改后信息
    @Column(name = "after_update")
    private String afterUpdate;

    //修改项类型
    @Column(name = "update_type")
    private Integer updateType;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    //学籍信息是被谁修改的（即记录是被谁插入的），1表示为学生，0表示为除学生的其他角色
    @Column(name = "alter_by_who")
    private Integer alterByWho;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDispatchInfoId() {
        return dispatchInfoId;
    }

    public void setDispatchInfoId(Integer dispatchInfoId) {
        this.dispatchInfoId = dispatchInfoId;
    }

    public String getBeforeUpdate() {
        return beforeUpdate;
    }

    public void setBeforeUpdate(String beforeUpdate) {
        this.beforeUpdate = beforeUpdate;
    }

    public String getAfterUpdate() {
        return afterUpdate;
    }

    public void setAfterUpdate(String afterUpdate) {
        this.afterUpdate = afterUpdate;
    }

    public Integer getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Integer updateType) {
        this.updateType = updateType;
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

    public Integer getAlterByWho() {
        return alterByWho;
    }

    public void setAlterByWho(Integer alterByWho) {
        this.alterByWho = alterByWho;
    }
}
