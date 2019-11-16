package com.dfl.ycp3.common.utils;

import java.util.*;


/**
 * 〈集合工具类〉
 *
 * @author lzh
 * @create 2019/11/6
 *
 */
public final class CollectionUtils {

    public static <T> T[] toArray(List<T> list) {
        if(isEmpty(list)) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] array = ((T[]) new Object[list.size()]);
        list.toArray(array);
        return array;
    }


	/**
	 * 将Map转换为List
	 * @param map
	 * @return list
	 */
	public static List<? extends Object> parseMap2List(Map<? extends Object, ? extends Object> map) {
		List<Object> list = null;
		if (isNotEmpty(map)) {
			list = new ArrayList<Object>();
			for (Map.Entry<? extends Object, ? extends Object> entry : map.entrySet()) {
				list.add(entry.getValue());
			}
		}
		return list;
	}


	/**
	 * 判断 map 是否为空
	 * @param map
	 * @return 
	 */
	public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
		return map == null || map.isEmpty();
	}


	/**
	 * 判断 map 是否不为空
	 * @param map
	 * @return 
	 */
	public static boolean isNotEmpty(Map<? extends Object, ? extends Object> map) {
		return map != null && !map.isEmpty();
	}

	
	/**
	 * 判断 Collection 是否为空
	 * @param list
	 * @return 
	 */
	public static boolean isEmpty(Collection<? extends Object> list) {
	    if(list == null || list.isEmpty()) {
	        return true;
	    }
	    return false;
	}

	/**
	 * 判断 Collection 是否不为空
	 * @param list
	 * @return 
	 */
	public static boolean isNotEmpty(Collection<? extends Object> list) {
	    if(list == null || list.isEmpty()) {
	        return false;
	    }
	    return true;
	}

    /**
     * 把list转换成string，中间以combineChar来连接
     * @param lists
     * @param combineChar
     * @return
     */
    public static <T> String combineListToString(List<T> lists, char combineChar) {
        StringBuffer sb = new StringBuffer();
        int i=0;
        for(T l : lists) {
            if(i > 0) {
                sb.append(combineChar);
            }
            i++;
            sb.append(String.valueOf(l));
        }

        return sb.toString();
    }

    /**
     * 把list转换成string，中间以combineChar来连接
     * @param lists
     * @param combineStr
     * @return
     */
    public static <T> String combineListToString(List<T> lists, String combineStr) {
        StringBuffer sb = new StringBuffer();
        int i=0;
        for(T l : lists) {
            if(i > 0) {
                sb.append(combineStr);
            }
            i++;
            sb.append(String.valueOf(l));
        }

        return sb.toString();
    }

	/**
	 * 调换集合中两个指定位置的元素, 若两个元素位置中间还有其他元素，需要实现中间元素的前移或后移的操作。
	 * @param list 集合
	 * @param oldPosition 需要调换的元素
	 * @param newPosition 被调换的元素
	 * @param <T>
	 */
	public static <T> void swap(List<T> list, int oldPosition, int newPosition){
		if(null == list){
			throw new IllegalStateException("The list can not be empty...");
		}
		T tempElement = list.get(oldPosition);

		// 向前移动，前面的元素需要向后移动
		if(oldPosition < newPosition){
			for(int i = oldPosition; i < newPosition; i++){
				list.set(i, list.get(i + 1));
			}
			list.set(newPosition, tempElement);
		}
		// 向后移动，后面的元素需要向前移动
		if(oldPosition > newPosition){
			for(int i = oldPosition; i > newPosition; i--){
				list.set(i, list.get(i - 1));
			}
			list.set(newPosition, tempElement);
		}
	}
}
