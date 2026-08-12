package com.war.domain;

import java.io.Serializable;
import java.util.List;

public class CityHero implements Serializable {

	private static final long serialVersionUID = -4563862364480575927L;
	
	/** 城市英雄编号 */
	private Integer cityHeroID;
	/** 城市编号 */
	private Integer cityID;
	/** 名称 */
	private String name;
	/** 性别(1.男 2.女) */
	private Integer gender;
	/** 头像 */
	private String head;
	/** 等级 */
	private Integer level;
	/** 经验 */
	private Long exp;
	/** 星级 */
	private Integer star;
	/** 经验最大值(升级所需经验) */
	private Long expMax;
	/** 体力 */
	private Integer stamina;
	/** 体力上限 */
	private Integer staminaMax;
	/** 指挥 */
	private Integer command;
	/** 防护 */
	private Integer defense;
	/** 思维 */
	private Integer mind;
	/** 行政 */
	private Integer executivepower;
	/** 未加点数 */
	private Integer unsetPoint;
	/** 军魂 */
	private Integer militarySpirit;
	/** 军魄 */
	private Integer militarySoul;
	/** 已提升军魂 */
	private Integer addedMilitarySpirit;
	/** 统驭 */
	private Integer rein;
	/** 领导 */
	private Integer leadership;
	/** 忠诚 */
	private Integer loyalty;
	/** 最大技能数量 */
	private Integer maxSkillNum;
	/** 肩章装备 */
	private Integer equipmentEpaulet;
	/** 帽子装备 */
	private Integer equipmentCap;
	/** 衣服装备 */
	private Integer equipmentClothes;
	/** 鞋子装备 */
	private Integer equipmentShoe;
	/** 武器装备 */
	private Integer equipmentWeapon;
	/** 品质(1.普通 2.稀有 3.史诗) */
	private Integer quality;
	/** 状态 (1.空闲 2.编制 3.训练 5.执政) */
	private Integer state;
	/** 肩章装备对象 */
	private Equipment equipmentEpauletObject;
	/** 帽子装备对象 */
	private Equipment equipmentCapObject;
	/** 衣服装备对象 */
	private Equipment equipmentClothesObject;
	/** 鞋子装备对象 */
	private Equipment equipmentShoeObject;
	/** 武器装备对象 */
	private Equipment equipmentWeaponObject;
	/** 技能列表 */
	private List<HeroSkill> skillList;
	/** 指挥官扩展信息 */
	private CityHeroExt cityHeroExt;
	
	
	public Integer getCityHeroID() {
		return cityHeroID;
	}

	public void setCityHeroID(Integer cityHeroID) {
		this.cityHeroID = cityHeroID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public Long getExpMax() {
		//计算经验最大值
		expMax = (long)45 + level*(level-1)*10;
		return expMax;
	}

	public void setExpMax(Long expMax) {
		this.expMax = expMax;
	}
	
	public Integer getStamina() {
		return stamina;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}
	
	public Integer getStaminaMax() {
		return staminaMax;
	}

	public void setStaminaMax(Integer staminaMax) {
		this.staminaMax = staminaMax;
	}

	public Integer getCommand() {
		return command;
	}

	public void setCommand(Integer command) {
		this.command = command;
	}
	
	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	
	public Integer getMind() {
		return mind;
	}

	public void setMind(Integer mind) {
		this.mind = mind;
	}
	
	public Integer getExecutivepower() {
		return executivepower;
	}

	public void setExecutivepower(Integer executivepower) {
		this.executivepower = executivepower;
	}
	
	public Integer getUnsetPoint() {
		return unsetPoint;
	}
	
	public Integer getMilitarySpirit() {
		return militarySpirit;
	}

	public void setMilitarySpirit(Integer militarySpirit) {
		this.militarySpirit = militarySpirit;
	}
	
	public Integer getMilitarySoul() {
		return militarySoul;
	}

	public void setMilitarySoul(Integer militarySoul) {
		this.militarySoul = militarySoul;
	}
	
	public Integer getAddedMilitarySpirit() {
		return addedMilitarySpirit;
	}

	public void setAddedMilitarySpirit(Integer addedMilitarySpirit) {
		this.addedMilitarySpirit = addedMilitarySpirit;
	}

	public void setUnsetPoint(Integer unsetPoint) {
		this.unsetPoint = unsetPoint;
	}
	
	public Integer getRein() {
		return rein;
	}

	public void setRein(Integer rein) {
		this.rein = rein;
	}
	
	public Integer getLeadership() {
		return leadership;
	}

	public void setLeadership(Integer leadership) {
		this.leadership = leadership;
	}
	
	public Integer getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Integer loyalty) {
		this.loyalty = loyalty;
	}
	
	public Integer getMaxSkillNum() {
		return maxSkillNum;
	}

	public void setMaxSkillNum(Integer maxSkillNum) {
		this.maxSkillNum = maxSkillNum;
	}

	public Integer getEquipmentEpaulet() {
		return equipmentEpaulet;
	}

	public void setEquipmentEpaulet(Integer equipmentEpaulet) {
		this.equipmentEpaulet = equipmentEpaulet;
	}
	
	public Integer getEquipmentCap() {
		return equipmentCap;
	}

	public void setEquipmentCap(Integer equipmentCap) {
		this.equipmentCap = equipmentCap;
	}
	
	public Integer getEquipmentClothes() {
		return equipmentClothes;
	}

	public void setEquipmentClothes(Integer equipmentClothes) {
		this.equipmentClothes = equipmentClothes;
	}
	
	public Integer getEquipmentShoe() {
		return equipmentShoe;
	}

	public void setEquipmentShoe(Integer equipmentShoe) {
		this.equipmentShoe = equipmentShoe;
	}
	
	public Integer getEquipmentWeapon() {
		return equipmentWeapon;
	}

	public void setEquipmentWeapon(Integer equipmentWeapon) {
		this.equipmentWeapon = equipmentWeapon;
	}
	
	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Equipment getEquipmentEpauletObject() {
		return equipmentEpauletObject;
	}

	public void setEquipmentEpauletObject(Equipment equipmentEpauletObject) {
		this.equipmentEpauletObject = equipmentEpauletObject;
	}

	public Equipment getEquipmentCapObject() {
		return equipmentCapObject;
	}

	public void setEquipmentCapObject(Equipment equipmentCapObject) {
		this.equipmentCapObject = equipmentCapObject;
	}

	public Equipment getEquipmentClothesObject() {
		return equipmentClothesObject;
	}

	public void setEquipmentClothesObject(Equipment equipmentClothesObject) {
		this.equipmentClothesObject = equipmentClothesObject;
	}

	public Equipment getEquipmentShoeObject() {
		return equipmentShoeObject;
	}

	public void setEquipmentShoeObject(Equipment equipmentShoeObject) {
		this.equipmentShoeObject = equipmentShoeObject;
	}

	public Equipment getEquipmentWeaponObject() {
		return equipmentWeaponObject;
	}

	public void setEquipmentWeaponObject(Equipment equipmentWeaponObject) {
		this.equipmentWeaponObject = equipmentWeaponObject;
	}

	public List<HeroSkill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<HeroSkill> skillList) {
		this.skillList = skillList;
	}

	public CityHeroExt getCityHeroExt() {
		return cityHeroExt;
	}

	public void setCityHeroExt(CityHeroExt cityHeroExt) {
		this.cityHeroExt = cityHeroExt;
	}

}