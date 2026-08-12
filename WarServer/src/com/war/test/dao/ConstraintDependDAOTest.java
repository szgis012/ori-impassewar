package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IConstraintDependDAO;
import com.war.domain.ConstraintDepend;

public class ConstraintDependDAOTest {

	private static IConstraintDependDAO constraintDependDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		constraintDependDAO = (IConstraintDependDAO)SpringService.getApplicationContext().getBean("constraintDependDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCURD() {

		Integer level = 1;
		Integer targetID = 1;
		Integer type = 1;
		String description = "测试字符串";
		String preBuildings = "测试字符串";
		String preTeches = "测试字符串";
		Long costPopulation = 1L;
		Long costWood = 1L;
		Long costSteel = 1L;
		Long costOil = 1L;
		Long costFood = 1L;
		Long costMoney = 1L;
		Long costTime = 1L;

		ConstraintDepend constraintDepend = new ConstraintDepend();
		
		constraintDepend.setLevel(level);
		constraintDepend.setTargetID(targetID);
		constraintDepend.setType(type);
		constraintDepend.setDescription(description);
		constraintDepend.setPreBuildings(preBuildings);
		constraintDepend.setCostPopulation(costPopulation);
		constraintDepend.setCostWood(costWood);
		constraintDepend.setCostSteel(costSteel);
		constraintDepend.setCostOil(costOil);
		constraintDepend.setCostFood(costFood);
		constraintDepend.setCostMoney(costMoney);
		constraintDepend.setCostTime(costTime);

		//测试创建
		Integer constraintDependID = constraintDependDAO.createConstraintDepend(constraintDepend);
		assertNotNull(constraintDependID);

		//测试通过编号获得对象
		ConstraintDepend destConstraintDepend = constraintDependDAO.getConstraintDependByID(constraintDependID);
		assertNotNull(destConstraintDepend);
		assertEquals(constraintDependID,destConstraintDepend.getConstraintDependID());
		assertEquals(level,destConstraintDepend.getLevel());
		assertEquals(targetID,destConstraintDepend.getTargetID());
		assertEquals(type,destConstraintDepend.getType());
		assertEquals(description,destConstraintDepend.getDescription());
		assertEquals(preBuildings,destConstraintDepend.getPreBuildings());
		assertEquals(costPopulation,destConstraintDepend.getCostPopulation());
		assertEquals(costWood,destConstraintDepend.getCostWood());
		assertEquals(costSteel,destConstraintDepend.getCostSteel());
		assertEquals(costOil,destConstraintDepend.getCostOil());
		assertEquals(costFood,destConstraintDepend.getCostFood());
		assertEquals(costMoney,destConstraintDepend.getCostMoney());
		assertEquals(costTime,destConstraintDepend.getCostTime());
		
		//测试获得列表
		List<ConstraintDepend> constraintDependList = constraintDependDAO.getConstraintDependList();
		assertFalse(constraintDependList.isEmpty());

		//测试更新
		level = 10;
		targetID = 10;
		type = 10;
		description = "字符串修改";
		preBuildings = "字符串修改";
		preTeches = "字符串修改";
		costPopulation = 10L;
		costWood = 10L;
		costSteel = 10L;
		costOil = 10L;
		costFood = 10L;
		costMoney = 10L;
		costTime = 10L;
		destConstraintDepend.setConstraintDependID(constraintDependID);
		destConstraintDepend.setLevel(level);
		destConstraintDepend.setTargetID(targetID);
		destConstraintDepend.setType(type);
		destConstraintDepend.setDescription(description);
		destConstraintDepend.setPreBuildings(preBuildings);
		destConstraintDepend.setCostPopulation(costPopulation);
		destConstraintDepend.setCostWood(costWood);
		destConstraintDepend.setCostSteel(costSteel);
		destConstraintDepend.setCostOil(costOil);
		destConstraintDepend.setCostFood(costFood);
		destConstraintDepend.setCostMoney(costMoney);
		destConstraintDepend.setCostTime(costTime);
		constraintDependDAO.updateConstraintDepend(destConstraintDepend);
		ConstraintDepend updatedConstraintDepend = constraintDependDAO.getConstraintDependByID(constraintDependID);
		assertNotNull(updatedConstraintDepend);
		assertEquals(constraintDependID,updatedConstraintDepend.getConstraintDependID());
		assertEquals(level,updatedConstraintDepend.getLevel());
		assertEquals(targetID,updatedConstraintDepend.getTargetID());
		assertEquals(type,updatedConstraintDepend.getType());
		assertEquals(description,updatedConstraintDepend.getDescription());
		assertEquals(preBuildings,updatedConstraintDepend.getPreBuildings());
		assertEquals(costPopulation,updatedConstraintDepend.getCostPopulation());
		assertEquals(costWood,updatedConstraintDepend.getCostWood());
		assertEquals(costSteel,updatedConstraintDepend.getCostSteel());
		assertEquals(costOil,updatedConstraintDepend.getCostOil());
		assertEquals(costFood,updatedConstraintDepend.getCostFood());
		assertEquals(costMoney,updatedConstraintDepend.getCostMoney());
		assertEquals(costTime,updatedConstraintDepend.getCostTime());

		//测试删除
		constraintDependDAO.deleteConstraintDependByID(constraintDependID);
		assertNull(constraintDependDAO.getConstraintDependByID(constraintDependID));

	}

}