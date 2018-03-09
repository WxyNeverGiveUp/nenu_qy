//package com.pandawork.nenu.data;
//
//import com.pandawork.nenu.AbstractTestCase;
//import com.pandawork.nenu.oa.service.data.StudentToExcelService;
//import org.apache.poi.hssf.usermodel.*;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.Date;
//
///**
// * user: lishicao
// * date 15/4/8
// * time 下午9:46
// */
//public class StudentToExcelServiceTest extends AbstractTestCase {
//    @Autowired
//    private StudentToExcelService studentToExcelService;
//
//    @Test
//    public void testInputExcel(){
//        String filePath = "/Users/lishicao/Desktop/";
//        String fileName = "2011级在校生.xls" ;
//        File file = new File( filePath+fileName);
//        studentToExcelService.setStudentInfomation( file );
//    }
//
//    @Test
//    public void testGetStudentInformationExcel() {
//        File file = new File( "/Users/lishicao/Desktop/output.xls") ;
//        HSSFWorkbook workbook = studentToExcelService.getStudentInfomation(2012);
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
//    public void testVarifyExcel() {
//        File file = new File( "/Users/lishicao/Desktop/2011级在校生.xls") ;
//        System.out.println(studentToExcelService.varifyStudentExcel( file));
//    }
//}
