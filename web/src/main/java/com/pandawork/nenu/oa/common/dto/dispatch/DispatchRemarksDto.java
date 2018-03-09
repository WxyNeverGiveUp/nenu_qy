package com.pandawork.nenu.oa.common.dto.dispatch;

/**
 * Description:
 * Author:luowanli
 * Date:2016/7/29
 * Time:9:01
 */
public class DispatchRemarksDto {
    //学号
    private Integer statusInfoId;

    //单位所在地代码
    private Integer cityId;

    //生源地代码
    private Integer originPlaceCode;

    //培养方式代码
    private Integer trainingModeCode;

    //师范生类别代码
    private Integer normalStuCode;

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getOriginPlaceCode() {
        return originPlaceCode;
    }

    public void setOriginPlaceCode(Integer originPlaceCode) {
        this.originPlaceCode = originPlaceCode;
    }

    public Integer getTrainingModeCode() {
        return trainingModeCode;
    }

    public void setTrainingModeCode(Integer trainingModeCode) {
        this.trainingModeCode = trainingModeCode;
    }

    public Integer getNormalStuCode() {
        return normalStuCode;
    }

    public void setNormalStuCode(Integer normalStuCode) {
        this.normalStuCode = normalStuCode;
    }
}
