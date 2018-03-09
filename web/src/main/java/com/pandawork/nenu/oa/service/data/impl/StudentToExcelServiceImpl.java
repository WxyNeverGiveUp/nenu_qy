package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.pweio.excel.DataType;
import com.pandawork.core.pweio.excel.ExcelReader;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.StudentMapper;
import com.pandawork.nenu.oa.service.data.CommonCodeService;
import com.pandawork.nenu.oa.service.data.InformationStatisticService;
import com.pandawork.nenu.oa.service.data.StudentService;
import com.pandawork.nenu.oa.service.data.StudentToExcelService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 学生信息的EXCEL导入数据、导出数据
 * user: lishicao
 * date 15/4/7
 * time 下午9:44
 */
@Service("studentToExcelService")
public class StudentToExcelServiceImpl implements StudentToExcelService {
    @Autowired
    CommonDao commonDao;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CommonCodeService commonCodeService;
    @Autowired
    StudentService studentService;
    @Autowired
    InformationStatisticService informationStatisticService;


    //导入系统格式
    String[] varify = {
            "协议编号", "所在院系", "院系代码", "姓名", "学号", "考生号", "性别", "民族", "院校名称", "院校代码", "学制", "学历", "学历代码", "政治面貌",
            "政治面貌代码", "身份证号", "入学日期", "预计毕业日期", "专业名称", "专业代码", "培养方式", "培养方式代码", "定向委培单位", "师范生类别",
            "师范生类别代码", "困难生类别", "困难生类别代码", "城乡生源", "生源所在地参考", "手机号", "拟毕业去向", "备注"
    };
//    String[] varify = {
//            "ksh","xh","xm","xb","csrq","sfzh","zzmm","mz","yxdm","yxmc","zydm","zymc","xsh","xldm",
//            "xz","rxrq","dqszj","byrq","cx","pyfsdm","sfslbdm","syszddm"
//    };


    DataType[] dataType = {
            new DataType("agreementNumber", 0), new DataType("collegeName", 1),
            new DataType("collegeId", 2), new DataType("name", 3),
            new DataType("studentId", 4), new DataType("examId", 5),
            new DataType("sex", 6), new DataType("nation", 7),
            new DataType("school", 8), new DataType("schoolId", 9),
            new DataType("stuLength", 10), new DataType("graduatQualification", 11),
            new DataType("qualificationId", 12), new DataType("politicalStand", 13),
            new DataType("politicalStandId", 14), new DataType("idNumber", 15),
            new DataType("registTime", 16), new DataType("graduationTime", 17),
            new DataType("major", 18), new DataType("majorId", 19),
            new DataType("trainingMode", 20), new DataType("trainingModeId", 21),
            new DataType("weipeiInsitution", 22), new DataType("normalStu", 23),
            new DataType("normalStuId", 24), new DataType("difficultyType", 25),
            new DataType("difficultyTypeId", 26), new DataType("studentClassification", 27),
            new DataType("nativePlaceRemind", 28), new DataType("tele", 29),
            new DataType("wherewantgo", 30), new DataType("remark", 31),
    };


