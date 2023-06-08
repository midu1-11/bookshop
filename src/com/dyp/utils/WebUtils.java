package com.dyp.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author howard
 * @version 1.0
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
