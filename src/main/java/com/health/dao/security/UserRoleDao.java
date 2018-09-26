package com.health.dao.security;

import com.health.entity.security.UserProfileEntity;
import com.health.entity.security.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author henry
 */
public interface UserRoleDao {
	/**
	 * 创建用户角色对应关系
	 * @param userRoleEntity
	 * @return
	 */
	int createUserRoleEntity(UserRoleEntity userRoleEntity);
	
	/**
	 * 删除用户角色对应关系
	 * @param userId
	 * @return
	 */
	int deleteByUserId(@Param("userId") Long userId);
}
