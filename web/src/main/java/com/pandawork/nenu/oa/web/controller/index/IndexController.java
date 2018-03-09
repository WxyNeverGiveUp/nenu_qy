package com.pandawork.nenu.oa.web.controller.index;


import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dujuan on 2014/9/28.
 */
@Controller
public class IndexController extends AbstractController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        if (username.matches("[0-9]+")) {
            return "student/status/index";
        } else {
            return "redirect:/admin/status/info/list";
        }
    }
}
