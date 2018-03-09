package com.pandawork.nenu.oa.common.dto.dispatch;

/**
 * DispatchUpdateDto
 *
 * 派遣修改信息Dto
 * Created by ZhangBiLai on 2017/9/26.
 */
public class DispatchUpdateDto {

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
