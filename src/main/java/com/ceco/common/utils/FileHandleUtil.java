package com.ceco.common.utils;

import com.ceco.common.exception.BusinessException;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zy
 * @Date: 2020/8/24 9:28
 * @desc：
 */
public class FileHandleUtil {

    /**
     * 上传单个文件
     *
     * @param inputStream 文件流
     * @param path        文件路径，如：image/
     * @param filename    文件名，如：test.jpg
     * @return 成功：上传后的文件访问路径，失败返回：null
     */
    public static String upload(InputStream inputStream, String path, String filename) {
        //创建文件夹
        createDirIfNotExists(path);
        //存文件
        File uploadFile = new File(path, filename);
        try {
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);
        } catch (IOException e) {
            throw new BusinessException("文件上传失败！");
        }
        return uploadFile.getPath();
    }

    /**
     * 创建文件夹路径
     */
    private static void createDirIfNotExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 删除文件
     *
     * @param path 文件访问的路径upload开始 如： /upload/image/test.jpg
     * @return true 删除成功； false 删除失败
     */
    public static boolean delete(String path) {
        File file = new File(path);
        return file.exists() == true ? file.delete() : true;
    }

    /**
     * 获取服务部署根路径 http:// + ip + port
     *
     * @param request
     * @return
     */
    public static String getServerIPPort(HttpServletRequest request) {
        //+ ":" + request.getServerPort()
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}

