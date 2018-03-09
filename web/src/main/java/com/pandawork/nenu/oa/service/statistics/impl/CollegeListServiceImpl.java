package com.pandawork.nenu.oa.service.statistics.impl;

import com.pandawork.nenu.oa.common.dto.history.College;
import com.pandawork.nenu.oa.service.statistics.CollegeListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取学院列表service实现类
 * @author Lw
 * @time 2017/8/10 10:51
 */
@Service("collegeListService")
public class CollegeListServiceImpl implements CollegeListService {

    @Override
    public List<College> getUnderCollegeList() {
        List<College> underCollegeList = new ArrayList<>();
        //传媒科学学院
        College college1 = new College();
        college1.setCollegeName("传媒科学学院");
        college1.setCollegeCode("101");

        //地理科学学院
        College college2 = new College();
        college2.setCollegeName("地理科学学院");
        college2.setCollegeCode("102");

        //化学学院
        College college3 = new College();
        college3.setCollegeName("化学学院");
        college3.setCollegeCode("105");

        //环境学院
        College college4 = new College();
        college4.setCollegeName("环境学院");
        college4.setCollegeCode("106");

        //信息科学与技术学院
        College college5 = new College();
        college5.setCollegeName("信息科学与技术学院");
        college5.setCollegeCode("107");

        //教育学部
        College college6 = new College();
        college6.setCollegeName("教育学部");
        college6.setCollegeCode("108");

        //经济学院
        College college7 = new College();
        college7.setCollegeName("经济学院");
        college7.setCollegeCode("109");

        //历史文化学院
        College college8 = new College();
        college8.setCollegeName("历史文化学院");
        college8.setCollegeCode("110");

        //马克思主义学部
        College college9 = new College();
        college9.setCollegeName("马克思主义学部");
        college9.setCollegeCode("112");

        //美术学院
        College college10 = new College();
        college10.setCollegeName("美术学院");
        college10.setCollegeCode("113");

        //商学院
        College college11 = new College();
        college11.setCollegeName("商学院");
        college11.setCollegeCode("116");

        //生命科学学院
        College college12 = new College();
        college12.setCollegeName("生命科学学院");
        college12.setCollegeCode("117");

        //数学与统计学院
        College college13 = new College();
        college13.setCollegeName("数学与统计学院");
        college13.setCollegeCode("120");

        //体育学院
        College college14 = new College();
        college14.setCollegeName("体育学院");
        college14.setCollegeCode("122");

        //外国语学院
        College college15 = new College();
        college15.setCollegeName("外国语学院");
        college15.setCollegeCode("123");

        //文学院
        College college16 = new College();
        college16.setCollegeName("文学院");
        college16.setCollegeCode("124");

        //物理学院
        College college17 = new College();
        college17.setCollegeName("物理学院");
        college17.setCollegeCode("125");

        //音乐学院
        College college18 = new College();
        college18.setCollegeName("音乐学院");
        college18.setCollegeCode("127");

        //政法学院
        College college19 = new College();
        college19.setCollegeName("政法学院");
        college19.setCollegeCode("128");

        //心理学院
        College college20 = new College();
        college20.setCollegeName("心理学院");
        college20.setCollegeCode("132");

        underCollegeList.add(college1);         underCollegeList.add(college11);
        underCollegeList.add(college2);         underCollegeList.add(college12);
        underCollegeList.add(college3);         underCollegeList.add(college13);
        underCollegeList.add(college4);         underCollegeList.add(college14);
        underCollegeList.add(college5);         underCollegeList.add(college15);
        underCollegeList.add(college6);         underCollegeList.add(college16);
        underCollegeList.add(college7);         underCollegeList.add(college17);
        underCollegeList.add(college8);         underCollegeList.add(college18);
        underCollegeList.add(college9);         underCollegeList.add(college19);
        underCollegeList.add(college10);        underCollegeList.add(college20);

        return underCollegeList;
    }

    @Override
    public List<College> getPostCollegeList() {
        List<College> postCollgeList = new ArrayList<>();

        List<College> underCollegeList = getUnderCollegeList();
        postCollgeList.addAll(underCollegeList);

        //古籍整理研究所
        College college21 = new College();
        college21.setCollegeName("古籍整理研究所");
        college21.setCollegeCode("103");

        //国际与比较教育研究所
        College college22 = new College();
        college22.setCollegeName("国际与比较教育研究所");
        college22.setCollegeCode("104");

        //国际汉学院
        College college23 = new College();
        college23.setCollegeName("国际汉学院");
        college23.setCollegeCode("111");

        //农村教育研究所
        College college24 = new College();
        college24.setCollegeName("农村教育研究所");
        college24.setCollegeCode("114");

        //日本研究所
        College college25 = new College();
        college25.setCollegeName("日本研究所");
        college25.setCollegeCode("115");

        //世界古典文明史研究所
        College college26 = new College();
        college26.setCollegeName("世界古典文明史研究所");
        college26.setCollegeCode("118");

        //世界中古史研究所
        College college27 = new College();
        college27.setCollegeName("世界中古史研究所");
        college27.setCollegeCode("119");

        //思想政治教育研究中心
        College college28 = new College();
        college28.setCollegeName("思想政治教育研究中心");
        college28.setCollegeCode("121");

        //亚洲文明研究院
        College college29 = new College();
        college29.setCollegeName("亚洲文明研究院");
        college29.setCollegeCode("126");

        //理想信息技术研究院
        College college30 = new College();
        college30.setCollegeName("理想信息技术研究院");
        college30.setCollegeCode("129");
        
        //世界文明史研究中心
        College college31 = new College();
        college31.setCollegeName("世界文明史研究中心");
        college31.setCollegeCode("130");
        
        //东北师范大学罗格斯大学纽瓦克学院
        College college32 = new College();
        college32.setCollegeName("东北师范大学罗格斯大学·纽瓦克学院");
        college32.setCollegeCode("131");

        postCollgeList.add(college21);          postCollgeList.add(college27);
        postCollgeList.add(college22);          postCollgeList.add(college28);
        postCollgeList.add(college23);          postCollgeList.add(college29);
        postCollgeList.add(college24);          postCollgeList.add(college30);
        postCollgeList.add(college25);          postCollgeList.add(college31);
        postCollgeList.add(college26);          postCollgeList.add(college32);

        return postCollgeList;
    }
}
