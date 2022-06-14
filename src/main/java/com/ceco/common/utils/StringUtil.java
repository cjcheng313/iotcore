package com.ceco.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final DecimalFormat FORMAT_ONE = new DecimalFormat("00");// 非0的补0，保证2位数字

	public static final String ZERO2 = "%2d";
	public static final String ZERO3 = "%3d";
    /** 标准日期格式 */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";

    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    public static String getUUId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    /**
     * 校验手机号码格式是否正确
     * @param mobile  需要校验的手机号码
     * @return  boolean
     */
    public static boolean isMobile(String mobile) {
        String regex = "^[1][3-9][0-9]{9}$";
        if (mobile.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mobile);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 去掉前N个字符串
     * @param origin
     * @param count
     * @return
     */
    public static String truncateHeadString(String origin, int count) {
        if (origin == null || origin.length() < count) {
            return null;
        }
        char[] arr = origin.toCharArray();
        char[] ret = new char[arr.length - count];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i + count];
        }
        return String.copyValueOf(ret);
    }
    /**
     * 字符串连接，将参数列表拼接为一个字符串
     * @param more 追加
     * @return 返回拼接后的字符串
     */
    public static String concat(Object... more) {
        return concatSpiltWith("", more);
    }
    public static String concatSpiltWith(String split, Object... more) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < more.length; i++) {
            if (i != 0) {
                buf.append(split);
            }
            buf.append(more[i]);
        }
        return buf.toString();
    }
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        else if (obj instanceof Map) return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }
    public static boolean isNull(CharSequence cs) {
        if (isEmpty(cs) || cs.equals("null")) {
            return true;
        }
        return false;
    }
    public static boolean isNotNull(CharSequence cs) {
        if (isNull(cs)) {
            return false;
        }
        return true;
    }
    
    /**
     * 	不足n位补零,如1变成01
     * @param wei 位数
     * @param num
     * @return
     */
    public static String addZeroForNum(int wei,Long num) {
    	String formatStr=String.format(ZERO2, num);
    	switch (wei) {
		case 2:
			formatStr = String.format(ZERO2, num);
			break;
		case 3:
			formatStr = String.format(ZERO3, num);
			break;
		case 4:
			formatStr = String.format("%4d", num);
			break;
		case 5:
			formatStr = String.format("%5d", num);
			break;
		default:
			formatStr = String.format("%"+wei+"d", num);
			break;
		}
    	return formatStr.replace(" ", "0");
    }

    /**
     * 获取某个日期 N天后的日期
     * @param time
     * @param days
     * @return
     */
    public static Date getDateBeforeNDays(Date time, int days){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
            Calendar calendar = Calendar.getInstance();//获取日历实例
            calendar.setTime(time);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            String dateStr = sdf.format(calendar.getTime());
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * p判断非空或null
     * @param str
     * @return
     */
    public static boolean isBank(String str) {
        int strLen;//标记字符长度
        // 字符串为空或者字符串长度为0
    if (str == null || (strLen = str.length()) == 0) {
        return true;
        }
        for (int i = 0; i < strLen; i++) {

        //isWhitespace() 方法用于判断指定字符是否为空白字符，空白符包含：空格、tab键、换行符。

        //charAt()功能类似于数组，可以把字符串看作是char类型的数组，它是把字符串拆分获取其中的某个字符；返回指定位置的字符。

        //charAt(i)，i为int类型，i从0开始。

         //判断空格，回车，换行等，如果有一个不是上述字符，就返回false
         if (Character.isWhitespace(str.charAt(i)) == false) {
             return false;
          }
        }
        return true;
    }




    public static String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }
    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

}
