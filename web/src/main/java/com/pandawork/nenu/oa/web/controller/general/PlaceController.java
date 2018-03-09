package com.pandawork.nenu.oa.web.controller.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * PlaceController
 * 地区controller
 *
 * @author wlm
 * @date 2016/7/27 17:37
 */

@Controller
@RequestMapping(value = URLConstants.GENERAL_PLACE)
public class PlaceController extends AbstractController {

    @RequestMapping(value = "get_all", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getArea() {
        JSONArray jsonArray = new JSONArray();
        try {
            List<PlaceCode> placeList = placeService.listAll();
            for (PlaceCode placeCode : placeList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", placeCode.getId());
                jsonObject.put("areacode", placeCode.getPlaceId());
                jsonObject.put("cnName", placeCode.getShowName());
                jsonObject.put("enName", placeCode.getPinyinInitial());
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }
}
