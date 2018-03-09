package com.pandawork.nenu.oa.mapper.company;

import com.pandawork.nenu.oa.common.dto.company.CompanyDto;
import com.pandawork.nenu.oa.common.entity.company.Company;

import java.util.List;

/**
 * user: lishicao
 * date 15/12/2
 * time 上午12:16
 */
public interface CompanyMapper {
    public List<Company> listCompany() throws Exception;

    public Company getCompany(int id) throws Exception;

    public List<CompanyDto> listAllCompanyDto() throws Exception;
}
