package com.health.entity.security;

import com.health.entity.BaseEntity;

import java.util.Date;

/**
 * @author Henry
 * @date 2018/7/9 0:38
 */
public class UserLoginEntity extends BaseEntity {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String account;
    /**
     *  手机号
     */
    private String mobile;
    /**
     *
     */
    private String token;
    /**
     * 时间戳
     */
    private Date serial;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public Date getSerial() {
        return serial;
    }
    
    public void setSerial(Date serial) {
        this.serial = serial;
    }
}
