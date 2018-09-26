package com.health.dto.security;

import java.util.Date;

/**
 * @author henry
 */
public class UserRecordDto {
    /**
     * 主键ID
     */
    private Long recordId;
    /**
     * APP用户ID
     */
    private Long userId;
    /**
     * APP用户手机号
     */
    private String mobile;
    /**
     * 排班开始日期
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
