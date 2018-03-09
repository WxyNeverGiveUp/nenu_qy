package com.pandawork.nenu.oa.mapper.business;

import com.pandawork.nenu.oa.common.entity.business.Protocol;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生端，业务预约mapper层
 *
 * @author zhaiaxin
 * @time: 2017/7/20 9:00.
 */
public interface ProtocolStuMapper {



    /**
     * 列出新申请协议的所有记录
     * @param statusInfoId
     * @throws Exception
     */
    public List<Protocol> listNewProtocol(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 列出申请毕业去向记录
     * @param statusInfoId
     * @throws Exception
     */
    public List<Protocol> listGraduate(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 列出申请师范生记录
     * @param statusInfoId
     * @throws Exception
     */
    public List<Protocol> listFreeTeacher(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 列出申请定向委培记录
     * @param statusInfoId
     * @throws Exception
     */
    public List<Protocol> listDirectional(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据id查询新申请协议的记录
     * @param id
     * @return
     * @throws Exception
     */
    public Protocol queryBusiness(@Param("id") int id) throws Exception;

    /**
     * 列出博士所有业务的记录
     * @param statusInfoId
     * @throws Exception
     */
    public List<Protocol> listDoctorBusiness(@Param("statusInfoId") int statusInfoId) throws Exception;
//
//
//    /**
//     * 列出申请师范生记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public Protocol queryFreeTeacher(@Param("statusInfoId") int statusInfoId) throws Exception;
//
//    /**
//     * 列出申请毕业去向记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public Protocol queryGraduate(@Param("statusInfoId") int statusInfoId) throws Exception;
//
//    /**
//     * 列出申请定向委培记录
//     * @param statusInfoId
//     * @throws Exception
//     */
//    public Protocol queryDirectional(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据code查询省份
     * @param code
     * @return
     * @throws Exception
     */
    public String queryProvince(@Param("code") String code) throws Exception;







}
