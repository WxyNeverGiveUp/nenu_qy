package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.data.EmploymentRateDTO;
import com.pandawork.nenu.oa.common.dto.data.EmploymentStructureDTO;
import com.pandawork.nenu.oa.common.dto.data.InformationStatisticDTO;
import com.pandawork.nenu.oa.common.dto.data.StudentStructureDTO;
import com.pandawork.nenu.oa.common.entity.data.InformationStatistic;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.InformationStatisticMapper;
import com.pandawork.nenu.oa.service.data.CommonCodeService;
import com.pandawork.nenu.oa.service.data.InformationStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;

/**
 * 数据统计服务实现
 * user: lishicao
 * date 15/4/22
 * time 上午10:13
 */
@Service( "InformationStatisticService" )
public class InformationStatisticServiceImpl implements InformationStatisticService {
    @Autowired
    CommonCodeService commonCodeService ;
    @Autowired
    InformationStatisticMapper informationStatisticMapper;
    @Autowired
    CommonDao commonDao;

    /**
     * 从国家系统导出的EXCEL添加学生
     * @param studentInfomation
     */
    public void addFromNation( StudentInfomation studentInfomation ) throws SSException {
        try {
            InformationStatistic informationStatistic = new InformationStatistic();

            informationStatistic.setName(studentInfomation.getName());                                                              //姓名
            informationStatistic.setExamId(studentInfomation.getExamId());                                                    //学号

            if( studentInfomation.getCollegeName() != null ) {                                                                      //学院
                informationStatistic.setCollegeName(studentInfomation.getCollegeName());
                informationStatistic.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            }
            else {
                informationStatistic.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));
                informationStatistic.setCollegeId(studentInfomation.getCollegeId());
            }

            if( studentInfomation.getSex() != null ) {                                                                              //性别
                informationStatistic.setSex(studentInfomation.getSex());
                informationStatistic.setSexId(commonCodeService.getSexId(studentInfomation.getSex()));
            }
            else {
                informationStatistic.setSex(commonCodeService.getSex(studentInfomation.getSexId()));
                informationStatistic.setSexId( studentInfomation.getSexId() );
            }

            informationStatistic.setQualificationStudy(studentInfomation.getQualification());                                       //攻读学位
            informationStatistic.setMajorDivision(commonCodeService.getMajorDivision(studentInfomation.getMajorId() , studentInfomation.getQualification())); //学科
            informationStatistic.setMajorId(studentInfomation.getMajorId());                                                        //专业号
            informationStatistic.setMajorName(commonCodeService.getMajorName(studentInfomation.getMajorId(),studentInfomation.getQualification()));                      //专业

            if( studentInfomation.getNormalStu() != null ) {
                informationStatistic.setNormalStu(studentInfomation.getNormalStu());
                informationStatistic.setNormalStuId(commonCodeService.getNormalStuId(studentInfomation.getNormalStu()));
            }
            else {
                informationStatistic.setNormalStu( commonCodeService.getNormalStu(studentInfomation.getNormalStuId()));
                informationStatistic.setNormalStuId( studentInfomation.getNormalStuId() );
            }
            if( studentInfomation.getNativePlaceId() != null ) {
                informationStatistic.setProvinceId(studentInfomation.getNativePlaceId() / 10000);                                       //生源省份号
            }
            else {
                informationStatistic.setProvinceId(null);
            }
            informationStatistic.setProvince(commonCodeService.getProvince(studentInfomation.getNativePlaceId()));                  //生源省份

            if( studentInfomation.getDifficultyType() != null ) {
                informationStatistic.setDifficultyMode(studentInfomation.getDifficultyType());
                informationStatistic.setDifficultyId(commonCodeService.getDifficultyId(studentInfomation.getDifficultyType()));
            }
            else {
                informationStatistic.setDifficultyId(studentInfomation.getDifficultyTypeId());                                          //困难生类别号
                informationStatistic.setDifficultyMode(commonCodeService.getDifficultyMode(studentInfomation.getDifficultyTypeId()));   //困难生类别
            }

            if( studentInfomation.getNation() != null ) {
                informationStatistic.setNationId(commonCodeService.getNationId(studentInfomation.getNation()));
                informationStatistic.setNation(studentInfomation.getNation());
            }
            else {
                informationStatistic.setNationId(studentInfomation.getNationId());                                                      //民族号
                informationStatistic.setNation(commonCodeService.getNation(studentInfomation.getNationId()));                           //民族
            }

