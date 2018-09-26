package com.health.service.apply;

import com.health.bo.apply.ApplyQueryBo;
import com.health.common.Constant;
import com.health.dao.apply.ApplyDao;
import com.health.dao.schedule.ScheduleDao;
import com.health.dao.security.UserDao;
import com.health.dao.security.UserProfileDao;
import com.health.dto.apply.ApplyDto;
import com.health.entity.apply.ApplyEntity;
import com.health.entity.schedule.ScheduleEntity;
import com.health.entity.security.UserEntity;
import com.health.entity.security.UserProfileEntity;
import com.health.exception.RequestConflictedException;
import com.health.service.BaseService;
import com.health.service.sms.SmsService;
import com.health.transfer.apply.ApplyTransfer;
import com.health.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author henry
 */
public class ApplyService extends BaseService {

    @Autowired
    private ApplyDao applyDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserProfileDao userProfileDao;
    
    @Autowired
    private ScheduleDao scheduleDao;
    
    @Autowired
    private SmsService smsService;
    /**
     * 条件查询医院列表
     * @param httpHeaders
     * @param applyDto
     * @return
     */
    public int create(HttpHeaders httpHeaders, ApplyDto applyDto) {
        int result = -1;
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        Long appUserId = applyDto.getAppUserId();
        if(appUserId == null){
            throw new RequestConflictedException("E0000001", "appUserId");
        }
        Long doctorUserId = applyDto.getDoctorUserId();
        if(doctorUserId == null){
            throw new RequestConflictedException("E0000001", "doctorUserId");
        }
        Long scheduleId = applyDto.getScheduleId();
        if(scheduleId == null){
            throw new RequestConflictedException("E0000001", "scheduleId");
        }
        String userAddr = applyDto.getUserAddr();
        if(StringUtils.isBlank(userAddr )){
            throw new RequestConflictedException("E0000001", "userAddr");
        }
        String userMobile = applyDto.getUserMobile();
        if(StringUtils.isBlank(userMobile)){
            throw new RequestConflictedException("E0000001", "userMobile");
        }
        UserEntity user = userDao.queryUserEntity(appUserId);
        if(user == null || user.getMobile() == null){
            throw new RequestConflictedException("E0000004", "appUserId");
        }
        UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(appUserId);
        if(userProfile == null){
            throw new RequestConflictedException("E0000004", "userProfile");
        }
        if(StringUtils.isBlank(userProfile.getAddress()) || !userAddr.equals(userProfile.getAddress())){
            userProfile.setAddress(userAddr);
            userProfile.setModifyBy(createBy);
            userProfile.setModifyOn(createOn);
            result += userProfileDao.updateUserProfileEntity(userProfile);
        }
        ScheduleEntity schedule = scheduleDao.selectScheduleEntity(scheduleId);
        if(schedule == null || schedule.getScheduleDate() == null){
            throw new RequestConflictedException("E0000004", "scheduleStartDate");
        }
        
        ApplyEntity apply = new ApplyEntity();
        apply.setAppUserId(appUserId);
        apply.setDoctorUserId(doctorUserId);
        apply.setScheduleId(scheduleId);
        apply.setScheduleDate(schedule.getScheduleDate());
        apply.setUserAddr(userAddr);
        apply.setUserMobile(userMobile);
        apply.setCreateBy(createBy);
        apply.setCreateOn(createOn);
        apply.setStatus(Constant.VALID);
        apply.setVersion(Constant.DEFAULT_VERSION);
        apply.setAuditStatus(Constant.AUDIT_STATUS_UNKNOWN);
        result += applyDao.createApplyEntity(apply);
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] params = new String[]{format.format(schedule.getScheduleDate())};
        smsService.sendWithParam(user.getMobile(), Constant.TEMPLATE_ID_APPLY, params);
        return result;
    }
    
    
    /**
     * 条件查询出诊申请单列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<ApplyTransfer> query(HttpHeaders httpHeaders, ApplyQueryBo queryBo ){
        Long userId = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        queryBo.setUserId(userId);
        
        List<ApplyEntity> applyEntityList = null;
        Boolean expireFlag = false;
        if(queryBo.getAuditStatus() != null && queryBo.getAuditStatus().equals(Constant.AUDIT_STATUS_EXPIRE)){
            applyEntityList = applyDao.queryListApplyExpired(queryBo);
            expireFlag = true;
        } else {
            applyEntityList = applyDao.queryListApply(queryBo);
        }
        
        List<ApplyTransfer> applyTransferList = new ArrayList<>();
        for(ApplyEntity entity : applyEntityList){
            ApplyTransfer transfer = new ApplyTransfer();
            BeanUtils.copyProperties(entity,transfer);
            Long appUserId = transfer.getAppUserId();
            if(appUserId != null){
                UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(appUserId);
                if(userProfile != null){
                    transfer.setAppUserName(userProfile.getName());
                }
            }
            
            Long scheduleId = transfer.getScheduleId();
            if(scheduleId != null){
                ScheduleEntity schedule = scheduleDao.selectScheduleEntity(scheduleId);
                if(schedule != null){
                    transfer.setScheduleDate(schedule.getScheduleDate());
                    transfer.setScheduleStart(schedule.getScheduleStart());
                    transfer.setScheduleEnd(schedule.getScheduleEnd());
                }
            }
            if(expireFlag){
                transfer.setAuditStatus(Constant.AUDIT_STATUS_EXPIRE);
            }
            applyTransferList.add(transfer);
        }
        return applyTransferList;
    }
    
    /**
     * 批量拒绝申请
     * @param httpHeaders
     * @param applyDto
     * @return
     */
    public int reject(HttpHeaders httpHeaders, ApplyDto applyDto){
        int result = -1;
        Long modifyBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        Date modifyOn = new Date();
        if(applyDto == null || applyDto.getApplyIdList() == null || applyDto.getApplyIdList().size() == 0){
            throw new RequestConflictedException("E0000001", "applyIdList");
        }
        
        for(Long applyId : applyDto.getApplyIdList()){
            ApplyEntity applyEntity = applyDao.selectApplyEntity(applyId);
            if(applyEntity == null || applyEntity.getAuditStatus().equals(Constant.AUDIT_STATUS_REJECT)){
                continue;
            }
            applyEntity.setAuditStatus(Constant.AUDIT_STATUS_REJECT);
            applyEntity.setRejectReason(applyDto.getRejectReason());
            applyEntity.setModifyBy(modifyBy);
            applyEntity.setModifyOn(modifyOn);
            result += applyDao.updateApplyEntity(applyEntity);
            
            ScheduleEntity schedule = scheduleDao.selectScheduleEntity(applyEntity.getScheduleId());
            if(schedule == null){
                throw new RequestConflictedException("E0000004", "scheduleId");
            }
            UserEntity user = userDao.queryUserEntity(applyEntity.getAppUserId());
            if(user == null || user.getMobile() == null){
                throw new RequestConflictedException("E0000004", "appUserId");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String[] params = new String[]{format.format(schedule.getScheduleDate()), applyDto.getRejectReason()};
            smsService.sendWithParam(user.getMobile(), Constant.TEMPLATE_ID_APPLY_REJECT, params);
        }
        
        return result;
    }
    
    /**
     * 批量审核申请
     * @param httpHeaders
     * @param applyDto
     * @return
     */
    public int audit(HttpHeaders httpHeaders, ApplyDto applyDto){
        int result = -1;
        Long modifyBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        Date modifyOn = new Date();
        if(applyDto == null || applyDto.getApplyIdList() == null || applyDto.getApplyIdList().size() == 0){
            throw new RequestConflictedException("E0000001", "applyIdList");
        }
    
        for(Long apply : applyDto.getApplyIdList()){
            ApplyEntity applyEntity = applyDao.selectApplyEntity(apply);
            if(applyEntity == null || !applyEntity.getAuditStatus().equals(Constant.AUDIT_STATUS_UNKNOWN)){
                continue;
            }
            int count = applyDao.countApplyEntity(applyEntity.getDoctorUserId(), applyEntity.getScheduleId());
            ScheduleEntity schedule = scheduleDao.selectScheduleEntity(applyEntity.getScheduleId());
            if(schedule == null){
                throw new RequestConflictedException("E0000004", "scheduleId");
            }
            if(count == schedule.getMax()){
                throw new RequestConflictedException("E2000003");
            }
            applyEntity.setAuditStatus(Constant.AUDIT_STATUS_ACCESS);
            applyEntity.setModifyBy(modifyBy);
            applyEntity.setModifyOn(modifyOn);
            result += applyDao.updateApplyEntity(applyEntity);
            UserEntity user = userDao.queryUserEntity(applyEntity.getAppUserId());
            if(user == null || user.getMobile() == null){
                throw new RequestConflictedException("E0000004", "appUserId");
            }
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String[] params = new String[]{format.format(schedule.getScheduleDate())};
            smsService.sendWithParam(user.getMobile(), Constant.TEMPLATE_ID_APPLY_ACCESS, params);
        }
        
        return result;
    }
}
