package com.health.bo.security;

import com.health.bo.BaseQueryBo;

/**
 * @author henry
 */
public class UserRecordQueryBo extends BaseQueryBo {
    /**
     * APP用户ID
     */
    private Long userId;
    /**
     * 用户手机号
     */
    private String mobile;
    public UserRecordQueryBo(){
        super();
    }
    
    public UserRecordQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
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
}