    //国家系统导出数据的导入格式
    String[] varifyNation = {
            "考生号", "身份证号", "姓名", "性别代码", "性别", "民族代码", "民族", "政治面貌代码", "政治面貌", "院校代码", "院校名称",    // 0 - 10
            "分校名称", "学历代码", "学历", "专业代码", "专业", "专业方向", "培养方式代码", "培养方式", "定向或委培单位", "生源所在地代码", // 11 - 20
            "生源所在地", "城乡生源", "学制", "入学时间", "毕业时间", "师范生类别代码", "师范生类别", "困难生类别代码", "困难生类别",       // 21 - 29
            "所在院系", "所在班级", "学号", "出生日期", "入学前档案所在单位", "入学前户口所在地派出所", "户口是否转入学校", "档案是否转入学校", "手机号码", // 30 - 38
            "电子邮箱", "QQ号码", "家庭住址", "家庭电话", "家庭邮编", "毕业去向代码", "毕业去向", "单位组织机构代码", "单位名称", "单位性质代码",// 39 - 48
            "单位性质", "单位行业代码", "单位行业", "单位所在地代码", "单位所在地", "工作职位类别代码", "工作职位类别", "单位联系人", "联系人电话",// 49 - 57
            "联系人手机", "联系人电子邮箱", "联系人传真", "单位地址", "单位邮编", "报到证签发类别代码", "报到证签发类别", "报到证签往单位名称", "签往单位所在地代码", // 58 - 66
            "签往单位所在地", "报到证编号", "报到起始时间", "档案转寄单位名称", "档案转寄单位地址", "档案转寄单位邮编", "户口迁转地址" // 67 - 73
    };

    DataType[] nationDataType = {
            new DataType("examId", 0), new DataType("idNumber", 1), new DataType("name", 2), new DataType("sexId", 3),
            new DataType("sex", 4), new DataType("nationId", 5), new DataType("nation", 6), new DataType("politicalStandId", 7),
            new DataType("politicalStand", 8), new DataType("schoolId", 9), new DataType("school", 10), new DataType("qualificationId", 12),
            new DataType("graduatQualification", 13), new DataType("majorId", 14), new DataType("major", 15), new DataType("majorField", 16),
            new DataType("trainingModeId", 17), new DataType("trainingMode", 18), new DataType("weipeiInsitution", 19), new DataType("nativePlaceId", 20),
            new DataType("nativePlace", 21), new DataType("studentClassification", 22), new DataType("stuLength", 23), new DataType("registTime", 24),
            new DataType("graduationTime", 25), new DataType("normalStuId", 26), new DataType("normalStu", 27), new DataType("difficultyTypeId", 28),
            new DataType("difficultyType", 29), new DataType("collegeName", 30), new DataType("studentClass", 31), new DataType("studentId", 32),
            new DataType("dob", 33), new DataType("tele", 38), new DataType("whereaboutgoId", 44), new DataType("whereaboutgo", 45),
            new DataType("institutionId", 46), new DataType("institutionName", 47), new DataType("insModeId", 48), new DataType("insMode", 49),
            new DataType("insFieldId", 50), new DataType("insField", 51), new DataType("insPlaceId", 52), new DataType("insPlace", 53), new DataType("jobId", 54),
            new DataType("jobType", 55), new DataType("reportModeId", 63), new DataType("reportMode", 64), new DataType("reportIns", 65),
            new DataType("desInsId", 66), new DataType("goPlace", 67), new DataType("reportNumId", 68), new DataType("reportStartTime", 69)
    };

    //导出格式
    DataType[] heads = {
            new DataType("考生号", 0),
            new DataType("姓名", 1), new DataType("身份证号", 2), new DataType("性别代码", 3),
            new DataType("性别", 4), new DataType("民族代码", 5), new DataType("民族", 6),
            new DataType("政治面貌代码", 7), new DataType("政治面貌", 8), new DataType("院校代码", 9),
            new DataType("院校", 10), new DataType("所在院系", 11), new DataType("所在班级", 12),
            new DataType("学号", 13), new DataType("学历代码", 14), new DataType("学历", 15),
            new DataType("专业代码", 16), new DataType("专业", 17), new DataType("专业方向", 18),
            new DataType("培养方式代码", 19), new DataType("定向或委培单位", 20), new DataType("培养方式", 21),
            new DataType("生源所在地代码", 22), new DataType("生源所在地", 23), new DataType("学制", 24),
            new DataType("入学时间", 25), new DataType("毕业时间", 26), new DataType("困难生类别代码", 27),
            new DataType("困难生类别", 28), new DataType("师范生类别代码", 29), new DataType("师范生类别", 30),
            new DataType("毕业去向代码", 31), new DataType("毕业去向", 32), new DataType("单位组织机构代码", 33),
            new DataType("单位名称", 34), new DataType("单位性质代码", 35), new DataType("单位性质", 36),
            new DataType("单位行业代码", 37), new DataType("单位行业", 38), new DataType("单位所在地代码", 39),
            new DataType("单位所在地", 40), new DataType("工作职位类别代码", 41), new DataType("工作职位类别", 42),
            new DataType("报到证签发类别代码", 43), new DataType("报到证签发类别", 44), new DataType("报到证签往单位名称", 45),
            new DataType("签往单位所在地代码", 46), new DataType("签往单位所在地", 47), new DataType("报到证编号", 48),
            new DataType("报到起始时间", 49)
    };

