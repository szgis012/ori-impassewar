package com.war.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.war.exception.GameException;

/**
 * 读取配置信息工具类(支持GBK编码)
 *
 * @author ghleed
 * @version 1.0
 */
public class CfgFileUtil {
	
	
	/**
	 * 返回配置信息，跳过空行和注释行
	 * @param file
	 * @return
	 */
	public static List<String> getDataByLine(File file){
		List<String> lines = new ArrayList<String>();
		
		if(file != null && file.exists()){
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(file));
				
				String line = br.readLine().trim();
				while(line != null){
					//如果是空行或者注释行，就跳过
					if(line.length() > 0 && !line.startsWith("#")){
						lines.add(line);
					}
					
					line = br.readLine();
				}
			} catch (Exception e) {
				throw new GameException(e.getMessage());
			}finally{
				try {
					br.close();
				} catch (IOException e) {
					//
				}
			}
		}
		
		return lines;
	}
	
	/**
	 * 将行数据解析放到properties中
	 * @param lines
	 * @return
	 */
	public static Properties getDataProperties(List<String> lines){
		Properties props = new Properties();
		
		if(lines != null && lines.size() > 0){
			String[] tmp;
			
			for(String line : lines){
				tmp = line.split("=");
				
				if(tmp.length == 2){
					props.put(tmp[0], tmp[1]);
				}
			}
		}
		
		return props;
	}
}
