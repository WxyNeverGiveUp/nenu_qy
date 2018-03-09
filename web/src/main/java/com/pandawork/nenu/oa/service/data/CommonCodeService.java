package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.Area;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.PlaceClass;
import com.pandawork.nenu.oa.common.dto.data.Province;
import com.pandawork.nenu.oa.common.entity.data.*;

import java.util.HashMap;
import java.util.List;

/**
 * user: lishicao
 * date 15/4/23
 * time 下午6:07
 */
public interface CommonCodeService {
    public void init();

    public List<PlaceCode> listShownameByProvinceId(int provinceId) throws SSException;

    public List<WhereAboutGoCode> getWhereaboutgoCodeList();

    public List<CollegeCode> getCollegeCodeList();

    public CollegeCode getCollegeCodeById(int id);

    public List<String> getQuanlificationStudyList();

    public List<MajorDivision> getMajorDivisionList();

    public List<MajorCode> getMajorCodeList();

    public List<SexCode> getSexCodeList();

    public List<NationCode> getNationCodeList();

    public List<DifficultyCode> getDifficultyCodeList();

    public List<PoliticalCode> getPoliticalCodeList();

    public List<NormalCode> getNormalCodeList();

    public List<PlaceCode> getPlaceCodeList();

    public List<InsModeCode> getInsModeCodeList();

    public List<InsFieldCode> getInsFieldCodeList();

    public List<PlaceClass> getPlaceClassList();

    public List<Province> getProvinceList();

    public List<Area> getAreaList();

    public List<TrainingModeCode> getTrainingModeCode();

    public HashMap<Integer, List<PlaceCode>> getCityList();

    public List<JobCode> getJobCodeList();

    public List<ReportCode> getReportCodeList();

    public String getCollegeName(Integer collegeId);

    public Integer getCollegeId(String collegeName);

    public String getSex(Integer sexId);

    public Integer getSexId(String sex);

    public String getMajorDivision(String majorCode, String qualification);

    public String getMajorName(String majorCode, String qualification);

    public String getNormalStu(Integer normalStuId);

    public Integer getNormalStuId(String normalStu);

    public String getProvince(Integer placeId);

    public String getRegion(Integer placeId);

    public String getDifficultyMode(Integer difficultyId);

    public Integer getDifficultyId(String DifficultyMode);

    public String getNation(String nationId);

    public String getNationId(String nation);

    public String getPolitical(String politicalId);

    public String getPoliticalId(String political);

    public String getWhereAboutGo(Integer whereaboutgoId);

    public Integer getWhereAboutGoId(String whereaboutgo);

    public String getPlaceClass(Integer placeCodeId);

    public Integer getPlaceClassId(String placeClass);

    public String getInsMode(Integer insModeId);

    public String getInsField(Integer insFieldId);
}
