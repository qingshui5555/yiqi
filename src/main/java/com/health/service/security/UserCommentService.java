package com.health.service.security;

import com.health.bo.security.UserRecordQueryBo;
import com.health.common.Constant;
import com.health.dao.security.UserCommentDao;
import com.health.dao.security.UserDao;
import com.health.dao.security.UserRecordDao;
import com.health.dto.security.UserCommentDto;
import com.health.dto.security.UserRecordDto;
import com.health.entity.security.UserCommentEntity;
import com.health.entity.security.UserEntity;
import com.health.entity.security.UserRecordEntity;
import com.health.exception.RequestConflictedException;
import com.health.service.BaseService;
import com.health.transfer.security.UserRecordTransfer;
import com.health.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.util.Date;

/**
 * @author henry
 */
public class UserCommentService extends BaseService {

    @Autowired
    private UserCommentDao userCommentDao;
    
    /**
     * 创建用户评价记录
     * @param httpHeaders
     * @param userCommentDto
     * @return
     */
    public int create(HttpHeaders httpHeaders, UserCommentDto userCommentDto) {
        int result = -1;
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        Long appUserId = userCommentDto.getAppUserId();
        if(appUserId == null){
            throw new RequestConflictedException("E0000001", "userId");
        }
        Long doctorUserId = userCommentDto.getDoctorUserId();
        if(doctorUserId == null){
            throw new RequestConflictedException("E0000001", "userId");
        }
        UserCommentEntity userCommentEntity = new UserCommentEntity();
        BeanUtils.copyProperties(userCommentDto, userCommentEntity);
        userCommentEntity.setStatus(Constant.VALID);
        userCommentEntity.setCreateBy(createBy);
        userCommentEntity.setCreateOn(createOn);
        userCommentEntity.setVersion(Constant.DEFAULT_VERSION);
        
        result += userCommentDao.createUserCommentEntity(userCommentEntity);
        
        return result;
    }
}
