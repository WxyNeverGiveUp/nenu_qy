package com.pandawork.nenu.oa.common.dto.business;

import java.util.Date;

/**
 * description:
 * 查询身份变更用
 * user:qiulan
 * date:2016/7/14
 * time:14:42
 */
public class ChangeQueryDto  {

    private Date createTime; //学生提交时间
    private String name;  //学生姓名
    private String studentNumber; //学号
    private Integer checkChangeResult; //审核状态
    private Integer changeType; //变更类型
    private Integer pCurrent;  //当前页码
    private Integer pSize; //当前数量
    private String keyWord; //关键字
    private Date beginTime;  //开始时间
    private Date endTime;  //结束时间

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getpCurrent() {
        return pCurrent;
    }

    public void setpCurrent(Integer pCurrent) {
        this.pCurrent = pCurrent;
    }

    public Integer getpSize() {
        return pSize;
    }

    public void setpSize(Integer pSize) {
        this.pSize = pSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getCheckChangeResult() {
        return checkChangeResult;
    }

    public void setCheckChangeResult(Integer checkChangeResult) {
        this.checkChangeResult = checkChangeResult;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }
}
