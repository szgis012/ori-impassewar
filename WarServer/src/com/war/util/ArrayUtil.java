package com.war.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数组工具类
 *
 * @author ghleed
 * @version 1.0
 */
public class ArrayUtil {
	/**
	 * 将数组中的值随机的打乱顺序
	 * @param arr
	 * @return
	 */
	public static int[] randArray(int[] arr){
		if(arr == null || arr.length < 2)
			return arr;
		
		int[] newArr = new int[arr.length];
		
		List<Integer> list = new ArrayList<Integer>(arr.length);
		//添加至列表
		for(int i=0; i<arr.length; i++){
			list.add(arr[i]);
		}
		//打乱顺序
		Collections.shuffle(list);
		//取出整数
		for(int j=0;j<list.size(); j++){
			newArr[j] = list.get(j);
		}
		
		return newArr;
	}
}
