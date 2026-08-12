package com.war.service.impl;

import java.util.Random;

import com.war.dao.INameDAO;
import com.war.service.INameService;

public class NameService implements INameService {

	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 4;
	
	private INameDAO nameDAO;
	
	public String generateName(){
		
		int length = (int)(MIN_NAME_LENGTH+(MAX_NAME_LENGTH-MIN_NAME_LENGTH+1)*Math.random());
		
		String firstName = nameDAO.getFirstNameArray(1)[0];
		StringBuffer lastNameBuffer = new StringBuffer();
		
		String[] lastNameArray = nameDAO.getLastNameArray(length-1);;
		for(int i=0;i<lastNameArray.length;i++){
			lastNameBuffer.append(lastNameArray[i]);
		}
		
		return firstName + lastNameBuffer.toString();
	}
	
	public String[] generateNameArray(int amount){
		
		//姓名数组
		String[] nameArray = new String[amount];
		
		int i;
		
		Random random = new Random();
		//姓数组
		String[] firstNameArray = nameDAO.getFirstNameArray(amount);
		//如果姓数据不足，则在已有数据中随机选取并赋值
		int maxFirstNameMaxIndex = 0;
		for(i=0;i<firstNameArray.length;i++){
			if(firstNameArray[i]==null){
				maxFirstNameMaxIndex = i;
				break;
			}
		}
		if(maxFirstNameMaxIndex!=0){
			for(i=maxFirstNameMaxIndex;i<firstNameArray.length;i++){
				firstNameArray[i] = firstNameArray[random.nextInt(maxFirstNameMaxIndex)];
			}
		}
		
		//名数组
		String[] lastNameArray = nameDAO.getLastNameArray(amount);
		//如果名数据不足，则在已有数据中随机选取并赋值
		int maxLastNameMaxIndex = 0;
		for(i=0;i<lastNameArray.length;i++){
			if(lastNameArray[i]==null){
				maxLastNameMaxIndex = i;
				break;
			}
		}
		if(maxLastNameMaxIndex!=0){
			for(i=maxLastNameMaxIndex;i<lastNameArray.length;i++){
				lastNameArray[i] = lastNameArray[random.nextInt(maxLastNameMaxIndex)];
			}
		}
		
		//临时名StringBuffer
		StringBuffer tempNameBuffer = new StringBuffer();
		
		for(i=0;i<nameArray.length;i++){
			
			//清空tempNameBuffer
			tempNameBuffer.delete(0, tempNameBuffer.length());
			
			tempNameBuffer.append(firstNameArray[i]);
			//tempNameBuffer.append(" ");
			tempNameBuffer.append(lastNameArray[i]);
			
			nameArray[i] = tempNameBuffer.toString();
		}
		
		return nameArray;
	}

	public String[] generateNameArray_Old(int amount){
		
		//姓名数组
		String[] nameArray = new String[amount];
		//姓名长度
		int[] nameLength = new int[amount];
		
		int i;
		
		//姓名总长度
		int totalLength = 0;
		for(i=0;i<nameLength.length;i++){
			nameLength[i] = (int)(MIN_NAME_LENGTH+(MAX_NAME_LENGTH-MIN_NAME_LENGTH+1)*Math.random());
			totalLength += nameLength[i];
		}
		
		Random random = new Random();
		//姓数组
		String[] firstNameArray = nameDAO.getFirstNameArray(amount);
		//如果姓数据不足，则在已有数据中随机选取并赋值
		int maxFirstNameMaxIndex = 0;
		for(i=0;i<firstNameArray.length;i++){
			if(firstNameArray[i]==null){
				maxFirstNameMaxIndex = i;
				break;
			}
		}
		if(maxFirstNameMaxIndex!=0){
			for(i=maxFirstNameMaxIndex;i<firstNameArray.length;i++){
				firstNameArray[i] = firstNameArray[random.nextInt(maxFirstNameMaxIndex)];
			}
		}
		
		//名数组
		String[] lastNameArray = nameDAO.getLastNameArray(totalLength-amount);
		//如果名数据不足，则在已有数据中随机选取并赋值
		int maxLastNameMaxIndex = 0;
		for(i=0;i<lastNameArray.length;i++){
			if(lastNameArray[i]==null){
				maxLastNameMaxIndex = i;
				break;
			}
		}
		if(maxLastNameMaxIndex!=0){
			for(i=maxLastNameMaxIndex;i<lastNameArray.length;i++){
				lastNameArray[i] = lastNameArray[random.nextInt(maxLastNameMaxIndex)];
			}
		}
		
		//临时名StringBuffer
		StringBuffer tempLastNameBuffer = new StringBuffer();
		//
		int currentLastNamePos = 0;
		
		for(i=0;i<nameArray.length;i++){
			
			//清空tempLastNameBuffer
			tempLastNameBuffer.delete(0, tempLastNameBuffer.length());
			
			for(int j=0;j<nameLength[i]-1;j++){
				tempLastNameBuffer.append(lastNameArray[currentLastNamePos]);
				currentLastNamePos++;
			}
			
			nameArray[i] = firstNameArray[i] + tempLastNameBuffer.toString();
		}
		
		return nameArray;
	}
	
	public INameDAO getNameDAO() {
		return nameDAO;
	}

	public void setNameDAO(INameDAO nameDAO) {
		this.nameDAO = nameDAO;
	}

}
