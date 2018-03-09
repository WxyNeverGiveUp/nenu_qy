package com.pandawork.nenu.oa.common.dto.student.status;

/**
 * UpdateInfoDto
 *
 * @author wlm
 * @date 2016/9/21 11:25
 */
public class UpdateInfoDto {

    private String beforeUpdate;

    private String afterUpdate;

    private String updateType;

    private String updateTime;

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

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
