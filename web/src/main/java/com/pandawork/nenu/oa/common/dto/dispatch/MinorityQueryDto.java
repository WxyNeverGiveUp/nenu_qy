package com.pandawork.nenu.oa.common.dto.dispatch;

import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;

import java.util.List;

/**
 * MinorityQueryDto
 * 少数民族查询条件Dto
 *
 * @author wlm
 * @date 2016/12/2 20:23
 */

public class MinorityQueryDto{

    //民族
    private String nation;

    //生源地所在省
    private Integer province;

    //学院
    private Integer college;

    //在读学历
    private Integer qualificationNow;

    //是否已提交
    private Integer showUncommitted;

    //查询者隶属学院
    private List<String> colleges;

    //查询者管理专业
    private List<String> majors;

    //当前页码
    private Integer curPage;

    //每页数量
    private Integer pageSize;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }

    public Integer getQualificationNow() {
        return qualificationNow;
    }

    public void setQualificationNow(Integer qualificationNow) {
        this.qualificationNow = qualificationNow;
    }

    public Integer getShowUncommitted() {
        return showUncommitted;
    }

    public void setShowUncommitted(Integer showUncommitted) {
        this.showUncommitted = showUncommitted;
    }

    public List<String> getColleges() {
        return colleges;
    }

    public void setColleges(List<String> colleges) {
        this.colleges = colleges;
    }

    public List<String> getMajors() {
        return majors;
    }

    public void setMajors(List<String> majors) {
        this.majors = majors;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
