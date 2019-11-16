package com.lzh.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;


/**
 * 〈String 工具类〉
 *
 * @author lzh
 * @create 2019/11/6
 *
 */
public class StringUtil extends StringUtils {
   
	/**
	 * 判断对象不为空
	 * @param str
	 * @param obj
	 * @return
	 */
    public static boolean isNotBlank(Object value) {
        if(value==null){
            return true;
        }
        return isNotBlank(value.toString());
    }


	/**
	 * Map 集合是否为 Null
	 * @param str
	 * @param obj
	 * @return
	 */
    public static void emptyIfNull(Map<String, Object> record) {
        Iterator<String> iterator = record.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if(record.get(key)==null){
                record.put(key,"");
            }
        }
    }

	/**
	 * 格式化string
	 * @param str
	 * @param obj
	 * @return
	 */
	public static String formatString(String str, Object ... obj) {
		return String.format(str, obj);
	}
	
	/**
	 * 去除无用的字符串，只保留字母和数字
	 * @return
	 */
	public static String getLetterOrDigit(String str) {
		if(str == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}
    
    /**
	 * 判断字符串是否 为空
	 * @param cs
	 * @return
	 */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

 	/**
	 * 判断字符串是否 不为空
	 * @param cs
	 * @return
	 */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
	 * 判断字符串是否 为整数
	 * @param cs
	 * @return
	 */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