    DataType[] head = {
            new DataType("Ksh", 0),
            new DataType("Xm", 1), new DataType("Sfzh", 2), new DataType("Xbdm", 3),
            new DataType("Xb", 4), new DataType("Mzdm", 5), new DataType("Mz", 6),
            new DataType("Zzmmdm", 7), new DataType("Zzmm", 8), new DataType("Yxdm", 9),
            new DataType("Yx", 10), new DataType("Szyx", 11), new DataType("Szbj", 12),
            new DataType("Xh", 13), new DataType("Xldm", 14), new DataType("Xl", 15),
            new DataType("Zydm", 16), new DataType("Zy", 17), new DataType("Zyfx", 18),
            new DataType("Pyfsdm", 19), new DataType("Dxhwpdw", 20), new DataType("Pyfs", 21),
            new DataType("Syszddm", 22), new DataType("Syszd", 23), new DataType("Xz", 24),
            new DataType("Rxsj", 25), new DataType("Bysj", 26), new DataType("Knslbdm", 27),
            new DataType("Knslb", 28), new DataType("Sfslbdm", 29), new DataType("Sfslb", 30),
            new DataType("Byqxdm", 31), new DataType("Byqx", 32), new DataType("Dwzzjgdm", 33),
            new DataType("Dwmc", 34), new DataType("Dwxzdm", 35), new DataType("Dwxz", 36),
            new DataType("Dwhydm", 37), new DataType("Dwhy", 38), new DataType("Dwszddm", 39),
            new DataType("Dwszd", 40), new DataType("Gzzwlbdm", 41), new DataType("Gzzwlb", 42),
            new DataType("Bdzqflbdm", 43), new DataType("Bdzqflb", 44), new DataType("Bdzqwdwmc", 45),
            new DataType("Qwdwszddm", 46), new DataType("Qwdwszd", 47), new DataType("bdzbh", 48),
            new DataType("Bdqssj", 49)
    };

    DataType[] dataTypes = {
            new DataType("examId", 0), new DataType("name", 1), new DataType("idNumber", 2),
            new DataType("sexId", 3), new DataType("sex", 4), new DataType("nationId", 5),
            new DataType("nation", 6), new DataType("politicalStandId", 7), new DataType("politicalStand", 8),
            new DataType("schoolId", 9), new DataType("school", 10), new DataType("collegeId", 11),
            new DataType("studentClass", 12), new DataType("studentId", 13), new DataType("qualificationId", 14),
            new DataType("graduatQualification", 15), new DataType("majorId", 16), new DataType("major", 17),
            new DataType("majorField", 18), new DataType("trainingModeId", 19), new DataType("weipeiInsitution", 20),
            new DataType("trainingMode", 21), new DataType("nativePlaceId", 22), new DataType("nativePlace", 23),
            new DataType("stuLength", 24), new DataType("registTime", 25), new DataType("graduationTime", 26),
            new DataType("difficultyTypeId", 27), new DataType("difficultyType", 28), new DataType("normalStuId", 29),
            new DataType("normalStu", 30), new DataType("whereaboutgoId", 31), new DataType("whereaboutgo", 32),
            new DataType("institutionId", 33), new DataType("institutionName", 34), new DataType("insModeId", 35),
            new DataType("insMode", 36), new DataType("insFieldId", 37), new DataType("insField", 38),
            new DataType("insPlaceId", 39), new DataType("insPlace", 40), new DataType("jobId", 41),
            new DataType("jobType", 42), new DataType("reportModeId", 43), new DataType("reportMode", 44),
            new DataType("reportIns", 45), new DataType("desInsId", 46), new DataType("desIns", 47),
            new DataType("reportNumId", 48), new DataType("reportStartTime", 49)
    };


