package com.pandawork.nenu.oa.common.util;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.ImageUtil;
import com.pandawork.core.framework.fileUpload.FileUploadProcessor;
import com.pandawork.core.framework.fileUpload.UploadFile;
import com.pandawork.core.framework.fileUpload.impl.DefaultFileUpload;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.exception.OaException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FilesUtil
 * @author: yangzg
 * @time: 13-10-10 下午2:46
 */
public final class FilesUtil {


    /**
     * 调用core上传文件
     *
     * @param file          要上传的文件
     * @param fileFolder    保存的文件夹
     * @param request
     * @return path         保存后的路径
     * SSException
     */
    public static String uploadFile(PandaworkMultipartFile file,
                             String fileFolder,
                             HttpServletRequest request) throws SSException {
        if(file != null) {
            try {
                FileUploadProcessor defaultFileUpload=new DefaultFileUpload();
                String filename = file.getOriginalFilename();
                String basePath = request.getSession().getServletContext().getRealPath("/resources/");
                UploadFile files = new UploadFile(file.getFile(), basePath, fileFolder, filename, request);
                String path = defaultFileUpload.uploadOneFile(files);
                return path;
            } catch (SSException e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.UploadFileFail,e);
            }
        }
        return null;
    }

    /**
     * 压缩并保存图片
     *
     * @param file          原文件
     * @param width         压缩后图片的宽
     * @param height        压缩后图片的高
     * @param fileFolder    图片的保存文件夹
     * @param request
     *
     * @return path         图片的保存路径
     * SSException
     */
    public static String compressAndUpload(PandaworkMultipartFile file,
                                           int width,
                                           int height,
                                           String fileFolder,
                                           HttpServletRequest request) throws SSException {
        // 压缩图片
        ByteArrayInputStream bis = ImageUtil.getPicCompressed(file.getFile(), width, height);

        // 保存图片
        String path = "";
        FileOutputStream fos = null;
        try {
            String md5Name = CommonUtil.md5(bis.toString());
            String basePath = request.getSession().getServletContext().getRealPath("/resources/");
            // 得到文件格式
            String filename = file.getOriginalFilename();
            String format = filename.substring(filename.indexOf(".") + 1).toLowerCase();

            path = fileFolder + md5Name + "." + format;

            System.out.println(path);
            File f = new File(basePath + path);

            fos = new FileOutputStream(f);
            byte[] bytes = new byte[1024];
            int byteRead = -1;
            while((byteRead = bis.read(bytes, 0, 1024)) != -1) {
                fos.write(bytes, 0, byteRead);
            }
        } catch (IOException e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UploadFileFail, e);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LogClerk.errLog.error(e);
                    throw SSException.get(OaException.UploadFileFail, e);
                }
            }
        }
        return path;
    }
}
