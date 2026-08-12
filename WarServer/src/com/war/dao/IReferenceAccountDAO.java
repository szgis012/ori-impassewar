package com.war.dao;


import com.war.domain.ReferenceAccount;

/**
 * 测试账号奖励
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IReferenceAccountDAO {

	/**
	 * 创建测试账号
	 * @param army
	 * @return
	 */
    public Integer createReferenceAccount(ReferenceAccount referenceAccount);

    /**
     * 更新
     * @param army
     */
    public void updateReferenceAccount(ReferenceAccount referenceAccount);

    /**
     * 根据编号删除参与测试的账号对象
     * @param armyID
     */
    public void deleteReferenceAccountByID(Integer id);

    /**
     * 根据编号获得参与测试的账号对象
     * @param armyID
     * @return
     */
    public ReferenceAccount getReferenceAccountByID(Integer id);
    
    /**
     * 根据账户名获得参与测试的账号对象
     * @param armyID
     * @return
     */
    public ReferenceAccount getReferenceAccountByAccountName(String accountName);
    
}