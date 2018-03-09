package com.pandawork.nenu.oa.common.dto.data;

/**
 * SchoolDto
 * 学校dto
 *
 * @author wlm
 * @date 2016/9/1 18:45
 */
public class SchoolDto {

    //高校id
    private Integer id;

    //高校代码
    private Integer code;

    //高校名称
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
