package com.pandawork.nenu.oa.web.controller.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.user.UserDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.entity.user.UserInfo;
import com.pandawork.nenu.oa.common.enums.general.MajorQualificationEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.MessageInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: chenyt
 * Date: 14-9-22
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = URLConstants.ADMIN_USER_URL)
public class UserController extends AbstractController {
    /**
     * 进入添加一条用户信息
     */
    @RequestMapping(value = "/toUser", method = RequestMethod.GET)
    public String toAddUser(Model model) {
        try {
            List<Role> roles = roleService.listRoles();
            Role role = roleService.getRoleByRoleId(6);
            if (roles.contains(role)) {
                roles.remove(role);
            }
            List<CollegeCode> colleges = commonCodeService.getCollegeCodeList();
            model.addAttribute("roles", roles);
            model.addAttribute("colleges", colleges);
            return "user/addUser";
        } catch (SSException e) {
            sendErrMsg(e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(User user,
                          @RequestParam("roleIds") List<Integer> roleIds,
                          @RequestParam("secondPwd") String secondPwd,
                          String secretaryCollege, String counselorCollege,
                          @RequestParam(value = "qualification", required = false) List<Integer> qualification,
                          @RequestParam(value = "majorCode", required = false) List<String> majorCode,
                          Model model, RedirectAttributes redirectAttributes) {
        if (!user.getPassword().equals(secondPwd)) {
            String failedUrl = "/" + URLConstants.ADMIN_USER_URL + "/toUser";
            redirectAttributes.addFlashAttribute("errMsg", OaException.PasswordNotEqual.getMes());
            return "redirect:" + failedUrl;
        }
        if (roleIds.contains(5)) {
            if (Assert.isNull(majorCode) || majorCode.size() <= 0) {
                String failedUrl = "/" + URLConstants.ADMIN_USER_URL + "/toUser";
                redirectAttributes.addFlashAttribute("errMsg", OaException.CounsellorMajorsNotNull.getMes());
                return "redirect:" + failedUrl;
            }
        }
        try {
            if (!Assert.isEmpty(majorCode) && majorCode.size() > 0) {
                HashSet<String> hs = new HashSet<>(majorCode);
                majorCode.clear();
                majorCode.addAll(hs);
            }
            if (roleIds.contains(4)) {
                user.setCollege(collegeService.queryByCode(secretaryCollege).getCollege());
            } else if (roleIds.contains(5)) {
                user.setCollege(collegeService.queryByCode(counselorCollege).getCollege());
            }
            User newUser = userService.addUser(user);
            userRoleService.addRelationRole(user.getId(), roleIds);
            if (roleIds.contains(4)) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(newUser.getId());
                userInfo.setCollegeCode(secretaryCollege);
                userInfoService.newUserInfo(userInfo);
            }
            if (roleIds.contains(5)) {
                if (!Assert.isNull(majorCode) && majorCode.size() > 0) {
                    for (int i = 0; i < majorCode.size(); i++) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(newUser.getId());
                        userInfo.setCollegeCode(counselorCollege);
                        userInfo.setMajorCode(majorCode.get(i));
                        MajorQualificationEnums majorQualification = MajorQualificationEnums.valueOf(qualification.get(i));
                        userInfo.setMajorQualification(majorQualification.getName());
                        userInfoService.newUserInfo(userInfo);
                    }
                }
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_USER_URL + "/toUser";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
        return "redirect:user/toUserList";
    }

    /**
     * 额外判断一次需要跳转到哪个id的修改密码页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "update/pwd", method = RequestMethod.GET)
    public String toPwd(Model model) {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        try {
            User user = userService.queryByUsername(username);
            if (Assert.isNull(user) || Assert.isNull(user.getId())) {
                model.addAttribute("errMsg", MessageInfo.NO_USER);
                return WebConstants.sysErrorCode;
            }
            return "redirect:/" + URLConstants.ADMIN_USER_URL + "/toUpdatePwd/" + user.getId();
        } catch (SSException e) {
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 跳转到修改密码
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toUpdatePwd/{id}", method = RequestMethod.GET)
    public String toUpdatePwd(@PathVariable(value = "id") int id, Model model) {
        try {
            User user = userService.queryUserById(id);
            model.addAttribute("id", id);
            model.addAttribute("user", user);
        } catch (SSException e) {
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
        return "user/updatePwd";
    }

    /**
     * 修改密码
     *
     * @param id
     * @param secondPwd
     * @param password
     * @param newPwd
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public String updatePwd(@RequestParam int id,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "newPwd") String newPwd,
                            @RequestParam(value = "secondPwd") String secondPwd,
                            Model model) {
        if (!newPwd.equals(secondPwd)) {//判断两次输入的密码是否一致
            model.addAttribute("msg", OaException.PasswordNotEqual.getMes());
            model.addAttribute("id", id);
            return "user/updatePwd";
        }
        try {
            userService.updatePassword(id, password, newPwd);
            model.addAttribute("msg", "密码修改成功");
            model.addAttribute("id", id);
            return "user/updatePwd";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("msg", OaException.PwdWrong.getMes());
            model.addAttribute("id", id);
            return "user/updatePwd";
        }
    }

    /**
     * 跳转到修改用户信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toUpdateUser/{id}", method = RequestMethod.GET)
    public String toUpdateUser(@PathVariable(value = "id") int id, Model model) {
        try {
            User user = userService.queryUserById(id);//根据ID查询用户
            List<String> collegeNow = userInfoService.listCollegesByUserName(user.getUsername());
            //userInfoService用户信息Service
            //listCollegesByUserName根据用户名查询所属专业
            List<CollegeCode> colleges = commonCodeService.getCollegeCodeList();

            model.addAttribute("user", user);//向前端传用户信息
            model.addAttribute("colleges", colleges);//向前端传用户所属学院
            List<Role> roles = roleService.listRoles();//获取所有角色列表
            Role role = roleService.getRoleByRoleId(6);//roleid 6 学生 获取学生角色列表
            if (roles.contains(role)) {//如果包含学生 从列表role中剔除
                roles.remove(role);
            }
            List<Integer> roleIds = userRoleService.listRoleByUserId(id);// listRoleByUserId根据用户Id,获得用户信息
            boolean[] isChecked = new boolean[roles.size()];//布尔类型数组 角色身份判定
            if (roles != null && roles.size() > 0) {//查找roles.ID是否存在
                for (int i = 0; i < isChecked.length; i++) {
                    int j = 0;
                    for (; j < roleIds.size(); j++) {
                        if ((roleIds.get(j)).equals(roles.get(i).getId())) {
                            isChecked[i] = true;
                            break;
                        }
                    }
                    if (j >= roleIds.size()) isChecked[i] = false;
                }
            }
            if (roleIds.contains(4)) {//ID 4 学院副书记
                if (!Assert.isEmpty(collegeNow) && collegeNow.size() > 0) {
                    model.addAttribute("collegeSec", collegeNow.get(0));
                }
            }
            if (roleIds.contains(5)) {//ID 5 辅导员
                List<MajorCode> majors = new ArrayList<MajorCode>();
                //majorCodes
                List<UserInfoDto> userInfoDot = userInfoService.listMajorsAndQualificationByUserName(user.getUsername());
                //listMajorsAndQualificationByUserName根据用户名查询所属专业及专业层次
                //userInfoDo用户信息（专业、专业层次）
                if (!Assert.isEmpty(userInfoDot) && userInfoDot.size() > 0) {
                    //改成用迭代器 加了一些判断 但是bug应该不再这部分
                    Iterator<UserInfoDto> iterator = userInfoDot.iterator();
                    while (iterator.hasNext()) {
                        UserInfoDto temp = iterator.next();
                        String code = temp.getMajorCode();//专业代码
                        String qualification = temp.getMajorQualification();//专业层次
                        if (code != null && qualification != null) {
                            MajorCode majorCode = majorService.queryByCode(code, qualification);//根据专业代码查询专业信息
                            if (majorCode != null) {
                                majors.add(majorCode);//添加专业代码？
                            }
                        }
                    }

                   /* for (UserInfoDto u : userInfoDot) {
                            majors.add(majorService.queryByCode(u.getMajorCode(), u.getMajorQualification()));
                    }*/
                }
                model.addAttribute("majors", majors);//推送辅导员所查的专业层次信息
                if (!Assert.isEmpty(collegeNow) && collegeNow.size() > 0) {
                    model.addAttribute("collegeCoun", collegeNow.get(0));
                }
            }
            model.addAttribute("roles", roles);//推送角色
            model.addAttribute("isChecked", isChecked);//推送权限
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return WebConstants.sysErrorCode;
        }
        return "user/updateUser";
    }

    /**
     * 非管理员修改用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.GET)
    public String toUpdateUser(@RequestParam(value = "msg", required = false) String msg,
                               Model model) {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("msg", msg);
            return "user/updateInfo";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 非管理员修改用户密码
     */
    @RequestMapping(value = "/updatePwd2", method = RequestMethod.POST)
    public String updatePwd2(@RequestParam(value = "password") String password,
                             @RequestParam(value = "newPwd") String newPwd,
                             @RequestParam(value = "secondPwd") String secondPwd,
                             Model model) {
        if (!newPwd.equals(secondPwd)) {//判断两次输入的密码是否一致
            model.addAttribute("msg", OaException.PasswordNotEqual.getMes());
        } else {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            try {
                userService.updatePassword(username, password, newPwd);
                model.addAttribute("msg", "修改密码成功");
            } catch (SSException e) {
                LogClerk.errLog.error(e);
                model.addAttribute("msg", OaException.PwdWrong.getMes());
            }
        }
        return "redirect: ../../../logout";
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(User user,
                             @RequestParam("roleIds") List<Integer> roleIds,
                             @RequestParam int id,
                             @RequestParam(value = "qualification", required = false) List<Integer> qualification,
                             @RequestParam(value = "majorCode", required = false) List<String> majorCode,
                             String secretaryCollege, String counselorCollege,
                             Model model, RedirectAttributes redirectAttributes) {
        try {
            user.setId(id);
            User user1 = userService.queryUserById(id);
            if (roleIds.contains(4)) {
                user1.setCollege(collegeService.queryByCode(secretaryCollege).getCollege());
            }
            if (roleIds.contains(5)) {
                if (Assert.isNull(majorCode) || majorCode.size() <= 0) {
                    String failedUrl = "/" + URLConstants.ADMIN_USER_URL + "/toUpdateUser/" + id;
                    redirectAttributes.addFlashAttribute("errMsg", OaException.CounsellorMajorsNotNull.getMes());
                    return "redirect:" + failedUrl;
                }
                user1.setCollege(collegeService.queryByCode(counselorCollege).getCollege());
            }
            if (!Assert.isEmpty(majorCode) && majorCode.size() > 0) {
                HashSet<String> hs = new HashSet<>(majorCode);
                majorCode.clear();
                majorCode.addAll(hs);
            }
            user1.setRealName(user.getRealName());
            user1.setCellphone(user.getCellphone());
            userService.updateUser(user1);
            userRoleService.delRelation(id);
            userRoleService.addRelationRole(id, roleIds);
            if (roleIds.contains(4)) {
                userInfoService.delByUserId(user1.getId());
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(user1.getId());
                userInfo.setCollegeCode(secretaryCollege);
                userInfoService.newUserInfo(userInfo);
            }
            if (roleIds.contains(5)) {
                userInfoService.delByUserId(user1.getId());
                if (!Assert.isNull(majorCode) && majorCode.size() > 0) {
                    for (int i = 0; i < majorCode.size(); i++) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(user1.getId());
                        userInfo.setCollegeCode(counselorCollege);
                        userInfo.setMajorCode(majorCode.get(i));
                        MajorQualificationEnums majorQualification = MajorQualificationEnums.valueOf(qualification.get(i));
                        userInfo.setMajorQualification(majorQualification.getName());
                        userInfoService.newUserInfo(userInfo);
                    }
                }
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_USER_URL + "/toUpdateUser/" + id;
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
        return "redirect:toUserList";
    }

    /**
     * 跳转到用户列表
     *
     * @return
     */
    @RequestMapping(value = "/toUserList", method = RequestMethod.GET)
    public String toUserList(Model model) {
        try {
            List<CollegeCode> collegeCodes = collegeService.listAll();
            List<Role> roles = roleService.listRoles();
            model.addAttribute("colleges",collegeCodes);
            model.addAttribute("roles",roles);
        } catch (SSException e) {
            e.printStackTrace();
        }
        return "user/userList";
    }

    /**
     * 用户列表
     *
     * @param curPage
     * @return
     */
    @RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject userList(@PathVariable("curPage") Integer curPage,
                        @RequestParam(value = "role" ,defaultValue = "-1")String roleId,
                        @RequestParam(value = "college",defaultValue = "-1")String college) {
        JSONArray ja = new JSONArray();
        int dataCount = 0;
        try {
            dataCount = userService.listUsersCount(roleId,college);
        } catch (SSException e) {
            e.printStackTrace();
            return sendErrMsgAndErrCode(e);
        }
        Pagination page = new Pagination(dataCount, pageSize, curPage);
        try {
            List<UserDto> userDtos = userService.listUsers(page,roleId,college);
            for (UserDto userDto : userDtos) {
                JSONObject jo = new JSONObject();
                jo.put("id", userDto.getUser().getId());
                jo.put("realName", userDto.getUser().getRealName());
                jo.put("username", userDto.getUser().getUsername());
                jo.put("cellphone", userDto.getUser().getCellphone());
                jo.put("locked", userDto.getUser().getLocked());
                jo.put("description", userDto.getDescription());//college , qualification
                jo.put("college", userDto.getUser().getCollege());
                jo.put("qualification", userDto.getUser().getVerify_qualification());
                ja.add(jo);
            }
            return sendJsonArray(ja, dataCount);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 根据用户Id删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject del(@RequestParam("ids[]") int[] ids) {
        try {
            for (int id : ids) {
                userService.delUserById(id);
                userRoleService.delRelation(id);
            }
            return sendJsonObject(null);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 封锁
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject lock(@RequestParam("id") int id, User user) {
        try {
            user = userService.queryUserById(id);
            if (user.getLocked() == 0) {
                user.setLocked(1);
                userService.lock(user);
            }
            return sendJsonObject(null);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 解锁
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/unlock", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject unlock(@RequestParam("id") int id, User user) {
        try {
            user = userService.queryUserById(id);
            if (user.getLocked() == 1) {
                user.setLocked(0);
                userService.lock(user);
            }
            return sendJsonObject(null);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 修改用户信息时判断用户名是否已存在
     *
     * @param username
     * @param id
     * @return
     */
    @RequestMapping(value = "/update/isExist", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject isExist(@RequestParam("username") String username,
                       @RequestParam("id") int id,
                       @RequestParam("cellphone") String cellphone,
                       @RequestParam("realName") String realName) {
        try {
            if (userService.isExistUsername(username, id)) {
                return sendErrMsgAndErrCode(SSException.get(OaException.UsernameExist));
            }
            return sendJsonObject(null);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 添加时判断用户名是否已存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/add/isExist", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject isExist(@RequestParam("username") String username) {
        try {
            if (userService.isExistUsername(username)) {
                return sendErrMsgAndErrCode(SSException.get(OaException.UsernameExist));
            }
            return sendJsonObject(null);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 获取角色列表ajax
     *
     * @return
     */
    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject roleList() {
        JSONArray ja = new JSONArray();
        try {
            List<Role> roles = roleService.listRoles();
            for (Role role : roles) {
                JSONObject jo = new JSONObject();
                if (role.getId() != 1) { //将管理员过滤掉
                    jo.put("id", role.getId());
                    jo.put("name", role.getDescription());
                    ja.add(jo);
                }
            }
            return sendJsonArray(ja);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }
/*
 * 查询辅导员信息
 * sunz
 * 对应页面：userInfo 页面
 * @param id
 * @param model
 * @return
 */
    @RequestMapping(value = "toFindCounsellorUser", method = RequestMethod.GET)
    public String toFindCounsellorUser( Model model) {
        try {
            int id = 0;
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();
            User tip = userService.queryByUsername(username);
            if(!Assert.isNull(tip)) {//非空判断 如果取到的用户名非空 根据用户名查询用户ID
                id = userService.queryByUsername(username).getId();
            }
            User user = userService.queryUserById(id);//根据ID查询用户
            List<String> collegeNow = userInfoService.listCollegesByUserName(user.getUsername());
            //userInfoService用户信息Service
            //listCollegesByUserName根据用户名查询所属专业

            model.addAttribute("user", user);//向前端传用户信息
            List<Role> roles = roleService.listRoles();//获取所有角色列表
            Role role = roleService.getRoleByRoleId(6);//roleid 6 学生 获取学生角色列表
            if (roles.contains(role)) {//如果包含学生 从列表role中剔除
                roles.remove(role);
            }
            List<Integer> roleIds = userRoleService.listRoleByUserId(id);// listRoleByUserId根据用户Id,获得用户信息
            if (roleIds.contains(5)) {//ID 5 辅导员
                List<MajorCode> majors = new ArrayList<MajorCode>();
                //majorCodes
                List<UserInfoDto> userInfoDot = userInfoService.listMajorsAndQualificationByUserName(user.getUsername());
                //listMajorsAndQualificationByUserName根据用户名查询所属专业及专业层次
                //userInfoDo用户信息（专业、专业层次）
                if (!Assert.isEmpty(userInfoDot) && userInfoDot.size() > 0) {
                    //改成用迭代器 加了一些判断 但是bug应该不再这部分
                    Iterator<UserInfoDto> iterator = userInfoDot.iterator();
                    while (iterator.hasNext()) {
                        UserInfoDto temp = iterator.next();
                        String code = temp.getMajorCode();//专业代码
                        String qualification = temp.getMajorQualification();//专业层次
                        if (code != null && qualification != null) {
                            MajorCode majorCode = majorService.queryByCode(code, qualification);//根据专业代码查询专业信息
                            if (majorCode != null) {
                                majors.add(majorCode);//添加专业代码？
                            }
                        }
                    }
                }
                model.addAttribute("majors", majors);//推送辅导员所查的专业层次信息
                if (!Assert.isEmpty(collegeNow) && collegeNow.size() > 0) {
                    model.addAttribute("collegeCoun", collegeNow.get(0));
                }
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return WebConstants.sysErrorCode;
        }
        return "user/userInfo";
    }
    /**
     * 超级管理员修改他人密码跳转到修改密码界面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toUpdatePwdNext/{id}", method = RequestMethod.GET)
    public String toUpdatePwdNext(@PathVariable(value = "id") int id, Model model) {
        try {
            User user = userService.queryUserById(id);
            model.addAttribute("id", id);
            model.addAttribute("user", user);
        } catch (SSException e) {
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
        return "user/updatePwdNext";
    }
    /**
     * 超级管理员修改密码其他用户密码
     *
     * @param id
     * @param secondPwd
     * @param newPwd
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePwdNext", method = RequestMethod.POST)
    public String updatePwdNext(@RequestParam int id,
                            @RequestParam(value = "newPwd") String newPwd,
                            @RequestParam(value = "secondPwd") String secondPwd,
                            Model model) {
        if (!newPwd.equals(secondPwd)) {//判断两次输入的密码是否一致
            model.addAttribute("msg", OaException.PasswordNotEqual.getMes());
            model.addAttribute("id", id);
            return "user/updatePwdNext";
        }
        try {
            userService.updatePasswordNext(id, newPwd);
            model.addAttribute("msg", "密码修改成功");
            model.addAttribute("id", id);
            return "user/updatePwdNext";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("msg", OaException.PwdWrong.getMes());
            model.addAttribute("id", id);
            return "user/updatePwdNext";
        }
    }
}

