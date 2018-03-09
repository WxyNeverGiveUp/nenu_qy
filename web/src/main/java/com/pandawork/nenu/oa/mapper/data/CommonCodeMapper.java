package com.pandawork.nenu.oa.mapper.data;


import com.pandawork.nenu.oa.common.entity.data.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * user: lishicao
 * date 15/4/24
 * time 上午1:29
 */

public interface CommonCodeMapper {
    public List<PlaceCode> listShownameByProvinceId(int provinceId);
    public List<WhereAboutGoCode> getWhereaboutgoCodeList() throws Exception;   //毕业去向
    public List<CollegeCode> getCollegeCodeList() throws Exception;             //学院
    //public List<QualificationCode> getQualificationCodeList() ; //毕业学历
    public List<MajorCode> getMajorCodeList() throws Exception;                 //专业
    public List<SexCode> getSexCodeList()throws Exception;                      //性别
    public List<NationCode> getNationCodeList()throws Exception;                //民族
    public List<DifficultyCode> getDifficultyCodeList()throws Exception;        //困难生类别
    public List<PoliticalCode> getPoliticalCodeList()throws Exception;          //政治面貌
    public List<NormalCode> getNormalCodeList()throws Exception;                //师范生类别
    public List<PlaceCode> getPlaceCodeList()throws Exception;                  //地区
    public List<InsModeCode> getInsModeCodeList()throws Exception;              //单位性质
    public List<InsFieldCode> getInsFieldCodeList()throws Exception;            //单位行业
    public List<JobCode> getJobCodeList() throws Exception;                     //工作职位类别
    public List<ReportCode> getReportCodeList() throws Exception;               //报到证发放类别


    public List<TrainingModeCode> getTrainingModeCodeList()throws Exception; // 培养方式

    public CollegeCode getCollegeCodeById(int id);
}
