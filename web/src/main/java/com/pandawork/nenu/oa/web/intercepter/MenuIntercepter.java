package com.pandawork.nenu.oa.web.intercepter;

import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.web.interceptor.DefualtInitInterceptor;
import com.pandawork.nenu.oa.service.menu.MenuService;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import com.pandawork.nenu.oa.service.user.StudentUserRoleService;
import com.pandawork.nenu.oa.service.user.StudentUserService;
import com.pandawork.nenu.oa.service.user.UserRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lixiang
 * Date: 14-10-17
 * Time: 上午12:28
 * To change this template use File | Settings | File Templates.
 */
public class MenuIntercepter extends DefualtInitInterceptor {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private StudentUserRoleService studentUserRoleService;

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private StuStatusInfoService stuStatusInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        List<Integer> list = new ArrayList<>();
        if (!Assert.isNull(userName)) {
            boolean isNum = userName.matches("[0-9]+");
            if (isNum) {
//                if (LoginUtil.isUserExist(userName)) {
                if (studentUserService.isUserNameExist(userName)) {
                    list = studentUserRoleService.listRolesByUsername(userName);
                }
            } else {
                list = userRoleService.getRolesByUsername(userName);
            }
            List arrayList = menuService.list(list);
            String prefix = "http://yun.dsjyw.net/index.php?user/loginSubmit&name=";
            switch (userName){
                case "jinxin":
                        request.setAttribute("yun",prefix + "jinxin&password=jinxin");
                        break;
                case "liuhaibin":
                        request.setAttribute("yun",prefix + "liuhb&password=liuhb");
                        break;
                case "bihaiyang":
                    request.setAttribute("yun",prefix + "qihua&password=qihua");
                    break;
                case "hanbing":
                    request.setAttribute("yun",prefix + "xinxi&password=xinxi");
                    break;
                case "lijian":
                    request.setAttribute("yun",prefix + "xinxi&password=xinxi");
                    break;
                case "kongjiejun":
                    request.setAttribute("yun",prefix + "kongjj&password=kongjj");
                    break;
                case "zuowenjing":
                    request.setAttribute("yun",prefix + "jiuye&password=jiuye");
                    break;
                case "fengxin":
                    request.setAttribute("yun",prefix + "zonghe&password=zonghe");
                    break;
                case "eyiqiang":
                    request.setAttribute("yun",prefix + "eyq&password=eyq");
                    break;
                default:
            }
            request.setAttribute("menus",arrayList);
        }
        return true;
    }

}
