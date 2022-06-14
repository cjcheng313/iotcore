package com.ccj.channel.common;

import com.ccj.common.utils.FileUploadResult;
import com.ccj.common.utils.FileUploadUtils;
import com.ccj.common.utils.aliyun.AliyunPhoneCodeUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class CommonController {

    @Autowired
    FileUploadUtils fileUploadUtils;

    @Autowired
    AliyunPhoneCodeUtils aliyunPhoneCodeUtils;
    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public FileUploadResult uploadFile(MultipartFile file)
    {
        return fileUploadUtils.upload(file);
    }


    @PostMapping("/sendPhoneCode/{phone}")
    @ApiOperation("发送手机验证码(五分钟内有效)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobile",value="手机号",required=true)
    })
    public boolean sendSms(@PathVariable("phone") String phone){
        return aliyunPhoneCodeUtils.getPhoneMsg(phone);
    }


}
