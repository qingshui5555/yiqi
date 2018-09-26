package com.health.service;

import com.health.dao.security.UserDao;
import com.health.entity.security.UserEntity;
import com.health.exception.RequestConflictedException;
import com.health.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;

/**
 * @author henry
 */
public class BaseService {
    public static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    UserDao userDao;

    /**
     * 获取当前登录用户的用户信息
     *
     * @param headers
     * @return
     */
    public UserEntity obtainCurrentUser(final HttpHeaders headers) {
        final String token = TokenUtils.getAuthToken(headers);
        if (token == null) {
            throw new RequestConflictedException("E000001", "token");
        }
        return obtainCurrentUser(TokenUtils.getUserIdFromToken(token));
    }

    /**
     * 获取当前登录用户的用户信息
     * @param userId
     * @return
     */
    public UserEntity obtainCurrentUser(final Long userId) {
        return userDao.queryUserEntity(userId);
    }
}
