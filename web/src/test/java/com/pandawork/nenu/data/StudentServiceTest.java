//
//package com.pandawork.nenu.data;
//
//import com.pandawork.core.common.exception.SSException;
//import com.pandawork.nenu.AbstractTestCase;
//import com.pandawork.nenu.oa.common.dto.data.StudentQueryDto;
//import com.pandawork.nenu.oa.common.entity.data.MajorCode;
//import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
//import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
//import com.pandawork.nenu.oa.service.constant.CityService;
//import com.pandawork.nenu.oa.service.data.StudentService;
//import com.pandawork.nenu.oa.service.data.StudentToExcelService;
//import javafx.scene.control.Pagination;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//
///**
// * Created by Hao Zhang on 2015/4/13.
// */
//
//public class StudentServiceTest extends AbstractTestCase {
//    @Autowired
//    StudentService studentService;
//    @Autowired
//    private StudentToExcelService studentToExcelService;
//    @Autowired
//    private CityService cityService;
//
//    @Test
//    public void testAddStudent() throws SSException {
//        StudentInfomation student = new StudentInfomation();
//        student.setName("zhanghao");
//        studentService.addStudent(student);
//    }
//    @Test
//    public void testDeleteStudent() throws SSException {
//        int id = 31987;
//        studentService.deleteStudent(id);
//    }
//
//    @Test
//    public void testListStudents() throws Exception {
//        List<StudentInfomation> l = null;
//        try {
//            l = studentService.listStudents();
//        } catch (SSException e) {
//            e.printStackTrace();
//        }
//        for(StudentInfomation s: l){
//            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void testUpdateStudent() throws Exception {
//        StudentInfomation s = new StudentInfomation();
//        //s.setId(1211410037);
//        s.setId(10);
//        s.setName("asdasdasdsa");
//        s.setCollegeName("asdasdasdd");
//        studentService.updateStudent(s);
//    }
//
//    @Test
//    public void testQuerryById() throws Exception {
//        int id = 10;
//        System.out.println(studentService.getStudentById(id));
//    }
//
//
//
//    /**
//     * TODO fromProvince and goPROVINCE have some bugs.
//     * We cannot do it like such.
//     * Place debug it later.
//     */
//    @Test
//    public void testS() throws Exception {
//
//
//         StudentQueryDto studentQueryDto = new StudentQueryDto();
//        //studentQueryDto.setMajorDevision("工学");
//        //studentQueryDto.setQualification("本科");
//       studentQueryDto.setCollegeName("信息与软件工程学院");
//        //studentQueryDto.setPoliticalStand("中共党员");
//       // studentQueryDto.setNormalStu("免费师范生");
//        //studentQueryDto.setFromProvince("湖北省");
//        //studentQueryDto.setFromPlace("湖北省大悟县");
//        //studentQueryDto.setGoPlaceType("县");
//        //studentQueryDto.setGoArea("西南地区");
//        //studentQueryDto.setIndMode("FUNKSYSTEM");
//        //studentQueryDto.setInsField("FUNKSYSTEM");
//        //studentQueryDto.setIndMode("机关");
//        //studentQueryDto.setWorkDifficultyMode("经济困难");
//       // studentQueryDto.setGraduateClass("2015");
//        //studentQueryDto.setPoliticalStandId("01");
//        int dataCount = studentService.countByCondition(studentQueryDto);
//        com.pandawork.core.util.Pagination page1 = new com.pandawork.core.util.Pagination(dataCount,10,1);
//        List<StudentInfomation> students = studentService.selectStuByCondition(studentQueryDto, page1);
//        for (StudentInfomation ss : students){
//            System.out.println(ss);
//        }
//    }
//    @Test
//    public void testS2() throws Exception {
//        List<StudentInfomation> l = studentService.listS();
//        for (StudentInfomation ss : l){
//            System.out.println(ss);
//        }
//    }
//    @Test
//    public void testP() throws SSException {
//        List<PlaceCode> placeCodes = cityService.listShownameByProvinceId(11);
//
//    }
//
//    @Test
//    public void testma() throws SSException {
//        List<MajorCode> majorCodeList = studentService.getMajorByCollegeName("传媒科学学院" , "本科" );
//        System.out.println();
//    }
//
//}
//
