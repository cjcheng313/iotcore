package com.ceco.interceptor;

import com.ceco.common.exception.BusinessException;
import com.ceco.common.properties.UserProperties;
import com.ceco.common.utils.CurrentContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;

@Component("ChannelInterceptor")
public class ChannelInterceptor extends HandlerInterceptorAdapter {
    @Value("${url.ignore.startWith}")
    private String startWith;
    private final static Logger logger = LoggerFactory.getLogger(ChannelInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String currentRequestUri = request.getRequestURI();
        List<String> ignoreResources = Arrays.asList(startWith.split(","));
        boolean isIgnore = ignoreResources.stream().anyMatch(item -> currentRequestUri.indexOf(item) !=-1);
        if (isIgnore) {
            return super.preHandle(request, response, handler);
        }
        String userToken = request.getHeader(UserProperties.getTokenHeader());
        CurrentContext.setUserToken(userToken);
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(UserProperties.getPubKeyBytes());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(spec);
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(userToken);
            Claims body = claimsJws.getBody();
            CurrentContext.setNonce((String) body.get("nonce"));
            if (StringUtils.isBlank(CurrentContext.getSystemKey())) {
                CurrentContext.setSystemKey((String) body.get("systemKey"));
            }
            CurrentContext.setUid((String) body.get("uid"));
            CurrentContext.setUname((String) body.get("uname"));
            CurrentContext.setMobile((String) body.get("mobile"));
        } catch (ExpiredJwtException e) {
            throw new BusinessException("40101", "用户尚未登录或者Token已经过期!");
        } catch (DecoderException | InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new BusinessException("500", "系统异常");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentContext.removeContext();
        super.afterCompletion(request, response, handler, ex);
    }
}
