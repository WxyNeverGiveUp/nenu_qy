package com.pandawork.nenu.oa.common.dto.menu;

import com.pandawork.nenu.oa.common.entity.menu.Resource;

import java.util.List;

/**
 * Created by root on 14-10-15.
 */
public class ResourceDto {

    private Resource resource;

    private List<Resource> childList;


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<Resource> getChildList() {
        return childList;
    }

    public void setChildList(List<Resource> childList) {
        this.childList = childList;
    }
}
