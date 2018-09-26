package com.health.dao.security;

import com.health.bo.security.UserQueryBo;
import com.health.entity.security.UserEntity;
import com.health.transfer.security.UserTransfer;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author henry
 */
public interface UserDao {
    /**
     * 条件查询用户列表
     * @param queryBo
     * @return
     */
    List<UserTransfer> queryListUserTransfer(@Param("queryBo") UserQueryBo queryBo);
    /**
     * 查询有排班的医生
     * @param queryBo
     * @return
     */
    List<UserTransfer> queryListScheduleUserTransfer(@Param("queryBo") UserQueryBo queryBo);
    /**
     * 获取用户
     * @param userId
     * @return
     */
    UserEntity queryUserEntity(@Param("userId") Long userId);
    /**
     * 根据电话号码查询用户信息
     * @param type
     * @param mobile
     * @return
     */
    UserEntity queryUserByMobile(@Param("type") Integer type, @Param("mobile") String mobile);
    
    /**
     * 根据电话号码查询用户信息
     * @param type
     * @param account
     * @return
     */
    UserEntity queryUserByAccount(@Param("type") Integer type, @Param("account") String account);
    
    /**
     * 根据短信验证码查询用户信息
     * @param type
     * @param mobile
     * @param vCode
     * @return
     */
    UserEntity queryUserByCode(@Param("type") Integer type, @Param("mobile") String mobile, @Param("vCode") String vCode);
    
    /**
     * 创建用户
     * @param userEntity
     * @return
     */
    Long createUserEntity(UserEntity userEntity);
    
    /**
     * 修改用户
     * @param userEntity
     * @return
     */
    Long updateUserEntity(UserEntity userEntity);
    
    /**
     * 删除用户
     * @param userId
     * @return
     */
	int deleteUserEntity(@Param("userId") Long userId);
}
