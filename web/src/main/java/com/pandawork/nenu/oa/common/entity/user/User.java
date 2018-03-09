package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;
import org.apache.ibatis.annotations.Update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体
 * user:ouym
 * date: 2014/9/12
 * time: 10:12
 */
@Entity
@Table(name = "t_sys_users")
public class User extends AbstractEntity{
    private static final long serialVersionUID = 2886404158901827683L;
    //唯一标识
    @Id
    private Integer id;

    //用户名
    private String username;

    //密码
    private String password;

    //盐
    private String salt;

    //是否锁定：0表示没锁定，1表示锁定
    private Integer locked;

    //真实姓名
    @Column(name = "real_name")
    private String realName;

    //电话号码
    private String cellphone;
    //学院
    private String college;
    //本科 硕士  博士
    private String verify_qualification;
    //最后登录时间
    private Date last_login_time;
    //审核完成时间
    private Date verify_complete_time;

    public User(int userId, String username,String newPassword) {
        this.id = userId;
        this.username = username;
        this.password = newPassword;
    }

    public User(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked=" + locked +
                ", realName='" + realName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", college='" + college + '\'' +
                ", verify_qualification='" + verify_qualification + '\'' +
                ", last_login_time=" + last_login_time +
                ", verify_complete_time=" + verify_complete_time +
                '}';
    }

    public String getVerify_qualification() {
        return verify_qualification;
    }

    public void setVerify_qualification(String verify_qualification) {
        this.verify_qualification = verify_qualification;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Date getVerify_complete_time() {
        return verify_complete_time;
    }

    public void setVerify_complete_time(Date verify_complete_time) {
        this.verify_complete_time = verify_complete_time;
    }

    public String retCredentialsSalt(){
        return username + salt;
    }
}
