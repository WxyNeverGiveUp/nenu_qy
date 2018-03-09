package com.pandawork.nenu.oa.common.enums.other;

/**
 * 文件上传之后的存放位置枚举
 * 方便统一管理
 * 文件的建议存放方式为枚举中定义的path + id + 文件名
 * 文件路径前后都要加"/"
 *
 * @author: zhangteng
 * @time: 2015/8/21 22:48
 **/
public enum FileUploadPathEnums {

    StuStatusInformation("/student/xueji", "学生学籍证明材料"),
    StuIdentityChange("/business/identity", "学生身份变更证明材料"),
    StuApplyForNewProtocol ("/business/protocol", "学生申请新协议 "),
    StuDispatchInformation("/student/dispatch", "学生派遣信息材料")
    ;

    // 路径
    private String path;

    // 描述
    private String description;

    FileUploadPathEnums(String path, String description) {
        this.path = path;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }
}