    private StudentInfomation transform(StudentInfomation studentInfomation) {

        if (studentInfomation.getCollegeId() == null) {
            studentInfomation.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
        }
        if (studentInfomation.getSexId() == null) {
            studentInfomation.setSexId(commonCodeService.getSexId(studentInfomation.getSex()));
        }
        if (studentInfomation.getNationId() == null) {
            studentInfomation.setNationId(commonCodeService.getNationId(studentInfomation.getNation()));
        }
        if (studentInfomation.getQualificationId() == null && studentInfomation.getGraduatQualification() != null) {
            if (studentInfomation.getGraduatQualification().equals("本科生毕业")) {
                studentInfomation.setQualificationId("31");
                studentInfomation.setQualification("本科");
            }
            if (studentInfomation.getGraduatQualification().equals("本科生结业")) {
                studentInfomation.setQualificationId("33");
                studentInfomation.setQualification("本科");
            }
            if (studentInfomation.getGraduatQualification().equals("硕士生毕业")) {
                studentInfomation.setQualificationId("11");
                studentInfomation.setQualification("硕士研究生");
            }
            if (studentInfomation.getGraduatQualification().equals("硕士生结业")) {
                studentInfomation.setQualificationId("13");
                studentInfomation.setQualification("硕士研究生");
            }
            if (studentInfomation.getGraduatQualification().equals("博士生毕业")) {
                studentInfomation.setQualificationId("01");
                studentInfomation.setQualification("博士研究生");
            }
            if (studentInfomation.getGraduatQualification().equals("博士生结业")) {
                studentInfomation.setQualificationId("03");
                studentInfomation.setQualification("博士研究生");
            }
        }
        if (studentInfomation.getPoliticalStandId() == null) {
            studentInfomation.setPoliticalStandId(commonCodeService.getPoliticalId(studentInfomation.getPoliticalStand()));
        }
        if (studentInfomation.getTrainingModeId() == null && studentInfomation.getTrainingMode() != null) {
            if (studentInfomation.getTrainingMode().equals("非定向")) studentInfomation.setTrainingModeId(1);
            if (studentInfomation.getTrainingMode().equals("定向")) studentInfomation.setTrainingModeId(2);
            if (studentInfomation.getTrainingMode().equals("在职")) studentInfomation.setTrainingModeId(3);
            if (studentInfomation.getTrainingMode().equals("委培")) studentInfomation.setTrainingModeId(4);
            if (studentInfomation.getTrainingMode().equals("自筹")) studentInfomation.setTrainingModeId(5);
        }
        if (studentInfomation.getNormalStuId() == null) {
            studentInfomation.setNormalStuId(commonCodeService.getNormalStuId(studentInfomation.getNormalStu()));
        }
        if (studentInfomation.getDifficultyTypeId() == null) {
            studentInfomation.setDifficultyTypeId(commonCodeService.getDifficultyId(studentInfomation.getDifficultyType()));
        }

        return studentInfomation;
    }

