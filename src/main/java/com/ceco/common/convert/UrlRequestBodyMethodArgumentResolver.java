package com.ccj.common.convert;

import com.ccj.common.annotation.UrlRequestBody;
import com.ccj.common.utils.serialize.JsonUtils;
import lombok.extern.java.Log;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;
import java.util.Map;

@Log
@Component
public class UrlRequestBodyMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UrlRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        Map<String, String[]> parameterMap = ((ServletWebRequest) webRequest).getRequest().getParameterMap();
        if (parameterMap == null) {
            return null;
        }
        Iterator<String> keyIterator = parameterMap.keySet().iterator();
        String key;
        StringBuffer json = new StringBuffer();
        json.append("{");
        boolean isFirst = true;
        while (keyIterator.hasNext()) {
            key = keyIterator.next();
            String[] strings = parameterMap.get(key);
            if (strings.length > 1) {
                log.warning("There can only be one parameter " + key + "!");
            }
            if(isFirst){
                isFirst = false;
            }else {
                json.append(",");
            }
            json.append("\"");
            json.append(key);
            json.append("\"");
            json.append(":");
            json.append("\"");
            json.append(strings[0]);
            json.append("\"");
        }
        json.append("}");
        System.out.println(json);
        Class<?> parameterType = parameter.getParameterType();
        final Object o = JsonUtils.json2Object(json.toString(), parameterType);
        return o;
    }
}
