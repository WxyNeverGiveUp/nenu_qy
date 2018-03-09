package com.pandawork.nenu.oa.common.dto.business;


import java.util.Date;

/**
 * description:
 * 查询协议详情用
 * user:qiulan
 * date:2016/7/12
 * time:9:28
 */
public class ProtocolQueryDto {

    //提交时间
    private Date createTime;

    //学生学号
    private String studentNumber;

    //学生姓名
    private String name;

    //审核状态
    private Integer checkProtocolResult;

    //协议变更类型
    private Integer protocolType;

    //当前页码
    private Integer pCurrent;

    //每页数量
    private Integer pSize;

    //关键字
    private String keyWord;

    //提交时间开始
    private Date beginTime;

    //提交时间结束
    private Date endTime;

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public Date getBeginTime() {return beginTime;}

    public void setBeginTime(Date beginTime) {this.beginTime = beginTime;}

    public Date getEndTime() {return endTime;}

    public void setEndTime(Date endTime) {this.endTime = endTime;}

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

    public String getStudentNumber() {return studentNumber;}

    public void setStudentNumber(String studentNumber) {this.studentNumber = studentNumber;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getCheckProtocolResult() {return checkProtocolResult;}

    public void setCheckProtocolResult(Integer checkProtocolResult) {this.checkProtocolResult = checkProtocolResult;}
}
