//package com.pandawork.nenu.data;
//
//import com.pandawork.nenu.AbstractTestCase;
//import com.pandawork.nenu.oa.common.dto.data.EmploymentRateDTO;
//import com.pandawork.nenu.oa.common.dto.data.EmploymentStructureDTO;
//import com.pandawork.nenu.oa.common.dto.data.InformationStatisticDTO;
//import com.pandawork.nenu.oa.common.dto.data.StudentStructureDTO;
//import com.pandawork.nenu.oa.common.entity.data.InformationStatistic;
//import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
//import com.pandawork.nenu.oa.mapper.data.InformationStatisticMapper;
//import com.pandawork.nenu.oa.service.data.InformationStatisticService;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
///**
// * user: lishicao
// * date 15/5/4
// * time 下午4:39
// */
//public class InformationStatisticServiceTest extends AbstractTestCase {
//    @Autowired
//    InformationStatisticService informationStatisticService;
//
//    @Test
//    public void testAdd() {
//        StudentInfomation studentInfomation = new StudentInfomation();
//        studentInfomation.setStudentId( 1291410019);
//        studentInfomation.setName( "李世操");
//        informationStatisticService.add(studentInfomation);
//    }
//
//    @Test
//    public void testDelete() {
//        informationStatisticService.delete(1291410019);
//    }
//
//    @Test
//    public void testUpdate() {
//        StudentInfomation studentInfomation = new StudentInfomation();
//        studentInfomation.setStudentId(1211410037);
//        studentInfomation.setName("哈哈哈");
//        informationStatisticService.update(studentInfomation);
//
//    }
//
//    @Test
//    public void testGetStudentStructure() {
//        long start =System.currentTimeMillis();
//
//        int sum = 0 ;
//        StudentStructureDTO studentStructureDTO = informationStatisticService.getStudentStructure( "major_name" , "sex" , "" , "" , 2015 , 2015 );
//
//        System.out.print("              ");
//        for( int i = 0 ; i < studentStructureDTO.getRows().get(0).getTuples().size() ; i ++ ) {
//            System.out.print(studentStructureDTO.getRows().get(0).getTuples().get(i).getTupleName() + "            ");
//        }
//        System.out.println();
//        for( int i = 0 ; i < studentStructureDTO.getRows().size() ; i ++ ) {
//            System.out.print( studentStructureDTO.getRows().get(i).getRowName() + "   ");
//            for( int j = 0 ;j < studentStructureDTO.getRows().get(i).getTuples().size() ; j ++ ) {
//                System.out.print(studentStructureDTO.getRows().get(i).getTuples().get(j).getCount() + "  "
//                                +studentStructureDTO.getRows().get(i).getTuples().get(j).getPercentage()*100 + "%        ");
//                if( j < studentStructureDTO.getRows().get(i).getTuples().size() - 1 ) sum += studentStructureDTO.getRows().get(i).getTuples().get(j).getCount();
//            }
//            System.out.println();
//        }
//
//        studentStructureDTO.sort(0,true);
//
//        System.out.print("              ");
//        for( int i = 0 ; i < studentStructureDTO.getRows().get(0).getTuples().size() ; i ++ ) {
//            System.out.print(studentStructureDTO.getRows().get(0).getTuples().get(i).getTupleName() + "         ");
//        }
//        System.out.println();
//        for( int i = 0 ; i < studentStructureDTO.getRows().size() ; i ++ ) {
//            System.out.print( studentStructureDTO.getRows().get(i).getRowName() + "   ");
//            for( int j = 0 ;j < studentStructureDTO.getRows().get(i).getTuples().size() ; j ++ ) {
//                System.out.print(studentStructureDTO.getRows().get(i).getTuples().get(j).getCount() + "  "
//                        +studentStructureDTO.getRows().get(i).getTuples().get(j).getPercentage()*100 + "%        ");
//                if( j < studentStructureDTO.getRows().get(i).getTuples().size() - 1 ) sum += studentStructureDTO.getRows().get(i).getTuples().get(j).getCount();
//            }
//            System.out.println();
//        }
//        long end =System.currentTimeMillis();
//
//        System.out.println(sum);
//        System.out.println("\n" + ( end - start ) );
//    }
//
//    @Test
//    public void testGetEmploymentRate() {
//        EmploymentRateDTO employmentRateDTO = informationStatisticService.getEmploymentRate( "major_name", "" , "" , "" , 2015 , 2015 );
//        System.out.print("                ");
//        for( int i = 0 ; i < employmentRateDTO.getRows().get(0).getTuples().size() ; i ++ ) {
//            System.out.print(employmentRateDTO.getRows().get(0).getTuples().get(i).getTupleName() + "                    ");
//        }
//        System.out.println();
//        for( int i = 0 ; i < employmentRateDTO.getRows().size() ; i ++ ) {
//            System.out.print(employmentRateDTO.getRows().get(i).getRowName() + "     ");
//            for( int j = 0 ; j < employmentRateDTO.getRows().get(i).getTuples().size() ; j ++ ) {
//                System.out.print(employmentRateDTO.getRows().get(i).getTuples().get(j).getEmploymentCount() + "  "
//                        + employmentRateDTO.getRows().get(i).getTuples().get(j).getGraduationCount() + "  "
//                        + employmentRateDTO.getRows().get(i).getTuples().get(j).getEmploymentRate() * 100 + "%       ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    public void testGetPlanEmploymentRate(){
//        EmploymentRateDTO employmentRateDTO = informationStatisticService.getPlanEmploymentRate("major_name", "", "", "", 2015, 2015);
//        System.out.print("                ");
//        for( int i = 0 ; i < employmentRateDTO.getRows().get(0).getTuples().size() ; i ++ ) {
//            System.out.print(employmentRateDTO.getRows().get(0).getTuples().get(i).getTupleName() + "                    ");
//        }
//        System.out.println();
//        for( int i = 0 ; i < employmentRateDTO.getRows().size() ; i ++ ) {
//            System.out.print(employmentRateDTO.getRows().get(i).getRowName() + "     ");
//            for( int j = 0 ; j < employmentRateDTO.getRows().get(i).getTuples().size() ; j ++ ) {
//                System.out.print(employmentRateDTO.getRows().get(i).getTuples().get(j).getEmploymentCount() + "  "
//                        + employmentRateDTO.getRows().get(i).getTuples().get(j).getGraduationCount() + "  "
//                        + employmentRateDTO.getRows().get(i).getTuples().get(j).getEmploymentRate() * 100 + "%       ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    public void testGetEmployStructure() {
//        EmploymentStructureDTO employmentStructureDTO = informationStatisticService.getEmployStructure("college_name", "whereaboutgo", "", "", 2015, 2015);
//        System.out.print( "               " );
//        for( int i = 0 ; i < employmentStructureDTO.getRows().get(0).getTuples().size() ; i ++ ) {
//            System.out.print(employmentStructureDTO.getRows().get(0).getTuples().get(i).getTupleName() + "               ");
//        }
//        System.out.println();
//        for( int i = 0 ; i < employmentStructureDTO.getRows().size() ; i ++ ) {
//            System.out.print(employmentStructureDTO.getRows().get(i).getRowName() + "    ");
//            for( int j = 0 ; j < employmentStructureDTO.getRows().get(i).getTuples().size() ; j ++ ) {
//                if( j < employmentStructureDTO.getRows().get(i).getTuples().size() - 1 )
//                    System.out.print(employmentStructureDTO.getRows().get(i).getTuples().get(j).getEmploymentCount() + " "
//                            + employmentStructureDTO.getRows().get(i).getTuples().get(j).getPercentage() * 100 + "%              ");
//                else System.out.print(employmentStructureDTO.getRows().get(i).getTuples().get(j).getGraduationCount() + " "
//                        + employmentStructureDTO.getRows().get(i).getTuples().get(j).getPercentage() * 100 + "%                ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    public void testGetStructureExcel() {
//        StudentStructureDTO studentStructureDTO = informationStatisticService.getStudentStructure("college_name", "", "", "", 2015, 2015) ;
//        File file = new File( "/Users/lishicao/Desktop/Structure.xls") ;
//        HSSFWorkbook workbook = studentStructureDTO.getExcel();
//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            workbook.write(outputStream);
//            outputStream.close();
//        }
//        catch (Exception ee ) {
//            System.out.println( ee.getMessage());
//        }
//    }
//
//    @Test
//    public void testGetEmploymenRateExcel() {
//        EmploymentRateDTO employmentRateDTO = informationStatisticService.getEmploymentRate( "college_name" , "sex" , "" ,"" , 2015 , 2015  );
//        File file = new File( "/Users/lishicao/Desktop/Structure.xls") ;
//        HSSFWorkbook workbook = employmentRateDTO.getExcel();
//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            workbook.write(outputStream);
//            outputStream.close();
//        }
//        catch (Exception ee ) {
//            System.out.println( ee.getMessage());
//        }
//    }
//
//    @Test
//    public void testGetEmploymentStructureExcel() {
//        EmploymentStructureDTO employmentStructureDTO = informationStatisticService.getEmployStructure("college_name", "whereaboutgo", "", "", 2015, 2015);
//        File file = new File( "/Users/lishicao/Desktop/Structure.xls") ;
//        HSSFWorkbook workbook = employmentStructureDTO.getExcel();
//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            workbook.write(outputStream);
//            outputStream.close();
//        }
//        catch (Exception ee ) {
//            System.out.println( ee.getMessage());
//        }
//    }
//}
