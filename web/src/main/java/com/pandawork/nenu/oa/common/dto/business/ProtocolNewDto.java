package com.pandawork.nenu.oa.common.dto.business;

import java.util.Date;

/**
 * 学生端，申请新协议表中所展示内容
 *
 * @author zhaiaxin
 * @time: 2017/7/18 20:06.
 */
public class ProtocolNewDto {

    //变更类型
    private Integer newProtocol;

    //提交时间
    private Date createTime;

    //审核状态
    private String checkStatus;

    //审核时间
    private Date checkTime;

    public Integer getNewProtocol() {
        return newProtocol;
    }

    public void setNewProtocol(Integer newProtocol) {
        this.newProtocol = newProtocol;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