            if( studentInfomation.getPoliticalStand() != null ) {
                informationStatistic.setPoliticalStand(studentInfomation.getPoliticalStand());
                informationStatistic.setPoliticalStandId(commonCodeService.getPoliticalId(studentInfomation.getPoliticalStand()));
            }
            else {
                informationStatistic.setPoliticalStandId(studentInfomation.getPoliticalStandId());                                      //政治面貌号
                informationStatistic.setPoliticalStand(commonCodeService.getPolitical(studentInfomation.getPoliticalStandId()));        //政治面貌
            }

            if( studentInfomation.getWhereaboutgo() != null ) {
                informationStatistic.setWhereaboutgo(studentInfomation.getWhereaboutgo());
                informationStatistic.setWhereaboutgoId(commonCodeService.getWhereAboutGoId(studentInfomation.getWhereaboutgo()));
            }
            else {
                informationStatistic.setWhereaboutgoId(studentInfomation.getWhereaboutgoId());                                          //毕业去向号
                informationStatistic.setWhereaboutgo(commonCodeService.getWhereAboutGo(studentInfomation.getWhereaboutgoId()));         //毕业去向
            }

            informationStatistic.setJobProvince(commonCodeService.getProvince(studentInfomation.getInsPlaceId()));                      //就业省份

            if( studentInfomation.getInsPlaceId() != null ) {
                informationStatistic.setJobProvinceId(studentInfomation.getInsPlaceId() / 10000);                                       //就业省份号
            }
            else {
                informationStatistic.setJobProvinceId(null);
            }
            if( studentInfomation.getInsPlaceId() !=null) {
                informationStatistic.setPlaceClass(commonCodeService.getPlaceClass(studentInfomation.getInsPlaceId()));             //就业地区类型
                informationStatistic.setPlaceClassId(commonCodeService.getPlaceClassId(informationStatistic.getPlaceClass()));
            }
            informationStatistic.setJobRegion(commonCodeService.getRegion(studentInfomation.getInsPlaceId()));                      //就业区域
            informationStatistic.setInsModeId(studentInfomation.getInsModeId());                                                    //单位性质号
            informationStatistic.setInsMode(commonCodeService.getInsMode(studentInfomation.getInsModeId()));                        //单位性质
            informationStatistic.setInsField(commonCodeService.getInsField(studentInfomation.getInsFieldId()));                     //单位行业
            informationStatistic.setInsFieldId(studentInfomation.getInsFieldId());                                                  //单位行业号
            informationStatistic.setWherewantgo(studentInfomation.getWherewantgo());                                                //拟就业类型
            informationStatistic.setWherewantgoIns(studentInfomation.getWherewantgoIns());                                          //拟就业单位

            informationStatistic.setGraduationTime(Integer.valueOf(studentInfomation.getGraduationTime().substring(0, 4)));

            informationStatistic.setStudentClassification(studentInfomation.getStudentClassification());                            //考生类型：城市应届，城市往届，农村应届，农村往届


