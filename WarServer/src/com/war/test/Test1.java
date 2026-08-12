package com.war.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;



public class Test1 {
	public static void main(String[] args) {
		String comment = "\u4e3a\u7b49\u7ea7\uff0cvalue\u4e3a\u5237\u602a\u76f8\u5173\u7684\u4fe1\u606f\u3002\u5df2;\u53f7\u5206\u9694\u4e0d\u540c\u7684\u5175\u79cd\u914d\u7f6e\u4fe1\u606f\uff0c\u6bcf\u4e00\u9879\u53c8\u4ee5:\u53f7\u5206\u9694\uff08\u7b2c\u4e00\u4e3a\u5175\u79cd\u7f16\u53f7\uff0c\u7b2c\u4e8c\u9879\u4e3a\u8be5\u5175\u79cd\u7684\u6700\u5c0f\u6570\u91cf\uff0c\u7b2c\u4e09\u9879\u4e3a\u8be5\u5175\u79cd\u7684\u6700\u5927\u6570\u91cf\uff09";
		/*	
		try {
			comment = new String(comment.getBytes("gbk"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(comment);*/
		/*
		String tmp = new String();
		
		Long exp = 20000L;
		
		for (int i = 1; i < 6; i++) {
			exp = exp - getCityHeroExpMax(i);
		}
		
		System.out.println(exp);
		int level = 5;
		
		for (int i = level; i > 0; i--) {
			exp += getCityHeroExpMax(i);
		}
		
		System.out.println(exp);
		*/
		/*
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list.size());
		
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int temp = random.nextInt(6);
			System.out.print(temp > 5 ? temp : "");
		}
		*/
		
		String ts = "1";
		System.out.println(ts.split(";").length);
		System.out.println(ts.split(";")[0]);
		
	}
	
	public static Long getCityHeroExpMax(Integer level) {
		return (long)45 + level*(level-1)*10;
	}
}
