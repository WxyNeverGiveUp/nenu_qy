package com.pandawork.nenu.oa.web.controller.student.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ReportCardController
 *
 * @author wlm
 * @date 2017/1/8 20:01
 */

@Controller
@RequestMapping(value = URLConstants.STUDENT_REPORT_CARD_URL)
public class ReportCardController extends AbstractController{

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String toDetail(Model model) {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            Integer statusId = stuStatusInfoService.queryIdByStuNumber(stuNumber);
            ReportCard reportCard = new ReportCard();
            if (Assert.isNotNull(statusId)) {
                reportCard = reportCardService.queryByStatusId(statusId);
                if (Assert.isNotNull(reportCard) && Assert.isNotNull(reportCard.getId())) {
                    model.addAttribute("isPrinted", "是");
                } else {
                    model.addAttribute("isPrinted", "否");
                }
            }
            model.addAttribute("reportCard", reportCard);
            return "student/dispatch/report_card";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }
}
