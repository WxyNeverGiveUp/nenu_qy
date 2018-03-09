package com.pandawork.nenu.oa.common.util;

import com.pandawork.core.pweio.excel.DataType;
import com.pandawork.core.pweio.excel.ExcelWriter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 14-9-26.
 */
public final class ExcelUtil {
    public static Map<String,String> exportFieldDesc = new HashMap<>();
    static{
        //学籍信息
        exportFieldDesc.put("name","姓名");
        exportFieldDesc.put("stuName", "姓名");
        exportFieldDesc.put("idNumber","身份证号");
        exportFieldDesc.put("candidateNumber","考生号");
        exportFieldDesc.put("studentNumber","学号");
        exportFieldDesc.put("sex","性别");
        exportFieldDesc.put("nationCode","民族代码");
        exportFieldDesc.put("nation","民族");
        exportFieldDesc.put("politicalStandCode","政治面貌代码");
        exportFieldDesc.put("politicalStand","政治面貌");
        exportFieldDesc.put("schoolCode","院校代码");
        exportFieldDesc.put("school","院校");
        exportFieldDesc.put("stuLength","学制");
        exportFieldDesc.put("qualificationNow","在读学历");
        exportFieldDesc.put("qualificationCode","学历代码");
        exportFieldDesc.put("qualification","学历");
        exportFieldDesc.put("collegeCode","学院代码");
        exportFieldDesc.put("college","学院");
        exportFieldDesc.put("grade","年级");
        exportFieldDesc.put("majorQualification","专业层次");
        exportFieldDesc.put("majorCode","专业代码");
        exportFieldDesc.put("major","专业名称");
        //新加列-其它专业
        exportFieldDesc.put("otherMajor","其他专业");
        exportFieldDesc.put("intendWhereaboutsCode","拟毕业去向代码");
        exportFieldDesc.put("intendWhereabouts","拟毕业去向");
        exportFieldDesc.put("difficultyCode","困难生类别代码");
        exportFieldDesc.put("difficulty","困难生类别");
        exportFieldDesc.put("normalStuCode","师范生类别代码");
        exportFieldDesc.put("normalStu","师范生类别");
        exportFieldDesc.put("originPlaceCode","生源所在地代码");
        exportFieldDesc.put("originPlaceType", "生源地类别");
//        exportFieldDesc.put("originPlace","生源所在地");
        //新加列-生源所在地(学生填写)
        exportFieldDesc.put("originPlace","生源所在地(学生填写)");
        exportFieldDesc.put("trainingModeCode","培养方式代码");
        exportFieldDesc.put("trainingMode","培养方式");
        exportFieldDesc.put("weipeiUnit","定向/委培单位");
        exportFieldDesc.put("weipeiUnitPlaceCode","定向/委培单位地区代码");
        exportFieldDesc.put("weipeiUnitPlace","定向/委培单位地区");
        exportFieldDesc.put("registDate","入学日期");
        exportFieldDesc.put("graduationDate","毕业日期");
        /*户口和档案信息暂时注掉不导出*/
//        exportFieldDesc.put("preHukouLocation","入学前户口所在地");
//        exportFieldDesc.put("isHukouIntoSchool","户口是否转入学校");
//        exportFieldDesc.put("preArchiveLocation","入学前档案所在地");
//        exportFieldDesc.put("isArchiveIntoSchool","档案是否转入学校");
        exportFieldDesc.put("cellphone","手机号");
        exportFieldDesc.put("cellphoneBak","备用手机号");
        exportFieldDesc.put("qq","qq");
        exportFieldDesc.put("email","email");
        exportFieldDesc.put("homePhone","家庭电话");
        exportFieldDesc.put("relativePhone","紧急联系人");
        exportFieldDesc.put("weixin","微信");
        exportFieldDesc.put("homeAddress","家庭住址");

        //派遣信息
        exportFieldDesc.put("agreementNumber","协议编码");
        exportFieldDesc.put("currentAgreement","是否是当前协议");
        exportFieldDesc.put("whereaboutgoId","毕业去向代码");
        exportFieldDesc.put("whereaboutgo","毕业去向");
        exportFieldDesc.put("companyName","签约单位名称");
        exportFieldDesc.put("organizationCode","单位组织结构代码");
        exportFieldDesc.put("cityId","单位所在地代码");
        exportFieldDesc.put("cityName","单位所在地");
        exportFieldDesc.put("propertyId","单位性质代码");
        exportFieldDesc.put("propertyName","单位性质");
        exportFieldDesc.put("tradeId","单位所属行业代码");
        exportFieldDesc.put("tradeName","单位所属行业");
        exportFieldDesc.put("subordinateDepartment","单位隶属部门");
        exportFieldDesc.put("jobId","工作职位类别代码");
        exportFieldDesc.put("job","工作职位类别");
        exportFieldDesc.put("fullAddress","单位详细地址");
        exportFieldDesc.put("companyPostcode","单位邮编");
        exportFieldDesc.put("companyContactPerson","单位联系人");
        exportFieldDesc.put("contactPersonFax","单位联系人传真");
        exportFieldDesc.put("contactPersonTele","单位联系人电话");
        exportFieldDesc.put("contactPersonMobile","单位联系人手机号");
        exportFieldDesc.put("contactPersonEmail","单位联系人邮箱号");
        exportFieldDesc.put("reportModeId","报到证类别代码");
        exportFieldDesc.put("reportMode","报到证类别");
        exportFieldDesc.put("reportCompany","报到证迁往单位");
        exportFieldDesc.put("reportCompanyAddress","报到证迁往单位地址代码");
        exportFieldDesc.put("reportAddressName","报到证迁往单位地址");
        exportFieldDesc.put("acceptFile","是否接受档案");
        exportFieldDesc.put("fileCompanyPostcode","档案转寄单位邮编");
        exportFieldDesc.put("fileCompany","档案转寄单位名称");
        exportFieldDesc.put("fileCompanyAddress","档案转寄单位地址代码");
        exportFieldDesc.put("fileAddressName","档案转寄单位地址");
        exportFieldDesc.put("acceptFileDepartment","接收档案部门");
        exportFieldDesc.put("acceptFilePerson","接收档案联系人");
        exportFieldDesc.put("acceptFileTele","接收档案电话");
        exportFieldDesc.put("acceptHukou","是否接受户口");


        //后加 大导出列
        exportFieldDesc.put("sexCode","性别代码");
        exportFieldDesc.put("agreementNumber","协议编号");
        exportFieldDesc.put("majorDivision","专业类别");
        exportFieldDesc.put("entranceWay","入学方式");
        exportFieldDesc.put("tradeId","单位行业代码");
        exportFieldDesc.put("trade","单位行业");
        exportFieldDesc.put("adminRemark1","备注1");
        exportFieldDesc.put("adminRemark2","备注2");
        exportFieldDesc.put("adminRemark3","备注3");
        exportFieldDesc.put("adminRemark4","备注4");
        exportFieldDesc.put("adminRemark5","备注5");
        exportFieldDesc.put("adminRemark6","备注6");
        exportFieldDesc.put("adminRemark7","备注7");
        exportFieldDesc.put("adminRemark8","备注8");
        exportFieldDesc.put("adminRemark9","备注9");
        exportFieldDesc.put("adminRemark10","备注10");
        exportFieldDesc.put("enterBeijing","进京函");
        exportFieldDesc.put("enterTianjin","进津函");
        exportFieldDesc.put("enterShanghai","进沪函");
        exportFieldDesc.put("orientationCancelContract","定向解约材料");
        exportFieldDesc.put("freeNormalCancelContract","免师解约材料");
        exportFieldDesc.put("freeNormalTransProvincial","免师跨省材料");

        //就业信息调查表导出
        exportFieldDesc.put("pk1", "1.就业意向");
        exportFieldDesc.put("pk2", "2.本科院校所属");
        exportFieldDesc.put("underGraduate", "其他院校");
        exportFieldDesc.put("pk3_1", "3.就业指导-信息获取");
        exportFieldDesc.put("pk3_2", "3.就业指导-简历制作");
        exportFieldDesc.put("pk3_3", "3.就业指导-网申技巧");
        exportFieldDesc.put("pk3_4", "3.就业指导-求职礼仪");
        exportFieldDesc.put("pk3_5", "3.就业指导-面试技巧");
        exportFieldDesc.put("pk3_6", "3.就业指导-就业心理咨询");
        exportFieldDesc.put("pk3_7", "3.就业指导-教师应聘指导");
        exportFieldDesc.put("pk3_8", "3.就业指导-教师技能训练");
        exportFieldDesc.put("pk3_9", "3.就业指导-公考指导");
        exportFieldDesc.put("pk3_10", "3.就业指导-基层项目指导");
        exportFieldDesc.put("pk3_11", "3.就业指导-政策解读");
        exportFieldDesc.put("pk3_12", "3.就业指导-其他");
        exportFieldDesc.put("otherGuidance", "3.就业指导-其他详情");
        exportFieldDesc.put("pk4_11", "4.第一就业意向地点-省");
        exportFieldDesc.put("pk4_12", "市");
        exportFieldDesc.put("pk4_21", "第二就业意向地点-省");
        exportFieldDesc.put("pk4_22", "市");
        exportFieldDesc.put("pk4_31", "第三就业意向地点-省");
        exportFieldDesc.put("pk4_32", "市");
        exportFieldDesc.put("pk5", "5.预期月薪");
        exportFieldDesc.put("pk6", "6.就业意向所属行业");
        exportFieldDesc.put("pk7_1", "7.教育行业-学前教育");
        exportFieldDesc.put("pk7_2", "7.教育行业-小学");
        exportFieldDesc.put("pk7_3", "7.教育行业-初中");
        exportFieldDesc.put("pk7_4", "7.教育行业-高中");
        exportFieldDesc.put("pk7_5", "7.教育行业-职业学校");
        exportFieldDesc.put("pk7_6", "7.教育行业-大中专学校");
        exportFieldDesc.put("pk7_7", "7.教育行业-高校");
        exportFieldDesc.put("pk7_8", "7.教育行业-科研机构");
        exportFieldDesc.put("pk7_9", "7.教育行业-培训学校");
        exportFieldDesc.put("pk8", "8.其他行业倾向于");
        exportFieldDesc.put("pk9_1", "9.择业时会选择哪些途径获取招聘信息-学校就业网");
        exportFieldDesc.put("pk9_2", "9.择业时会选择哪些途径获取招聘信息-单位网站和宣传册");
        exportFieldDesc.put("pk9_3", "9.择业时会选择哪些途径获取招聘信息-亲戚朋友介绍");
        exportFieldDesc.put("pk9_4", "9.择业时会选择哪些途径获取招聘信息-招聘网站");
        exportFieldDesc.put("pk9_5", "9.择业时会选择哪些途径获取招聘信息-导师推荐");
        exportFieldDesc.put("pk9_6", "9.择业时会选择哪些途径获取招聘信息-朋辈引荐和内推");
        exportFieldDesc.put("pk9_7", "9.择业时会选择哪些途径获取招聘信息-其他");
        exportFieldDesc.put("pk10_1", "10.第一意向就业单位");
        exportFieldDesc.put("pk10_2", "第二意向就业单位");
        exportFieldDesc.put("pk10_3", "第三意向就业单位");

        exportFieldDesc.put("countColumn1", "签就业协议形式就业人数");
        exportFieldDesc.put("statisticsColumn1", "签就业协议形式就业率");
        exportFieldDesc.put("countColumn2", "签劳动合同形式就业人数");
        exportFieldDesc.put("statisticsColumn2", "签劳动合同形式就业率");
        exportFieldDesc.put("countColumn3", "其他录用形式就业人数");
        exportFieldDesc.put("statisticsColumn3", "其他录用形式就业率");
        exportFieldDesc.put("countColumn4", "升学人数");
        exportFieldDesc.put("statisticsColumn4", "升学率");
        exportFieldDesc.put("countColumn5", "出国(境)人数");
        exportFieldDesc.put("statisticsColumn5", "出国(境)比率");
        exportFieldDesc.put("countColumn6", "科研助理人数");
        exportFieldDesc.put("statisticsColumn6", "科研助理比率");
        exportFieldDesc.put("countColumn7", "应征义务兵人数");
        exportFieldDesc.put("statisticsColumn7", "应征义务兵比率");
        exportFieldDesc.put("countColumn8", "国家基层项目人数");
        exportFieldDesc.put("statisticsColumn8", "国家基层项目比率");
        exportFieldDesc.put("countColumn9", "地方基层项目人数");
        exportFieldDesc.put("statisticsColumn9", "地方基层项目比率");
        exportFieldDesc.put("countColumn10", "自由职业人数");
        exportFieldDesc.put("statisticsColumn10", "自由职业比率");
        exportFieldDesc.put("countColumn11", "自主创业人数");
        exportFieldDesc.put("statisticsColumn11", "自主创业比率");
        exportFieldDesc.put("countColumn12", "未就业人数");
        exportFieldDesc.put("statisticsColumn12", "未就业比率");
        exportFieldDesc.put("countEmployment", "就业人数");
        exportFieldDesc.put("countAll", "总计");
        exportFieldDesc.put("statisticsEmploymentNoClever", "就业率(不含灵活)");
        exportFieldDesc.put("statisticsEmployment", "就业率");
        exportFieldDesc.put("countWithNormal", "就业人数(包括免师)");
        exportFieldDesc.put("countWithWeipei", "就业人数(包括委培)");
        exportFieldDesc.put("countWithNormalAndWeipei", "就业人数(包括免师和委培)");
        exportFieldDesc.put("statisticsWithNormal", "就业率(包括免师)");
        exportFieldDesc.put("statisticsWithWeipei", "就业率(包括委培)");
        exportFieldDesc.put("statisticsWithNormalAndWeipei", "就业率(包括免师和委培)");

        //业务预约协议信息
        exportFieldDesc.put("result", "审核结果");
        exportFieldDesc.put("reason", "审核理由");
        exportFieldDesc.put("operator", "操作人");
        exportFieldDesc.put("checkTime", "审核时间");
        exportFieldDesc.put("fetchPlace", "领取地点");
        exportFieldDesc.put("fetchTime", "领取时间");
        exportFieldDesc.put("freeTeacherProvice", "免师跨省");
        exportFieldDesc.put("protocolType", "协议类型");

    }

