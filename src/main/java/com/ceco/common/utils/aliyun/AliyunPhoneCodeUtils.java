package com.ceco.common.utils.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ceco.common.exception.BusinessException;
import com.ceco.common.utils.StringUtil;
import com.ceco.configure.AliyunPhoneCodeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.ceco.common.utils.Constants.VERIFY;
import static com.ceco.common.utils.Constants.VERIFY_EXPIRE;

@Slf4j
@Component
public class AliyunPhoneCodeUtils {

    public static String code ;

    @Autowired
    AliyunPhoneCodeConfig aliyunPhoneCodeConfig;


    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 阿里云短信服务配置
     * @param mobile
     * @return
     */
    public  boolean getPhoneMsg(String mobile) {

        /**
         * 进行正则关系校验
         */
        log.info(mobile);
        if (mobile == null || mobile == "") {
            log.info("手机号为空");
            throw new BusinessException("手机号为空");
        }

        if (!StringUtil.isMobile(mobile)) {
            log.info("手机号错误");
            throw  new BusinessException("手机号错误");
        }

        Object o = redisTemplate.opsForValue().get(VERIFY+"_"+mobile);
        if(o != null){
            throw new BusinessException("手机发送消息过频繁,请稍后再试");
        }
        /**
         * 短信验证---阿里工具
         */
        // 设置超时时间-可自行调整
        System.setProperty(aliyunPhoneCodeConfig.getDefaultConnectTimeoutKey(), aliyunPhoneCodeConfig.getDefaultConnectTimeoutValue());
        System.setProperty(aliyunPhoneCodeConfig.getDefaultReadTimeoutKey(), aliyunPhoneCodeConfig.getDefaultReadTimeoutValue());
        // 初始化ascClient需要的几个参数
        final String product = aliyunPhoneCodeConfig.getProduct();// 短信API产品名称（短信产品名固定，无需修改）
        final String domain = aliyunPhoneCodeConfig.getDomain();// 短信API产品域名（接口地址固定，无需修改）
        // 替换成你的AK
        final String accessKeyId = aliyunPhoneCodeConfig.getAccessKeyId();// 你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = aliyunPhoneCodeConfig.getAccessKeySecret();// 你的accessKeySecret，参考本文档步骤2
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
                    domain);
        } catch (ClientException e1) {
            e1.printStackTrace();
        }

        //获取验证码
        code = vcode();

        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(aliyunPhoneCodeConfig.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(aliyunPhoneCodeConfig.getTemplateCode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{ \"code\":\""+code+"\"}");
        // 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse;

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null
                    && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功
                redisTemplate.opsForValue().set(VERIFY+"_"+mobile,code,VERIFY_EXPIRE, TimeUnit.SECONDS);
                log.info("获取验证码成功！！！");
            } else {
                //如果验证码出错，会输出错误码告诉你具体原因
                log.info(sendSmsResponse.getCode());
                log.info("获取验证码失败...");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 生成6位随机数验证码
     * @return
     */
    public static String vcode(){
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}