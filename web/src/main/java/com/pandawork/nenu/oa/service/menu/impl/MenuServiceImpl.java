package com.pandawork.nenu.oa.service.menu.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.menu.ResourceDto;
import com.pandawork.nenu.oa.common.entity.menu.Resource;
import com.pandawork.nenu.oa.common.entity.menu.RoleResource;
import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.RoleMapper;
import com.pandawork.nenu.oa.service.menu.MenuService;
import com.pandawork.nenu.oa.mapper.menu.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * Created by lixiang on 14-10-14.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMapper roleMapper;

    private static final Map cache = new HashMap();

    //初始化菜单在缓存中
    @PostConstruct
    public void initMenu() throws SSException {
        try {
            if (roleMapper != null) {
                List<Role> roleList = roleMapper.listRoles();

                for (Role role : roleList) {
                    List<RoleResource> listByRoleId = menuMapper.menuByRoleId(role.getId());
                    List<Resource> list1 = new ArrayList();
                    List list3 = new ArrayList();
                    for (RoleResource roleResource : listByRoleId) {
                        Resource resource = menuMapper.getByResourceId(roleResource.getResourceId());
                        if (resource.getParentId() != 0 && resource.getAvailable() != 0) {
                            list1.add(resource);
                        }
                    }
                    for (RoleResource roleResource : listByRoleId) {
                        ResourceDto resourceDto = new ResourceDto();
                        List<Resource> list2 = new ArrayList();
                        Resource resource = menuMapper.getByResourceId(roleResource.getResourceId());
                        if (resource.getParentId() == 0 && resource.getAvailable() != 0) {
                            resourceDto.setResource(resource);//设置父菜单
                            for (Resource resource1 : list1) {
                                if (resource1.getParentId() == resource.getId()) {
                                    list2.add(resource1);
                                }
                            }
                            resourceDto.setChildList(list2);//设置子菜单
                            list3.add(resourceDto);
                        }
                    }
                    //对应角色放入该角色的菜单及子菜单
                    cache.put(role.getId(), list3);
                }
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }


    @Override
    public List list(List<Integer> ids) throws SSException {
        Set<Resource> set = new HashSet();
        Set<Resource> set2 = new HashSet();
        List<Resource> listChild = new ArrayList<>();
        for (Integer id : ids) {
            List<ResourceDto> menuList = (List) cache.get(id);//从缓存中读出该角色的菜单列表
            for (ResourceDto resourceDto : menuList) {
                set2.add(resourceDto.getResource());//合并不重复的父亲菜单
                listChild.addAll(resourceDto.getChildList());
            }
            for (Resource resource : listChild) {
                set.add(resource);//合并不重复的子菜单
            }
        }

        Iterator iterator = set2.iterator();
        List<Resource> listParent = new ArrayList<>();
        List<ResourceDto> resourceDtos = new ArrayList<>();
        while (iterator.hasNext()) {
            Resource resourceParent = (Resource) iterator.next();
            listParent.add(resourceParent);
        }
        Collections.sort(listParent);//对父菜单排序
        for (Resource resource : listParent) {
            List<Resource> listChildren = new ArrayList<>();
            Iterator it = set.iterator();
            ResourceDto resourceDto = new ResourceDto();
            resourceDto.setResource(resource);
            while (it.hasNext()) {
                Resource resource1 = (Resource) it.next();
                if (resource1.getParentId() == resource.getId()) {
                    listChildren.add(resource1);
                }
            }
            Collections.sort(listChildren);//对子菜单排序
            resourceDto.setChildList(listChildren);
            resourceDtos.add(resourceDto);
        }
        return resourceDtos;
    }
}
