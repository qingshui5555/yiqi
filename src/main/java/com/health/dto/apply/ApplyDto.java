package com.health.dto.apply;

import java.util.List;

/**
 * @author henry
 */
public class ApplyDto {
    /**
     * APP用户ID
     */
    private Long appUserId;
    /**
     * 医生ID
     */
    private Long doctorUserId;
    /**
     * 用户地址
     */
    private String userAddr;
    /**
     * 用户联系方式
     */
    private String userMobile;
    /**
     * 排班时间ID
     */
    private Long scheduleId;
    /**
     * 拒绝理由
     */
    private String rejectReason;
    /**
     * 出诊申请ID列表
     */
    private List<Long> applyIdList;
    
    public Long getAppUserId() {
        return appUserId;
    }
    
    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }
    
    public Long getDoctorUserId() {
        return doctorUserId;
    }
    
    public void setDoctorUserId(Long doctorUserId) {
        this.doctorUserId = doctorUserId;
    }
    
    public String getUserAddr() {
        return userAddr;
    }
    
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }
    
    public String getUserMobile() {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    
    public Long getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public List<Long> getApplyIdList() {
        return applyIdList;
    }
    
    public void setApplyIdList(List<Long> applyIdList) {
        this.applyIdList = applyIdList;
    }
    
}
