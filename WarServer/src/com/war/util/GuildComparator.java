package com.war.util;

import java.util.Comparator;

import com.war.domain.Guild;

public class GuildComparator implements Comparator<Guild> {

	public int compare(Guild guild1, Guild guild2) {
		
		/*if (guild1.getLevel()>=guild2.getLevel()) {
			if(guild1.getRenown()<guild2.getRenown()){
				return -1;
			}
			if(guild1.getRenown().longValue()==guild2.getRenown().longValue()){
				return 0;
			}
			if(guild1.getRenown()>guild2.getRenown()){
				return 1;
			}
		} else {
			return 1;
		}*/
		
		if (guild1.getLevel()>guild2.getLevel()) {
			return 1;
		} else if (guild1.getLevel()<guild2.getLevel()) {
			return -1;
		} else {
			if (guild1.getRenown()>guild2.getRenown()) {
				return 1;
			} else if (guild1.getRenown()<guild2.getRenown()) {
				return -1;
			} else {
				return 0;
			}
		}
		
		//return 0;
	}

}
