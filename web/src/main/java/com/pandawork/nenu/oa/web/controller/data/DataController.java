package com.pandawork.nenu.oa.web.controller.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.company.CompanyDto;
import com.pandawork.nenu.oa.common.dto.data.*;
import com.pandawork.nenu.oa.common.entity.company.Company;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * user: lishicao
 * date 15/5/20
 * time 下午2:41
 */
@Controller
@RequestMapping(value = "/data")
public class DataController extends AbstractController {


    /**
     * 进入增加学生的页面
     *
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAddStudent(Model model) throws SSException {
        try {
            // get users's information
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            List<WhereAboutGoCode> whereAboutGoCodes = commonCodeService.getWhereaboutgoCodeList();
            List<CollegeCode> collegeCodes = commonCodeService.getCollegeCodeList();
            List<String> quanlificationStudyList = commonCodeService.getQuanlificationStudyList();
            List<MajorDivision> majorDivisions = commonCodeService.getMajorDivisionList();
            List<MajorCode> majors = null;
            //if the user is fudaoyuan or fushuji , search the majors in their college()
            if (roles.contains(11) || roles.contains(10)) {
                majors = studentService.getMajorByCollegeName(college, qualification);
            } else
                majors = commonCodeService.getMajorCodeList();
            List<SexCode> sexCodes = commonCodeService.getSexCodeList();
            List<NationCode> nationCodes = commonCodeService.getNationCodeList();
            List<DifficultyCode> difficultyCodes = commonCodeService.getDifficultyCodeList();
            List<PoliticalCode> politicalCodes = commonCodeService.getPoliticalCodeList();
            List<NormalCode> normalCodes = commonCodeService.getNormalCodeList();
            List<InsModeCode> insModeCodes = commonCodeService.getInsModeCodeList();
            List<InsFieldCode> insFieldCodes = commonCodeService.getInsFieldCodeList();
            List<PlaceClass> placeClasses = commonCodeService.getPlaceClassList();
            List<Province> provinces = commonCodeService.getProvinceList();
            List<Area> areas = commonCodeService.getAreaList();
            List<PlaceCode> placeCodes = commonCodeService.getPlaceCodeList();
            List<TrainingModeCode> trainingModeCodes = commonCodeService.getTrainingModeCode();

            model.addAttribute("whereAboutGoCodes", whereAboutGoCodes);
            model.addAttribute("majors", majors);
            model.addAttribute("collegeCodes", collegeCodes);
            model.addAttribute("quanlificationStudyList", quanlificationStudyList);
            model.addAttribute("sexCodes", sexCodes);
            model.addAttribute("nationCodes", nationCodes);
            model.addAttribute("difficultyCodes", difficultyCodes);
            model.addAttribute("normalCodes", normalCodes);
            model.addAttribute("politicalCodes", politicalCodes);
            model.addAttribute("insFieldCodes", insFieldCodes);
            model.addAttribute("placeClasses", placeClasses);
            model.addAttribute("provinces", provinces);
            model.addAttribute("areas", areas);
            model.addAttribute("majorDivisions", majorDivisions);
            model.addAttribute("insModeCodes", insModeCodes);
            model.addAttribute("placeCodes", placeCodes);
            model.addAttribute("trainingModeCodes", trainingModeCodes);
            model.addAttribute("college", college);
            return "data/addStudent";
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 增加学生信息
     *
     * @param studentInfomation
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(Model model, StudentInfomation studentInfomation) {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            if (roles.contains(10)) { //副书记
                studentInfomation.setCollegeName(college);
                studentInfomation.setCollegeId(commonCodeService.getCollegeId(college));
            } else if (roles.contains(11)) { //辅导员
                studentInfomation.setCollegeName(college);
                studentInfomation.setCollegeId(commonCodeService.getCollegeId(college));
                studentInfomation.setQualification(qualification);
            }

            if (studentInfomation.getQualification().contains("本科")) {
                studentInfomation.setQualificationId("31");
            } else if (studentInfomation.getQualification().contains("硕士")) {
                studentInfomation.setQualificationId("11");
            } else if (studentInfomation.getQualification().contains("博士")) {
                studentInfomation.setQualificationId("01");
            }
            if (studentInfomation.getCollegeId() == null)
                studentInfomation.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            if (studentInfomation.getCollegeName() == null)
                studentInfomation.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));
            studentInfomation.setSchoolId(10200);//Nenu
            //对于所有的时间，如果数据库中以STRING方式存储，在存入数据库的时候，去掉‘-’。
            //如1992-08-21,那么存入数据库的时候变成19920821。
            Date now = new Date();
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String registerTime = studentInfomation.getRegistTime();
            String dob = studentInfomation.getDob();
            String graduationTime = studentInfomation.getGraduationTime();
            studentInfomation.setGrade(Integer.valueOf(studentInfomation.getRegistTime().substring(0, 4)).intValue());
            String nowS = format1.format(now);
            nowS = nowS.replaceAll("-", "");
            if (registerTime != null || registerTime.length() != 0) {
                registerTime = registerTime.replaceAll("-", "");
                studentInfomation.setRegistTime(registerTime);
            }
            if (graduationTime != null || graduationTime.length() != 0) {
                graduationTime = graduationTime.replaceAll("-", "");
                studentInfomation.setGraduationTime(graduationTime);
            }
            if (dob != null || dob.length() != 0) {
                dob = dob.replaceAll("-", "");
                studentInfomation.setDob(dob);
            }

            studentInfomation.setModifyTime(nowS);

            //不存在这个学生才插入
            if (!studentService.studentExist(studentInfomation)) {
                studentService.addStudent(studentInfomation);
                informationStatisticService.add(studentInfomation);
                return "redirect:/data/toListStudent";
            }
            //否则提示
            else {
                model.addAttribute("errMsg", "学生已存在");
                return WebConstants.sysErrorCode;
            }

        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * TO DO need handle exception
     * 跳转到显示学生信息列表，并且传入页面多种信息
     *
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "/toListStudent", method = RequestMethod.GET)
    public String toListStudent(Model model) {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            List<WhereAboutGoCode> whereAboutGoCodes = commonCodeService.getWhereaboutgoCodeList();
            List<CollegeCode> collegeCodes = commonCodeService.getCollegeCodeList();
            List<String> quanlificationStudyList = commonCodeService.getQuanlificationStudyList();
            List<MajorDivision> majorDivisions = commonCodeService.getMajorDivisionList();
            List<SexCode> sexCodes = commonCodeService.getSexCodeList();
            List<NationCode> nationCodes = commonCodeService.getNationCodeList();
            List<DifficultyCode> difficultyCodes = commonCodeService.getDifficultyCodeList();
            List<PoliticalCode> politicalCodes = commonCodeService.getPoliticalCodeList();
            List<NormalCode> normalCodes = commonCodeService.getNormalCodeList();
            List<InsModeCode> insModeCodes = commonCodeService.getInsModeCodeList();
            List<InsFieldCode> insFieldCodes = commonCodeService.getInsFieldCodeList();
            List<PlaceClass> placeClasses = commonCodeService.getPlaceClassList();
            List<Province> provinces = commonCodeService.getProvinceList();
            List<Area> areas = commonCodeService.getAreaList();
            List<PlaceCode> placeCodes = commonCodeService.getPlaceCodeList();
            List<MajorCode> majors = null;
            if (roles.contains(11) || roles.contains(10)) {
                majors = studentService.getMajorByCollegeName(college, qualification);
            } else
                majors = commonCodeService.getMajorCodeList();
            model.addAttribute("whereAboutGoCodes", whereAboutGoCodes);
            model.addAttribute("collegeCodes", collegeCodes);
            model.addAttribute("quanlificationStudyList", quanlificationStudyList);
            model.addAttribute("sexCodes", sexCodes);
            model.addAttribute("nationCodes", nationCodes);
            model.addAttribute("difficultyCodes", difficultyCodes);
            model.addAttribute("normalCodes", normalCodes);
            model.addAttribute("politicalCodes", politicalCodes);
            model.addAttribute("insFieldCodes", insFieldCodes);
            model.addAttribute("placeClasses", placeClasses);
            model.addAttribute("provinces", provinces);
            model.addAttribute("areas", areas);
            model.addAttribute("majorDivisions", majorDivisions);
            model.addAttribute("insModeCodes", insModeCodes);
            model.addAttribute("placeCodes", placeCodes);
            model.addAttribute("majors", majors);
            return "data/listStudent";
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    @RequestMapping(value = "/ajax/place/query/{provinceId}", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject listCityByProvince(@PathVariable("provinceId") int provinceId) {
        try {
            List<PlaceCode> placeCodes = commonCodeService.listShownameByProvinceId(provinceId);
            JSONArray array = new JSONArray();
            if (placeCodes != null) {
                for (PlaceCode placeCode : placeCodes) {
                    JSONObject json = new JSONObject();
                    json.put("cityId", placeCode.getPlaceId());
                    json.put("cityName", placeCode.getShowName());
                    array.add(json);
                }
            }
            return sendJsonArray(array);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    @RequestMapping(value = "/ajax/major/query/{collegeId}&{qualification}", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject listMajorByCollegeId(@PathVariable("collegeId") int collegeId,
                                    @PathVariable("qualification") String qualification) {
        try {
            CollegeCode collegeCode = commonCodeService.getCollegeCodeById(collegeId);
//            if( qualification.equals("本科") ) qualification = "本科专业";
//            else qualification = "研究生专业";
            List<MajorCode> majors = studentService.getMajorByCollegeName(collegeCode.getCollege(), qualification);
            JSONArray array = new JSONArray();
            if (majors.size() != 0) {
                for (MajorCode major : majors) {
                    JSONObject json = new JSONObject();
                    json.put("majorId", major.getMajorId());
                    json.put("majorName", major.getMajorName());
                    array.add(json);
                }
            }
            return sendJsonArray(array);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 分页显示学生信息列表
     *
     * @param page
     * @param studentQueryDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ajax/listStudent/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject listStudent(@PathVariable("page") int page, StudentQueryDto studentQueryDto) {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            if (studentQueryDto.getMajorId() != null && studentQueryDto.getMajorId().equals("0"))
                studentQueryDto.setMajorId(null);
            if (studentQueryDto.getFromPlace() != null && studentQueryDto.getFromPlace().equals("0"))
                studentQueryDto.setFromPlace(null);
            if (studentQueryDto.getGoPlace() != null && studentQueryDto.getGoPlace().equals("0"))
                studentQueryDto.setGoPlace(null);

            if (roles.contains(10)) {
                studentQueryDto.setCollegeName(college);
                studentQueryDto.setCollegeId(commonCodeService.getCollegeId(college));
            } else if (roles.contains(11)) {
                studentQueryDto.setCollegeName(college);
                studentQueryDto.setCollegeId(commonCodeService.getCollegeId(college));
                studentQueryDto.setQualification(qualification);
                //studentQueryDto.setQualificationId();
            }

            JSONArray jsonArray = new JSONArray();
            int dataCount = 0;
            dataCount = studentService.countByCondition(studentQueryDto);
            Pagination page1 = new Pagination(dataCount, pageSize, page);
            List<StudentInfomation> students = studentService.selectStuByCondition(studentQueryDto, page1);

            if (dataCount == 0) dataCount = -1;   //这是前端要求的。。。。因为0的时候前端做不了,获取不到，所以传－1过去再判断，再让它等于0

            for (StudentInfomation studentInfomation : students) {
                JSONObject jsonObject = new JSONObject();
                HashMap<String, String> map = new HashMap<>();
                map.put("college", studentInfomation.getCollegeName());
                map.put("qualification", studentInfomation.getQualification());
                List<String> verifyers = verifyInfoService.verifyer(map);
                if (verifyers.size() != 0)
                    jsonObject.put("verifyer", verifyers.get(0));
                jsonObject.put("id", studentInfomation.getId());
                jsonObject.put("studentId", studentInfomation.getStudentId());
                jsonObject.put("name", studentInfomation.getName());
                jsonObject.put("collegeName", studentInfomation.getCollegeName());
                jsonObject.put("major", studentInfomation.getMajor());
                jsonObject.put("qualification", studentInfomation.getQualification());
                jsonObject.put("whereaboutgo", studentInfomation.getWhereaboutgo());
                jsonObject.put("institutionName", studentInfomation.getInstitutionName());
                jsonObject.put("checkinStatus", studentInfomation.getCheckinStatus());
                jsonObject.put("verifyStatus", studentInfomation.getVerifyStatus());
                if (studentInfomation.getVerifyTime() != null) {
                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format1.format(studentInfomation.getVerifyTime());
                    jsonObject.put("verifyTime", time);
                }
                if (studentInfomation.getCheckinTime() != null) {
                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format1.format(studentInfomation.getCheckinTime());
                    jsonObject.put("checkinTime", time);
                }
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray, dataCount);
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            return sendErrMsgAndErrCode(ee);
        }
    }

    /**
     * 下载学生列表的EXCEL
     *
     * @param response
     * @param studentQueryDto
     */
    @RequestMapping(value = "/exportStudentListExcel", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportStudentListExcel(HttpServletResponse response, StudentQueryDto studentQueryDto) throws ServletException, IOException {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            if (studentQueryDto.getMajorId() != null && studentQueryDto.getMajorId().equals("0"))
                studentQueryDto.setMajorId(null);
            if (studentQueryDto.getFromPlace() != null && studentQueryDto.getFromPlace().equals("0"))
                studentQueryDto.setFromPlace(null);
            if (studentQueryDto.getGoPlace() != null && studentQueryDto.getGoPlace().equals("0"))
                studentQueryDto.setGoPlace(null);

            if (roles.contains(10)) {
                studentQueryDto.setCollegeName(college);
                studentQueryDto.setCollegeId(commonCodeService.getCollegeId(college));
            } else if (roles.contains(11)) {
                studentQueryDto.setCollegeName(college);
                studentQueryDto.setCollegeId(commonCodeService.getCollegeId(college));
                studentQueryDto.setQualification(qualification);
                //studentQueryDto.setQualificationId();
            }

            List<StudentInfomation> students = studentService.selectStudentByCondition(studentQueryDto);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sheet1");
            XSSFRow row0 = sheet.createRow(0);
            XSSFCell cell00 = row0.createCell(0);
            XSSFCell cell01 = row0.createCell(1);
            XSSFCell cell02 = row0.createCell(2);
            XSSFCell cell03 = row0.createCell(3);
            XSSFCell cell04 = row0.createCell(4);
            XSSFCell cell05 = row0.createCell(5);
            XSSFCell cell06 = row0.createCell(6);
            XSSFCell cell07 = row0.createCell(7);
            XSSFCell cell08 = row0.createCell(8);

            cell00.setCellValue("学号");
            cell01.setCellValue("姓名");
            cell02.setCellValue("学院");
            cell03.setCellValue("专业");
            cell04.setCellValue("学历");
            cell05.setCellValue("毕业去向");
            cell06.setCellValue("单位名称");
            cell07.setCellValue("审核状态");
            cell08.setCellValue("录入状态");

            for (int i = 0; i < students.size(); i++) {
                StudentInfomation studentInfomation = students.get(i);
                XSSFRow row = sheet.createRow(i + 1);
                XSSFCell cell0 = row.createCell(0);
                XSSFCell cell1 = row.createCell(1);
                XSSFCell cell2 = row.createCell(2);
                XSSFCell cell3 = row.createCell(3);
                XSSFCell cell4 = row.createCell(4);
                XSSFCell cell5 = row.createCell(5);
                XSSFCell cell6 = row.createCell(6);
                XSSFCell cell7 = row.createCell(7);
                XSSFCell cell8 = row.createCell(8);

                if (studentInfomation.getStudentId() != null) cell0.setCellValue(studentInfomation.getStudentId());
                else cell0.setCellValue("");
                if (studentInfomation.getName() != null) cell1.setCellValue(studentInfomation.getName());
                else cell1.setCellValue("");
                if (studentInfomation.getCollegeName() != null) cell2.setCellValue(studentInfomation.getCollegeName());
                else cell2.setCellValue("");
                if (studentInfomation.getMajor() != null) cell3.setCellValue(studentInfomation.getMajor());
                else cell3.setCellValue("");
                if (studentInfomation.getQualification() != null)
                    cell4.setCellValue(studentInfomation.getQualification());
                else cell4.setCellValue("");
                if (studentInfomation.getWhereaboutgo() != null)
                    cell5.setCellValue(studentInfomation.getWhereaboutgo());
                else cell5.setCellValue("");
                if (studentInfomation.getInstitutionName() != null)
                    cell6.setCellValue(studentInfomation.getInstitutionName());
                else cell6.setCellValue("");
                if (studentInfomation.getCheckinStatus() != null)
                    cell7.setCellValue(studentInfomation.getCheckinStatus());
                else cell7.setCellValue("");
                if (studentInfomation.getVerifyStatus() != null)
                    cell8.setCellValue(studentInfomation.getVerifyStatus());
                else cell8.setCellValue("");
            }

            File servletFile = new File("students.xlsx");

            FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            response.setHeader("Content-disposition", "attachment;filename=" + "students.xlsx");
            response.setContentType("application/msexcel");
            long fileLength = servletFile.length();
            String length = String.valueOf(fileLength);
            response.setHeader("content_Length", length);
            OutputStream servletOutPutStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(servletFile);

            byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
            int len = 0;
            //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
            while ((len = fileInputStream.read(bytes)) != -1) {
                //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                servletOutPutStream.write(bytes, 0, len);
            }
            servletOutPutStream.close();
            fileInputStream.close();
            servletFile.delete();

        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
        }
    }


