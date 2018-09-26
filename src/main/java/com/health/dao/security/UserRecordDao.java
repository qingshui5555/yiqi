package com.health.dao.security;

import com.health.bo.security.UserRecordQueryBo;
import com.health.entity.security.UserRecordEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author henry
 */
public interface UserRecordDao {
    /**
     * 创建用户记录
     * @param userRecordEntity
     * @return
     */
    Long createUserRecordEntity(UserRecordEntity userRecordEntity);
    
    /**
     * 修改用户记录
     * @param userRecordEntity
     * @return
     */
    Long updateUserRecordEntity(UserRecordEntity userRecordEntity);
    
    /**
     * 条件查询用户记录
     * @param queryBo
     * @return
     */
    UserRecordEntity queryUserRecord(@Param("queryBo") UserRecordQueryBo queryBo);
    
}
