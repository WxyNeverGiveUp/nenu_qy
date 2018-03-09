package com.pandawork.nenu.oa.web.controller.login;

import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * 登出Controller
 * User: chenyt
 * Date: 14-10-3
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("")
public class LogoutController extends AbstractController {

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }

}
