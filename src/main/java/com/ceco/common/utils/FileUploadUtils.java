package com.ceco.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.ceco.configure.AliyunConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * aliyun oss上传文件
 */
@Component
@Slf4j
public class FileUploadUtils {
    //允许上传格式及上传目录
    private static Map<String, String> fileMap = new HashMap<String, String>(){{
        put(".bmp", "images/");
        put(".jpg", "images/");
        put(".jpeg", "images/");
        put(".gif", "images/");
        put(".png", "images/");
        put(".mp3", "audio/");
        put(".mp4", "video/");
    }};
    @Autowired
    private OSS ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;

    /**
     * @desc 文件上传带进度条
     * @date 2019-07-31 11:31
     */
    public FileUploadResult upload(MultipartFile uploadFile) {
        // 校验图片格式
        boolean isLegal = false;
        for (String type : fileMap.keySet()) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),
                    type)) {
                isLegal = true;
                break;
            }
        }
        //封装Result对象，并且将文件的byte数组放置到result对象中
        FileUploadResult fileUploadResult = new FileUploadResult();
        if (!isLegal) {
            fileUploadResult.setStatus("error");
            fileUploadResult.setResponse("文件格式不符合");
            return fileUploadResult;
        }
        //文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
        // 上传到阿里云
        try {
//            不带进度条
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new
                    ByteArrayInputStream(uploadFile.getBytes()));
            //            带进度条(存入session)
//                 file = BaseFileUtils.multipartFileToFile(uploadFile);
//                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//                ossClient.putObject(new PutObjectRequest(aliyunConfig.getBucketName(), filePath + uploadFile.getOriginalFilename(), file).withProgressListener(new PutObjectProgressListener(session)));



        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        fileUploadResult.setSize(uploadFile.getSize());
        fileUploadResult.setStatus("done");
        fileUploadResult.setResponse("success");
        fileUploadResult.setUrl(this.aliyunConfig.getUrlPrefix() + filePath);
        fileUploadResult.setName(fileName);
        return fileUploadResult;
    }

    /**
     * @desc 生成路径以及文件名 例如：//images/2019/08/10/15564277465972939.jpg
     * @date 2019-07-31 11:31
     */
    private String getFilePath(String sourceFileName) {
        String suffix = sourceFileName.substring(sourceFileName.lastIndexOf("."));
        String rootDir="";
        DateTime dateTime = new DateTime();
        for (String type : fileMap.keySet()) {
            if (type.equals(suffix)){
                rootDir=fileMap.get(type);
            }
        }
        System.out.println(suffix);
        return rootDir + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

    /**
     * @desc 查看文件列表
     * @date 2019-07-31 11:31
     */
    public List<OSSObjectSummary> list() {
        // 设置最大个数。
        final int maxKeys = 200;
        // 列举文件。
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(aliyunConfig.getBucketName()).withMaxKeys(maxKeys));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        return sums;
    }

    /**
     * @desc 删除文件
     * @date 2019-07-31 11:31
     */
    public FileUploadResult delete(String objectName) {
        // 根据BucketName,objectName删除文件
        ossClient.deleteObject(aliyunConfig.getBucketName(), objectName);
        FileUploadResult fileUploadResult = new FileUploadResult();
        fileUploadResult.setName(objectName);
        fileUploadResult.setStatus("removed");
        fileUploadResult.setResponse("success");
        return fileUploadResult;
    }

    /**
     * @desc 下载文件
     * @date 2019-07-31 11:31
     */
    public void exportOssFile(OutputStream os, String objectName) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(aliyunConfig.getBucketName(), objectName);
        // 读取文件内容。
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght = 0;
        while ((lenght = in.read(buffer)) != -1) {
            out.write(buffer, 0, lenght);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }
    static class PutObjectProgressListener implements ProgressListener {

        private HttpSession session;
        private long bytesWritten = 0;
        private long totalBytes = -1;
        private boolean succeed = false;
        private int percent = 0;

        //构造方法中加入session
        public PutObjectProgressListener() {
        }
        public PutObjectProgressListener(HttpSession mSession) {
            this.session = mSession;
            session.setAttribute("upload_percent", percent);
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    log.info("开始上传");
                    break;

                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    log.info(" {}字节总数将被上传到OSS",this.totalBytes );
                    break;

                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.bytesWritten += bytes;
                    if (this.totalBytes != -1) {
                        int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                        log.info("上传进度：{}",percent);
                        //将进度percent放入session中
                        session.setAttribute("uploadPercent", percent);
                        log.info("上传进度：{}",percent);
                    } else {
                        log.info("已经被写入{}",bytes);
                    }
                    break;

                case TRANSFER_COMPLETED_EVENT:
                    this.succeed = true;
                    log.info("上传成功");
                    break;

                case TRANSFER_FAILED_EVENT:
                    log.info("上传失败");
                    break;

                default:
                    break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }
    }
}

