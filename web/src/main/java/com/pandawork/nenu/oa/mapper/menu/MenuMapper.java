package com.pandawork.nenu.oa.mapper.menu;

import com.pandawork.nenu.oa.common.entity.menu.Resource;
import com.pandawork.nenu.oa.common.entity.menu.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lixiang on 14-10-14.
 */
public interface MenuMapper {
    //得到所有菜单
    public List<RoleResource> menu() throws Exception;
    //根据角色id得到角色菜单关联
    public List<RoleResource> menuByRoleId(@Param("roleId") Integer roleId) throws Exception;
    //根据菜单id得到菜单
    public Resource getByResourceId(@Param("resourceId") Integer resourceId) throws Exception;
    //根据父亲id得到菜单
    public List<Resource> getResourceByParent(@Param("parentId") Integer parentId) throws Exception;
}