    /**
     * 查询生源结构
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toQueryStudentStructure", method = RequestMethod.GET)
    public String toQueryStudentStructure(Model model) {
        try {
            //获取用户角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            return "data/queryStudentStructure";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 获取生源结构
     *
     * @param model
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryStudentStructure", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject queryStudentStructure(Model model, Integer start, Integer end,
                                     String dimension1, String dimension2, Integer orderBy,
                                     Integer id) {
        try {
            StudentStructureDTO studentStructureDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year + 1;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                studentStructureDTO = informationStatisticService.getStudentStructure(dimension1, dimension2, college,
                        qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    studentStructureDTO.sort(sortColumn, sortByDescend);
                }
            }

            //构造JSON
            JSONArray dimension = new JSONArray();
            JSONArray dataList = new JSONArray();
            if (studentStructureDTO != null && studentStructureDTO.getRows().size() > 0) {
                for (int i = 0; i < studentStructureDTO.getRows().get(0).getTuples().size(); i++) {
                    StudentStructureDTO.Tuple tuple = studentStructureDTO.getRows().get(0).getTuples().get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i + 1);
                    jsonObject.put("name", tuple.getTupleName());
                    dimension.add(jsonObject);
                }
                for (StudentStructureDTO.Row row : studentStructureDTO.getRows()) {

                    JSONObject json = new JSONObject();
                    json.put("dataName", row.getRowName());
                    JSONArray list = new JSONArray();

                    for (StudentStructureDTO.Tuple tuple : row.getTuples()) {
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("peopleNum", tuple.getCount());
                        jsonObject.put("share", df.format(tuple.getPercentage() * 100) + "%");
                        list.add(jsonObject);
                    }

                    json.put("list", list);

                    dataList.add(json);
                }
            }
            JSONObject data = new JSONObject();
            data.put("dimension", dimension);
            data.put("dataList", dataList);

            //传回JSON
            return sendJsonObject(data);
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            return sendErrMsgAndErrCode(ee);
        }
    }


    /**
     * 下载生源结构的EXCEL
     *
     * @param response
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/exportStudentStructureExcel", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportStudentStructureExcel(HttpServletResponse response,
                                     Integer start, Integer end, String dimension1,
                                     String dimension2, Integer orderBy, Integer id)
            throws ServletException, IOException {
        try {
            StudentStructureDTO studentStructureDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                studentStructureDTO = informationStatisticService.getStudentStructure(dimension1, dimension2, college,
                        qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    studentStructureDTO.sort(sortColumn, sortByDescend);
                }


                //导出查询结果的EXCEL
                XSSFWorkbook workbook = studentStructureDTO.getExcel();
//                HSSFWorkbook workbook = studentStructureDTO.getExcel() ;
                File servletFile = new File("studentStructure.xlsx");

                FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
                workbook.write(fileOutputStream);
                fileOutputStream.close();

                response.setHeader("Content-disposition", "attachment;filename=" + "studentStructure.xlsx");
                response.setContentType("application/msexcel");
                long fileLength = servletFile.length();
                String length = String.valueOf(fileLength);
                response.setHeader("content_Length", length);
                OutputStream servletOutPutStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(servletFile);

                byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
                int len = 0;
                //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
                while ((len = fileInputStream.read(bytes)) != -1) {
                    //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                    servletOutPutStream.write(bytes, 0, len);
                }
                servletOutPutStream.close();
                fileInputStream.close();
                servletFile.delete();
            }

        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
        }
    }


    /**
     * 查询就业率
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toQueryEmploymentRate", method = RequestMethod.GET)
    public String toQueryEmploymentRate(Model model) {
        try {
            //获取用户角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            return "data/queryEmploymentRate";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     *  获取就业率
     *
     * @param model
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryEmploymentRate", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject queryEmploymentRate(Model model, Integer start, Integer end, String dimension1
            , String dimension2, Integer orderBy, Integer id) {
        try {
            EmploymentRateDTO employmentRateDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year + 1;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentRateDTO = informationStatisticService.getEmploymentRate(dimension1, dimension2, college,
                        qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentRateDTO.sort(sortColumn, sortByDescend);
                }
            }

            //构造JSON
            JSONArray dimension = new JSONArray();
            JSONArray dataList = new JSONArray();
            if (employmentRateDTO != null && employmentRateDTO.getRows().size() > 0) {
                for (int i = 0; i < employmentRateDTO.getRows().get(0).getTuples().size(); i++) {
                    EmploymentRateDTO.Tuple tuple = employmentRateDTO.getRows().get(0).getTuples().get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i + 1);
                    jsonObject.put("name", tuple.getTupleName());
                    dimension.add(jsonObject);
                }
                for (EmploymentRateDTO.Row row : employmentRateDTO.getRows()) {

                    JSONObject json = new JSONObject();
                    json.put("dataName", row.getRowName());
                    JSONArray list = new JSONArray();

                    for (EmploymentRateDTO.Tuple tuple : row.getTuples()) {
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("GraduationNum", tuple.getGraduationCount());
                        jsonObject.put("EmpolymentNum", tuple.getEmploymentCount());
                        jsonObject.put("EmpolymentRate", df.format(tuple.getEmploymentRate() * 100) + "%");
                        list.add(jsonObject);
                    }

                    json.put("list", list);
                    dataList.add(json);
                }
            }
            JSONObject data = new JSONObject();
            data.put("dimension", dimension);
            data.put("dataList", dataList);
            //传回JSON
            return sendJsonObject(data);
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            return sendErrMsgAndErrCode(ee);
        }
    }


    /**
     * 下载就业率的EXCEL
     *
     * @param response
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/exportEmploymentRateExcel", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportEmploymentRateExcel(HttpServletResponse response,
                                   Integer start, Integer end, String dimension1,
                                   String dimension2, Integer orderBy, Integer id)
            throws ServletException, IOException {
        try {
            EmploymentRateDTO employmentRateDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentRateDTO = informationStatisticService.getEmploymentRate(dimension1, dimension2, college,
                        qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentRateDTO.sort(sortColumn, sortByDescend);
                }


                //导出查询结果的EXCEL
                XSSFWorkbook workbook = employmentRateDTO.getExcel();
//                HSSFWorkbook workbook = employmentRateDTO.getExcel() ;
                File servletFile = new File("employmentRate.xlsx");

                FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
                workbook.write(fileOutputStream);
                fileOutputStream.close();

                response.setHeader("Content-disposition", "attachment;filename=" + "employmentRate.xlsx");
                response.setContentType("application/msexcel");
                long fileLength = servletFile.length();
                String length = String.valueOf(fileLength);
                response.setHeader("content_Length", length);
                OutputStream servletOutPutStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(servletFile);

                byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
                int len = 0;
                //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
                while ((len = fileInputStream.read(bytes)) != -1) {
                    //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                    servletOutPutStream.write(bytes, 0, len);
                }
                servletOutPutStream.close();
                fileInputStream.close();
                servletFile.delete();
            }

        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
        }
    }


    /**
     * 查询拟就业率
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toQueryPlanEmploymentRate", method = RequestMethod.GET)
    public String toQueryPlanEmploymentRate(Model model) {
        try {
            //获取用户角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            return "data/queryPlanEmploymentRate";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }


    /**
     * 获取拟就业率
     *
     * @param model
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryPlanEmploymentRate", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject queryPlanEmploymentRate(Model model, Integer start, Integer end,
                                       String dimension1, String dimension2, Integer orderBy,
                                       Integer id) {
        try {
            EmploymentRateDTO employmentRateDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year + 1;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentRateDTO = informationStatisticService.getPlanEmploymentRate(dimension1, dimension2, college,
                        qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentRateDTO.sort(sortColumn, sortByDescend);
                }
            }

            //构造JSON
            JSONArray dimension = new JSONArray();
            JSONArray dataList = new JSONArray();
            if (employmentRateDTO != null && employmentRateDTO.getRows().size() > 0) {
                for (int i = 0; i < employmentRateDTO.getRows().get(0).getTuples().size(); i++) {
                    EmploymentRateDTO.Tuple tuple = employmentRateDTO.getRows().get(0).getTuples().get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i + 1);
                    jsonObject.put("name", tuple.getTupleName());
                    dimension.add(jsonObject);
                }
                for (EmploymentRateDTO.Row row : employmentRateDTO.getRows()) {

                    JSONObject json = new JSONObject();
                    json.put("dataName", row.getRowName());
                    JSONArray list = new JSONArray();

                    for (EmploymentRateDTO.Tuple tuple : row.getTuples()) {
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("GraduationNum", tuple.getGraduationCount());
                        jsonObject.put("EmpolymentNum", tuple.getEmploymentCount());
                        jsonObject.put("EmpolymentRate", df.format(tuple.getEmploymentRate() * 100) + "%");
                        list.add(jsonObject);
                    }

                    json.put("list", list);
                    dataList.add(json);
                }
            }
            JSONObject data = new JSONObject();
            data.put("dimension", dimension);
            data.put("dataList", dataList);
            //传回JSON
            return sendJsonObject(data);
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            return sendErrMsgAndErrCode(ee);
        }
    }

    /**
     * 下载拟就业率的EXCEL
     *
     * @param response
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/exportPlanEmploymentRateExcel", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportPlanEmploymentRateExcel(HttpServletResponse response,
                                       Integer start, Integer end, String dimension1,
                                       String dimension2, Integer orderBy, Integer id)
            throws ServletException, IOException {
        try {
            EmploymentRateDTO employmentRateDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }

            //查询拟就业率
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentRateDTO = informationStatisticService.getPlanEmploymentRate(dimension1,
                        dimension2, college, qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentRateDTO.sort(sortColumn, sortByDescend);
                }


                //导出查询结果的EXCEL
                XSSFWorkbook workbook = employmentRateDTO.getExcel();
                File servletFile = new File("planEmploymentRate.xlsx");

                FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
                workbook.write(fileOutputStream);
                fileOutputStream.close();

                response.setHeader("Content-disposition", "attachment;filename=" + "planEmploymentRate.xlsx");
                response.setContentType("application/msexcel");
                long fileLength = servletFile.length();
                String length = String.valueOf(fileLength);
                response.setHeader("content_Length", length);
                OutputStream servletOutPutStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(servletFile);

                byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
                int len = 0;
                //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
                while ((len = fileInputStream.read(bytes)) != -1) {
                    //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                    servletOutPutStream.write(bytes, 0, len);
                }
                servletOutPutStream.close();
                fileInputStream.close();
                servletFile.delete();
            }

        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
        }
    }


    /**
     * 查询就业结构
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toQueryEmploymentStructure", method = RequestMethod.GET)
    public String toQueryEmploymentStructure(Model model) {
        try {
            //获取用户角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            return "data/queryEmploymentStructure";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 获取就业结构
     *
     * @param model
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryEmploymentStructure", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject queryEmploymentStructure(Model model, Integer start, Integer end,
                                        String dimension1, String dimension2,
                                        Integer orderBy, Integer id) {
        try {
            EmploymentStructureDTO employmentStructureDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }
            model.addAttribute("isSecretary", isSecretary);
            model.addAttribute("isCounselor", isCounselor);

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year + 1;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentStructureDTO = informationStatisticService.getEmployStructure(dimension1, dimension2, college, qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentStructureDTO.sort(sortColumn, sortByDescend);
                }
            }

            //构造JSON
            JSONArray dimension = new JSONArray();
            JSONArray dataList = new JSONArray();
            if (employmentStructureDTO != null && employmentStructureDTO.getRows().size() > 0) {
                for (int i = 0; i < employmentStructureDTO.getRows().get(0).getTuples().size(); i++) {
                    EmploymentStructureDTO.Tuple tuple = employmentStructureDTO.getRows().get(0).getTuples().get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i + 1);
                    jsonObject.put("name", tuple.getTupleName());
                    dimension.add(jsonObject);
                }
                for (EmploymentStructureDTO.Row row : employmentStructureDTO.getRows()) {

                    JSONObject json = new JSONObject();
                    json.put("dataName", row.getRowName());
                    JSONArray list = new JSONArray();

                    for (int i = 0; i < row.getTuples().size(); i++) {
                        EmploymentStructureDTO.Tuple tuple = row.getTuples().get(i);

                        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("peopleNum", tuple.getEmploymentCount());
                        jsonObject.put("share", df.format(tuple.getPercentage() * 100) + "%");
                        list.add(jsonObject);
                    }

                    json.put("list", list);

                    dataList.add(json);
                }
            }
            JSONObject data = new JSONObject();
            data.put("dimension", dimension);
            data.put("dataList", dataList);

            //传回JSON
            return sendJsonObject(data);
        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
            return sendErrMsgAndErrCode(ee);
        }
    }

    /**
     * 下载就业结构的EXCEL
     *
     * @param response
     * @param start
     * @param end
     * @param dimension1
     * @param dimension2
     * @param orderBy
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/exportEmploymentStructureExcel", method = RequestMethod.GET)
    public
    @ResponseBody
    void exportEmploymentStructureExcel(HttpServletResponse response,
                                        Integer start, Integer end, String dimension1, String dimension2, Integer orderBy, Integer id)
            throws ServletException, IOException {
        try {
            EmploymentStructureDTO employmentStructureDTO = null;

            Date time = new Date();
            Integer year = time.getYear() + 1900;

            //取得角色
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            boolean isSecretary = false;
            boolean isCounselor = false;
            if (roles.contains(10)) {
                isSecretary = true;
            } else if (roles.contains(11)) {
                isCounselor = true;
            }

            //查询生源结构
            if (start != null && end != null && dimension1 != null && dimension2 != null) {
                if (college == null) college = "";
                if (qualification == null) qualification = "";
                if (start.equals(0)) start = year;
                if (end.equals(0)) end = year;
                if (dimension1.equals(dimension2)) dimension2 = "";
                if (dimension2.equals("0")) dimension2 = "";
                employmentStructureDTO = informationStatisticService.getEmployStructure(dimension1, dimension2, college, qualification, start, end);

                if (orderBy != null && id != null) {
                    boolean sortByDescend;
                    int sortColumn = id - 1;
                    if (orderBy == 0) sortByDescend = false;
                    else sortByDescend = true;
                    employmentStructureDTO.sort(sortColumn, sortByDescend);
                }


                //导出查询结果的EXCEL
                XSSFWorkbook workbook = employmentStructureDTO.getExcel();
//                HSSFWorkbook workbook = employmentStructureDTO.getExcel() ;
                File servletFile = new File("EmploymentStructure.xlsx");

                FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
                workbook.write(fileOutputStream);
                fileOutputStream.close();

                response.setHeader("Content-disposition", "attachment;filename=" + "EmploymentStructure.xlsx");
                response.setContentType("application/msexcel");
                long fileLength = servletFile.length();
                String length = String.valueOf(fileLength);
                response.setHeader("content_Length", length);
                OutputStream servletOutPutStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(servletFile);

                byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
                int len = 0;
                //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
                while ((len = fileInputStream.read(bytes)) != -1) {
                    //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                    servletOutPutStream.write(bytes, 0, len);
                }
                servletOutPutStream.close();
                fileInputStream.close();
                servletFile.delete();
            }

        } catch (SSException ee) {
            LogClerk.errLog.error(ee);
        }
    }


    /**
     * 导出数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toExportData", method = RequestMethod.GET)
    public String toExportData(Model model) {
        return "data/exportData";
    }

    /**
     * 导出数据
     *
     * @param response
     * @param year
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public String exportData(Model model, HttpServletResponse response, Integer year) throws ServletException, IOException {
        try {
            XSSFWorkbook workbook = studentToExcelService.getStudentInfomation(year);
            File servletFile = new File("StudentInformation.xlsx");

            FileOutputStream fileOutputStream = new FileOutputStream(servletFile);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            response.setHeader("Content-disposition", "attachment;filename=" + "StudentInformation.xlsx");
            response.setContentType("application/msexcel");
            long fileLength = servletFile.length();
            String length = String.valueOf(fileLength);
            response.setHeader("content_Length", length);
            OutputStream servletOutPutStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(servletFile);

            byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
            int len = 0;
            //读取数据。返回值为读入缓冲区的字节总数,如果到达文件末尾，则返回-1
            while ((len = fileInputStream.read(bytes)) != -1) {
                //将指定 byte数组中从下标 0 开始的 len个字节写入此文件输出流,(即读了多少就写入多少)
                servletOutPutStream.write(bytes, 0, len);
            }
            servletOutPutStream.close();
            fileInputStream.close();
            servletFile.delete();
            return "data/exportData";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 导入数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toImportData", method = RequestMethod.GET)
    public String toImportData(Model model) {
        return "data/importData";
    }

    /**
     * 导入数据
     *
     * @param file
     * @param model
     * @return
     */
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    public String importData(@RequestParam MultipartFile file, Model model) {
        try {
            File excel = new File(file.getOriginalFilename());
            file.transferTo(excel);
            if (studentToExcelService.varifyStudentExcel(excel)) {
                studentToExcelService.setStudentInfomation(excel);
                excel.delete();
                return "data/importData";
            } else {
                excel.delete();
                return WebConstants.sysErrorCode;
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 导入国家系统导出的EXCEL数据
     *
     * @param file
     * @param model
     * @return
     */
    @RequestMapping(value = "/importNationData", method = RequestMethod.POST)
    public String importNationData(MultipartFile file, Model model) {
        try {
            File excel = new File(file.getOriginalFilename());
            file.transferTo(excel);
            if (studentToExcelService.varifyNationExcel(excel)) {
                studentToExcelService.setStudentInfomationFormNationSystem(excel);
                excel.delete();
                return "data/importData";
            } else {
                excel.delete();
                return WebConstants.sysErrorCode;
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 补全从国家系统导出的EXCEL中的学号缺失
     *
     * @param file
     * @param model
     * @return
     */
    @RequestMapping(value = "/completeStudentId", method = RequestMethod.POST)
    public String completeStudentId(MultipartFile file, Model model) {
        try {
            File excel = new File(file.getOriginalFilename());
            file.transferTo(excel);
            studentToExcelService.completeStudentId(excel);
            excel.delete();
            return "data/importData";
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            model.addAttribute("errMsg", ee.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    //进入更新学生页面
    @RequestMapping(value = "/update/{id}/{type}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") int id,
                           @PathVariable("type") int type,
                           Model model) {

        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);

            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();
            List<MajorCode> majors;
            if (roles.contains(11)) {
                majors = studentService.getMajorByCollegeName(college, qualification);
            } else {
                majors = commonCodeService.getMajorCodeList();
            }

            StudentInfomation student = studentService.selectStuByid(id);
            List<WhereAboutGoCode> whereAboutGoCodes = commonCodeService.getWhereaboutgoCodeList();
            List<CollegeCode> collegeCodes = commonCodeService.getCollegeCodeList();
            List<String> quanlificationStudyList = commonCodeService.getQuanlificationStudyList();
            List<MajorDivision> majorDivisions = commonCodeService.getMajorDivisionList();
            List<SexCode> sexCodes = commonCodeService.getSexCodeList();
            List<NationCode> nationCodes = commonCodeService.getNationCodeList();
            List<DifficultyCode> difficultyCodes = commonCodeService.getDifficultyCodeList();
            List<PoliticalCode> politicalCodes = commonCodeService.getPoliticalCodeList();
            List<NormalCode> normalCodes = commonCodeService.getNormalCodeList();
            List<InsModeCode> insModeCodes = commonCodeService.getInsModeCodeList();
            List<InsFieldCode> insFieldCodes = commonCodeService.getInsFieldCodeList();
            List<PlaceClass> placeClasses = commonCodeService.getPlaceClassList();
            List<Province> provinces = commonCodeService.getProvinceList();
            List<Area> areas = commonCodeService.getAreaList();
            List<PlaceCode> placeCodes = commonCodeService.getPlaceCodeList();
            List<TrainingModeCode> trainingModeCodes = commonCodeService.getTrainingModeCode();
            List<JobCode> jobCodes = commonCodeService.getJobCodeList();
            List<ReportCode> reportCodes = commonCodeService.getReportCodeList();
            Company company = companyService.getCompany(student.getCompanyId());

            model.addAttribute("type", type);
            model.addAttribute("whereAboutGoCodes", whereAboutGoCodes);
            model.addAttribute("student", student);
            model.addAttribute("collegeCodes", collegeCodes);
            model.addAttribute("majors", majors);
            model.addAttribute("quanlificationStudyList", quanlificationStudyList);
            model.addAttribute("sexCodes", sexCodes);
            model.addAttribute("nationCodes", nationCodes);
            model.addAttribute("difficultyCodes", difficultyCodes);
            model.addAttribute("normalCodes", normalCodes);
            model.addAttribute("politicalCodes", politicalCodes);
            model.addAttribute("insFieldCodes", insFieldCodes);
            model.addAttribute("placeClasses", placeClasses);
            model.addAttribute("provinces", provinces);
            model.addAttribute("areas", areas);
            model.addAttribute("majorDivisions", majorDivisions);
            model.addAttribute("insModeCodes", insModeCodes);
            model.addAttribute("placeCodes", placeCodes);
            model.addAttribute("trainingModeCodes", trainingModeCodes);
            model.addAttribute("jobCodes", jobCodes);
            model.addAttribute("reportCodes", reportCodes);
            model.addAttribute("company", company);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }

        return "data/updateStudent";
    }

    /**
     * 更新学生，录入和修改都用的这个界面。用type的值区分，录入为0，审核为1，修改为2.也需要注意时间的问题。
     *
     * @param type
     * @param studentInfomation
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{type}", method = RequestMethod.POST)
    public String toUpdate(@PathVariable int type, StudentInfomation studentInfomation, Model model) {
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String college = user.getCollege();
            String qualification = user.getVerify_qualification();

            if (roles.contains(11)) {
                studentInfomation.setQualification(qualification);
                studentInfomation.setCollegeName(college);
                studentInfomation.setCollegeId(commonCodeService.getCollegeId(college));
            }

            if (studentInfomation.getQualification().contains("本科")) {
                studentInfomation.setQualificationId("31");
            } else if (studentInfomation.getQualification().contains("硕士")) {
                studentInfomation.setQualificationId("11");
            } else if (studentInfomation.getQualification().contains("博士")) {
                studentInfomation.setQualificationId("01");
            }
            if (studentInfomation.getCollegeId() == null)
                studentInfomation.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
            if (studentInfomation.getCollegeName() == null)
                studentInfomation.setCollegeName(commonCodeService.getCollegeName(studentInfomation.getCollegeId()));

            studentInfomation.setSchoolId(10200);//Nenu
            Date now = new Date();
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String registerTime = studentInfomation.getRegistTime();
            String dob = studentInfomation.getDob();
            String reportTime = studentInfomation.getReportStartTime();
            String graduationTime = studentInfomation.getGraduationTime();
            studentInfomation.setGrade(2012);
            String nowS = format1.format(now);
            nowS = nowS.replaceAll("-", "");
            if (registerTime != null && registerTime.length() != 0) {
                registerTime = registerTime.replaceAll("-", "");
                studentInfomation.setRegistTime(registerTime);
            }
            if (graduationTime != null && graduationTime.length() != 0) {
                graduationTime = graduationTime.replaceAll("-", "");
                studentInfomation.setGraduationTime(graduationTime);
            }
            if (reportTime != null && reportTime.length() != 0) {
                reportTime = reportTime.replaceAll("-", "");
                studentInfomation.setReportStartTime(reportTime);
            }
            studentInfomation.setModifyTime(nowS);
            if (dob != null && dob.length() != 0) {
                dob = dob.replaceAll("-", "");
                studentInfomation.setDob(dob);
            }

            studentInfomation.setModifyTime(nowS);
            if (type == 1) {
                studentInfomation.setVerifyStatus("已审核");
                studentInfomation.setVerifyTime(now);
            }
            if (type == 0) {
                studentInfomation.setCheckinStatus("已审核");
                studentInfomation.setCheckinTime(now);
            }
            studentService.updateStudent(studentInfomation);
            informationStatisticService.update(studentInfomation);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
        return "data/listStudent";
    }

    /**
     * 跳转到审核状态
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toVerifyStatus", method = RequestMethod.GET)
    public String toVerify(Model model) {
        return "data/verify";
    }

    /**
     * 审核状态
     * 查询学院的审核状态。如果是副书记，那么hard code 学院。如果是工作人员，那么遍历每个学院的每个学历。
     *
     * @param grade
     * @param verifyStatus
     * @return
     */
    @RequestMapping(value = "/ajax/verify/query", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject queryVerify(@RequestParam(value = "grade", required = false) String grade,
                           @RequestParam(value = "verifyStatus", required = false) String verifyStatus
    ) {
        VarifyQueryDto varifyQueryDto = new VarifyQueryDto();
        JSONArray jsonArray = new JSONArray();
        try {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            User user = null;
            user = userService.queryByUsername(username);
            List<Integer> roles = userRoleService.getRolesByUsername(username);
            String ucollege = user.getCollege();
            List<CollegeCode> collegeCodes = commonCodeService.getCollegeCodeList();

            if (roles.contains(10)) {
                CollegeCode collegeCode = new CollegeCode();
                collegeCode.setCollege(ucollege);
                collegeCodes.clear();
                collegeCodes.add(collegeCode);
            }

            for (CollegeCode c : collegeCodes) {
                for (int i = 0; i < 3; i++) {                        // 0 is  本科 1 is 研究生 2 is 博士
                    String qualification = "";
                    String college = c.getCollege();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("college", c.getCollege());
                    if (i == 0)
                        qualification = "本科";
                    if (i == 1)
                        qualification = "硕士研究生";
                    if (i == 2)
                        qualification = "博士研究生";
                    varifyQueryDto.setCollege(college);
                    varifyQueryDto.setQualification(qualification);
                    if (grade == null || grade == "") varifyQueryDto.setGrade("");
                    else varifyQueryDto.setGrade(grade);

                    HashMap<String, String> map = new HashMap<>();
                    map.put("college", college);
                    map.put("qualification", qualification);
                    int numsofGraduation = verifyInfoService.numsOfGraduation(varifyQueryDto);
                    if (numsofGraduation == 0) continue;                           //如果没有毕业生则不显示
                    int numsifVerifying = verifyInfoService.numsOfVerifying(varifyQueryDto);
                    String uverifyStatus = "";
                    List<String> verifyers;
                    verifyers = verifyInfoService.verifyer(map);
                    if (numsifVerifying == 0) {
                        uverifyStatus = "审核完毕";
                        if (verifyStatus != null && verifyStatus.equals("审核中")) continue;
                    } else {
                        uverifyStatus = "审核中";
                        if (verifyStatus != null && verifyStatus.equals("审核完毕")) continue;
                    }
                    Date verifyComTime = verifyInfoService.verifyCompleteTime(user.getId());
                    String sverifyComTime = "";
                    if (verifyComTime != null) {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        sverifyComTime = format.format(verifyComTime);
                    }
                    if (verifyers.size() != 0) {
                        jsonObject.put("exPerson", verifyers.get(0));
                    }
                    jsonObject.put("status", uverifyStatus);
                    jsonObject.put("gruNum", numsofGraduation);
                    jsonObject.put("exNum", numsifVerifying); //未审核人数
                    jsonObject.put("college", college);
                    jsonObject.put("exStopTime", sverifyComTime);
                    jsonObject.put("qualification", qualification);        //学历
                    jsonArray.add(jsonObject);
                }
            }
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            jsonArray.add(1);
            return sendJsonArray(jsonArray);
        }
    }

    /**
     * 列出一个学生的所有信息，需要连表查询，如果有遗漏，需要检查sql语句。
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id, Model model) {
        try {
            StudentInfomation studentInfomation = studentService.selectStuByid(id);
            Company company = companyService.getCompany(studentInfomation.getCompanyId());
            model.addAttribute("company", company);
            model.addAttribute("student", studentInfomation);
            return "data/detailStudent";

        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * Delete a student.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable int id, Model model) {
        try {
            StudentInfomation studentInfomation = studentService.getStudentById(id);
            informationStatisticService.delete(studentInfomation.getExamId());
            studentService.deleteStudent(id);
            return "data/listStudent";

        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    @RequestMapping(value = "/ajax/company", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject listAllCompany() {
        try {
            List<CompanyDto> companyDtos = companyService.listAllCompanyDto();
            JSONArray jsonArray = JSONArray.fromObject(companyDtos);
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

}
