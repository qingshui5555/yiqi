package com.health.dao.security;

import com.health.entity.security.UserProfileEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author henry
 */
public interface UserProfileDao {
    /**
	  * 获取用户
     * @param userId
	  * @return
	  */
    UserProfileEntity queryUserProfileEntity(@Param("userId") Long userId);
	
	/**
	 * 创建个人详细信息
	 * @param userProfileEntity
	 * @return
	 */
	int createUserProfileEntity(UserProfileEntity userProfileEntity);
	
	/**
	 * 修改个人详细信息
	 * @param userProfileEntity
	 * @return
	 */
	int updateUserProfileEntity(UserProfileEntity userProfileEntity);
}
