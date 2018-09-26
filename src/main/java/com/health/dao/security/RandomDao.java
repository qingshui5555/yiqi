package com.health.dao.security;

import com.health.entity.security.*;

/**
 * @author henry
 */
public interface RandomDao {
    /**
	  * 获取一个有效的不重复账号
     * @return
     */
    RandomEntity openAccount();
    
    /**
	  * 更新账号
	  * @param randomAccount
     * @return
     */
	 int closeAccount(RandomEntity randomAccount);
	
	/**
	 * 创建随机账号
	 * @param randomEntity
	 * @return
	 */
	int createRandomEntity(RandomEntity randomEntity);
}
