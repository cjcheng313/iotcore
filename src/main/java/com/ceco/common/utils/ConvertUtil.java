package com.ccj.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 类型转换工具类
 * @author gjy
 * @since 2020-03-21
 */
@Slf4j
public class ConvertUtil {

    /**
     * 实体转换
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T convert(Object source, @NonNull Class<T> target) {
        if (Objects.isNull(source)) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtil.copyProperties(source, targetObject);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("convertObject error", e);
        }
        return targetObject;
    }

    /**
     * 列表转换
     * @param sourceList
     * @param target
     * @param <T>
     * @return
     */
    public static <T> List<T> convert(Collection<?> sourceList, @NonNull Class<T> target) {
        List<T> targetList = CollectionUtil.newArrayList();
        if (CollectionUtil.isEmpty(sourceList)) {
            return targetList;
        }
        sourceList.forEach(item -> {
            try {
                T targetObject = target.newInstance();
                BeanUtil.copyProperties(item, targetObject);
                targetList.add(targetObject);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("convertList error", e);
            }
        });
        return targetList;
    }

}
