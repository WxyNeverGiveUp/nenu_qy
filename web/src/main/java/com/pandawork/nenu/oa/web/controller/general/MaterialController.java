package com.pandawork.nenu.oa.web.controller.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.service.general.MaterialService;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MaterialController
 *
 * @author wlm
 * @date 2016/9/19 16:17
 */

@Controller
@RequestMapping(value = URLConstants.GENERAL_MATERIAL)
public class MaterialController extends AbstractController {

    @Autowired
    MaterialService materialService;

    /**
     * ajax重命名上传的材料
     *
     * @param id
     * @param picName
     * @return
     */
    @RequestMapping(value = "ajax/rename")
    @ResponseBody
    public JSONObject Rename(@RequestParam("id") int id, @RequestParam("picName") String picName) {
        try {
            materialService.updateName(id, picName);
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * ajax删除上传的材料
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "ajax/del")
    @ResponseBody
    public JSONObject Delete(@RequestParam("id") int id) {
        try {
            materialService.delById(id);
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }
}
