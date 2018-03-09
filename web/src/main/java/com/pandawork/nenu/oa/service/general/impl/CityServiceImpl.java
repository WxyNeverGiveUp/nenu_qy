package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.general.CityProperty;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CityPropertyMapper;
import com.pandawork.nenu.oa.service.general.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * CityServiceImpl
 * 市service实现
 *
 * @author chenwy
 * @date 2017/5/15 15:34
 */
@Service("CityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityPropertyMapper cityPropertyMapper;

    @Override
    public List<CityProperty> listByProvinceId(int provinceId) throws SSException{
      List<CityProperty> cityProperties =  Collections.emptyList();
      try{
          cityProperties = cityPropertyMapper.listByProvinceId(provinceId);
      }catch (Exception e){
          LogClerk.errLog.error(e);
          throw SSException.get(OaException.ListCitiesByProvinceIdFailed, e);
      }
      return cityProperties;
    }

    @Override
    public List<CityProperty> listAll() throws Exception{
        List<CityProperty> cityProperties =  Collections.emptyList();
        try{
            cityProperties = cityPropertyMapper.listAll();
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListAllCitiesFailed, e);
        }
        return cityProperties;
    }

    @Override
    public String queryCityById(int id) throws SSException{
        try{
            return cityPropertyMapper.queryCityById(id);
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.queryCityNameFailed, e);
        }
    }
}