            commonDao.insert(informationStatistic) ;
        }
        catch ( Exception ee ) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    public void add( StudentInfomation studentInfomation ) throws SSException {
        try {
            if( studentInfomation.getExamId() == null
                    || studentInfomation.getName() == null ) {
                throw SSException.get(OaException.ParamWrong);
            }

            InformationStatistic informationStatistic = new InformationStatistic();

            informationStatistic.setName(studentInfomation.getName());                                                              //姓名
            informationStatistic.setExamId(studentInfomation.getExamId());                                                           //学号

            if( studentInfomation.getCollegeName() != null ) {                                                                      //学院
                informationStatistic.setCollegeName(studentInfomation.getCollegeName());
                informationStatistic.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            }
            else {
                informationStatistic.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));
                informationStatistic.setCollegeId(studentInfomation.getCollegeId());
            }

            if( studentInfomation.getSex() != null ) {                                                                              //性别
                informationStatistic.setSex(studentInfomation.getSex());
                informationStatistic.setSexId(commonCodeService.getSexId(studentInfomation.getSex()));
            }
            else {
                informationStatistic.setSex(commonCodeService.getSex(studentInfomation.getSexId()));
                informationStatistic.setSexId( studentInfomation.getSexId() );
            }

            informationStatistic.setQualificationStudy(studentInfomation.getQualification());                                       //攻读学位
            informationStatistic.setMajorDivision(commonCodeService.getMajorDivision(studentInfomation.getMajorId() , studentInfomation.getQualification())); //学科
            informationStatistic.setMajorId(studentInfomation.getMajorId());                                                        //专业号
            informationStatistic.setMajorName(commonCodeService.getMajorName(studentInfomation.getMajorId(),studentInfomation.getQualification()));                      //专业

            if( studentInfomation.getNormalStu() != null ) {
                informationStatistic.setNormalStu(studentInfomation.getNormalStu());
                informationStatistic.setNormalStuId(commonCodeService.getNormalStuId(studentInfomation.getNormalStu()));
            }
            else {
                informationStatistic.setNormalStu( commonCodeService.getNormalStu(studentInfomation.getNormalStuId()));
                informationStatistic.setNormalStuId( studentInfomation.getNormalStuId() );
            }
            if( studentInfomation.getNativePlaceId() != null ) {
                informationStatistic.setProvinceId(studentInfomation.getNativePlaceId() / 10000);                                       //生源省份号
            }
            else {
                informationStatistic.setProvinceId(null);
            }
            informationStatistic.setProvince(commonCodeService.getProvince(studentInfomation.getNativePlaceId()));                  //生源省份

            if( studentInfomation.getDifficultyType() != null ) {
                informationStatistic.setDifficultyMode(studentInfomation.getDifficultyType());
                informationStatistic.setDifficultyId(commonCodeService.getDifficultyId(studentInfomation.getDifficultyType()));
            }
            else {
                informationStatistic.setDifficultyId(studentInfomation.getDifficultyTypeId());                                          //困难生类别号
                informationStatistic.setDifficultyMode(commonCodeService.getDifficultyMode(studentInfomation.getDifficultyTypeId()));   //困难生类别
            }

            if( studentInfomation.getNation() != null ) {
                informationStatistic.setNationId(commonCodeService.getNationId(studentInfomation.getNation()));
                informationStatistic.setNation(studentInfomation.getNation());
            }
            else {
                informationStatistic.setNationId(studentInfomation.getNationId());                                                      //民族号
                informationStatistic.setNation(commonCodeService.getNation(studentInfomation.getNationId()));                           //民族
            }

            if( studentInfomation.getPoliticalStand() != null ) {
                informationStatistic.setPoliticalStand(studentInfomation.getPoliticalStand());
                informationStatistic.setPoliticalStandId(commonCodeService.getPoliticalId(studentInfomation.getPoliticalStand()));
            }
            else {
                informationStatistic.setPoliticalStandId(studentInfomation.getPoliticalStandId());                                      //政治面貌号
                informationStatistic.setPoliticalStand(commonCodeService.getPolitical(studentInfomation.getPoliticalStandId()));        //政治面貌
            }

            if( studentInfomation.getWhereaboutgo() != null ) {
                informationStatistic.setWhereaboutgo(studentInfomation.getWhereaboutgo());
                informationStatistic.setWhereaboutgoId(commonCodeService.getWhereAboutGoId(studentInfomation.getWhereaboutgo()));
            }
            else {
                informationStatistic.setWhereaboutgoId(studentInfomation.getWhereaboutgoId());                                          //毕业去向号
                informationStatistic.setWhereaboutgo(commonCodeService.getWhereAboutGo(studentInfomation.getWhereaboutgoId()));         //毕业去向
            }

            if( studentInfomation.getInsPlaceId() !=null) {
                informationStatistic.setPlaceClass(commonCodeService.getPlaceClass(studentInfomation.getInsPlaceId()));             //就业地区类型
                informationStatistic.setPlaceClassId(commonCodeService.getPlaceClassId(informationStatistic.getPlaceClass()));
            }

            informationStatistic.setJobProvince(commonCodeService.getProvince(studentInfomation.getInsPlaceId()));                      //就业省份

            if( studentInfomation.getInsPlaceId() != null ) {
                informationStatistic.setJobProvinceId(studentInfomation.getInsPlaceId() / 10000);                                       //就业省份号
            }
            else {
                informationStatistic.setJobProvinceId(null);
            }
            informationStatistic.setJobRegion(commonCodeService.getRegion(studentInfomation.getInsPlaceId()));                      //就业区域
            informationStatistic.setInsModeId(studentInfomation.getInsModeId());                                                    //单位性质号
            informationStatistic.setInsMode(commonCodeService.getInsMode(studentInfomation.getInsModeId()));                        //单位性质
            informationStatistic.setInsField(commonCodeService.getInsField(studentInfomation.getInsFieldId()));                     //单位行业
            informationStatistic.setInsFieldId(studentInfomation.getInsFieldId());                                                  //单位行业号
            informationStatistic.setWherewantgo(studentInfomation.getWherewantgo());                                                //拟就业类型
            informationStatistic.setWherewantgoIns(studentInfomation.getWherewantgoIns());                                          //拟就业单位

            if( studentInfomation.getGraduationTime() != null && !studentInfomation.getGraduationTime().equals(""))informationStatistic.setGraduationTime(Integer.valueOf(studentInfomation.getGraduationTime().substring(0, 4)));

            informationStatistic.setStudentClassification(studentInfomation.getStudentClassification());                            //考生类型：城市应届，城市往届，农村应届，农村往届


            commonDao.insert(informationStatistic) ;
        }
        catch ( Exception ee ) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 更新学生信息
     * @param studentInfomation
     */
    public void update( StudentInfomation studentInfomation ) throws SSException {
        try{
            InformationStatistic informationStatistic = new InformationStatistic();

            informationStatistic.setName(studentInfomation.getName());                                                              //姓名
            informationStatistic.setExamId(studentInfomation.getExamId());                                                      //学号

            if( studentInfomation.getCollegeName() != null ) {                                                                      //学院
                informationStatistic.setCollegeName(studentInfomation.getCollegeName());
                informationStatistic.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            }
            else {
                informationStatistic.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));
                informationStatistic.setCollegeId(studentInfomation.getCollegeId());
            }

            if( studentInfomation.getSex() != null ) {                                                                              //性别
                informationStatistic.setSex(studentInfomation.getSex());
                informationStatistic.setSexId(commonCodeService.getSexId(studentInfomation.getSex()));
            }
            else {
                informationStatistic.setSex(commonCodeService.getSex(studentInfomation.getSexId()));
                informationStatistic.setSexId( studentInfomation.getSexId() );
            }

            informationStatistic.setQualificationStudy(studentInfomation.getQualification());                                       //攻读学位

            informationStatistic.setMajorDivision(commonCodeService.getMajorDivision(studentInfomation.getMajorId(),studentInfomation.getQualification())) ;
            informationStatistic.setMajorId(studentInfomation.getMajorId());                                                        //专业号
            informationStatistic.setMajorName(commonCodeService.getMajorName(studentInfomation.getMajorId(),studentInfomation.getQualification()));                      //专业

            if( studentInfomation.getNormalStu() != null ) {
                informationStatistic.setNormalStu(studentInfomation.getNormalStu());
                informationStatistic.setNormalStuId(commonCodeService.getNormalStuId(studentInfomation.getNormalStu()));
            }
            else {
                informationStatistic.setNormalStu( commonCodeService.getNormalStu(studentInfomation.getNormalStuId()));
                informationStatistic.setNormalStuId( studentInfomation.getNormalStuId() );
            }
            if( studentInfomation.getNativePlaceId() != null ) {
                informationStatistic.setProvinceId(studentInfomation.getNativePlaceId() / 10000);                                       //生源省份号
            }
            else {
                informationStatistic.setProvinceId( null );
            }
            informationStatistic.setProvince(commonCodeService.getProvince(studentInfomation.getNativePlaceId()));                  //生源省份

            if( studentInfomation.getDifficultyType() != null ) {
                informationStatistic.setDifficultyMode(studentInfomation.getDifficultyType());
                informationStatistic.setDifficultyId(commonCodeService.getDifficultyId(studentInfomation.getDifficultyType()));
            }
            else {
                informationStatistic.setDifficultyId(studentInfomation.getDifficultyTypeId());                                          //困难生类别号
                informationStatistic.setDifficultyMode(commonCodeService.getDifficultyMode(studentInfomation.getDifficultyTypeId()));   //困难生类别
            }

            if( studentInfomation.getNation() != null ) {
                informationStatistic.setNationId(commonCodeService.getNationId(studentInfomation.getNation()));
                informationStatistic.setNation(studentInfomation.getNation());
            }
            else {
                informationStatistic.setNationId(studentInfomation.getNationId());                                                      //民族号
                informationStatistic.setNation(commonCodeService.getNation(studentInfomation.getNationId()));                           //民族
            }

            if( studentInfomation.getPoliticalStand() != null ) {
                informationStatistic.setPoliticalStand(studentInfomation.getPoliticalStand());
                informationStatistic.setPoliticalStandId(commonCodeService.getPoliticalId(studentInfomation.getPoliticalStand()));
            }
            else {
                informationStatistic.setPoliticalStandId(studentInfomation.getPoliticalStandId());                                      //政治面貌号
                informationStatistic.setPoliticalStand(commonCodeService.getPolitical(studentInfomation.getPoliticalStandId()));        //政治面貌
            }

            if( studentInfomation.getWhereaboutgo() != null ) {
                informationStatistic.setWhereaboutgo(studentInfomation.getWhereaboutgo());
                informationStatistic.setWhereaboutgoId(commonCodeService.getWhereAboutGoId(studentInfomation.getWhereaboutgo()));
            }
            else {
                informationStatistic.setWhereaboutgoId(studentInfomation.getWhereaboutgoId());                                          //毕业去向号
                informationStatistic.setWhereaboutgo(commonCodeService.getWhereAboutGo(studentInfomation.getWhereaboutgoId()));         //毕业去向
            }

            informationStatistic.setJobProvince(commonCodeService.getProvince(studentInfomation.getInsPlaceId()));                      //就业省份

            if( studentInfomation.getInsPlaceId() != null ) {
                informationStatistic.setJobProvinceId(studentInfomation.getInsPlaceId() / 10000);                                       //就业省份号
            }
            else {
                informationStatistic.setJobProvinceId(null);
            }
            if( studentInfomation.getInsPlaceId() !=null) {
                informationStatistic.setPlaceClass(commonCodeService.getPlaceClass(studentInfomation.getInsPlaceId()));             //就业地区类型
                informationStatistic.setPlaceClassId(commonCodeService.getPlaceClassId(informationStatistic.getPlaceClass()));
            }

            informationStatistic.setJobRegion(commonCodeService.getRegion(studentInfomation.getInsPlaceId()));                      //就业区域
            informationStatistic.setInsModeId(studentInfomation.getInsModeId());                                                    //单位性质号
            informationStatistic.setInsMode(commonCodeService.getInsMode(studentInfomation.getInsModeId()));                        //单位性质
            informationStatistic.setInsField(commonCodeService.getInsField(studentInfomation.getInsFieldId()));                     //单位行业
            informationStatistic.setInsFieldId(studentInfomation.getInsFieldId());                                                  //单位行业号
            informationStatistic.setWherewantgo(studentInfomation.getWherewantgo());                                                //拟就业类型
            informationStatistic.setWherewantgoIns(studentInfomation.getWherewantgoIns());                                          //拟就业单位

            informationStatistic.setGraduationTime(Integer.valueOf(studentInfomation.getGraduationTime().substring(0,4)) );

            informationStatistic.setStudentClassification(studentInfomation.getStudentClassification());                            //考生类型：城市应届，城市往届，农村应届，农村往届

            informationStatisticMapper.update(informationStatistic);
            //commonDao.update(informationStatistic);
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }

    }

    /**
     * 更新学生信息
     * @param studentInfomation
     */
    public void updateFromNation( StudentInfomation studentInfomation ) throws SSException {
        try{
            InformationStatistic informationStatistic = new InformationStatistic();

            informationStatistic.setName(studentInfomation.getName());                                                              //姓名
            informationStatistic.setExamId(studentInfomation.getExamId());                                                          //考生号

            if( studentInfomation.getCollegeName() != null ) {                                                                      //学院
                informationStatistic.setCollegeName(studentInfomation.getCollegeName());
                informationStatistic.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            }
            else {
                informationStatistic.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));
                informationStatistic.setCollegeId(studentInfomation.getCollegeId());
            }

            if( studentInfomation.getSex() != null ) {                                                                              //性别
                informationStatistic.setSex(studentInfomation.getSex());
                informationStatistic.setSexId(commonCodeService.getSexId(studentInfomation.getSex()));
            }
            else {
                informationStatistic.setSex(commonCodeService.getSex(studentInfomation.getSexId()));
                informationStatistic.setSexId( studentInfomation.getSexId() );
            }

            informationStatistic.setQualificationStudy(studentInfomation.getQualification());                                       //攻读学位

            informationStatistic.setMajorDivision(commonCodeService.getMajorDivision(studentInfomation.getMajorId(),studentInfomation.getQualification())) ;
            informationStatistic.setMajorId(studentInfomation.getMajorId());                                                        //专业号
            informationStatistic.setMajorName(commonCodeService.getMajorName(studentInfomation.getMajorId(),studentInfomation.getQualification()));                      //专业

            if( studentInfomation.getNormalStu() != null ) {
                informationStatistic.setNormalStu(studentInfomation.getNormalStu());
                informationStatistic.setNormalStuId(commonCodeService.getNormalStuId(studentInfomation.getNormalStu()));
            }
            else {
                informationStatistic.setNormalStu( commonCodeService.getNormalStu(studentInfomation.getNormalStuId()));
                informationStatistic.setNormalStuId( studentInfomation.getNormalStuId() );
            }
            if( studentInfomation.getNativePlaceId() != null ) {
                informationStatistic.setProvinceId(studentInfomation.getNativePlaceId() / 10000);                                       //生源省份号
            }
            else {
                informationStatistic.setProvinceId( null );
            }
            informationStatistic.setProvince(commonCodeService.getProvince(studentInfomation.getNativePlaceId()));                  //生源省份

            if( studentInfomation.getDifficultyType() != null ) {
                informationStatistic.setDifficultyMode(studentInfomation.getDifficultyType());
                informationStatistic.setDifficultyId(commonCodeService.getDifficultyId(studentInfomation.getDifficultyType()));
            }
            else {
                informationStatistic.setDifficultyId(studentInfomation.getDifficultyTypeId());                                          //困难生类别号
                informationStatistic.setDifficultyMode(commonCodeService.getDifficultyMode(studentInfomation.getDifficultyTypeId()));   //困难生类别
            }

            if( studentInfomation.getNation() != null ) {
                informationStatistic.setNationId(commonCodeService.getNationId(studentInfomation.getNation()));
                informationStatistic.setNation(studentInfomation.getNation());
            }
            else {
                informationStatistic.setNationId(studentInfomation.getNationId());                                                      //民族号
                informationStatistic.setNation(commonCodeService.getNation(studentInfomation.getNationId()));                           //民族
            }

            if( studentInfomation.getPoliticalStand() != null ) {
                informationStatistic.setPoliticalStand(studentInfomation.getPoliticalStand());
                informationStatistic.setPoliticalStandId(commonCodeService.getPoliticalId(studentInfomation.getPoliticalStand()));
            }
            else {
                informationStatistic.setPoliticalStandId(studentInfomation.getPoliticalStandId());                                      //政治面貌号
                informationStatistic.setPoliticalStand(commonCodeService.getPolitical(studentInfomation.getPoliticalStandId()));        //政治面貌
            }

            if( studentInfomation.getWhereaboutgo() != null ) {
                informationStatistic.setWhereaboutgo(studentInfomation.getWhereaboutgo());
                informationStatistic.setWhereaboutgoId(commonCodeService.getWhereAboutGoId(studentInfomation.getWhereaboutgo()));
            }
            else {
                informationStatistic.setWhereaboutgoId(studentInfomation.getWhereaboutgoId());                                          //毕业去向号
                informationStatistic.setWhereaboutgo(commonCodeService.getWhereAboutGo(studentInfomation.getWhereaboutgoId()));         //毕业去向
            }

            informationStatistic.setJobProvince(commonCodeService.getProvince(studentInfomation.getInsPlaceId()));                      //就业省份

            if( studentInfomation.getInsPlaceId() != null ) {
                informationStatistic.setJobProvinceId(studentInfomation.getInsPlaceId() / 10000);                                       //就业省份号
            }
            else {
                informationStatistic.setJobProvinceId(null);
            }
            informationStatistic.setJobRegion(commonCodeService.getRegion(studentInfomation.getInsPlaceId()));                      //就业区域
            informationStatistic.setInsModeId(studentInfomation.getInsModeId());                                                    //单位性质号
            informationStatistic.setInsMode(commonCodeService.getInsMode(studentInfomation.getInsModeId()));                        //单位性质
            informationStatistic.setInsField(commonCodeService.getInsField(studentInfomation.getInsFieldId()));                     //单位行业
            informationStatistic.setInsFieldId(studentInfomation.getInsFieldId());                                                  //单位行业号
            informationStatistic.setWherewantgo(studentInfomation.getWherewantgo());                                                //拟就业类型
            informationStatistic.setWherewantgoIns(studentInfomation.getWherewantgoIns());                                          //拟就业单位

            informationStatistic.setGraduationTime(Integer.valueOf(studentInfomation.getGraduationTime().substring(0,4)) );

            informationStatistic.setStudentClassification(studentInfomation.getStudentClassification());                            //考生类型：城市应届，城市往届，农村应届，农村往届

            informationStatisticMapper.updateFromNation(informationStatistic);
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }

    }

    /**
     * 删除
     * @param studentInfomation
     */
    public void delete( StudentInfomation studentInfomation ) throws SSException {
        try{
            informationStatisticMapper.delete( studentInfomation.getExamId() );
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 删除
     * @param examId
     */
    public void delete( String examId ) throws SSException {
        try{
            informationStatisticMapper.delete( examId );
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 查询生源结构
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     */
    public StudentStructureDTO getStudentStructure( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException{
        if ( dimension1 == null || dimension1.equals("") ) {
            throw SSException.get(OaException.LakeParam) ;
        }
        try {
            StudentStructureDTO studentStructureDTO = new StudentStructureDTO();
            List<InformationStatisticDTO> informationStatisticDTOs ;
            informationStatisticDTOs = informationStatisticMapper.studentStructure( dimension1 , dimension2 , college , qualification , start , end );
            studentStructureDTO.setData(informationStatisticDTOs);
            return studentStructureDTO;
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 查询就业率
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     */
    public EmploymentRateDTO getEmploymentRate( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException{
        if ( dimension1 == null || dimension1.equals("") ) {
            throw SSException.get(OaException.LakeParam) ;
        }
        try {
            EmploymentRateDTO employmentRateDTO = new EmploymentRateDTO();

            List<InformationStatisticDTO> informationStatisticDTOs ;
            List<InformationStatisticDTO> studentStructureDTOs ;
            informationStatisticDTOs = informationStatisticMapper.employmentRate( dimension1, dimension2, college, qualification, start, end );
            studentStructureDTOs = informationStatisticMapper.studentStructure( dimension1, dimension2, college, qualification, start, end );
            employmentRateDTO.setData( informationStatisticDTOs , studentStructureDTOs);
            return employmentRateDTO;
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 查询拟就业率
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     */
    public EmploymentRateDTO getPlanEmploymentRate( String dimension1 , String dimension2 , String college , String qualification , int start , int end )throws SSException{
        if ( dimension1 == null || dimension1.equals("") ) {
            throw SSException.get(OaException.LakeParam) ;
        }
        try{
            EmploymentRateDTO employmentRateDTO = new EmploymentRateDTO();

            List<InformationStatisticDTO> informationStatisticDTOs ;
            List<InformationStatisticDTO> studentStructureDTOs ;

            informationStatisticDTOs = informationStatisticMapper.planEmploymentRate(dimension1, dimension2, college, qualification, start, end);
            studentStructureDTOs = informationStatisticMapper.studentStructure( dimension1, dimension2, college, qualification, start, end );
            employmentRateDTO.setData( informationStatisticDTOs , studentStructureDTOs);
            return employmentRateDTO;
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 查询就业结构
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     */
    public EmploymentStructureDTO getEmployStructure( String dimension1 , String dimension2 , String college , String qualification , int start , int end )throws SSException{
        if ( dimension1 == null || dimension1.equals("") ) {
            throw SSException.get(OaException.LakeParam) ;
        }
        try{
            EmploymentStructureDTO employmentStructureDTO = new EmploymentStructureDTO();
            List<InformationStatisticDTO> informationStatisticDTOs ;
            informationStatisticDTOs = informationStatisticMapper.employmentRate(dimension1, dimension2, college, qualification, start, end) ;
            employmentStructureDTO.setData(informationStatisticDTOs);
            return employmentStructureDTO;
        }
        catch (Exception ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }
}
