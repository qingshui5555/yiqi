package com.health.entity.security;

import com.health.entity.BaseEntity;

/**
 * @author henry
 */
public class UserRecordEntity extends BaseEntity {
    /**
     * 主键ID
     */
    private Long recordId;
    /**
     * APP用户ID
     */
    private Long userId;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 记录内容
     */
    private String remark;
    
    public Long getRecordId() {
        return recordId;
    }
    
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
