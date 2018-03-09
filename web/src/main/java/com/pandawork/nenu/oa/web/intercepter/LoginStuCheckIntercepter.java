package com.pandawork.nenu.oa.web.intercepter;

import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginStuCheckIntercepter
 *
 * @author wlm
 * @date 2016/9/8 13:57
 */
public class LoginStuCheckIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    StuStatusInfoService stuStatusInfoService;

    private String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
//        System.out.println(requestUrl);
        if (null != allowUrls && allowUrls.length >= 1) {
            for (String url : allowUrls) {
                if (requestUrl.contains(url)) {
                    return true;
                }
            }
        }

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/student/info/error";

        Subject subject = SecurityUtils.getSubject();
        if (Assert.isNull(subject.getPrincipal())) {
            return false;
        }
        String username = subject.getPrincipal().toString();
        boolean isNum = username.matches("[0-9]+");

        if (isNum && Assert.isNull(stuStatusInfoService.queryIdByStuNumber(username)) && !requestUrl.contains("/student/info/error")) {
            response.sendRedirect(url);
            return false;
        }

        return true;
    }
}
