package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.data.Area;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.PlaceClass;
import com.pandawork.nenu.oa.common.dto.data.Province;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.CommonCodeMapper;
import com.pandawork.nenu.oa.service.data.CommonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * user: lishicao
 * date 15/4/23
 * time 下午6:07
 */
@Service("commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {

    @Autowired
    CommonCodeMapper commonCodeMapper ;

    private List<WhereAboutGoCode> whereaboutgoCodeList ;   //毕业去向
    private List<CollegeCode> collegeCodeList ;             //学院
    //private List<QualificationCode> qualificationCodeList ; //毕业学历
    private List<String> quanlificationStudyList ;          //攻读学历
    private List<MajorDivision> majorDivisionList;         //学科(研究生)
    private List<MajorDivision> majorDivisionList1;         //学科(本科)
    private List<MajorCode> majorCodeList ;                 //专业
    private List<SexCode> sexCodeList;                      //性别
    private List<NationCode> nationCodeList;                //民族
    private List<DifficultyCode> difficultyCodeList;        //困难生类别
    private List<PoliticalCode> politicalCodeList;          //政治面貌
    private List<NormalCode> normalCodeList;                //师范生类别
    private List<PlaceCode> placeCodeList;                  //地区
    private List<InsModeCode> insModeCodeList;              //单位性质
    private List<InsFieldCode> insFieldCodeList;            //单位行业
    private List<JobCode> jobCodeList;                      //工作职位类别
    private List<ReportCode> reportCodeList;                //报到证签发类别


    private List<PlaceClass> placeClassList;                //地区类型
    private List<TrainingModeCode> trainingModeCodeList;                //培养方式
    private List<Province> provinceList;                    //省份
    private List<Area>     areaList;                        //区域
    private HashMap<Integer,List<PlaceCode> > cityList;     //城市


    private HashMap<Integer,String> collegeMap ;            //学院hash表
    private HashMap<String,Integer> _collegeMap ;

    private HashMap<Integer,String> sexMap ;                // 性别
    private HashMap<String,Integer> _sexMap;

    private HashMap<Integer,String> placeClassMap;          //地区类型
    private HashMap<String,Integer> _placeClassMap;


    private HashMap<String,String> majorDivisionMap;        //研究生
    private HashMap<String,String> majorDivisionMap1;       //本科生
    private HashMap<String,String> majorNameMap;            //专业名称
    private HashMap<String,String> majorNameMap1;
    private HashMap<Integer,String> normalStuMap;           //师范生类别
    private HashMap<String,Integer> _normalStuMap;

    private HashMap<Integer,String> provinceMap ;
    private HashMap<Integer,String> difficultyMap;
    private HashMap<String,Integer> _difficultyMap;
    private HashMap<Integer,String> insModeMap;
    private HashMap<Integer,String> insFieldMap;
    private HashMap<String,String> nationMap;
    private HashMap<String,String> _nationMap;
    private HashMap<String,String> politicalMap;
    private HashMap<String,String> _politicalMap;
    private HashMap<Integer,String> whereaboutgoMap;
    private HashMap<String,Integer> _whereaboutgoMap;
    private HashMap<Integer,String> regionMap;




    public void init(){
        try {

            quanlificationStudyList = new ArrayList<String>();
            quanlificationStudyList.add("本科");
            quanlificationStudyList.add("硕士研究生");
            quanlificationStudyList.add("博士研究生");

            /**
             * 研究生的学科
             */
            majorDivisionList = new ArrayList<MajorDivision>();
            majorDivisionList.add(new MajorDivision("01", "哲学"));
            majorDivisionList.add(new MajorDivision("02", "经济学"));
            majorDivisionList.add(new MajorDivision("03", "法学"));
            majorDivisionList.add(new MajorDivision("04", "教育学"));
            majorDivisionList.add(new MajorDivision("05", "文学"));
            majorDivisionList.add(new MajorDivision("06", "历史学"));
            majorDivisionList.add(new MajorDivision("07", "理学"));
            majorDivisionList.add(new MajorDivision("08", "工学"));
            majorDivisionList.add(new MajorDivision("09", "农学"));
            majorDivisionList.add(new MajorDivision("10", "医学"));
            majorDivisionList.add(new MajorDivision("11", "军事学"));
            majorDivisionList.add(new MajorDivision("12", "管理学"));
            majorDivisionList.add(new MajorDivision("13", "艺术学"));

            /**
             * 本科的学科
             */
            majorDivisionList1 = new ArrayList<MajorDivision>();

            majorDivisionList1.add(new MajorDivision("01", "哲学"));
            majorDivisionList1.add(new MajorDivision("02", "经济学"));
            majorDivisionList1.add(new MajorDivision("03", "法学"));
            majorDivisionList1.add(new MajorDivision("04", "教育学"));
            majorDivisionList1.add(new MajorDivision("05", "文学"));
            majorDivisionList1.add(new MajorDivision("06", "历史学"));
            majorDivisionList1.add(new MajorDivision("07", "理学"));
            majorDivisionList1.add(new MajorDivision("08", "工学"));
            majorDivisionList1.add(new MajorDivision("09", "农学"));
            majorDivisionList1.add(new MajorDivision("10", "医学"));
            majorDivisionList1.add(new MajorDivision("12", "管理学"));
            majorDivisionList1.add(new MajorDivision("13", "艺术学"));

            placeClassList = new ArrayList<PlaceClass>();
            placeClassList.add(new PlaceClass(1, "直辖市"));
            placeClassList.add(new PlaceClass(2, "省会城市"));
            placeClassList.add(new PlaceClass(3, "计划单列市"));
            placeClassList.add(new PlaceClass(4, "地级市"));
            placeClassList.add(new PlaceClass(5, "县级市"));
            placeClassList.add(new PlaceClass(6, "县"));

            provinceList = new ArrayList<Province>();
            provinceList.add(new Province(11, "北京市"));
            provinceList.add(new Province(12, "天津市"));
            provinceList.add(new Province(13, "河北省"));
            provinceList.add(new Province(14, "山西省"));
            provinceList.add(new Province(15, "内蒙古"));
            provinceList.add(new Province(21, "辽宁省"));
            provinceList.add(new Province(22, "吉林省"));
            provinceList.add(new Province(23, "黑龙江省"));
            provinceList.add(new Province(31, "上海市"));
            provinceList.add(new Province(32, "江苏省"));
            provinceList.add(new Province(33, "浙江省"));
            provinceList.add(new Province(34, "安徽省"));
            provinceList.add(new Province(35, "福建省"));
            provinceList.add(new Province(36, "江西省"));
            provinceList.add(new Province(37, "山东省"));
            provinceList.add(new Province(41, "河南省"));
            provinceList.add(new Province(42, "湖北省"));
            provinceList.add(new Province(43, "湖南省"));
            provinceList.add(new Province(44, "广东省"));
            provinceList.add(new Province(45, "广西"));
            provinceList.add(new Province(46, "海南省"));
            provinceList.add(new Province(50, "重庆市"));
            provinceList.add(new Province(51, "四川省"));
            provinceList.add(new Province(52, "贵州省"));
            provinceList.add(new Province(53, "云南省"));
            provinceList.add(new Province(54, "西藏"));
            provinceList.add(new Province(61, "陕西省"));
            provinceList.add(new Province(62, "甘肃省"));
            provinceList.add(new Province(63, "青海省"));
            provinceList.add(new Province(64, "宁夏"));
            provinceList.add(new Province(65, "新疆"));
            provinceList.add(new Province(71, "台湾省"));
            provinceList.add(new Province(81, "香港特别行政区"));
            provinceList.add(new Province(82, "澳门特别行政区"));

            areaList = new ArrayList<Area>();
            Area area1 = new Area();
            area1.setAreaName("东北地区");
            List<Province> provincesInArea1 = new ArrayList<Province>();
            provincesInArea1.add(new Province(21, "辽宁省"));
            provincesInArea1.add(new Province(22, "吉林省"));
            provincesInArea1.add(new Province(23, "黑龙江省"));
            area1.setProvincesInArea(provincesInArea1);
            areaList.add(area1);


            Area area2 = new Area();
            area2.setAreaName("北部沿海地区");
            List<Province> provincesInArea2 = new ArrayList<Province>();
            provincesInArea2.add(new Province(37, "山东省"));
            provincesInArea2.add(new Province(13, "河北省"));
            provincesInArea2.add(new Province(11, "北京市"));
            provincesInArea2.add(new Province(12, "天津市"));
            area2.setProvincesInArea(provincesInArea2);
            areaList.add(area2);

            Area area3 = new Area();
            area3.setAreaName("东部沿海地区");
            List<Province> provincesInArea3 = new ArrayList<Province>();
            provincesInArea3.add(new Province(31, "上海市"));
            provincesInArea3.add(new Province(32, "江苏省"));
            provincesInArea3.add(new Province(33, "浙江省"));
            area3.setProvincesInArea(provincesInArea3);
            areaList.add(area3);

            Area area4 = new Area();
            area4.setAreaName("南部沿海地区");
            List<Province> provincesInArea4 = new ArrayList<Province>();
            provincesInArea4.add(new Province(44, "广东省"));
            provincesInArea4.add(new Province(35, "福建省"));
            provincesInArea4.add(new Province(46, "海南省"));
            provincesInArea4.add(new Province(81, "香港特别行政区"));
            area4.setProvincesInArea(provincesInArea4);
            areaList.add(area4);

            Area area5 = new Area();
            area5.setAreaName("黄河中游地区");
            List<Province> provincesInArea5 = new ArrayList<Province>();
            provincesInArea5.add(new Province(61, "陕西省"));
            provincesInArea5.add(new Province(41, "河南省"));
            provincesInArea5.add(new Province(14, "山西省"));
            provincesInArea5.add(new Province(15, "内蒙古"));
            area5.setProvincesInArea(provincesInArea5);
            areaList.add(area5);

            Area area6 = new Area();
            area6.setAreaName("长江中游地区");
            List<Province> provincesInArea6 = new ArrayList<Province>();
            provincesInArea6.add(new Province(43, "湖南省"));
            provincesInArea6.add(new Province(42, "湖北省"));
            provincesInArea6.add(new Province(36, "江西省"));
            provincesInArea6.add(new Province(34, "安徽省"));
            area6.setProvincesInArea(provincesInArea6);
            areaList.add(area6);

            Area area7 = new Area();
            area7.setAreaName("西南地区");
            List<Province> provincesInArea7 = new ArrayList<Province>();
            provincesInArea7.add(new Province(45, "广西"));
            provincesInArea7.add(new Province(53, "云南省"));
            provincesInArea7.add(new Province(52, "贵州省"));
            provincesInArea7.add(new Province(51, "四川省"));
            provincesInArea7.add(new Province(50, "重庆市"));
            area7.setProvincesInArea(provincesInArea7);
            areaList.add(area7);

            Area area8 = new Area();
            area8.setAreaName("西北地区");
            List<Province> provincesInArea8 = new ArrayList<Province>();
            provincesInArea8.add(new Province(62, "甘肃省"));
            provincesInArea8.add(new Province(63, "青海省"));
            provincesInArea8.add(new Province(64, "宁夏"));
            provincesInArea8.add(new Province(54, "西藏"));
            provincesInArea8.add(new Province(65, "新疆"));
            area8.setProvincesInArea(provincesInArea8);
            areaList.add(area8);

            whereaboutgoCodeList = commonCodeMapper.getWhereaboutgoCodeList();
            collegeCodeList = commonCodeMapper.getCollegeCodeList();
            majorCodeList = commonCodeMapper.getMajorCodeList();
            sexCodeList = commonCodeMapper.getSexCodeList();
            nationCodeList = commonCodeMapper.getNationCodeList();
            difficultyCodeList = commonCodeMapper.getDifficultyCodeList();
            politicalCodeList = commonCodeMapper.getPoliticalCodeList();
            normalCodeList = commonCodeMapper.getNormalCodeList();
            placeCodeList = commonCodeMapper.getPlaceCodeList();
            insModeCodeList = commonCodeMapper.getInsModeCodeList();
            insFieldCodeList = commonCodeMapper.getInsFieldCodeList();
            trainingModeCodeList = commonCodeMapper.getTrainingModeCodeList();
            jobCodeList = commonCodeMapper.getJobCodeList();
            reportCodeList = commonCodeMapper.getReportCodeList();

            cityList = new HashMap<Integer, List<PlaceCode>>();
            for (int i = 0; i < placeCodeList.size(); i++) {
                if (cityList.containsKey(placeCodeList.get(i).getPlaceId() / 10000)) {
                    List<PlaceCode> places = cityList.get(placeCodeList.get(i).getPlaceId() / 10000);
                    places.add(placeCodeList.get(i));
                    cityList.put(placeCodeList.get(i).getPlaceId() / 10000, places);
                } else {
                    List<PlaceCode> places = new ArrayList<PlaceCode>();
                    places.add(placeCodeList.get(i));

                    int provinceCode = placeCodeList.get(i).getPlaceId() / 10000;
                    cityList.put(provinceCode, places);
                }
            }

            collegeMap = new HashMap<Integer, String>();
            _collegeMap = new HashMap<>();
            sexMap = new HashMap<Integer, String>();
            _sexMap = new HashMap<>();
            majorDivisionMap = new HashMap<String, String>();
            majorDivisionMap1 = new HashMap<>();
            majorNameMap = new HashMap<String, String>();
            majorNameMap1 = new HashMap<String, String>();
            normalStuMap = new HashMap<Integer, String>();
            _normalStuMap = new HashMap<>();
            provinceMap = new HashMap<Integer, String>();
            difficultyMap = new HashMap<Integer, String>();
            _difficultyMap = new HashMap<String, Integer>();
            insModeMap = new HashMap<>();
            insFieldMap = new HashMap<>();
            nationMap = new HashMap<>();
            _nationMap = new HashMap<>();
            politicalMap = new HashMap<>();
            _politicalMap = new HashMap<>();
            whereaboutgoMap = new HashMap<>();
            _whereaboutgoMap = new HashMap<>();
            regionMap = new HashMap<>();
            placeClassMap = new HashMap<>();
            _placeClassMap = new HashMap<>();

            for( int i = 0 ; i < placeCodeList.size() ; i ++ ) {
                placeClassMap.put( placeCodeList.get(i).getPlaceId() , placeCodeList.get(i).getPlaceClass() );
            }

            for( int i = 0 ; i < placeClassList.size() ; i ++ ) {
                _placeClassMap.put(  placeCodeList.get(i).getPlaceClass() , placeCodeList.get(i).getPlaceId()  ) ;
            }

            for (int i = 0; i < collegeCodeList.size(); i++) {
                collegeMap.put(collegeCodeList.get(i).getCollegeId(), collegeCodeList.get(i).getCollege());
                _collegeMap.put(collegeCodeList.get(i).getCollege(), collegeCodeList.get(i).getCollegeId());
            }
            for (int i = 0; i < sexCodeList.size(); i++) {
                sexMap.put(sexCodeList.get(i).getSexId(), sexCodeList.get(i).getSex());
                _sexMap.put(sexCodeList.get(i).getSex(), sexCodeList.get(i).getSexId());
            }
            for (int i = 0; i < majorDivisionList.size(); i++) {
                majorDivisionMap.put(majorDivisionList.get(i).getMajorDivisionCode(), majorDivisionList.get(i).getMajorDivisionName());
            }
            for (int i = 0; i < majorDivisionList.size(); i++) {
                majorDivisionMap1.put(majorDivisionList1.get(i).getMajorDivisionCode(), majorDivisionList1.get(i).getMajorDivisionName());
            }
            for (int i = 0; i < majorCodeList.size(); i++) {
                if( majorCodeList.get(i).getQualification().equals("本科专业"))
                    majorNameMap.put(majorCodeList.get(i).getMajorId(), majorCodeList.get(i).getMajorName());
                if( majorCodeList.get(i).getQualification().equals("研究生专业"))
                    majorNameMap1.put(majorCodeList.get(i).getMajorId(), majorCodeList.get(i).getMajorName());
            }
            for (int i = 0; i < normalCodeList.size(); i++) {
                normalStuMap.put(normalCodeList.get(i).getNormalStuId(), normalCodeList.get(i).getNormalStu());
                _normalStuMap.put(normalCodeList.get(i).getNormalStu(), normalCodeList.get(i).getNormalStuId());
            }
            for (int i = 0; i < provinceList.size(); i++) {
                provinceMap.put(provinceList.get(i).getProvinceCode(), provinceList.get(i).getProvinceName());

                boolean find = false;
                for (int j = 0; j < areaList.size(); j++) {
                    for (int k = 0; k < areaList.get(j).getProvincesInArea().size(); k++) {
                        if (provinceList.get(i).getProvinceCode() == areaList.get(j).getProvincesInArea().get(k).getProvinceCode()) {
                            regionMap.put(provinceList.get(i).getProvinceCode(), areaList.get(j).getAreaName());
                            find = true;
                            break;
                        }
                    }
                    if (find == true) break;
                }
            }
            for (int i = 0; i < difficultyCodeList.size(); i++) {
                difficultyMap.put(difficultyCodeList.get(i).getDifficultyId(), difficultyCodeList.get(i).getDifficultyMode());
                _difficultyMap.put(difficultyCodeList.get(i).getDifficultyMode(), difficultyCodeList.get(i).getDifficultyId());
            }

            for( int i = 0 ; i < insModeCodeList.size() ; i ++ ) {
                insModeMap.put(insModeCodeList.get(i).getInsModeId(),insModeCodeList.get(i).getInsMode());
            }

            for( int i = 0 ; i < insFieldCodeList.size() ; i ++ ) {
                insFieldMap.put(insFieldCodeList.get(i).getInsFieldId(),insFieldCodeList.get(i).getInsField());
            }

            for (int i = 0; i < nationCodeList.size(); i++) {
                nationMap.put(nationCodeList.get(i).getNationId(), nationCodeList.get(i).getNation());
                _nationMap.put(nationCodeList.get(i).getNation(), nationCodeList.get(i).getNationId());
            }
            for (int i = 0; i < politicalCodeList.size(); i++) {
                politicalMap.put(politicalCodeList.get(i).getPoliticalStandId(), politicalCodeList.get(i).getPoliticalStand());
                _politicalMap.put(politicalCodeList.get(i).getPoliticalStand(), politicalCodeList.get(i).getPoliticalStandId());
            }
            for (int i = 0; i < whereaboutgoCodeList.size(); i++) {
                whereaboutgoMap.put(whereaboutgoCodeList.get(i).getWhereAboutGoId(), whereaboutgoCodeList.get(i).getWhereAboutGo());
                _whereaboutgoMap.put(whereaboutgoCodeList.get(i).getWhereAboutGo(),whereaboutgoCodeList.get(i).getWhereAboutGoId());
            }
        }
        catch (Exception ee ) {
            LogClerk.errLog.error( ee );
            System.out.println( ee );
        }
    }

    @Override
    public List<WhereAboutGoCode> getWhereaboutgoCodeList(){
        ArrayList<WhereAboutGoCode> WhereAboutGoCodeList =(ArrayList<WhereAboutGoCode>)whereaboutgoCodeList;
        return (List<WhereAboutGoCode>) WhereAboutGoCodeList.clone();
    }

    @Override
    public List<CollegeCode> getCollegeCodeList(){
        ArrayList<CollegeCode> CollegeCodeList = (ArrayList<CollegeCode>)collegeCodeList;
        return (List<CollegeCode>) CollegeCodeList.clone();
    }

    @Override
    public List<String> getQuanlificationStudyList(){
        ArrayList<String> QuanlificationStudyList = (ArrayList<String>) quanlificationStudyList;
        return (List<String>) QuanlificationStudyList.clone();
    }

    public List<MajorDivision> getMajorDivisionList(){
        ArrayList<MajorDivision> list = (ArrayList<MajorDivision>) majorDivisionList;
        return (List<MajorDivision>) list.clone();
    }

    @Override
    public List<MajorCode> getMajorCodeList(){
        ArrayList<MajorCode> list = ( ArrayList<MajorCode>) majorCodeList;
        return (List<MajorCode>) list.clone();
    }

    @Override
    public List<SexCode> getSexCodeList(){
        ArrayList<SexCode> list = (ArrayList<SexCode>) sexCodeList;
        return (List<SexCode>) list.clone();
    }

    @Override
    public List<NationCode> getNationCodeList() {
        ArrayList<NationCode> list = (ArrayList<NationCode>) nationCodeList;
        return (List<NationCode>) list.clone();
    }

    @Override
    public List<DifficultyCode> getDifficultyCodeList(){
        ArrayList<DifficultyCode> list = (ArrayList<DifficultyCode>) difficultyCodeList;
        return (List<DifficultyCode>) list.clone();
    }

    @Override
    public List<PoliticalCode> getPoliticalCodeList(){
        ArrayList<PoliticalCode> list = (ArrayList<PoliticalCode>) politicalCodeList;
        return (List<PoliticalCode>) list.clone();
    }

    @Override
    public List<NormalCode> getNormalCodeList(){
        ArrayList<NormalCode> list = (ArrayList<NormalCode>) normalCodeList;
        return (List<NormalCode>) list.clone();
    }

    @Override
    public List<PlaceCode> getPlaceCodeList(){
        ArrayList<PlaceCode> list = (ArrayList<PlaceCode>) placeCodeList;
        return (List<PlaceCode>) list.clone();
    }

    @Override
    public List<InsModeCode> getInsModeCodeList(){
        ArrayList<InsModeCode> list = (ArrayList<InsModeCode>) insModeCodeList;
        return (List<InsModeCode>) list.clone();
    }

    @Override
    public List<InsFieldCode> getInsFieldCodeList() {
        ArrayList<InsFieldCode> list = (ArrayList<InsFieldCode>) insFieldCodeList ;
        return (List<InsFieldCode>) list.clone();
    }

    @Override
    public List<PlaceClass> getPlaceClassList() {
        ArrayList<PlaceClass> list = (ArrayList<PlaceClass>) placeClassList;
        return (List<PlaceClass>) list.clone();
    }

    @Override
    public List<Province> getProvinceList(){
        ArrayList<Province> list = (ArrayList<Province>) provinceList;
        return (List<Province>) list.clone();
    }

    @Override
    public List<Area> getAreaList() {
        ArrayList<Area> list = (ArrayList<Area>) areaList;
        return (List<Area>) list.clone();
    }

    @Override
    public List<TrainingModeCode> getTrainingModeCode() {
        ArrayList<TrainingModeCode> list = (ArrayList<TrainingModeCode>) trainingModeCodeList;
        return (List<TrainingModeCode>) list.clone();
    }

    @Override
    public HashMap<Integer,List<PlaceCode> > getCityList(){
        return (HashMap<Integer,List<PlaceCode> >)cityList.clone();
    }

    @Override
    public List<JobCode> getJobCodeList() {
        ArrayList<JobCode> list = (ArrayList<JobCode>) jobCodeList;
        return (List<JobCode>) list.clone();
    }

    @Override
    public List<ReportCode> getReportCodeList() {
        ArrayList<ReportCode> list = (ArrayList<ReportCode>) reportCodeList;
        return (List<ReportCode>) list.clone();
    }

    @Override
    public CollegeCode getCollegeCodeById(int id) {
        return commonCodeMapper.getCollegeCodeById(id);
    }

    @Override
    public String getCollegeName( Integer collegeId ) {
        if( collegeId == null ) return null;
        return collegeMap.get(collegeId) ;
    }

    @Override
    public Integer getCollegeId( String college ) {
        if( college == null || college == "" ) return null;
        return _collegeMap.get( college ) ;
    }

    @Override
    public String getSex( Integer sexId ) {
        if( sexId == null ) return null;
        return sexMap.get( sexId ) ;
    }

    @Override
    public Integer getSexId( String sex ) {
        if( sex == null || sex == "" ) return null;
        return _sexMap.get( sex ) ;
    }

    @Override
    public String getMajorDivision( String majorCode , String qualification ) {
        if( majorCode != null && majorCode.length() > 1 ) {
            String majorDivisionCode = majorCode.substring( 0 , 2 ) ;
            if( qualification.equals("本科")) {
                return majorDivisionMap1.get( majorDivisionCode) ;
            }
            else {
                return majorDivisionMap.get( majorDivisionCode ) ;
            }
        }
        else return null;
    }

    @Override
    public String getMajorName( String majorCode , String qualification ) {
        if( majorCode == null || majorCode == "" ) return null;
        if( qualification.equals("本科")) return majorNameMap.get( majorCode );
        else return majorNameMap1.get( majorCode);
    }

    @Override
    public String getNormalStu( Integer normalStuId ) {
        if( normalStuId == null ) return null ;
        return normalStuMap.get( normalStuId ) ;
    }

    @Override
    public Integer getNormalStuId( String normalStu ) {
        if( normalStu == "" || normalStu == null) return null;
        return _normalStuMap.get( normalStu );
    }

    @Override
    public String getProvince( Integer placeId ) {
        if( placeId == null ) return null;
        return provinceMap.get( placeId/ 10000 ) ;
    }

    @Override
    public String getDifficultyMode( Integer difficultyId ) {
        if( difficultyId == null ) return null ;
        return difficultyMap.get(difficultyId);
    }

    @Override
    public Integer getDifficultyId( String DifficultyMode ) {
        if( DifficultyMode == null || DifficultyMode == "" ) return null;
        return _difficultyMap.get(DifficultyMode);
    }

    @Override
    public String getNation( String nationId ) {
        if( nationId == null || nationId == "" ) return null ;
        return nationMap.get(nationId) ;
    }

    @Override
    public String getNationId( String nation) {
        if( nation == null || nation == "" ) return null;
        return _nationMap.get(nation);
    }

    @Override
    public String getPolitical( String politicalId ) {
        if( politicalId == null || politicalId == "" ) return null;
        return politicalMap.get(politicalId) ;
    }

    @Override
    public String getPoliticalId( String political ) {
        if( political == null || political == "" ) return null;
        return _politicalMap.get(political);
    }

    @Override
    public String getWhereAboutGo( Integer whereaboutgoId ) {
        if( whereaboutgoId == null ) return null;
        return whereaboutgoMap.get( whereaboutgoId ) ;
    }

    @Override
    public Integer getWhereAboutGoId( String whereaboutgo) {
        if( whereaboutgo == null || whereaboutgo == "" ) return null;
        return _whereaboutgoMap.get(whereaboutgo);
    }

    @Override
    public String getRegion( Integer placeId ) {
        if( placeId == null ) return null ;
        return regionMap.get( placeId/10000 );
    }

    @Override
    public String getPlaceClass( Integer PlaceId ) {
        return placeClassMap.get(PlaceId);
    }

    @Override
    public Integer getPlaceClassId( String placeClass ) {
        return _placeClassMap.get( placeClass ) ;
    }

    @Override
    public List<PlaceCode> listShownameByProvinceId(int provinceId) throws SSException {
        try{
            return commonCodeMapper.listShownameByProvinceId(provinceId);
        }catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public String getInsMode( Integer insModeId){
        if( insModeId == null )return null;
        return insModeMap.get(insModeId);
    }

    @Override
    public String getInsField( Integer insFieldId){
        if( insFieldId == null ) return null;
        return insFieldMap.get(insFieldId);
    }

}

