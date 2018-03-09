package com.pandawork.nenu.oa.web.controller.login;

import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.entity.user.StudentUser;
import com.pandawork.nenu.oa.common.entity.user.StudentUserRole;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.util.LoginUtil;
import com.pandawork.nenu.oa.service.user.UserService;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * 登录相关Controller
 * Created by erdan on 2014/9/17.
 */
@Controller
@RequestMapping("")
public class LoginController extends AbstractController{

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest req, Model model){
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login/login";
    }


    @RequestMapping(value = "/unauthorized",method = RequestMethod.GET)
    public String toUnauthorized(){
        return "login/unauthorized";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String  login(int graduateYear, User user, Model model){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        String error = null;
        try {
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("graduateYear", graduateYear);
//            if (user.getUsername().matches("[0-9]+")) {
//                StudentUser studentUser1 = studentUserService.queryByStuNumber(user.getUsername());
//                studentUserRoleService.newRoleRelation(new StudentUserRole(studentUser1.getId(), 6));
//                session.setAttribute("realName", studentUser1.getName());
            if (user.getUsername().matches("[0-9]+") && LoginUtil.isUserExist(user.getUsername())) {
                // 登录密码
//                if (!LoginUtil.checkPassword(user.getUsername(),user.getPassword())){
//                    throw new Exception("用户名或密码错误");
//                }
                StudentUser studentUser = new StudentUser();
                studentUser.setStuNumber(user.getUsername());
                studentUser.setName(LoginUtil.getNameById(user.getUsername()));
                StudentUser studentUser1 = studentUserService.newStudentUser(studentUser);
                studentUserRoleService.newRoleRelation(new StudentUserRole(studentUser1.getId(), 6));
                session.setAttribute("realName", studentUser1.getName());
            } else {
                session.setAttribute("realName", userService.queryByUsername((String) subject.getPrincipal()).getRealName());
            }
            return "redirect:/";
        } catch (Exception e) {
            error = "用户名或密码错误";
        }
        model.addAttribute("error",error);
        return "login/login";
    }


//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public String toTest(){
//        return "login/test";
//    }
//
//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    public @ResponseBody  JSONObject getName(String year,String type){
//        JSONObject json = new JSONObject();
//        json.put("taskName",year + type);
//        return json;
//    }
}
