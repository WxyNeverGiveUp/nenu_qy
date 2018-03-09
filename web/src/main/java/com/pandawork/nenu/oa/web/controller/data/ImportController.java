package com.pandawork.nenu.oa.web.controller.data;

import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.util.MessageInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * ImportController
 *
 * @author wlm
 * @date 2016/9/6 20:33
 */
@Controller
@RequestMapping(value = URLConstants.ADMIN_IMPORT_URL)
public class ImportController extends AbstractController{

    /**
     * 去导入信息页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toImport() {
        return "admin/manage/import";
    }

    /**
     * 导入学籍信息
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "status/info", method = RequestMethod.POST)
    public String importStatusInfo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        try {
            File excel = new File(file.getOriginalFilename());
//            File excel = new File(request.getSession().getServletContext().getRealPath("/")+file.getOriginalFilename());//本地调试，必须制定特定的存在目录，否则找不到存储文件的位置
            file.transferTo(excel);
            if (importService.checkStuStatusInfo(excel)) {
                importService.importStuStatusInfo(excel);
                excel.delete();
                String successUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.IMPORT_SUCCESS);
                return "redirect:" + successUrl;
            } else {
                excel.delete();
                String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.IMPORT_CHECK_FAILED);
                return "redirect:" + failedUrl;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 导入派遣信息
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "dispatch", method = RequestMethod.POST)
    public String importDispatchInfo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes , HttpServletRequest request) {
        try {
                File excel = new File(file.getOriginalFilename());//服务器端
//                File excel = new File(request.getSession().getServletContext().getRealPath("/")+file.getOriginalFilename());//本地调试，必须制定特定的存在目录，否则找不到存储文件的位置
                file.transferTo(excel);
                if (importService.checkDispatchInfo(excel)) {
                    importService.importDispatchInfo(excel);
                    excel.delete();
                    String successUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                    redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.IMPORT_SUCCESS);
                    return "redirect:" + successUrl;
                } else {
                    excel.delete();
                    String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                    redirectAttributes.addFlashAttribute("errMsg", MessageInfo.IMPORT_CHECK_FAILED);
                    return "redirect:" + failedUrl;
                }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public String importReportCardInfo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            File excel = new File(file.getOriginalFilename());
            //File excel = new File(request.getSession().getServletContext().getRealPath("/")+file.getOriginalFilename());//本地调试，必须制定特定的存在目录，否则找不到存储文件的位置
            file.transferTo(excel);
            if (importService.checkReportCard(excel)) {
                importService.importReportCard(excel);
                excel.delete();
                String successUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.IMPORT_SUCCESS);
                return "redirect:" + successUrl;
            } else {
                excel.delete();
                String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.IMPORT_CHECK_FAILED);
                return "redirect:" + failedUrl;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_IMPORT_URL;
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }
}
