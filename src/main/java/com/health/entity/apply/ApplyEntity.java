package com.health.entity.apply;

import com.health.entity.BaseEntity;

import java.util.Date;

/**
 * @author henry
 */
public class ApplyEntity extends BaseEntity {
    /**
     * 主键ID
     */
    private Long applyId;
    /**
     * APP用户ID
     */
    private Long appUserId;
    /**
     * 医生ID
     */
    private Long doctorUserId;
    /**
     * APP用户地址
     */
    private String userAddr;
    /**
     * APP用户联系方式
     */
    private String userMobile;
    /**
     * 排班ID
     */
    private Long scheduleId;
    /**
     * 预约日期
     */
    private Date scheduleDate;
    /**
     * 审核状态 0 待审核 1 已批准 2 已拒绝
     */
    private Integer auditStatus;
    /**
     * 拒绝理由
     */
    private String rejectReason;
    
    public Long getApplyId() {
        return applyId;
    }
    
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    
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
    
    public Date getScheduleDate() {
        return scheduleDate;
    }
    
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
