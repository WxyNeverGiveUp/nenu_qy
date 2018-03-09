package com.pandawork.nenu.oa.service.other.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.sytem.SystemInstance;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.CommonUtil;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.ImageUtils;
import com.pandawork.nenu.oa.service.other.FileUploadService;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 默认的文件上传实现
 * 上传到本地
 *
 * @author: zhangteng
 * @time: 2015/10/29 15:09
 **/
public class FileUploadToLocalServiceImpl implements FileUploadService {

    private String defaultUploadPath;

    @PostConstruct
    public void init() {
        SystemInstance systemInstance = SystemInstance.getIntance();
        defaultUploadPath = (String) systemInstance.getProperty("defaultUploadPath");
        defaultUploadPath = FileUploadToLocalServiceImpl.class.getResource("/../../").getFile() + "upload/";
    }

    @Override
    public String uploadFile(PandaworkMultipartFile file, FileUploadPathEnums pathEnums, int width, int height, String... extendPath) throws SSException {
        // 非空检查
        Assert.isNotNull(file, OaException.UploadFileNotNull);
        Assert.isNotNull(pathEnums, OaException.UploadPathNotNull);
        if (file.isEmpty()) {
            return "";
        }

        // 检查目标文件夹是否存在，不存在则创建
        String tmpPath = pathEnums.getPath();
        if (!tmpPath.startsWith("/")) {
            tmpPath = "/" + tmpPath;
        }
        if (!tmpPath.endsWith("/")) {
            tmpPath += "/";
        }
        if (!Assert.isNull(extendPath)
                && extendPath.length != 0) {
            if (extendPath[0].startsWith("/")) {
                tmpPath += extendPath[0].substring(1);
            } else {
                tmpPath += extendPath[0];
            }
        }
        if (!tmpPath.endsWith("/")) {
            tmpPath += "/";
        }
        String targetPath = defaultUploadPath + tmpPath;
        File path = new File(targetPath);
        boolean mkdirSuccess = true;
        if (!path.isDirectory()) {
            // 创建目录
            synchronized (FileUploadToLocalServiceImpl.class) {
                if (!path.isDirectory()) {
                    mkdirSuccess = path.mkdirs();
                }
            }
        }
        // 创建失败，返回空
        if (!mkdirSuccess) {
            throw SSException.get(OaException.UploadDirCreateFail);
        }

        // 对文件名进行md5加密
        File srcFile = file.getFile();
        String fileName = CommonUtil.md5(srcFile) + System.currentTimeMillis();
        String format = CommonUtil.getExtention(file.getOriginalFilename());
        if (!Assert.isNull(format)) {
            fileName += format;
        }

        String targetFileName = targetPath + fileName;
        // 拷贝文件
        File targetFile = new File(targetFileName);
        if (CommonUtil.copyFile(srcFile, targetFile)) {
            // 判断是否需要压缩
            if (!Assert.lessOrEqualZero(width)
                    || !Assert.lessOrEqualZero(height)) {
                try {
                    ImageUtils.compressImage(targetFile, width, height);
                } catch (Exception e) {
                    LogClerk.errLog.error(e);
                    throw SSException.get(OaException.ImageCompressedFail, e);
                }
            }
        } else {
            throw SSException.get(OaException.ImageCompressedFail);
        }
        System.out.println(tmpPath + fileName+"哈哈哈哈哈哈哈哈哈哈");
        return tmpPath + fileName;
    }

    @Override
    public String uploadFile(PandaworkMultipartFile file, FileUploadPathEnums pathEnums, String... extendPath) throws SSException {
        return this.uploadFile(file, pathEnums, 0, 0, extendPath);
    }
}
