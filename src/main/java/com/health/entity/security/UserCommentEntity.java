package com.health.entity.security;

import com.health.entity.BaseEntity;

/**
 * @author henry
 */
public class UserCommentEntity extends BaseEntity {
    /**
     * 主键ID
     */
    private Long commentId;
    /**
     * APP用户ID
     */
    private Long appUserId;
    /**
     * 医生ID
     */
    private Long doctorUserId;
    /**
     * 评价等级
     */
    private String comment;
    
    public Long getCommentId() {
        return commentId;
    }
    
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    
}
