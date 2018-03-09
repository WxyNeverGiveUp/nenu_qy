package com.pandawork.nenu.oa.service.company.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.company.CompanyDto;
import com.pandawork.nenu.oa.common.entity.company.Company;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.company.CompanyMapper;
import com.pandawork.nenu.oa.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lishicao on 2015/12/2.
 */
@Service( value = "companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    public List<Company> listCompany() throws SSException {
        List<Company> companies ;
        try{
            companies = companyMapper.listCompany();
            return companies;
        }catch (Exception ee ){
            System.out.println( ee.getMessage() );
            throw SSException.get(OaException.JYOACompanyWrong);
        }
    }

    public Company getCompany( Integer id )throws SSException{
        try{
            if( id == null ) return null;
            Company company = companyMapper.getCompany( id );
            return company;
        }catch (Exception ee ){
            System.out.println( ee.getMessage() );
            throw SSException.get(OaException.JYOACompanyWrong);
        }
    }

    @Override
    public List<CompanyDto> listAllCompanyDto() throws SSException {
        try {
            return companyMapper.listAllCompanyDto();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }
}
