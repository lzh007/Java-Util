package com.lzh.utils;

import java.util.UUID;

/**
 * 〈生成 id 工具类〉
 *
 * @author lzh
 * @create 2019/11/6
 *
 */
public class UUIDUtil {

	/**
     * JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid() {
        return uuid(true);
    }

	/**
	 * 生成 32 UUID，通过Random数字生成, 中间无-分割
	 * @return 
	 */
    public static String genUuid32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    
    
}
