package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.service.general.MaterialService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * MaterialServiceTest
 *
 * @author wlm
 * @date 2016/7/21 14:56
 */
public class MaterialServiceTest extends AbstractTestCase {

    @Autowired
    MaterialService materialService;

    @Test
    public void newMaterial() throws SSException {
        Material material = new Material();
        material.setStatusInfoId(11);
        material.setMaterialName("222");
        materialService.newMaterial(material);
    }

    @Test
    public void updateName() throws SSException {
        materialService.updateName(11, "2123");
    }

    @Test
    public void queryById() throws SSException {
        Material material = materialService.queryById(11);
        System.out.println(material.getMaterialName());
    }

    @Test
    public void delById() throws SSException {
        materialService.delById(11);
    }

    @Test
    public void list() throws SSException {
        System.out.println(materialService.listByStatusInfoIdAndType(5,1));
    }

    @Test
    public void listByStatusIdAndType() throws SSException {
        List<Material> materialList = materialService.listByStatusInfoIdAndType(1, MaterialTypeEnums.IdentityChange.getId());
        for (Material material : materialList) {
            System.out.println(material.getMaterialName());
        }


//        List<Material> materialList = materialService.listByStatusInfoIdAndType(1, MaterialTypeEnums.IdentityChange.getId());
//        for (Material material : materialList) {
//            String url = material.getMaterialUrl();
//            String a = "http://jysj.dsjyw.local.net/upload/" + url;
//            System.out.println(a);
//            System.out.println(material.getMaterialName());
//        }

    }
}
