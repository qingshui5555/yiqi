package com.health.dao.security;

import com.health.entity.security.RandomEntity;
import com.health.entity.security.UserLoginEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author henry
 */
public interface UserLoginDao {
    /**
	  * insert操作
	  * @param userLoginEntity
     * @return
     */
    Long insertUserLoginEntity(UserLoginEntity userLoginEntity);
	
	/**
	 * 判定用户是否在线
	 * @param userId
	 * @return
	 */
    Integer countUserLoginEntity(@Param("userId") Long userId);
	
	/**
	 * 用户退出
	 * @param userId
	 * @return
	 */
	int userLogout(@Param("userId") Long userId);
}
