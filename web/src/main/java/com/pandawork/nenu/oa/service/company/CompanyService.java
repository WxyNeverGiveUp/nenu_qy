package com.pandawork.nenu.oa.service.company;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.company.CompanyDto;
import com.pandawork.nenu.oa.common.entity.company.Company;

import java.util.List;

/**
 * Created by lishicao on 2015/12/2.
 */
public interface CompanyService {
    public List<Company> listCompany() throws SSException;
    public Company getCompany( Integer id) throws SSException;
    public List<CompanyDto> listAllCompanyDto() throws SSException;
}
