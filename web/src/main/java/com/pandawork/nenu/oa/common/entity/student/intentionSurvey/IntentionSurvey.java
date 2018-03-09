package com.pandawork.nenu.oa.common.entity.student.intentionSurvey;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 实体
 * user:chenwy
 * date: 2017/05/12
 * time: 10:47
 */
@Entity
@Table(name = "t_stu_intention_survey")
public class IntentionSurvey extends AbstractEntity{
    private static final long serialVersionUID = 134233297682172107L;
    @Id
    private Integer id;

    //学籍id
    @Column(name = "status_info_id")
    private String statusInfoId;

    //第一题
    @Column(name = "pk_1")
    private String pk1;

    //第二题
    @Column(name = "pk_2")
    private String pk2;

    //本科毕业院校
    @Column(name = "undergraduate")
    private String undergraduate;

    //第三题
    @Column(name = "pk_3")
    private String pk3;

    //第三题其他就业指导
    @Column(name = "other_guidance")
    private String otherGuidance;

    //第四题
    @Column(name = "pk_4")
    private String pk4;

    //第五题
    @Column(name = "pk_5")
    private String pk5;

    //第六题
    @Column(name = "pk_6")
    private String pk6;

    //第七题
    @Column(name = "pk_7")
    private String pk7;

    //第八题
    @Column(name = "pk_8")
    private String pk8;

    //第九题
    @Column(name = "pk_9")
    private String pk9;

    //第十题
    @Column(name = "pk_10")
    private String pk10;

    //创建时间
    @Column(name = "created_time")
    private Timestamp createdTime;

    //最近修改时间
    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(String statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public String getPk1() {
        return pk1;
    }

    public void setPk1(String pk1) {
        this.pk1 = pk1;
    }

    public String getPk2() {
        return pk2;
    }

    public void setPk2(String pk2) {
        this.pk2 = pk2;
    }

    public String getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(String undergraduate) {
        this.undergraduate = undergraduate;
    }

    public String getPk3() {
        return pk3;
    }

    public void setPk3(String pk3) {
        this.pk3 = pk3;
    }

    public String getOtherGuidance() {
        return otherGuidance;
    }

    public void setOtherGuidance(String otherGuidance) {
        this.otherGuidance = otherGuidance;
    }

    public String getPk4() {
        return pk4;
    }

    public void setPk4(String pk4) {
        this.pk4 = pk4;
    }

    public String getPk5() {
        return pk5;
    }

    public void setPk5(String pk5) {
        this.pk5 = pk5;
    }

    public String getPk6() {
        return pk6;
    }

    public void setPk6(String pk6) {
        this.pk6 = pk6;
    }

    public String getPk7() {
        return pk7;
    }

    public void setPk7(String pk7) {
        this.pk7 = pk7;
    }

    public String getPk8() {
        return pk8;
    }

    public void setPk8(String pk8) {
        this.pk8 = pk8;
    }

    public String getPk9() {
        return pk9;
    }

    public void setPk9(String pk9) {
        this.pk9 = pk9;
    }

    public String getPk10() {
        return pk10;
    }

    public void setPk10(String pk10) {
        this.pk10 = pk10;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
