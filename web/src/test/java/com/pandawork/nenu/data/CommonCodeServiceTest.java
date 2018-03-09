package com.pandawork.nenu.data;

import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.data.Area;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.PlaceClass;
import com.pandawork.nenu.oa.common.dto.data.Province;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.service.data.CommonCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user: lishicao
 * date 15/4/25
 * time 下午12:34
 */
public class CommonCodeServiceTest extends AbstractTestCase {
    @Autowired
    CommonCodeService commonCodeService;

    @Test
    public void testGetCollegeList(){
        List<CollegeCode> list = commonCodeService.getCollegeCodeList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getCollegeId() + "       "  + list.get(i).getCollege());
        }
    }

    @Test
    public void testGetQuanlificationStudyList() {
        List<String> list = commonCodeService.getQuanlificationStudyList();
        for (int i = 0 ; i <list.size() ; i ++ ) {
            System.out.println( list.get(i));
        }
    }

    @Test
    public void testGetMajorDivisionList() {
        List<MajorDivision> list = commonCodeService.getMajorDivisionList();
        for ( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getMajorDivisionCode() + "   " + list.get(i).getMajorDivisionName() );
        }
    }

    @Test
    public void testGetMajorCodeList(){
        List<MajorCode> list = commonCodeService.getMajorCodeList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getMajorId() + "  " + list.get(i).getMajorClass() + "  " +
                                list.get(i).getMajorDivision() + " " + list.get(i).getMajorName() );
        }
    }

    @Test
    public void testGetSexCodeList(){
        List<SexCode> list = commonCodeService.getSexCodeList();
        for ( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getSexId() + " " + list.get(i).getSex());
        }
    }

    @Test
    public void testGetNationCodeList(){
        List<NationCode> list = commonCodeService.getNationCodeList();
        for ( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getNationId() + "  " + list.get(i).getNation());
        }
    }

    @Test
    public void testGetDifficultyCodeList(){
        List<DifficultyCode> list = commonCodeService.getDifficultyCodeList() ;
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getDifficultyId() + " " + list.get(i).getDifficultyMode());
        }
    }

    @Test
    public void testGetPoliticalCodeList() {
        List<PoliticalCode> list = commonCodeService.getPoliticalCodeList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getPoliticalStandId() + " " + list.get(i).getPoliticalStand() );
        }
    }

    @Test
    public void testGetNormalCodeList(){
        List<NormalCode> list = commonCodeService.getNormalCodeList();
        for ( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getNormalStuId() + "  " + list.get(i).getNormalStu());
        }
    }

    @Test
    public void testGetPlaceCodeList(){
        List<PlaceCode> list = commonCodeService.getPlaceCodeList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getPlaceId() + " " + list.get(i).getPlace() + " " +
                                list.get(i).getPlaceClassId() + " " + list.get(i).getPlaceClass() + " " + list.get(i).getShowName());
        }
    }

    @Test
    public void testGetInsModeCodeList(){
        List<InsModeCode> list = commonCodeService.getInsModeCodeList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getInsModeId() + " " + list.get(i).getInsMode() );
        }
    }

    @Test
    public void testGetPlaceClassList() {
        List<PlaceClass> list = commonCodeService.getPlaceClassList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getPlaceClassCode() + " " + list.get(i).getPlaceClassName() );
        }
    }

    @Test
    public void testGetProvinceList() {
        List<Province> list = commonCodeService.getProvinceList();
        for ( int i = 0 ; i < list.size() ; i ++ ) {
            System.out.println( list.get(i).getProvinceCode() + "  " + list.get(i).getProvinceName());
        }
    }

    @Test
    public void testGetAreaList() {
        List<Area> list = commonCodeService.getAreaList();
        for( int i = 0 ; i < list.size() ; i ++ ) {
            Area area = list.get(i) ;
            System.out.println( area.getAreaName() + ":" );
            for( int j = 0; j < area.getProvincesInArea().size() ; j ++ ) {
                System.out.println( "    " + area.getProvincesInArea().get(j).getProvinceName());
            }
        }
    }

    @Test
    public void testGetCityList() {
        HashMap<Integer,List<PlaceCode> > hashMap = commonCodeService.getCityList();
        List<Province> provinces = commonCodeService.getProvinceList();
        for(Map.Entry<Integer,List<PlaceCode>> entry :hashMap.entrySet() ) {
            String name = "" ;
            for( int i = 0 ; i < provinces.size() ; i ++ ) {
                if( entry.getKey().intValue() == provinces.get(i).getProvinceCode() ){
                    name = provinces.get(i).getProvinceName();
                    break;
                }
            }

            System.out.println( entry.getKey() + "   " + name +  ": " );
            for ( PlaceCode placeCode : entry.getValue() ) {
                System.out.println( "    " + placeCode.getShowName() );
            }
        }
    }
}
