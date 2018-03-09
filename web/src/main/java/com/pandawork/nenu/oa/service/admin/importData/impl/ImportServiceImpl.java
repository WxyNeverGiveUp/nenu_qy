package com.pandawork.nenu.oa.service.admin.importData.impl;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.pweio.excel.DataType;
import com.pandawork.core.pweio.excel.ExcelReader;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchImportDto;
import com.pandawork.nenu.oa.common.dto.dispatch.ReportCardImportDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.student.status.StuTypeEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.service.admin.importData.ImportService;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
import com.pandawork.nenu.oa.service.dispatch.ReportCardService;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * ImportServiceImpl
 *
 * @author wlm
 * @date 2016/9/7 16:44
 */

@Service("importService")
public class ImportServiceImpl implements ImportService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private StuStatusInfoService stuStatusInfoService;

    @Autowired
    private DispatchInfoService dispatchInfoService;

    @Autowired
    private ReportCardService reportCardService;

    //导入系统格式
    String[] stuExcelFormat = {
            "学号", "考生号", "姓名", "身份证号", "性别代码", "民族代码", "院校代码", "学制", "学历代码", "学院代码", "师范生类别代码", "培养方式代码", "定向委培单位", "生源所在地"};

    DataType[] stuDataType = {
            new DataType("studentNumber", 0), new DataType("candidateNumber", 1),
            new DataType("name", 2), new DataType("idNumber", 3),
            new DataType("sex",4), new DataType("nationCode",5),
            new DataType("schoolCode", 6), new DataType("stuLength", 7),
            new DataType("qualificationCode", 8), new DataType("collegeCode", 9),
            new DataType("normalStuCode", 10), new DataType("trainingModeCode", 11),
            new DataType("weipeiUnit", 12),new DataType("originPlaceImport",13)
    };

    String[] dispatchExcelFormat = {"学号", "协议编号"};

    DataType[] dispatchDataType = {
            new DataType("studentNumber", 0), new DataType("agreementNumber", 1),
    };

    String[] reportExcelFormat = {"学号", "报到证号"};

    DataType[] reportDataType = {
            new DataType("studentNumber", 0), new DataType("reportCardNumber", 1),
    };


    @Override
    public boolean checkStuStatusInfo(File studentExcel) throws SSException {
        return this.checkFormat(studentExcel, stuExcelFormat);
    }

    @Override
    public void importStuStatusInfo(File studentExcel) throws SSException {
        try {
            List<StuStatusInfo> stuStatusInfoList =
                    (List<StuStatusInfo>) ExcelReader.readExcel(StuStatusInfo.class, studentExcel, 1, stuDataType);
            for (int i = 0; i < stuStatusInfoList.size(); i++) {
                StuStatusInfo stuStatusInfo = stuStatusInfoList.get(i);
                if (Assert.isNull(stuStatusInfo.getStudentNumber()) || stuStatusInfo.getStudentNumber().equals("")){
                    continue;
                }
                StuStatusInfo stuStatusInfo1 = stuStatusInfoService.queryByStuNumber(stuStatusInfo.getStudentNumber());
                if (!Assert.isNull(stuStatusInfo1)) {
                    continue;
                }
                stuStatusInfo.setStuType(StuTypeEnums.SystemImport.getId());
                //验证性别是否合法
                if (!stuStatusInfo.getSex().equals(1) && !stuStatusInfo.getSex().equals(2)) {
                    throw SSException.get(OaException.ImportSexError);
                }
                //验证民族代码是否为空
                if(stuStatusInfo.getNationCode().equals(null) || stuStatusInfo.getNationCode().equals("")){
                    throw SSException.get(OaException.ImportNationCodeError);
                }
                //验证学制是否为空
                if(stuStatusInfo.getStuLength().equals(null) || stuStatusInfo.getStuLength().equals("")){
                    throw SSException.get(OaException.ImportStuLengthError);
                }
                //验证学历是否为空
                if(stuStatusInfo.getQualificationCode().equals(null) || stuStatusInfo.getQualificationCode().equals("")){
                    throw SSException.get(OaException.ImportQualificationCodeError);
                }
                //截取学号前四位作为年级
                if(!stuStatusInfo.getStudentNumber().equals("") || !stuStatusInfo.getStudentNumber().equals(null) ){
                   String gradeStr = stuStatusInfo.getStudentNumber().substring(0,4);
                   stuStatusInfo.setGrade(Integer.parseInt(gradeStr));
                }
                //根据学历层次设置专业层次
                if(stuStatusInfo.getQualificationCode().equals("01") || stuStatusInfo.getQualificationCode().equals("02") ||stuStatusInfo.getQualificationCode().equals("11") || stuStatusInfo.getQualificationCode().equals("13")  ){
                    stuStatusInfo.setMajorQualification("研究生专业");
                }
                if(stuStatusInfo.getQualificationCode().equals("31") || stuStatusInfo.getQualificationCode().equals("33") ){
                    stuStatusInfo.setMajorQualification("本科专业");
                }
                /*//验证生源所在地
                if(stuStatusInfo.getOriginPlaceImport().equals(null) || stuStatusInfo.getOriginPlaceImport().equals("")){
                    throw SSException.get(OaException.ImportOriginPlaceError);
                }*/
                commonDao.insert(stuStatusInfo);
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.ImportInfoIllegal,ee);
        }
    }

    @Override
    public boolean checkDispatchInfo(File dispatchExcel) throws SSException {
        return this.checkFormat(dispatchExcel, dispatchExcelFormat);
    }

    @Override
    public void importDispatchInfo(File dispatchExcel) throws SSException {
        try {
            List<DispatchImportDto> dispatchImportDtoList =
                    (List<DispatchImportDto>) ExcelReader.readExcel(DispatchImportDto.class, dispatchExcel, 1, dispatchDataType);
            for (int i = 0; i < dispatchImportDtoList.size(); i++) {
                DispatchImportDto dispatchImportDto = dispatchImportDtoList.get(i);
                if (Assert.isNull(dispatchImportDto.getStudentNumber()) || dispatchImportDto.getStudentNumber().equals("")){
                    continue;
                }
                String stuId = dispatchImportDto.getStudentNumber();
//                System.out.println(stuId);
                Integer statusInfoId = stuStatusInfoService.queryIdByStuNumber(dispatchImportDto.getStudentNumber());
                if (Assert.isNull(statusInfoId) || Assert.lessOrEqualZero(statusInfoId)) {
                    continue;
                }
                //查询出该生所有的派遣信息，并将其设置为非当前协议，然后将新导入的设置为当前协议
                DispatchInfo dispatchInfo = new DispatchInfo();
                dispatchInfoService.updateCurrentAgreement(statusInfoId);
                dispatchInfo.setStatusInfoId(statusInfoId);
                dispatchInfo.setAgreementNumber(dispatchImportDto.getAgreementNumber());
                commonDao.insert(dispatchInfo);

            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public boolean checkReportCard(File reportCardExcel) throws SSException {
        return this.checkFormat(reportCardExcel, reportExcelFormat);
    }

    @Override
    public void importReportCard(File reportCardExcel) throws SSException {
        try {
            List<ReportCardImportDto> reportCardImportDtoList =
                    (List<ReportCardImportDto>) ExcelReader.readExcel(ReportCardImportDto.class, reportCardExcel, 1, reportDataType);
            for (int i = 0; i < reportCardImportDtoList.size(); i++) {
                ReportCardImportDto reportCardImportDto = reportCardImportDtoList.get(i);
                if (Assert.isNull(reportCardImportDto.getStudentNumber()) || reportCardImportDto.getStudentNumber().equals("")){
                    continue;
                }
                Integer statusId = stuStatusInfoService.queryIdByStuNumber(reportCardImportDto.getStudentNumber());
                if (Assert.isNull(statusId) || Assert.lessOrEqualZero(statusId)) {
                    continue;
                }
                ReportCard tmp = reportCardService.queryByStatusId(statusId);
                ReportCard reportCard = new ReportCard();
                reportCard.setStatusInfoId(statusId);
                reportCard.setNumber(reportCardImportDto.getReportCardNumber());
                if (Assert.isNotNull(tmp)) {
                    if (Assert.isNotNull(tmp.getId())) {
                        reportCard.setId(tmp.getId());
                        commonDao.update(reportCard);
                    }
                } else {
                    commonDao.insert(reportCard);
                }
            }
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    private boolean checkFormat(File excel, String[] excelFormat) throws SSException {
        try {
            Workbook workbook = null;
            System.out.println(excel.getName());
            String extensionName = FilenameUtils.getExtension(excel.getName());
            if (extensionName.toLowerCase().equals("xls")) {
                FileInputStream inputStream = new FileInputStream(excel);
                workbook = new HSSFWorkbook(inputStream);
            } else if (extensionName.toLowerCase().equals("xlsx")) {
                FileInputStream inputStream = new FileInputStream(excel);
                workbook = new XSSFWorkbook(inputStream);
            } else return false;
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(0);

            for (Cell cell : row) {
                String data = cell.getStringCellValue();
                boolean find = false;
                if (!Assert.isNull(data) || !data.equals("")) {
                    for (String name : excelFormat) {
                        if (data.equals(name)) {
                            find = true;
                            break;
                        }
                    }
                    if (find == false) return false;
                }
            }
            return true;
        } catch (Exception ee) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.ImportInfoIllegal);
        }
    }
}
