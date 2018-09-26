package com.health.dto.security;

/**
 * @author henry
 */
public class UserCommentDto {
    /**
     * APP用户ID
     */
    private Long appUserId;
    /**
     * 医生ID
     */
    private Long doctorUserId;
    /**
     * 评论等级
     */
    private String comment;
    
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
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}
