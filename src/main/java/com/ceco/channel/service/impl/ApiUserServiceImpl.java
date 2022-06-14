package com.ceco.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ceco.channel.admin.model.req.AppUserListReq;
import com.ceco.channel.admin.model.req.UserLoginReq;
import com.ceco.channel.admin.model.resp.AppUserDetailResp;
import com.ceco.channel.admin.model.resp.AppUserDeviceResp;
import com.ceco.channel.admin.model.resp.AppUserResp;
import com.ceco.channel.admin.model.resp.UserLoginResp;
import com.ceco.channel.app.model.req.AppUserRegisterReq;
import com.ceco.channel.app.model.req.AppUserSaveReq;
import com.ceco.channel.service.IApiUserService;
import com.ceco.common.exception.BusinessException;
import com.ceco.common.properties.UserProperties;
import com.ceco.common.utils.Constants;
import com.ceco.common.utils.ConvertUtil;
import com.ceco.common.utils.CurrentContext;
import com.ceco.common.utils.ValidatorUtils;
import com.ceco.common.utils.encrypt.AES128;
import com.ceco.module.entity.AdminUser;
import com.ceco.module.entity.AppUser;
import com.ceco.module.service.IAdminUserService;
import com.ceco.module.service.IAppUserService;
import com.ceco.module.service.IDeviceService;
import com.ceco.module.service.IRoomDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.UUID;

import static com.ceco.common.utils.Constants.VERIFY;

@Slf4j
@Service
public class ApiUserServiceImpl implements IApiUserService {

    @Autowired
    IAdminUserService adminUserService;

    @Autowired
    IAppUserService appUserService;

    @Autowired
    IRoomDeviceService roomDeviceService;

    @Autowired
    IDeviceService deviceService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserLoginResp loginByPwd(UserLoginReq req,String systemKey) {

        AdminUser userEntity = adminUserService.getOne(
                new QueryWrapper<AdminUser>()
                        .lambda()
                        .eq(AdminUser::getPhone, req.getAccount())
        );
        String encrypt =null;
        try {
            encrypt = AES128.encrypt(req.getPassword());
        } catch (Exception e) {
            log.info("AES128加密出错,出错类容{}",e);
        }
        if (null == userEntity) {
            throw new BusinessException("登录账号不存在!");
        }
        if (!encrypt.equals(userEntity.getPassword())) {
            throw new BusinessException("用户名或密码错误!");
        }
        try {
            String userToken = this.generateToken(userEntity, systemKey);
            UserLoginResp response = new UserLoginResp();
            response.setId(userEntity.getId());
            response.setUserToken(userToken);
            response.setExpire(UserProperties.getExpire());
            return response;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | DecoderException e) {
            throw new BusinessException("登录票据生成失败!");
        }
    }


    private String generateToken(AdminUser userEntity, String systemKey) throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
        if (StringUtils.isBlank(systemKey)) {
            systemKey = CurrentContext.getSystemKey();
        }
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(UserProperties.getPriKeyBytes());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        String compactJws = Jwts.builder()
                .setSubject(userEntity.getId())
                .claim("nonce", UUID.randomUUID().toString())
                .claim("systemKey", systemKey)
                .claim("uid", userEntity.getId())
                .claim("uname", userEntity.getName())
                .claim("mobile", userEntity.getPhone())
                .setExpiration(DateTime.now().plusSeconds(UserProperties.getExpire()).toDate())
                .signWith(SignatureAlgorithm.RS256, kf.generatePrivate(spec))
                .compact();
        return compactJws;
    }


    @Override
    public PageInfo<AppUserResp> appList(AppUserListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<AppUser>  appUserList = appUserService.list(new QueryWrapper<AppUser>().lambda().like(StringUtils.isNotEmpty(req.getNickName()),AppUser::getNickName,req.getNickName()));
        List<AppUserResp> appUserRespList = ConvertUtil.convert(appUserList,AppUserResp.class);

        return new PageInfo<>(appUserRespList);
    }

    @Override
    public AppUserDetailResp appUserDetail(String id) {
        AppUser appUser = appUserService.getById(id);
        if(appUser !=null ){
            AppUserDetailResp appUserDetailResp = ConvertUtil.convert(appUser,AppUserDetailResp.class);
            List<AppUserDeviceResp> appUserDeviceRespList = deviceService.selectUserDetailDevice(appUser.getId());
            appUserDetailResp.setAppUserDeviceRespList(appUserDeviceRespList);
        }
        throw  new BusinessException("用户不存在! ");

    }

    @Override
    public boolean saveUserInfo(AppUserSaveReq req) {

        ValidatorUtils.validateEntity(req);
        AppUser appUser = appUserService.getById(req.getId());
        if(appUser == null) {
            throw  new BusinessException("用户不存在!");
        }
        else{
            appUser.setNickName(req.getNickName());
            appUser.setHeadImg(req.getHeadImg());
            return appUserService.updateById(appUser);
        }
    }

    @Override
    public boolean register(AppUserRegisterReq req) {
        ValidatorUtils.validateEntity(req.getAccount());
        String code = redisTemplate.opsForValue().get(VERIFY + "_"+req.getAccount()) == null ? "" : redisTemplate.opsForValue().get(VERIFY + "_"+req.getAccount()).toString();
        if(!code.equals(req.getAccount())){
            throw  new BusinessException("验证码不一致");
        }
        AppUser appUser = appUserService.getOne(new QueryWrapper<AppUser>().lambda().eq(AppUser::getAccount,req.getAccount()));
        if(appUser !=null ){
            throw  new BusinessException("当前账号已存在！");
        }
        appUser = ConvertUtil.convert(req,AppUser.class);
        if(req.getSource().intValue() == Constants.OVERSEAS_APP_USER.intValue()){
            appUser.setEmail(req.getAccount());
        }else{
            appUser.setPhone(req.getAccount());
        }
        try {
           String encrypt = AES128.encrypt(req.getPassword());
           appUser.setPassword(encrypt);
        } catch (Exception e) {
            log.info("AES128加密出错,出错类容{}",e);
        }
        return appUserService.save(appUser);
    }
}