    /**
     * 用core导出学生数据，导出上传国家的EXCEL
     *
     * @return 学生信息EXCEL
     */
    @Override
    public XSSFWorkbook getStudentInfomation(Integer year) throws SSException {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Sheet1");
            List<StudentInfomation> studentInfomationList = studentMapper.getStudentInfomationByYear(year.toString());

            sheet.setColumnWidth(1, studentInfomationList.size());

            XSSFRow row0 = sheet.createRow(0);
            for (int i = 0; i < head.length; i++) {
                XSSFCell cell = row0.createCell(i);
                cell.setCellValue(head[i].getFiledName());
            }

            XSSFRow row1 = sheet.createRow(1);
            for (int i = 0; i < heads.length; i++) {
                XSSFCell cell = row1.createCell(i);
                cell.setCellValue(heads[i].getFiledName());
            }

            for (int i = 0; i < studentInfomationList.size(); i++) {
                StudentInfomation studentInfomation = studentInfomationList.get(i);

                XSSFRow row = sheet.createRow(2 + i);
                for (int j = 0; j < dataTypes.length; j++) {
                    XSSFCell cell = row.createCell(j);

                    String methodName = "get" + dataTypes[j].getFiledName();
                    char[] name = methodName.toCharArray();
                    name[3] = (char) (name[3] - 32);
                    methodName = String.valueOf(name);
                    Method method = studentInfomation.getClass().getDeclaredMethod(methodName);

                    Object object = method.invoke(studentInfomation);

                    if (object == null) {
                        cell.setCellValue("");
                    } else {
                        String obj = object.toString();
                        cell.setCellValue(obj);
                    }
                }
            }
            return wb;
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 传入学生数据的EXCEL，用core包读，导入数据库
     *
     * @param studentExcel 传入学生信息EXCEL
     */
    @Override
    public void setStudentInfomation(File studentExcel) throws SSException {
        try {
            List<StudentInfomation> studentInfomationList =
                    (List<StudentInfomation>) ExcelReader.readExcel(StudentInfomation.class, studentExcel, 1, dataType);
            for (int i = 0; i < studentInfomationList.size(); i++) {
                StudentInfomation studentInfomation = studentInfomationList.get(i);
                if (studentInfomation.getExamId() == null) continue;

                StudentInfomation studentInfomation1 = studentMapper.getStudentByExamId(studentInfomation.getExamId());
                if (studentInfomation1 == null) {
                    studentInfomation = transform(studentInfomation);
                    //studentMapper.insertStudentInfomation(studentInfomation);
                    commonDao.insert(studentInfomation);
                    informationStatisticService.add(studentInfomation);
                } else continue;
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 传入从国家系统导入的EXCEL，导入数据库
     *
     * @param studentExcel
     * @throws SSException
     */
    @Override
    public void setStudentInfomationFormNationSystem(File studentExcel) throws SSException {
        try {
            List<StudentInfomation> studentInfomationList =
                    (List<StudentInfomation>) ExcelReader.readExcel(StudentInfomation.class, studentExcel, 1, nationDataType);
            for (int i = 0; i < studentInfomationList.size(); i++) {
                Date date = new Date();
                StudentInfomation studentInfomation = studentInfomationList.get(i);

                if (studentInfomation.getQualificationId().equals("01") || studentInfomation.equals("03")) {
                    studentInfomation.setQualification("博士研究生");
                } else if (studentInfomation.getQualificationId().equals("11") || studentInfomation.getQualificationId().equals("13")) {
                    studentInfomation.setQualification("硕士研究生");
                } else if (studentInfomation.getQualificationId().equals("31") || studentInfomation.getQualificationId().equals("33")) {
                    studentInfomation.setQualification("本科");
                }

                studentInfomation.setCollegeId(commonCodeService.getCollegeId(studentInfomation.getCollegeName()));
                studentInfomation.setGraduationTime(studentInfomation.getGraduationTime().substring(0, 4));
                studentInfomation.setGrade(Integer.valueOf(studentInfomation.getRegistTime().substring(0, 4)));
                studentInfomation.setVerifyStatus("已审核");
                studentInfomation.setVerifyTime(date);
                studentInfomation.setCheckinStatus("已录入");
                studentInfomation.setCheckinTime(date);

                commonDao.insert(studentInfomation);
                informationStatisticService.addFromNation(studentInfomation);
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 补全从国家系统导出的EXCEL中的学号缺失
     *
     * @param studentExcel
     * @throws SSException
     */
    @Override
    public void completeStudentId(File studentExcel) throws SSException {
        try {
            FileInputStream fileInputStream = new FileInputStream(studentExcel);
            jxl.Workbook workbook = jxl.Workbook.getWorkbook(fileInputStream);
            jxl.Sheet sheet = workbook.getSheet(0);

            int indexStudentId = -1, indexIdNumber = -1;
            jxl.Cell firstRow[] = sheet.getRow(0);
            for (int i = 0; i < firstRow.length; i++) {
                if (firstRow[i].getContents().equals("学号") || firstRow[i].getContents().equals("xh"))
                    indexStudentId = i;
                if (firstRow[i].getContents().equals("身份证号") || firstRow[i].getContents().equals("sfzh"))
                    indexIdNumber = i;
            }

            if (indexIdNumber >= 0 && indexStudentId >= 0) {
                HashMap<String, Long> hashMap = new HashMap<String, Long>();

                for (int i = 1; i < sheet.getRows(); i++) {
                    jxl.Cell cells[] = sheet.getRow(i);
                    hashMap.put(cells[indexIdNumber].getContents(), Long.valueOf(cells[indexStudentId].getContents()));
                }

                List<StudentInfomation> studentInfomationList = studentService.listStudents();

                for (StudentInfomation studentInfomation : studentInfomationList) {
                    if (studentInfomation.getStudentId() != null) continue;

                    Long studentId = hashMap.get(studentInfomation.getIdNumber());
                    studentInfomation.setStudentId(studentId);

                    studentService.updateStudent(studentInfomation);
                    informationStatisticService.updateFromNation(studentInfomation);
                }

            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 传入EXCEL，判断是否符合要求
     *
     * @param studentExcel
     * @return
     */
    @Override
    public boolean varifyStudentExcel(File studentExcel) throws SSException {
        try {
            Workbook workbook = null;
            System.out.println(studentExcel.getName());
            String extensionName = FilenameUtils.getExtension(studentExcel.getName());
            if (extensionName.toLowerCase().equals("xls")) {
                FileInputStream inputStream = new FileInputStream(studentExcel);
                workbook = new HSSFWorkbook(inputStream);
            } else if (extensionName.toLowerCase().equals("xlsx")) {
                FileInputStream inputStream = new FileInputStream(studentExcel);
                workbook = new XSSFWorkbook(inputStream);
            } else return false;
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(0);

            for (Cell cell : row) {
                String data = cell.getStringCellValue();
                boolean find = false;
                for (String name : varify) {
                    if (data.equals(name)) {
                        find = true;
                        break;
                    }
                }
                if (find == false) return false;
            }
            return true;
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    /**
     * 传入从国家系统导出的EXCEL，用于2015级学生的东西，判断是否符合
     *
     * @param studentExcel
     * @return
     * @throws SSException
     */
    @Override
    public boolean varifyNationExcel(File studentExcel) throws SSException {
        try {
            Workbook workbook = null;
            System.out.println(studentExcel.getName());
            String extensionName = FilenameUtils.getExtension(studentExcel.getName());
            if (extensionName.toLowerCase().equals("xls")) {
                FileInputStream inputStream = new FileInputStream(studentExcel);
                workbook = new HSSFWorkbook(inputStream);
            } else if (extensionName.toLowerCase().equals("xlsx")) {
                FileInputStream inputStream = new FileInputStream(studentExcel);
                workbook = new XSSFWorkbook(inputStream);
            } else return false;
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(0);


            for (String name : varifyNation) {
                boolean find = false;
                for (Cell cell : row) {
                    String data = cell.getStringCellValue();
                    if (data.equals(name)) {
                        find = true;
                        break;
                    }
                }
                if (find == false) return false;
            }

            return true;
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }
}
