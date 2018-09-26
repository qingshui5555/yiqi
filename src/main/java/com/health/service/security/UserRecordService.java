package com.health.service.security;

import com.health.bo.security.UserRecordQueryBo;
import com.health.common.Constant;
import com.health.dao.security.UserDao;
import com.health.dao.security.UserRecordDao;
import com.health.dto.security.UserRecordDto;
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
public class UserRecordService extends BaseService {

    @Autowired
    private UserRecordDao userRecordDao;
    
    @Autowired
    private UserDao userDao;
    
    /**
     * 创建或保存用户记录
     * @param httpHeaders
     * @param userRecordDto
     * @return
     */
    public int create(HttpHeaders httpHeaders, UserRecordDto userRecordDto) {
        int result = -1;
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        Long userId = userRecordDto.getUserId();
        String mobile = userRecordDto.getMobile();
        if(userId == null && StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "userId");
        }
        UserRecordQueryBo queryBo = new UserRecordQueryBo();
        queryBo.setUserId(userRecordDto.getUserId());
        queryBo.setMobile(userRecordDto.getMobile());
        UserRecordEntity userRecordEntity = userRecordDao.queryUserRecord(queryBo);
        if(userRecordEntity == null){
            userRecordEntity = new UserRecordEntity();
            BeanUtils.copyProperties(userRecordDto, userRecordEntity);
            userRecordEntity.setStatus(Constant.VALID);
            userRecordEntity.setCreateBy(createBy);
            userRecordEntity.setCreateOn(createOn);
            userRecordEntity.setVersion(Constant.DEFAULT_VERSION);
            if(userId == null){
                UserEntity userEntity = userDao.queryUserByMobile(Constant.TYPE_USER_APP, mobile);
                if(userEntity == null){
                    throw new RequestConflictedException("E0000004", "account");
                }
                userRecordEntity.setUserId(userEntity.getUserId());
            }
            if(StringUtils.isBlank(mobile)){
                UserEntity userEntity = userDao.queryUserEntity(userId);
                if(userEntity == null){
                    throw new RequestConflictedException("E0000004", "account");
                }
                userRecordEntity.setMobile(userEntity.getMobile());
            }
            
            result += userRecordDao.createUserRecordEntity(userRecordEntity);
        } else {
            userRecordEntity.setRemark(userRecordDto.getRemark());
            userRecordEntity.setModifyBy(createBy);
            userRecordEntity.setModifyOn(createOn);
            result += userRecordDao.updateUserRecordEntity(userRecordEntity);
        }
        return result;
    }
    
    /**
     * 查询出诊时间段列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public UserRecordTransfer query(HttpHeaders httpHeaders, UserRecordQueryBo queryBo){
        UserRecordEntity entity = userRecordDao.queryUserRecord(queryBo);
        UserRecordTransfer transfer = new UserRecordTransfer();
        if(entity != null) {
            BeanUtils.copyProperties(entity, transfer);
        }
        return transfer;
    }
}
