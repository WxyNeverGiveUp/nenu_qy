package com.pandawork.nenu.company;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.company.Company;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.service.company.CompanyService;
import com.pandawork.nenu.oa.service.data.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * user: lishicao
 * date 15/12/2
 * time 上午12:19
 */
public class CompanyTest extends AbstractTestCase {
    @Autowired
    CompanyService companyService;
    @Autowired
    StudentService studentService;
    @Test
    public void testGetCompany(){
        try {
            List<Company> companies = companyService.listCompany();
            for( int i = 0 ; i < companies.size() ; i ++ ){
                Company company = companies.get(i);
                System.out.println( company.getId() + " " + company.getCompanyName() );
            }
            List<StudentInfomation> studentInfomations = studentService.listS();
        }
        catch (SSException ee) {
            System.out.println( ee.getMessage());
        }
    }

    @Test
    public void testGetCompanyById(){
        try{
            Company company = companyService.getCompany(5);
            System.out.println( company.getCompanyName());
        }catch (SSException ee ){
            System.out.println( ee.getMessage());
        }
    }
}
