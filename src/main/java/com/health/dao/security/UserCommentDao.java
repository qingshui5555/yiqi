package com.health.dao.security;

import com.health.entity.security.UserCommentEntity;

/**
 * @author henry
 */
public interface UserCommentDao {
    /**
     * 创建用户记录
     * @param userCommentEntity
     * @return
     */
    int createUserCommentEntity(UserCommentEntity userCommentEntity);
    
}