    /**
     * 调用core导入Excel
     *
     * @param clazz 具体类
     * @param saveFileName 导入后Excel名称
     * @param beginRow 从第几行导入
     * @return list 返回对应Excel的list
     * SSException
     */
    public static List readExcel(HttpServletRequest request, Class clazz,String saveFileName,int beginRow){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        File file=new File(request.getSession().getServletContext().getRealPath("/resources/upload") + "/"+saveFileName) ;
        try{
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        DataType[] dataTypes = new DataType[fields.length-1];
        for (int j = 1; j < fields.length; j++) {
            if(!fields[j].isAccessible()){
                fields[j].setAccessible(true);
            }
            DataType dataType = new DataType(fields[j].getName(),j-1);
            dataTypes[j-1] = dataType;
        }
        List list0 = null;
        try {
            Class c = Class.forName("com.pandawork.core.excel.ExcelReader");
            Class[] args1 = new Class[4];
            args1[0] = Class.class;
            args1[1] = jxl.Sheet.class;
            args1[2] = int.class;
            args1[3] =dataTypes.getClass();
            Method m = c.getDeclaredMethod("readShell",args1);
            m.setAccessible(true);
            Workbook book  =  Workbook.getWorkbook(file);
            Sheet sheet = book.getSheet(0);

            list0 = (List) m.invoke(c.newInstance(), clazz, sheet, beginRow, dataTypes);
            for(int i=0;i<list0.size();i++){
                if(i==(list0.size()-1)){
                    list0.remove(i);
                }
            }
            System.out.println(list0);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list0;
    }

    /**
     * 调用core导出Excel
     *
     * @param list   要导出的数据集合
     * @param sheetName    Excel的sheet名称
     * @param path         导出Excel名称
     * SSException
     */
    public static void writeExcel(List list, String sheetName, String path, HttpServletResponse response){
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        DataType[] dataTypes = new DataType[fields.length-1];
        DataType[] heads = new DataType[fields.length-1];
        for (int j = 1; j < fields.length; j++) {
            if(!fields[j].isAccessible()){
                fields[j].setAccessible(true);
            }
            DataType dataType = new DataType(fields[j].getName(),j-1);
            dataTypes[j-1] = dataType;
            heads[j-1] = new DataType(exportFieldDesc.get(fields[j].getName()),j-1);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //out = new FileOutputStream(path);
            ExcelWriter.writeExcel(list,sheetName,out,heads,dataTypes);
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (Exception e) {
            e.printStackTrace();
         }
    }

    public static String encodeFilename(String filename, HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
                return MimeUtility.encodeText(filename, "UTF-8", "B");

            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }



}
