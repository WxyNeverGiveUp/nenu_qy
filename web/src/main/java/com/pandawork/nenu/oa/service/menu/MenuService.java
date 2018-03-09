package com.pandawork.nenu.oa.service.menu;


import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.menu.ResourceDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public interface MenuService {
    //根据用户角色列表得到所有菜单及其子菜单
    public List<ResourceDto> list(List<Integer> ids) throws  SSException;
}
