package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import com.pandawork.nenu.oa.service.general.PlaceService;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * PlaceServiceTest
 * 地区service测试
 *
 * @author wlm
 * @date 2016/7/27 17:33
 */
public class PlaceServiceTest extends AbstractTestCase {

    @Autowired
    PlaceService placeService;

    @Test
    public void listAll() throws SSException {
        List<PlaceCode> list = placeService.listAll();
        for (PlaceCode PlaceCode : list) {
            System.out.println(PlaceCode.getId() + "  " + PlaceCode.getPlaceId() + "  " + PlaceCode.getPlace());
        }
    }

    @Test
    public void queryById() throws SSException {
        PlaceCode PlaceCode = placeService.queryById(15);
        System.out.println(PlaceCode.getId() + "  " + PlaceCode.getPlaceId() + "  " + PlaceCode.getPlace());
    }

    @Test
    public void queryByCode() throws SSException {
        PlaceCode PlaceCode = placeService.queryByCode("110229");
        System.out.println(PlaceCode.getId() + "  " + PlaceCode.getPlace());
    }

//    @Test
//    public void setPinyin() throws SSException {
//        List<PlaceCode> placeCodeList = placeService.listAll();
//        for (PlaceCode placeCode : placeCodeList) {
//            String convert = "";
//            String str = placeCode.getShowName();
//            for (int j = 0; j < str.length(); j++) {
//                char word = str.charAt(j);
//                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
//                if (pinyinArray != null) {
//                    convert += pinyinArray[0].charAt(0);
//                } else {
//                    convert += word;
//                }
//            }
//            placeCode.setPinyinInitial(convert);
//            placeService.updateById(placeCode);
//            System.out.println(placeCode.getId() + "   " + placeCode.getShowName() + "  " + placeCode.getPinyinInitial());
//        }
//    }
}
