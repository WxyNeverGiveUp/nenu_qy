package com.pandawork.nenu.oa.service.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.business.ProtocolStuDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;

import java.util.List;

/**
 * 学生端，业务预约service层
 *
 * @author zhaiaxin
 * @time: 2017/7/19 14:15.
 */
public interface ProtocolStuService {

    /**
     * 分别增加申请新协议，毕业去向，免费师范生，定向委培，4个业务
     * @param protocol
     * @throws SSException
     */
    public void newBusiness (Protocol protocol) throws SSException;


    /**
     * 分别更改申请新协议，毕业去向，免费师范生，定向委培，4个业务
     * @param protocol
     * @throws SSException
     */
    public void updateBusiness(Protocol protocol) throws SSException;

    /**
     * 列出，申请新协议的所有记录
     * @param statusInfoId
     * @throws SSException
     */
    public List<ProtocolStuDto> listNewProtocol (int statusInfoId) throws SSException;

    /**
     * 列出申请毕业去向记录
     * @param statusInfoId
     * @throws SSException
     */
    public List<ProtocolStuDto> listGraduate(int statusInfoId) throws SSException;

    /**
     * 列出申请师范生记录
     * @param statusInfoId
     * @throws SSException
     */
    public List<ProtocolStuDto> listFreeTeacher(int statusInfoId) throws SSException;

    /**
     * 列出申请定向委培记录
     * @param statusInfoId
     * @throws SSException
     */
    public List<ProtocolStuDto> listDirectional(int statusInfoId) throws SSException;

    /**
     * 根据id查询新申请协议的记录
     * @param id
     * @return
     * @throws SSException
     */
    public ProtocolStuDto queryBusiness( int id) throws SSException;


    /**
     * 列出，博士生所有业务的记录
     * @param statusInfoId
     * @throws SSException
     */
    public List<ProtocolStuDto> listDoctorBusiness (int statusInfoId) throws SSException;


    /**
     * 根据学籍Id寻找表
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<Protocol> queryProtocol( int statusInfoId , int type) throws SSException;


//
//    /**
//     * 列出申请师范生记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public ProtocolStuDto queryFreeTeacher( int statusInfoId) throws SSException;
//
//    /**
//     * 列出申请师范生记录的实体
//     * @param statusInfoId
//     * @return
//     * @throws SSException
//     */
//    public Protocol queryFreeTeacherProtocol (int statusInfoId) throws SSException;
//
//    /**
//     * 列出申请毕业去向记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public ProtocolStuDto queryGraduate(int statusInfoId) throws SSException;
//
//    /**
//     * 列出申请定向委培记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public ProtocolStuDto queryDirectional(int statusInfoId) throws SSException;

    /**
     * 根据code查询省份
     * @param code
     * @return
     * @throws Exception
     */
    public String queryProvince( String code) throws SSException;
}
